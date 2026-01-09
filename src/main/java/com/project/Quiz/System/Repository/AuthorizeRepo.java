package com.project.Quiz.System.Repository;

import com.project.Quiz.System.Entity.UserAuth;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorizeRepo {
    EntityManager em;
    public AuthorizeRepo(EntityManager em) {
        this.em = em;
    }
    public List<UserAuth> ifAllowedCreateQuiz(String token) {
        return em.createQuery("FROM UserAuth ua where token=:token", UserAuth.class)
                .setParameter("token", token)
                .getResultList();
    }

    public List<UserAuth> isAllowedAttemptQuiz(String token) {
        return em.createQuery("FROM UserAuth ua where token=:token", UserAuth.class)
                .setParameter("token", token)
                .getResultList();
    }
}
