package com.project.Quiz.System.Exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
    }
    public UserNotFoundException(String message) {
        super(message);
    }
}
