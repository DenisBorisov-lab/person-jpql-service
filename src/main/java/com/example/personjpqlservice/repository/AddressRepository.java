package com.example.personjpqlservice.repository;

import com.example.personjpqlservice.models.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Repository
@Transactional
public class AddressRepository implements JpaRepository<Address> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Address findById(Long id) {
        return entityManager.find(Address.class, id);
    }

    @Override
    public List<Address> findAll() {
        TypedQuery<Address> query = entityManager.createQuery("select e from address e", Address.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Address address = entityManager.find(Address.class, id);
        if (address != null) {
            entityManager.remove(address);
        } else {
            log.warn(String.format("There is no entity with id = %s", id));
        }
    }

    @Override
    public void save(Address address) {
        entityManager.persist(address);
    }
}
