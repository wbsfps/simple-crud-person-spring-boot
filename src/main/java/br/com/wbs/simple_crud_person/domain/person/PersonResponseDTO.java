package br.com.wbs.simple_crud_person.domain.person;

public record PersonResponseDTO (
        Long id,
        String name,
        Integer age,
        String email
) {
    public PersonResponseDTO(Person person) {
        this(person.getId(), person.getName(), person.getAge(), person.getEmail());
    }
}
