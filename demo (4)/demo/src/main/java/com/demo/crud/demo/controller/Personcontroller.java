package com.demo.crud.demo.controller;

import com.demo.crud.demo.entity.Person;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/persons")
public class Personcontroller {
    private ArrayList<Person> persons=new ArrayList<>();

    @GetMapping("/")
    public List<Person> getAllPerson() {
        return persons;
    }

    @GetMapping("/{name}")
    public Person getPerson(@PathVariable String name) {
        return persons.stream().filter(per->per.getName().equals(name)).findFirst().orElse(null);
    }

    @PostMapping("/")
    public Person addPerson(@RequestBody Person person) {
        persons.add(person);
        return person;

    }

    @PutMapping("/{name}")
    public Person updatePerson(@PathVariable String name, @RequestBody Person person) {
    Person p = persons.stream().filter(per->per.getName().equals(name)).findFirst().orElse(null);
    if(p!=null){
        p.setName(person.getName());
        p.setAge(person.getAge());
    }
    return p;
    }

    @DeleteMapping("/{name}")
    public String deletePerson(@PathVariable String name) {
        persons.removeIf(p->p.getName().equals(name));
        return "Person with name" +name +"deleted successfully.";
    }
}
