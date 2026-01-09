package com.project.Quiz.System.Repository;

import com.project.Quiz.System.Entity.UserAnswer;
import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserAnswerRepo {

    @Autowired
    private EntityManager em;

    public void save(UserAnswer ua) {
        em.persist(ua);
    }
}

