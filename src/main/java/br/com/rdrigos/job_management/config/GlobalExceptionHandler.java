package br.com.rdrigos.job_management.config;

import br.com.rdrigos.job_management.shared.dtos.ResponseDTO;
import br.com.rdrigos.job_management.shared.dtos.ValidationErrorDTO;
import br.com.rdrigos.job_management.shared.enums.ServiceStatus;
import br.com.rdrigos.job_management.exceptions.CompanyFoundException;
import br.com.rdrigos.job_management.exceptions.UserFoundException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResponseDTO<Void>> handleUsernameNotFoundException(UsernameNotFoundException exception) {
        ResponseDTO<Void> response = new ResponseDTO<>();

        response.setStatus(ServiceStatus.UNAUTHORIZED);
        response.setMessages(Collections.singletonList(exception.getMessage()));

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseDTO<Void>> handleAuthenticationException(AuthenticationException exception) {
        ResponseDTO<Void> response = new ResponseDTO<>();

        response.setStatus(ServiceStatus.UNAUTHORIZED);
        response.setMessages(Collections.singletonList("Invalid username or password"));

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<ResponseDTO<Void>> handleUserFoundException(UserFoundException exception) {
        ResponseDTO<Void> response = new ResponseDTO<>();

        response.setStatus(ServiceStatus.CONFLICT);
        response.setMessages(Collections.singletonList(exception.getMessage()));

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CompanyFoundException.class)
    public ResponseEntity<ResponseDTO<Void>> handleCompanyFoundException(CompanyFoundException exception) {
        ResponseDTO<Void> response = new ResponseDTO<>();

        response.setStatus(ServiceStatus.CONFLICT);
        response.setMessages(Collections.singletonList(exception.getMessage()));

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO<List<ValidationErrorDTO>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ResponseDTO<List<ValidationErrorDTO>> response = new ResponseDTO<>();
        List<ValidationErrorDTO> validationErrorList = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            ValidationErrorDTO validationError = new ValidationErrorDTO(message, error.getField());
            validationErrorList.add(validationError);
        });

        response.setStatus(ServiceStatus.VALIDATION_ERROR);
        response.setMessages(Collections.singletonList("One or more fields contain invalid values"));
        response.setPayload(validationErrorList);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
