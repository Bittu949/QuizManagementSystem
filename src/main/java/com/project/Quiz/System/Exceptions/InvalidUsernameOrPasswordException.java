package com.project.Quiz.System.Exceptions;

public class InvalidUsernameOrPasswordException extends RuntimeException{
    public InvalidUsernameOrPasswordException(String message){
        super(message);
    }
}
