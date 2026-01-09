package com.project.Quiz.System.Repository;

import com.project.Quiz.System.Entity.Quiz;
import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuizRepo {

    @Autowired
    private EntityManager em;

    public void save(Quiz quiz) {
        em.persist(quiz);
    }

    public Quiz find(Long id) {
        return em.find(Quiz.class, id);
    }

    public List<Quiz> findAll() {
        return em.createQuery("from Quiz", Quiz.class).getResultList();
    }
}

