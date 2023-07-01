package com.example.personjpqlservice.repository;

import com.example.personjpqlservice.models.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PersonRepository implements JpaRepository<Person> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Person findById(Long id) {
        return entityManager.find(Person.class, id);
    }

    @Override
    public List<Person> findAll() {
        TypedQuery<Person> query = entityManager.createQuery("select e from person e", Person.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Person person = entityManager.find(Person.class, id);
        if (person != null) {
            entityManager.remove(person);
        }
    }

    @Override
    public void save(Person person) {
        entityManager.persist(person);
    }
}
