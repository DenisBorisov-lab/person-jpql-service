package com.example.personjpqlservice.repository;

import com.example.personjpqlservice.models.Country;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Slf4j
public class CountryRepository implements JpaRepository<Country> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Country findById(Long id) {
        return entityManager.find(Country.class, id);
    }

    @Override
    public List<Country> findAll() {
        TypedQuery<Country> query = entityManager.createQuery("select e from country e", Country.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Country country = entityManager.find(Country.class, id);
        if (country != null) {
            entityManager.remove(country);
        } else {
            log.warn(String.format("There is no entity with id = %s", id));
        }
    }

    @Override
    public void save(Country country) {
        entityManager.persist(country);
    }
}
