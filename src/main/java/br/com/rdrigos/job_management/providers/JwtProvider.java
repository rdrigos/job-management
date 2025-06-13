package br.com.rdrigos.job_management.providers;

import br.com.rdrigos.job_management.config.SecurityProps;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
public class JwtProvider {

    private final SecurityProps securityProps;

    public JwtProvider(SecurityProps securityProps) {
        this.securityProps = securityProps;
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(this.securityProps.getSecret());
    }

    public String createToken(UUID subject) {
        return this.createToken(subject, Duration.ofHours(3));
    }

    public String createToken(UUID subject, Duration duration) {
        return JWT.create()
            .withIssuer(this.securityProps.getIssuer())
            .withSubject(subject.toString())
            .withExpiresAt(Instant.now().plus(duration))
            .sign(this.getAlgorithm());
    }

    public String validateToken(String token) {
        token = token.replace("Bearer ", "");

        try {
            return JWT.require(this.getAlgorithm())
                .build()
                .verify(token)
                .getSubject();
        } catch (JWTVerificationException exception) {
            log.warn(exception.getMessage());
            return "";
        }
    }

}
