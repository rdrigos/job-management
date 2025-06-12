package br.com.rdrigos.job_management.modules.company.controllers;

import br.com.rdrigos.job_management.dtos.ResponseDTO;
import br.com.rdrigos.job_management.enums.ServiceStatus;
import br.com.rdrigos.job_management.modules.company.entities.Job;
import br.com.rdrigos.job_management.modules.company.usecases.CreateJobUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/job")
public class JobController {

    private final CreateJobUseCase createJobUseCase;

    public JobController(CreateJobUseCase createJobUseCase) {
        this.createJobUseCase = createJobUseCase;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<Job>> create(
        @Valid @RequestBody Job body
    ) {
        Job job = this.createJobUseCase.execute(body);

        ResponseDTO<Job> response = new ResponseDTO<>();
        response.setStatus(ServiceStatus.CREATED);
        response.setMessages(Collections.singletonList("Company created successfully"));
        response.setPayload(job);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
