package br.com.rdrigos.job_management.modules.company.controllers;

import br.com.rdrigos.job_management.dtos.ResponseDTO;
import br.com.rdrigos.job_management.enums.ServiceStatus;
import br.com.rdrigos.job_management.modules.company.dtos.AuthCompanyDTO;
import br.com.rdrigos.job_management.modules.company.usecases.AuthCompanyUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

    private final AuthCompanyUseCase authCompanyUseCase;

    public AuthCompanyController(AuthCompanyUseCase authCompanyUseCase) {
        this.authCompanyUseCase = authCompanyUseCase;
    }

    @PostMapping("/company")
    public ResponseEntity<ResponseDTO<String>> authenticate(
        @RequestBody AuthCompanyDTO authCompanyDTO
    ) throws AuthenticationException {
        String token = this.authCompanyUseCase.execute(authCompanyDTO);

        ResponseDTO<String> response = new ResponseDTO<>();
        response.setStatus(ServiceStatus.SUCCESS);
        response.setMessages(Collections.emptyList());
        response.setPayload(token);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
