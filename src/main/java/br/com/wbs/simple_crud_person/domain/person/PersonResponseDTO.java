package br.com.wbs.simple_crud_person.domain.person;

import br.com.wbs.simple_crud_person.domain.job.Job;

public record PersonResponseDTO (
        Long id,
        String name,
        Integer age,
        String email,
        Long jobId
) {
    public PersonResponseDTO(Person person) {
        this(person.getId(), person.getName(), person.getAge(), person.getEmail(), person.getJob().getId());
    }
}
