package br.com.rdrigos.job_management.modules.candidate.usecases;

import br.com.rdrigos.job_management.exceptions.UserFoundException;
import br.com.rdrigos.job_management.modules.candidate.entities.Candidate;
import br.com.rdrigos.job_management.modules.candidate.repositories.CandidateRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    private final CandidateRepository candidateRepository;

    public CreateCandidateUseCase(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Candidate execute(Candidate candidate) {
        this.candidateRepository.findByUsernameOrEmail(
            candidate.getUsername(),
            candidate.getEmail()
        ).ifPresent(user -> {
            throw new UserFoundException();
        });

        return this.candidateRepository.save(candidate);
    }

}
