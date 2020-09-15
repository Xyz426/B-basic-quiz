package com.thoughtworks.gtb.quiz.controller;

import com.thoughtworks.gtb.quiz.domain.Education;
import com.thoughtworks.gtb.quiz.domain.User;
import com.thoughtworks.gtb.quiz.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @GetMapping(path = "/{id}/educations")
    public ResponseEntity<List<Education>> getEducationsByUserId(@PathVariable int id){
        return ResponseEntity.ok(userService.getEducationsByUserId(id));
    }
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody @Valid User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(user));
    }

    @PostMapping(path = "/{id}/educations")
    public ResponseEntity<Education> addUserEducation(@PathVariable int id,@RequestBody @Valid Education education){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUserEducation(id,education));
    }
}
