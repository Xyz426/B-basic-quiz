package com.thoughtworks.gtb.quiz.repository;

import com.thoughtworks.gtb.quiz.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void should_return_user_when_given_one_exist_id() {

        entityManager.persistAndFlush(User.builder()
                .name("xyz")
                .age(19L)
                .avatar("http://xxx.png")
                .description("L啊我的娃达娃大师父ore,")
                .build());

        Optional<User> optionalUser = userRepository.findById(1L);

        assertThat(optionalUser.isPresent()).isTrue();
        assertThat(optionalUser.get()).isEqualTo(User.builder()
                .id(1l)
                .name("xyz")
                .age(19L)
                .avatar("http://xxx.png")
                .description("L啊我的娃达娃大师父ore,")
                .build());
    }
}
