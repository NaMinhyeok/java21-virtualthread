package minhyeok.virtualthread.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;


    @Builder
    private Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static Person create(String name, int age) {
        return Person.builder()
            .name(name)
            .age(age)
            .build();
    }

}
