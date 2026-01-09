package com.project.Quiz.System.Exceptions;

public class QuizAttemptNotAllowedException extends RuntimeException{
    public QuizAttemptNotAllowedException(){
    }
    public QuizAttemptNotAllowedException(String message){
        super(message);
    }
}
