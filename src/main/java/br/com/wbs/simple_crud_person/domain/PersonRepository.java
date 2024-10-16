package br.com.wbs.simple_crud_person.domain;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Page<Person> findAllByActivateTrue(Pageable pageable);
}
