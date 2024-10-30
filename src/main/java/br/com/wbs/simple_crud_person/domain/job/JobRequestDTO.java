package br.com.wbs.simple_crud_person.domain.job;

import jakarta.validation.constraints.NotBlank;

public record JobRequestDTO(
        @NotBlank
        String job
) {
    public JobRequestDTO(Job job) {
        this(job.getJobName());
    }
}
