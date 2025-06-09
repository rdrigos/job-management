package br.com.rdrigos.job_management.modules.company.usecases;

import br.com.rdrigos.job_management.modules.company.entities.Job;
import br.com.rdrigos.job_management.modules.company.repositories.JobRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    private final JobRepository jobRepository;

    public CreateJobUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job execute(Job job) {
        return this.jobRepository.save(job);
    }
}
