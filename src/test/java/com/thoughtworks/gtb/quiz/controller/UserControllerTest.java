package com.thoughtworks.gtb.quiz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.gtb.quiz.domain.Education;
import com.thoughtworks.gtb.quiz.domain.User;
import com.thoughtworks.gtb.quiz.exception.UserIsNotExistException;
import com.thoughtworks.gtb.quiz.repository.EducationRepository;
import com.thoughtworks.gtb.quiz.repository.UserRepository;
import com.thoughtworks.gtb.quiz.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {
    @MockBean
    UserService userService;
    @Autowired
    private MockMvc mockMvc;

    private User firstUser;
    private Education firstUserEducation;
    @BeforeEach
    public void beforeEach(){
        firstUser = User.builder()
                .id(1l)
                .age(18l)
                .avatar("http://...")
                .description("hello world")
                .name("zh")
                .build();
        firstUserEducation = Education.builder()
                .user(firstUser)
                .year(1999)
                .title("埃里克为大家瓦活佛空间和可是你不")
                .description("哇据考虑到囧i后就开始不积跬步.")
                .build();
    }

    @Test
    public void should_return_user_by_id_when_user_id_exist() throws Exception {
        when(userService.getUserById(1)).thenReturn(firstUser);

        mockMvc.perform(get("/users/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("zh")));

        verify(userService).getUserById(1);
    }
    @Test
    public void should_return_User_NOT_FOUND_when_user_id_not_exist() throws Exception {
        when(userService.getUserById(1)).thenThrow(new UserIsNotExistException("User Not Found"));

        mockMvc.perform(get("/users/{id}", 1))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("zh")));

        verify(userService).getUserById(1);
    }
    @Test
    public void should_return_user_when_user_add_success() throws Exception {
        when(userService.addUser(firstUser)).thenReturn(firstUser);
        ObjectMapper objectMapper = new ObjectMapper();
        String josnUser = objectMapper.writeValueAsString(firstUser);

        mockMvc.perform(post("/users")
                .content(josnUser).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("zh")));

        verify(userService).addUser(firstUser);
    }

    @Test
    public void should_return_user_educations_when_user_id_exist() throws Exception {
        List<Education> educations = Collections.singletonList(firstUserEducation);
        when(userService.getEducationsByUserId(1)).thenReturn(educations);

        mockMvc.perform(get("/users/"+firstUser.getId()+"/educations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].year", is(1999)));

        verify(userService).getEducationsByUserId(1);
    }
}
