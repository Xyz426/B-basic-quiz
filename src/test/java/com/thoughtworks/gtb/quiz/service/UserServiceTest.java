package com.thoughtworks.gtb.quiz.service;

import com.thoughtworks.gtb.quiz.domain.Education;
import com.thoughtworks.gtb.quiz.domain.User;
import com.thoughtworks.gtb.quiz.exception.UserIsNotExistException;
import com.thoughtworks.gtb.quiz.repository.EducationRepository;
import com.thoughtworks.gtb.quiz.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private EducationRepository educationRepository;

    private User user;
    private Education education;
    @BeforeEach
    public void beforeEach(){
        userService = new UserService(userRepository,educationRepository);

        user = User.builder()
                .id(1L)
                .name("xyz")
                .age(19L)
                .avatar("http://xxx.png")
                .description("L啊我的娃达娃大师父ore,")
                .build();
        education = Education.builder()
                .id(1)
                .user(user)
                .year(1999)
                .title("埃里克为大家瓦活佛空间和可是你不")
                .description("哇据考虑到囧i后就开始不积跬步.")
                .build();
    }

    @Test
    public void should_return_user_when_id_exist() {
        when(userRepository.findUserById(1L)).thenReturn(Optional.of(user));
        User user = userService.getUserById(1);

        assertThat(user).isEqualTo(User.builder()
                .id(1L)
                .name("xyz")
                .age(19L)
                .avatar("http://xxx.png")
                .description("L啊我的娃达娃大师父ore,")
                .build());
    }

    @Test
    public void should_return_user_when_id_not_exist() {
        when(userRepository.findUserById(2L)).thenReturn(Optional.empty());

        UserIsNotExistException thrownException = assertThrows(UserIsNotExistException.class, () -> {
            userService.getUserById(2);
        });

        assertThat(thrownException.getMessage()).containsSequence("User Not Found");
    }

    @Test
    public void should_return_user_educations_when_user_id_exist() {
        when(educationRepository.findAllByUserId(1L)).thenReturn(Collections.singletonList(education));
        List<Education> educationList = userService.getEducationsByUserId(1);

        assertThat(educationList).isEqualTo(Collections.singletonList(education));
    }

    @Test
    public void should_return_user_educations_when_add_user_educations_success() {
        when(userRepository.findUserById(1L)).thenReturn(Optional.of(user));
        when(educationRepository.save(education)).thenReturn(education);
        Education education = userService.addUserEducation(1, this.education);

        assertThat(education).isEqualTo(Education.builder()
                .id(1)
                .user(user)
                .year(1999)
                .title("埃里克为大家瓦活佛空间和可是你不")
                .description("哇据考虑到囧i后就开始不积跬步.")
                .build());
    }
}