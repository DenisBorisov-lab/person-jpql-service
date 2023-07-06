package com.example.personjpqlservice;


import com.example.personjpqlservice.models.Address;
import com.example.personjpqlservice.models.Car;
import com.example.personjpqlservice.models.Person;
import com.example.personjpqlservice.repository.AddressRepository;
import com.example.personjpqlservice.repository.CarRepository;
import com.example.personjpqlservice.repository.JpaRepository;
import com.example.personjpqlservice.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class PersonJpqlServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PersonJpqlServiceApplication.class, args);

        JpaRepository<Person> personJpaRepository = context.getBean(PersonRepository.class);
        JpaRepository<Car> carJpaRepository = context.getBean(CarRepository.class);
        AddressRepository addressRepository = context.getBean(AddressRepository.class);

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

        carJpaRepository.save(bentley);
        log.info(String.format("Car with model: %s was saved to the database", bentley.getModel()));

        addressRepository.save(address);
        log.info(String.format("Address with street: %s was saved to the database", address.getStreet()));

        personJpaRepository.save(person);
        log.info(String.format("Person with name: %s %s was saved to the database", person.getFirstName(), person.getLastName()));

        Car car = carJpaRepository.findById(1L);
        log.info(String.format("%s was gotten from the database", car));

        Person owner = personJpaRepository.findById(1L);
        log.info(String.format("%s was gotten from the database", owner));


    }

}
