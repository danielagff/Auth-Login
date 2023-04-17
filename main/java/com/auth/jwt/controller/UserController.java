package com.auth.jwt.controller;

import com.auth.jwt.dto.Login;
import com.auth.jwt.model.User;
import com.auth.jwt.service.UserService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public record UserController(UserService userService) {


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
            return ResponseEntity.ok(userService.createUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login)
    {
        return ResponseEntity.ok(userService.login(login));
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUser()
    {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/home")
    public ResponseEntity<String> youAreAuth()
    {
        return ResponseEntity.ok("You Are OK!");
    }

}
