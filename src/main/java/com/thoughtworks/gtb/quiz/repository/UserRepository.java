package com.thoughtworks.gtb.quiz.repository;

import com.thoughtworks.gtb.quiz.domain.Education;
import com.thoughtworks.gtb.quiz.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    List<User> users = new ArrayList<>();
    {
        users.add(User.builder().id(1l).age(16l)
                .name("KAMIL")
                .avatar("https://inews.gtimg.com/newsapp_match/0/3581582328/0")
                .description("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repellendus, non, dolorem, cumque distinctio magni quam expedita velit laborum sunt amet facere tempora ut fuga aliquam ad asperiores voluptatem dolorum! Quasi.")
                .educationList(new ArrayList<>())
                .build());
        users.get(0).getEducationList().add(
                Education.builder()
                        .description("Eos, explicabo, nam, tenetur et ab eius deserunt aspernatur ipsum ducimus quibusdam quis voluptatibus.")
                        .year(2005)
                        .title("Secondary school specializing in artistic")
                        .userId(1).build());
    }
    public User getUserById(int id){
        if(users.size() < id)
            return null;
        return users.get(id - 1);
    }

    public User addUser(User user) {
        user.setId((long) (users.size() + 1));
        user.setEducationList(new ArrayList<>());
        users.add(user);
        return user;
    }

    public Education addUserEducation(int id, Education education) {
        users.get(id - 1).getEducationList().add(education);
        return education;
    }
}
