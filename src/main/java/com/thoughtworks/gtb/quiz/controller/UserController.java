package com.thoughtworks.gtb.quiz.controller;

import com.thoughtworks.gtb.quiz.domain.User;
import com.thoughtworks.gtb.quiz.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
