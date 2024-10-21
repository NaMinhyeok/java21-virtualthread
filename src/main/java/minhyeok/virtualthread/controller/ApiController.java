package minhyeok.virtualthread.controller;

import lombok.RequiredArgsConstructor;
import minhyeok.virtualthread.domain.Person;
import minhyeok.virtualthread.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final PersonService personService;

    @PostMapping("/person")
    public ResponseEntity<Person> createPerson(@RequestBody PersonRequest request) {
        return ResponseEntity.ok().body(personService.createPerson(request));
    }

    @GetMapping("/person")
    public ResponseEntity<List<Person>> getPerson() {
        return ResponseEntity.ok().body(personService.getPerson());
    }

    @GetMapping("/test")
    public String test() {
//        Thread.sleep(1000);
        return personService.querySleep();
    }

}
