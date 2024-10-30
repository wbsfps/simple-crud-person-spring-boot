package br.com.wbs.simple_crud_person.controller;

import br.com.wbs.simple_crud_person.domain.job.Job;
import br.com.wbs.simple_crud_person.domain.job.JobRepository;
import br.com.wbs.simple_crud_person.domain.person.*;
import br.com.wbs.simple_crud_person.infra.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonRepository repository;
    @Autowired
    private JobRepository jobRepository;

    @GetMapping
    public ResponseEntity<Page<PersonResponseDTO>> getAll(@PageableDefault(size = 5) Pageable pageable) {
        var page = repository.findAllByActivateTrue(pageable).map(PersonResponseDTO::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<PersonResponseDTO> save(@RequestBody PersonRequestDTO data) {
        Job job = jobRepository.findById(data.jobId())
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        var person = new Person(data, job);

        // Salvando a nova Person
        repository.save(person);


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new PersonResponseDTO(person));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponseDTO> showPerson(@PathVariable Long id) {
        var person = repository.getReferenceById(id);

        return ResponseEntity.ok(new PersonResponseDTO(person));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PersonResponseDTO> alterData(@RequestBody @Valid PersonAlterDataDTO data) {
        var person = repository.getReferenceById(data.id());
        person.alterDataPerson(data);

        return ResponseEntity.ok(new PersonResponseDTO(person));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var person = repository.getReferenceById(id);
        person.delete();
        return ResponseEntity.noContent().build();
    }
}
