package br.com.wbs.simple_crud_person.domain.person;

import br.com.wbs.simple_crud_person.domain.job.Job;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PersonRequestDTO (
        @NotBlank
        String name,
        @NotNull
        Integer age,
        @NotNull
        @Email
        String email,
        @NotNull
        Long jobId

) {
    public PersonRequestDTO(Person person) {
        this(person.getName(), person.getAge(), person.getEmail(), person.getJob().getId());
    }
}
