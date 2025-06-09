package br.com.rdrigos.job_management.modules.candidate.repositories;

import java.util.Optional;
import java.util.UUID;

import br.com.rdrigos.job_management.modules.candidate.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, UUID> {
    Optional<Candidate> findByUsernameOrEmail(String username, String email);
}
