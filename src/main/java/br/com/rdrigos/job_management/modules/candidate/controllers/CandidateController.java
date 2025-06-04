package br.com.rdrigos.job_management.modules.candidate.controllers;

import br.com.rdrigos.job_management.modules.candidate.CandidateEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @PostMapping
    public void create(
        @Valid @RequestBody CandidateEntity candidateEntity
    ) {
        System.out.println(candidateEntity.toString());
    }

}
