package com.project.Quiz.System.Controller;

import com.project.Quiz.System.DTO.ApiResponse;
import com.project.Quiz.System.DTO.UserLogin;
import com.project.Quiz.System.Entity.User;
import com.project.Quiz.System.Service.AuthorizeService;
import com.project.Quiz.System.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;
    private final AuthorizeService authorizeService;
    public UserController(UserService service, AuthorizeService authorizeService) {
        this.service = service;
        this.authorizeService = authorizeService;
    }
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Object>> register(@RequestBody User user) {
        service.register(user);
        return new ResponseEntity<>(new ApiResponse<>(LocalDateTime.now(), HttpStatus.OK.value(),
                true, "User created successfully.", null),
                HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUser(@PathVariable Long id,
                                                     @RequestHeader String token) {
        authorizeService.assertAdmin(token);
        User user = service.getUser(id);
        return new ResponseEntity<>(new ApiResponse<>(LocalDateTime.now(), HttpStatus.OK.value(),
                true, "User found.", user),
                HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody UserLogin userLogin){
        return new ResponseEntity<>(new ApiResponse<>(LocalDateTime.now(),
                HttpStatus.OK.value(),true, "Login successful !",
                service.checkUser(userLogin.getUsername(), userLogin.getPassword())),
                HttpStatus.OK);
    }
}

