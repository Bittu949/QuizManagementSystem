package com.project.Quiz.System.Repository;

import com.project.Quiz.System.Entity.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionRepo {

    @Autowired
    private EntityManager em;

    public void save(Question q) {
        em.persist(q);
    }

    public List<Question> findByQuiz(Long quizId) {
        return em.createQuery("from Question where quiz.id=:id", Question.class)
                .setParameter("id", quizId)
                .getResultList();
    }
}

