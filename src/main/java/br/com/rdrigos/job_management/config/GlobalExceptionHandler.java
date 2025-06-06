package br.com.rdrigos.job_management.config;

import br.com.rdrigos.job_management.dtos.ResponseDTO;
import br.com.rdrigos.job_management.dtos.ValidationErrorDTO;
import br.com.rdrigos.job_management.enums.ServiceStatus;
import br.com.rdrigos.job_management.exceptions.UserFoundException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<ResponseDTO<Void>> handleUserFoundException(UserFoundException exception) {
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
