package br.com.rdrigos.job_management.modules.candidate.controllers;

import br.com.rdrigos.job_management.dtos.ResponseDTO;
import br.com.rdrigos.job_management.enums.ServiceStatus;
import br.com.rdrigos.job_management.exceptions.UserFoundException;
import br.com.rdrigos.job_management.modules.candidate.CandidateEntity;
import br.com.rdrigos.job_management.modules.candidate.CandidateRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private final CandidateRepository candidateRepository;

    public CandidateController(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @PostMapping("/")
    public ResponseDTO<CandidateEntity> create(
        @Valid @RequestBody CandidateEntity candidateEntity
    ) {
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(),
            candidateEntity.getEmail()).ifPresent(user -> {
            throw new UserFoundException();
        });

        CandidateEntity candidate = this.candidateRepository.save(candidateEntity);

        ResponseDTO<CandidateEntity> response = new ResponseDTO<>();
        response.setStatus(ServiceStatus.CREATED);
        response.setMessages(Collections.singletonList("Candidate created successfully"));
        response.setPayload(candidate);

        return response;
    }

}
