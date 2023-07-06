package com.example.personjpqlservice.repository;

import com.example.personjpqlservice.models.Account;
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
public class AccountRepository implements JpaRepository<Account> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Account findById(Long id) {
        return entityManager.find(Account.class, id);
    }

    @Override
    public List<Account> findAll() {
        TypedQuery<Account> query = entityManager.createQuery("select e from account e", Account.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Account account = entityManager.find(Account.class, id);
        if (account != null) {
            entityManager.remove(account);
        } else {
            log.warn(String.format("There is no entity with id = %s", id));
        }
    }

    @Override
    public void save(Account account) {
        entityManager.persist(account);
    }
}
