package br.com.rdrigos.job_management.modules.company.usecases;

import br.com.rdrigos.job_management.exceptions.CompanyFoundException;
import br.com.rdrigos.job_management.modules.company.entities.Company;
import br.com.rdrigos.job_management.modules.company.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    private final CompanyRepository companyRepository;

    public CreateCompanyUseCase(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company execute(Company company) {
        this.companyRepository.findByUsernameOrEmail(
            company.getUsername(),
            company.getEmail()
        ).ifPresent(user -> {
            throw new CompanyFoundException();
        });

        return this.companyRepository.save(company);
    }
}
