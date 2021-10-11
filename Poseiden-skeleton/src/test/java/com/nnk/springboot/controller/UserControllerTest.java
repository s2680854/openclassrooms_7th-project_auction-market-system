package com.nnk.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.controller.user.UserCreationService;
import com.nnk.springboot.controller.user.UserDeletionService;
import com.nnk.springboot.controller.user.UserReadService;
import com.nnk.springboot.controller.user.UserUpdateService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserCreationService userCreationService;
    @MockBean
    private UserReadService userReadService;
    @MockBean
    private UserUpdateService userUpdateService;
    @MockBean
    private UserDeletionService userDeletionService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserController userController;

    @Before("")
    public void setup() {

        userController = new UserController();
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @BeforeEach
    public void shouldInitialize() throws Exception {

        userRepository.deleteAll();

        User user = new User();
        user.setUsername("gringotts@jkr.com");
        user.setPassword("1234567");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setFullname("Gringotts");
        user.setRole("ADMIN");
        userRepository.save(user);
    }

    @Test
    public void shouldGetUserList() throws Exception {

        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"));
    }
}
