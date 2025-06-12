package br.com.rdrigos.job_management.modules.company.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthCompanyDTO {
    private String username;
    private String password;
}
