package br.com.rdrigos.job_management.dtos;

import br.com.rdrigos.job_management.enums.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {
    private ServiceStatus status;
    private List<String> messages;
    private T payload;
}
