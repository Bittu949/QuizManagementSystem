package com.project.Quiz.System.Exceptions;

public class QuizNotFoundException extends RuntimeException{
    public QuizNotFoundException(){
    }
    public QuizNotFoundException(String message){
        super(message);
    }
}
