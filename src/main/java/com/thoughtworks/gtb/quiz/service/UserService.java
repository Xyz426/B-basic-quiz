package com.thoughtworks.gtb.quiz.service;

import com.thoughtworks.gtb.quiz.domain.Education;
import com.thoughtworks.gtb.quiz.domain.User;
import com.thoughtworks.gtb.quiz.exception.UserEducationsIsNotExistException;
import com.thoughtworks.gtb.quiz.exception.UserIsNotExistException;
import com.thoughtworks.gtb.quiz.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Education> getEducationsByUserId(int id) {
        User user = getUserById(id);
        if(user.getEducationList().size() == 0){
            throw new UserEducationsIsNotExistException("此用户没有教育经历！");
        }
        return user.getEducationList();
    }
}
