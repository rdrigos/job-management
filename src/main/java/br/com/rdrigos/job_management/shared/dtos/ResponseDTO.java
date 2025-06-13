package br.com.rdrigos.job_management.shared.dtos;

import br.com.rdrigos.job_management.shared.enums.ServiceStatus;
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
