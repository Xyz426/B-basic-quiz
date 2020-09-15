package com.thoughtworks.gtb.quiz.service;

import com.thoughtworks.gtb.quiz.domain.User;
import com.thoughtworks.gtb.quiz.exception.UserIsNotExistException;
import com.thoughtworks.gtb.quiz.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(int id){
        User user = userRepository.getUserById(id);
        if(user == null){
            throw new UserIsNotExistException("此用户不存在");
        }
        return user;
    }
}
