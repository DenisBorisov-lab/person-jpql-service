package com.example.personjpqlservice;


import com.example.personjpqlservice.models.*;
import com.example.personjpqlservice.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@Slf4j
@SpringBootApplication
public class PersonJpqlServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PersonJpqlServiceApplication.class, args);

        JpaRepository<Person> personJpaRepository = context.getBean(PersonRepository.class);
        JpaRepository<Car> carJpaRepository = context.getBean(CarRepository.class);
        AddressRepository addressRepository = context.getBean(AddressRepository.class);
        UniversityRepository universityRepository = context.getBean(UniversityRepository.class);

        Car bentley = Car.builder()
                .model("Bentley")
                .build();

        Address address = Address.builder()
                .street("Broadway: New York City")
                .build();

        Person person = Person.builder()
                .age(37)
                .firstName("Bob")
                .lastName("Marley")
                .car(bentley)
                .address(address)
                .build();

        Account uber = Account.builder()
                .serviceName("Uber")
                .phoneNumber("+79994443322")
                .person(person)
                .build();

        Account telegram = Account.builder()
                .serviceName("Telegram")
                .phoneNumber("+79864445522")
                .person(person)
                .build();

        person.setAccountList(List.of(uber, telegram));

        carJpaRepository.save(bentley);
        log.info(String.format("Car with model: %s was saved to the database", bentley.getModel()));

        addressRepository.save(address);
        log.info(String.format("Address with street: %s was saved to the database", address.getStreet()));

//        personJpaRepository.save(person);
        log.info(String.format("Person with name: %s %s was saved to the database", person.getFirstName(), person.getLastName()));

        Car car = carJpaRepository.findById(1L);
        log.info(String.format("%s was gotten from the database", car));

        Person owner = personJpaRepository.findById(1L);
        log.info(String.format("%s was gotten from the database", owner));

        // результатом будет null
        Person byId = personJpaRepository.findById(2L);
        log.info(String.format("%s was found in the database", byId));

        List<Person> personList = List.of(person);
        University university = University.builder()
                .name("MAI")
                .personList(personList)
                .build();

        universityRepository.save(university);

        University univ = universityRepository.findById(1L);
        log.info(String.format("%s was gotten from the database", univ));


    }

}
