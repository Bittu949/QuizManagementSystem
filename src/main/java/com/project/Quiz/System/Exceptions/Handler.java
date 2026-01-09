package com.project.Quiz.System.Exceptions;

import com.project.Quiz.System.DTO.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class Handler {
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ApiResponse<Object>>InvalidTokenHandler(
            InvalidTokenException e){
        return new ResponseEntity<>(new ApiResponse<>(LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(),false,
                e.getMessage(),null),
                HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(QuizAttemptNotAllowedException.class)
    public ResponseEntity<ApiResponse<Object>> quizAttemptNotAllowedHandler(QuizAttemptNotAllowedException e){
        return new ResponseEntity<>(new ApiResponse<>(LocalDateTime.now(),
                HttpStatus.FORBIDDEN.value(),false,
                e.getMessage(),null),
                HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> quizNotFoundHandler(QuizNotFoundException e){
        return new ResponseEntity<>(new ApiResponse<>(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),false,
                e.getMessage(),null), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> userNotFoundHandler(UserNotFoundException e){
        return new ResponseEntity<>(new ApiResponse<>(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),false,
                e.getMessage(),null), HttpStatus.NOT_FOUND);
    }
}
