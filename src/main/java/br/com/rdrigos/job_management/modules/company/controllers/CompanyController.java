package br.com.rdrigos.job_management.modules.company.controllers;

import br.com.rdrigos.job_management.dtos.ResponseDTO;
import br.com.rdrigos.job_management.enums.ServiceStatus;
import br.com.rdrigos.job_management.modules.company.entities.Company;
import br.com.rdrigos.job_management.modules.company.usecases.CreateCompanyUseCase;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CreateCompanyUseCase createCompanyUseCase;

    public CompanyController(CreateCompanyUseCase createCompanyUseCase) {
        this.createCompanyUseCase = createCompanyUseCase;
    }

    @PostMapping
    public ResponseDTO<Company> create(
        @Valid @RequestBody Company body
    ) {
        Company company = this.createCompanyUseCase.execute(body);

        ResponseDTO<Company> response = new ResponseDTO<>();
        response.setStatus(ServiceStatus.CREATED);
        response.setMessages(Collections.singletonList("Company created successfully"));
        response.setPayload(company);

        return response;
    }

}
