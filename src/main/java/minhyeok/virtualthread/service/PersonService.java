package minhyeok.virtualthread.service;

import lombok.RequiredArgsConstructor;
import minhyeok.virtualthread.controller.PersonRequest;
import minhyeok.virtualthread.domain.Person;
import minhyeok.virtualthread.repository.PersonRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public Person createPerson(PersonRequest request) {
        return personRepository.save(Person.create(request.name(), request.age()));
    }

    public List<Person> getPerson() {
        return personRepository.findAll();
    }

    public String querySleep() {
        return jdbcTemplate.queryForList("select sleep(1)").toString();
    }

}
