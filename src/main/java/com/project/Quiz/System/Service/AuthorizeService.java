package com.project.Quiz.System.Service;

import com.project.Quiz.System.Entity.Quiz;
import com.project.Quiz.System.Entity.UserAuth;
import com.project.Quiz.System.Exceptions.InvalidTokenException;
import com.project.Quiz.System.Exceptions.QuizAttemptNotAllowedException;
import com.project.Quiz.System.Exceptions.UserNotFoundException;
import com.project.Quiz.System.Repository.AuthorizeRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AuthorizeService {
    final private AuthorizeRepo authorizeRepo;
    public AuthorizeService(AuthorizeRepo authorizeRepo) {
        this.authorizeRepo = authorizeRepo;
    }
    public void assertAdmin(String token) {
        List<UserAuth> userAuth = authorizeRepo.ifAllowedCreateQuiz(token);
        if(!userAuth.isEmpty()) {
            if(!userAuth.getFirst().getUser().getRole().equalsIgnoreCase("admin"))
                throw new InvalidTokenException("Only admin can perform this operation !");
        }else{
            throw new UserNotFoundException("User not found !");
        }
    }
    public void assertUser(String token) {
        List<UserAuth> userAuth = authorizeRepo.ifAllowedCreateQuiz(token);
        if(!userAuth.isEmpty()) {
            if(!userAuth.getFirst().getUser().getRole().equalsIgnoreCase("user"))
                throw new QuizAttemptNotAllowedException("Only User can perform this operation !");
        }else{
            throw new UserNotFoundException("User not found !");
        }
    }
}
