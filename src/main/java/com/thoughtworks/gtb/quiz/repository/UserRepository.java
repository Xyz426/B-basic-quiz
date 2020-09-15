package com.thoughtworks.gtb.quiz.repository;

import com.thoughtworks.gtb.quiz.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    List<User> users = new ArrayList<>();
    {
        users.add(User.builder().id(1l).age(16)
                .name("KAMIL")
                .avatar("https://inews.gtimg.com/newsapp_match/0/3581582328/0")
                .description("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repellendus, non, dolorem, cumque distinctio magni quam expedita velit laborum sunt amet facere tempora ut fuga aliquam ad asperiores voluptatem dolorum! Quasi.")
                .build());
    }
    public User getUserById(int id){
        if(users.size() < id)
            return null;
        return users.get(id - 1);
    }

}
