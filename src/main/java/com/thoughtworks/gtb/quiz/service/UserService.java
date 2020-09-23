package com.thoughtworks.gtb.quiz.service;

import com.thoughtworks.gtb.quiz.domain.Education;
import com.thoughtworks.gtb.quiz.domain.User;
import com.thoughtworks.gtb.quiz.exception.UserIsNotExistException;
import com.thoughtworks.gtb.quiz.repository.EducationRepository;
import com.thoughtworks.gtb.quiz.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final EducationRepository educationRepository;

    public UserService(UserRepository userRepository, EducationRepository educationRepository) {
        this.userRepository = userRepository;
        this.educationRepository = educationRepository;
    }

    public User getUserById(int id){
        Optional<User> user = userRepository.findUserById((long) id);
        if(!user.isPresent()){
            throw new UserIsNotExistException("此用户不存在");
        }
        return user.get();
    }

    public List<Education> getEducationsByUserId(int id) {
        return educationRepository.findAllByUserId(id);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public Education addUserEducation(int id, Education education) {
        education.setUser(getUserById(id));

        return educationRepository.save(education);
    }
}
