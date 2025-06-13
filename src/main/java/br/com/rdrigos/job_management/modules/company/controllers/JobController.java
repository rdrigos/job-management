package br.com.rdrigos.job_management.modules.company.controllers;

import br.com.rdrigos.job_management.shared.dtos.ResponseDTO;
import br.com.rdrigos.job_management.shared.enums.ServiceStatus;
import br.com.rdrigos.job_management.modules.company.dtos.CreateJobDTO;
import br.com.rdrigos.job_management.modules.company.entities.Company;
import br.com.rdrigos.job_management.modules.company.entities.Job;
import br.com.rdrigos.job_management.modules.company.usecases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobController {

    private final CreateJobUseCase createJobUseCase;

    public JobController(CreateJobUseCase createJobUseCase) {
        this.createJobUseCase = createJobUseCase;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<Job>> create(
        @Valid @RequestBody CreateJobDTO body,
        HttpServletRequest request
    ) {
        Object companyId = request.getAttribute("companyId");

        Job jobEntity = Job.builder()
            .benefits(body.getBenefits())
            .company(new Company(UUID.fromString(companyId.toString())))
            .description(body.getDescription())
            .level(body.getLevel())
            .build();

        Job job = this.createJobUseCase.execute(jobEntity);

        ResponseDTO<Job> response = new ResponseDTO<>();
        response.setStatus(ServiceStatus.CREATED);
        response.setMessages(Collections.singletonList("Company created successfully"));
        response.setPayload(job);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
