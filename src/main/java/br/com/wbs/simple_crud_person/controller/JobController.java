package br.com.wbs.simple_crud_person.controller;


import br.com.wbs.simple_crud_person.domain.job.Job;
import br.com.wbs.simple_crud_person.domain.job.JobRepository;
import br.com.wbs.simple_crud_person.domain.job.JobRequestDTO;
import br.com.wbs.simple_crud_person.domain.job.JobResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobRepository repository;

    @GetMapping
    public ResponseEntity<Page<JobResponseDTO>> jobs(@PageableDefault(sort = "jobName", size = 5) Pageable pageable) {
        var page = repository.findAll(pageable).map(JobResponseDTO::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<JobResponseDTO> create(@RequestBody JobRequestDTO jobDTO) {
        var job = new Job(jobDTO);
        repository.save(job);
        return ResponseEntity.status(HttpStatus.CREATED).body(new JobResponseDTO(job));
    }

}
