package br.com.rdrigos.job_management.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorDTO {
    private String field;
    private String message;
}
