package com.example.personjpqlservice.repository;

import com.example.personjpqlservice.models.University;
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
public class UniversityRepository implements JpaRepository<University> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public University findById(Long id) {
        return entityManager.find(University.class, id);
    }

    @Override
    public List<University> findAll() {
        TypedQuery<University> query = entityManager.createQuery("select e from university e", University.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        University university = entityManager.find(University.class, id);
        if (university != null) {
            entityManager.remove(university);
        } else {
            log.warn(String.format("There is no entity with id = %s", id));
        }
    }

    @Override
    public void save(University university) {
        entityManager.persist(university);
    }
}
