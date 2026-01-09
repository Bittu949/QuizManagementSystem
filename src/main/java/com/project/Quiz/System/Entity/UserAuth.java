package com.project.Quiz.System.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "UserAuth")
public class UserAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long Id;
    private String token;
    @OneToOne
    private User user;
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
