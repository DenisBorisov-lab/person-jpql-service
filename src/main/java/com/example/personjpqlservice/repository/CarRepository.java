package com.example.personjpqlservice.repository;

import com.example.personjpqlservice.models.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CarRepository implements JpaRepository<Car> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Car findById(Long id) {
        return entityManager.find(Car.class, id);
    }

    @Override
    public List<Car> findAll() {
        TypedQuery<Car> query = entityManager.createQuery("select e from car e", Car.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Car car = entityManager.find(Car.class, id);
        if(car != null){
            entityManager.remove(car);
        }
    }

    @Override
    public void save(Car car) {
        entityManager.persist(car);
    }
}
