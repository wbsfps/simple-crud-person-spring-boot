package br.com.wbs.simple_crud_person.domain.person;

import br.com.wbs.simple_crud_person.domain.job.Job;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "persons")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;
    @Column(name = "email")
    private String email;
    @Column(name = "activate")
    private Boolean activate;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id")
    private Job job;

    public Person(PersonRequestDTO person, Job job) {
        this.name = person.name();
        this.age = person.age();
        this.email = person.email();
        this.job = job;
        this.activate = true;
    }

    public void alterDataPerson(PersonAlterDataDTO data) {
        if (data.age() != null) {
            this.age = data.age();
        }
    }

    public void delete() {
        this.activate = false;
    }
}
