package br.com.wbs.simple_crud_person.domain.job;

public record JobResponseDTO(
        Long id,
        String job
) {
    public JobResponseDTO(Job job) {
        this(job.getId(), job.getJobName());
    }
}
