package br.com.rdrigos.job_management.modules.candidate.controllers;

import br.com.rdrigos.job_management.dtos.ResponseDTO;
import br.com.rdrigos.job_management.enums.ServiceStatus;
import br.com.rdrigos.job_management.modules.candidate.entities.Candidate;
import br.com.rdrigos.job_management.modules.candidate.usecases.CreateCandidateUseCase;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private final CreateCandidateUseCase createCandidateUseCase;

    public CandidateController(CreateCandidateUseCase createCandidateUseCase) {
        this.createCandidateUseCase = createCandidateUseCase;
    }

    @PostMapping("/")
    public ResponseDTO<Candidate> create(
        @Valid @RequestBody Candidate candidateEntity
    ) {
        Candidate candidate = this.createCandidateUseCase.execute(candidateEntity);

        ResponseDTO<Candidate> response = new ResponseDTO<>();
        response.setStatus(ServiceStatus.CREATED);
        response.setMessages(Collections.singletonList("Candidate created successfully"));
        response.setPayload(candidate);

        return response;
    }

}
