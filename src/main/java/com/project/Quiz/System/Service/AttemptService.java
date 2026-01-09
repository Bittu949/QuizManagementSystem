package com.project.Quiz.System.Service;

import com.project.Quiz.System.DTO.QuizAttemptReturnResult;
import com.project.Quiz.System.DTO.SingleUserAttempts;
import com.project.Quiz.System.Entity.*;
import com.project.Quiz.System.Repository.QuestionRepo;
import com.project.Quiz.System.Repository.QuizAttemptRepo;
import com.project.Quiz.System.Repository.UserAnswerRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AttemptService {

    @Autowired
    private QuizAttemptRepo attemptRepo;

    @Autowired
    private UserAnswerRepo answerRepo;

    public QuizAttemptReturnResult startAttempt(User user, Quiz quiz, Map<Long, String> answers) {
        QuizAttempt qa = new QuizAttempt();
        int score = 0;

        List<Question> questions = attemptRepo.getAllQuestions(quiz.getId());
        for(Question question : questions){
            if(answers.get(question.getId()) != null && question.getCorrectAnswer().equals(answers.get(question.getId())))
                score++;
        }
        qa.setUser(user);
        qa.setQuiz(quiz);
        qa.setAttemptedAt(LocalDateTime.now());
        qa.setScore(score);
        attemptRepo.save(qa);

        for(Question question : questions){
            UserAnswer ua = new UserAnswer();
            ua.setAttempt(qa);
            ua.setCorrect(question.getId() != null && question.getCorrectAnswer().equals(answers.get(question.getId())));
            ua.setQuestion(question);
            ua.setSelectedAnswer(answers.get(question.getId()));
            answerRepo.save(ua);
        }

        QuizAttemptReturnResult result = new QuizAttemptReturnResult();
        result.setQuizId(quiz.getId());
        result.setTime(LocalDateTime.now());
        result.setScore(qa.getScore());
        return result;
    }

    public List<SingleUserAttempts> getAllAttempts(long id){
        List<QuizAttempt> attempts = attemptRepo.findAllAttempts(id);
        List<SingleUserAttempts> allAttempts = new ArrayList<>();
        for(QuizAttempt quizAttempt : attempts){
            SingleUserAttempts singleUserAttempts = new SingleUserAttempts();
            singleUserAttempts.setQid(quizAttempt.getQuiz().getId());
            singleUserAttempts.setScore(quizAttempt.getScore());
            singleUserAttempts.setTime(quizAttempt.getAttemptedAt());
            singleUserAttempts.setUid(quizAttempt.getUser().getId());
            allAttempts.add(singleUserAttempts);
        }
        return allAttempts;
    }
}

