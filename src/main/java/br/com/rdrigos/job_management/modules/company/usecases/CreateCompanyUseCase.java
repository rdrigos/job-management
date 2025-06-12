package br.com.rdrigos.job_management.modules.company.usecases;

import br.com.rdrigos.job_management.exceptions.CompanyFoundException;
import br.com.rdrigos.job_management.modules.company.entities.Company;
import br.com.rdrigos.job_management.modules.company.repositories.CompanyRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateCompanyUseCase(CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Company execute(Company company) {
        this.companyRepository.findByUsernameOrEmail(
            company.getUsername(),
            company.getEmail()
        ).ifPresent(user -> {
            throw new CompanyFoundException();
        });

        String encodedPassword = this.passwordEncoder.encode(company.getPassword());
        company.setPassword(encodedPassword);

        return this.companyRepository.save(company);
    }
}
