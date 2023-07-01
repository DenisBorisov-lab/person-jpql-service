package com.example.personjpqlservice.repository;

import com.example.personjpqlservice.models.Address;
import com.example.personjpqlservice.models.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class AddressRepository implements JpaRepository<Address> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Address findById(Long id) {
        return entityManager.find(Address.class,id);
    }

    @Override
    public List<Address> findAll() {
        TypedQuery<Address> query = entityManager.createQuery("select e from address e", Address.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Address address = entityManager.find(Address.class, id);
        if(address != null){
            entityManager.remove(address);
        }

    }

    @Override
    public void save(Address address) {
        entityManager.persist(address);
    }
}
