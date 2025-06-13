package br.com.rdrigos.job_management.modules.company.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobDTO {
    private String description;
    private String benefits;
    private String level;
}
