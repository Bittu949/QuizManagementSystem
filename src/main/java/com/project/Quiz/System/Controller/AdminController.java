package com.project.Quiz.System.Controller;

import com.project.Quiz.System.DTO.ApiResponse;
import com.project.Quiz.System.DTO.SingleUserAttempts;
import com.project.Quiz.System.Entity.Question;
import com.project.Quiz.System.Entity.Quiz;
import com.project.Quiz.System.Service.AttemptService;
import com.project.Quiz.System.Service.AuthorizeService;
import com.project.Quiz.System.Service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    final private QuizService quizService;
    final private AttemptService attemptService;
    final private AuthorizeService authorizeService;
    public AdminController(QuizService quizService, AttemptService attemptService, AuthorizeService authorizeService) {
        this.quizService = quizService;
        this.attemptService = attemptService;
        this.authorizeService = authorizeService;
    }
    @PostMapping("/quiz")
    public ResponseEntity<ApiResponse<Object>> createQuiz(@RequestBody Quiz quiz,
                                                          @RequestHeader("token") String token) {
        authorizeService.assertAdmin(token);
        quizService.createQuiz(quiz);
        return new ResponseEntity<>(new ApiResponse<>(LocalDateTime.now(), HttpStatus.CREATED.value(),
                true, "Quiz created successfully", null),
                HttpStatus.CREATED);
    }
    @PostMapping("/quiz/{quizId}/question")
    public ResponseEntity<ApiResponse<Object>> addQuestions(
            @PathVariable Long quizId,
            @RequestBody List<Question> q,
            @RequestHeader String token) {
        authorizeService.assertAdmin(token);
        quizService.addQuestion(quizId, q);
                return new ResponseEntity<>(new ApiResponse<>(LocalDateTime.now(),
                        HttpStatus.OK.value(), true,
                        "Questions added", null),
                        HttpStatus.OK);
        }
    @GetMapping("/{user_id}/attempts")
    public ResponseEntity<ApiResponse<List<SingleUserAttempts>>> findAllAttempts(@PathVariable("user_id") long user_id,
                                                                                 @RequestHeader String token){
        authorizeService.assertAdmin(token);
            return new ResponseEntity<>(new ApiResponse<>(LocalDateTime.now(),
                    HttpStatus.OK.value(), true,
                    "Data Found.", attemptService.getAllAttempts(user_id)),
                    HttpStatus.OK);
    }
}

