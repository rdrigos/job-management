package br.com.rdrigos.job_management.modules.company.repositories;

import br.com.rdrigos.job_management.modules.company.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {}
