package com.project.Quiz.System.Service;

import com.project.Quiz.System.Entity.Question;
import com.project.Quiz.System.Entity.Quiz;
import com.project.Quiz.System.Exceptions.QuizNotFoundException;
import com.project.Quiz.System.Repository.QuestionRepo;
import com.project.Quiz.System.Repository.QuizRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuestionRepo questionRepo;

    public void createQuiz(Quiz quiz) {
        quizRepo.save(quiz);
    }

    public void addQuestion(Long quizId, List<Question> q) {
        Quiz quiz = quizRepo.find(quizId);
        if(quiz!=null) {
            int index = 0;
            for (Question question : q) {
                question.setQuiz(quiz);
                question.setOrderIndex(index++);
                questionRepo.save(question);
            }
        }else {
            throw new QuizNotFoundException("Quiz with this ID not found !");
        }
    }

    public Quiz getQuiz(long quizId) {
        return quizRepo.find(quizId);
    }

    public List<Question> getQuestions(Long quizId) {
        return questionRepo.findByQuiz(quizId);
    }

}

