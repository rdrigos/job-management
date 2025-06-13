package br.com.rdrigos.job_management.modules.company.usecases;

import br.com.rdrigos.job_management.modules.company.dtos.AuthCompanyDTO;
import br.com.rdrigos.job_management.modules.company.entities.Company;
import br.com.rdrigos.job_management.modules.company.repositories.CompanyRepository;
import br.com.rdrigos.job_management.providers.JwtProvider;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class AuthCompanyUseCase {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public AuthCompanyUseCase(
        CompanyRepository companyRepository,
        PasswordEncoder passwordEncoder,
        JwtProvider jwtProvider
    ) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        Company company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
            () -> new UsernameNotFoundException("Invalid username or password")
        );

        boolean isMatchingPassword = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!isMatchingPassword ) throw new AuthenticationException();

        return this.jwtProvider.createToken(company.getId());
    }

}
