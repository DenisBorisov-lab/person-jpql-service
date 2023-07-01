package com.example.personjpqlservice;

import com.example.personjpqlservice.models.Address;
import com.example.personjpqlservice.models.Car;
import com.example.personjpqlservice.models.Person;
import com.example.personjpqlservice.repository.AddressRepository;
import com.example.personjpqlservice.repository.CarRepository;
import com.example.personjpqlservice.repository.JpaRepository;
import com.example.personjpqlservice.repository.PersonRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class PersonJpqlServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PersonJpqlServiceApplication.class, args);

        Person person = Person.builder()
                .age(37)
                .firstName("Bob")
                .lastName("Marley").build();

        Car bentley = Car.builder()
                .model("Bentley")
                .person(person)
                .build();

        Car mercedes = Car.builder()
                .model("Mercedes")
                .person(person)
                .build();
        Address address = Address.builder()
                .streetName("Arbatskay").build();

        person.setAddress(address);
        person.setCars(List.of(mercedes, bentley));


        JpaRepository<Person> bean = context.getBean(PersonRepository.class);
        JpaRepository<Car> bean1 = context.getBean(CarRepository.class);
        JpaRepository<Address> bean2 = context.getBean(AddressRepository.class);

        bean2.save(address);
        bean.save(person);
        bean1.save(bentley);
        bean1.save(mercedes);

    }

}
