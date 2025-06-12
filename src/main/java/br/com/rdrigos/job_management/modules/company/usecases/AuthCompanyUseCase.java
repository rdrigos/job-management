package br.com.rdrigos.job_management.modules.company.usecases;

import br.com.rdrigos.job_management.config.SecurityProps;
import br.com.rdrigos.job_management.modules.company.dtos.AuthCompanyDTO;
import br.com.rdrigos.job_management.modules.company.entities.Company;
import br.com.rdrigos.job_management.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class AuthCompanyUseCase {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityProps securityProps;

    public AuthCompanyUseCase(CompanyRepository companyRepository, PasswordEncoder passwordEncoder, SecurityProps securityProps) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
        this.securityProps = securityProps;
    }

    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        Company company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
            () -> new UsernameNotFoundException("Invalid username or password")
        );

        boolean isMatchingPassword = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!isMatchingPassword ) throw new AuthenticationException();

        Algorithm algorithm = Algorithm.HMAC256(this.securityProps.getSecret());

        return JWT.create()
            .withIssuer(this.securityProps.getIssuer())
            .withSubject(company.getId().toString())
            .sign(algorithm);
    }

}
