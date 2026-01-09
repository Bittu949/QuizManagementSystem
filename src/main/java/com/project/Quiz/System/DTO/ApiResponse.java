package com.project.Quiz.System.DTO;

import java.time.LocalDateTime;

public class ApiResponse <T>{
    LocalDateTime timeStamp;
    int status;
    boolean success;
    String message;
    T data;

    public ApiResponse(LocalDateTime timeStamp, int status, boolean success, String message, T data) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
