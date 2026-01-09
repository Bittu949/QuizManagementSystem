package com.project.Quiz.System.Controller;

import com.project.Quiz.System.DTO.ApiResponse;
import com.project.Quiz.System.DTO.QuizAttemptReturnResult;
import com.project.Quiz.System.Entity.User;
import com.project.Quiz.System.Entity.UserAuth;
import com.project.Quiz.System.Service.AttemptService;
import com.project.Quiz.System.Service.AuthorizeService;
import com.project.Quiz.System.Service.QuizService;
import com.project.Quiz.System.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/attempt")
public class QuizAttemptController {
    private final AttemptService attemptService;
    private final UserService userService;
    private final QuizService quizService;
    private final AuthorizeService authorizeService;
    public QuizAttemptController(AttemptService attemptService, UserService userService, QuizService quizService, AuthorizeService authorizeService) {
        this.attemptService = attemptService;
        this.userService = userService;
        this.quizService = quizService;
        this.authorizeService = authorizeService;
    }
    @PostMapping("/start")
    public ResponseEntity<ApiResponse<QuizAttemptReturnResult>> start(
            @RequestParam Long quizId,
            @RequestBody Map<Long, String> answers,
            @RequestHeader String token) {
        authorizeService.assertUser(token);
        return new ResponseEntity<>(new ApiResponse<>(LocalDateTime.now(), HttpStatus.OK.value(),
                true, "Quiz submitted successfully.",
                attemptService.startAttempt(
                        userService.getUser(userService.findUserUsingToken(token)),
                        quizService.getQuiz(quizId),
                        answers
                )), HttpStatus.OK);
    }
}

