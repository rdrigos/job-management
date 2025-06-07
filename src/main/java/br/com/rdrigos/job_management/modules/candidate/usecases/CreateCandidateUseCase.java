package br.com.rdrigos.job_management.modules.candidate.usecases;

import br.com.rdrigos.job_management.exceptions.UserFoundException;
import br.com.rdrigos.job_management.modules.candidate.CandidateEntity;
import br.com.rdrigos.job_management.modules.candidate.CandidateRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    private final CandidateRepository candidateRepository;

    public CreateCandidateUseCase(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository.findByUsernameOrEmail(
            candidateEntity.getUsername(),
            candidateEntity.getEmail()
        ).ifPresent(user -> {
            throw new UserFoundException();
        });

        return this.candidateRepository.save(candidateEntity);
    }

}
