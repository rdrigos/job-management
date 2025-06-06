package br.com.rdrigos.job_management.modules.candidate.controllers;

import br.com.rdrigos.job_management.modules.candidate.CandidateEntity;
import br.com.rdrigos.job_management.modules.candidate.CandidateRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    private final CandidateRepository candidateRepository;

    public CandidateController(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @PostMapping
    public CandidateEntity create(
        @Valid @RequestBody CandidateEntity candidateEntity
    ) {
        return this.candidateRepository.save(candidateEntity);
    }

}
