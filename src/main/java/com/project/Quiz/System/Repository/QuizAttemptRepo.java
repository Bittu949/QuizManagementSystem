package com.project.Quiz.System.Repository;

import com.project.Quiz.System.Entity.Question;
import com.project.Quiz.System.Entity.QuizAttempt;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuizAttemptRepo {

    @Autowired
    private EntityManager em;

    public void save(QuizAttempt qa) {
        em.persist(qa);
    }

    public List<QuizAttempt> findAllAttempts(Long id) {
        return em.createQuery("FROM QuizAttempt q WHERE q.user.id=:id",QuizAttempt.class)
                .setParameter("id",id)
                .getResultList();
    }

    public List<Question> getAllQuestions(Long id) {
        return em.createQuery("FROM Question q where q.quiz.id=:id ORDER BY q.orderIndex", Question.class)
                .setParameter("id",id)
                .getResultList();
    }
}
