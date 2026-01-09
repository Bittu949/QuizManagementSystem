package com.project.Quiz.System.Service;

import com.project.Quiz.System.Entity.User;
import com.project.Quiz.System.Entity.UserAuth;
import com.project.Quiz.System.Exceptions.InvalidTokenException;
import com.project.Quiz.System.Exceptions.UserNotFoundException;
import com.project.Quiz.System.Exceptions.InvalidUsernameOrPasswordException;
import com.project.Quiz.System.Repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public void register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
    }
    public User getUser(Long id) {
        User user = userRepo.find(id);
        if(user==null)
            throw new UserNotFoundException("User Not Found !");
        return user;
    }
    public String checkUser(String userName, String password){
        List<User> users = userRepo.validateUser(userName);
        String token = "";
        if(!users.isEmpty()) {
            for(User user : users) {
                if(encoder.matches(password, user.getPassword())) {
                    userRepo.deleteInvalidToken(user.getId());
                    token = createToken(user);
                    return token;
                }
            }
        }
        throw new InvalidUsernameOrPasswordException("Invalid username or password !");
    }
    public String createToken(User user){
        String token = UUID.randomUUID().toString();
        UserAuth auth = new UserAuth();
        auth.setToken(token);
        auth.setUser(user);
        userRepo.addToken(auth);
        return token;
    }
    public long findUserUsingToken(String token){
        List<UserAuth> userAuth = userRepo.findFromToken(token);
        if(!userAuth.isEmpty())
            return userAuth.getFirst().getUser().getId();
        throw new InvalidTokenException("Invalid token");
    }
}

