package br.com.rdrigos.job_management.modules.candidate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
@ToString
public class CandidateEntity {
    private UUID id;
    private String name;

    @Pattern(regexp = "\\S+", message = "The field cannot be blank")
    private String username;

    @Email(message = "The field must contain a valid email address")
    private String email;

    @Length(min = 8, max = 100, message = "The field must be between 8 and 100 characters")
    private String password;
    private String description;
    private String curriculum;
}
