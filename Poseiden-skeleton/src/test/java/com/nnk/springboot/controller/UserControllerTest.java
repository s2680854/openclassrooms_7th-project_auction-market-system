package com.nnk.springboot.controller;

import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserController userController;

    @Before("")
    public void setup() {

        userController = new UserController();
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void shouldGetUserList() throws Exception {

        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"));
    }

    @Test
    public void shouldAddUser() throws Exception {

        mockMvc.perform(get("/user/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"));
    }

    @Test
    public void shouldShowUpdateUserForm() throws Exception {

        User user = new User();
        user.setUsername("grinngotts@jkr.com");
        user.setPassword("1234567");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setFullname("Grinngott's");
        user.setRole("ADMIN");
        userRepository.save(user);
        Long id = userRepository.findByEmail("grinngotts@jkr.com").getId();

        mockMvc.perform(get("/user/update/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("user/update"));
    }

    @Test
    public void shouldDeleteUser() throws Exception {

        Long id = userRepository.findByEmail("david@test.com").getId();

        mockMvc.perform(delete("/user/delete/" + id))
                .andExpect(status().is(302))
                .andExpect(view().name("user/list"));
    }

    @Test
    public void shouldDeleteUserList() throws Exception {

        mockMvc.perform(delete("/user/delete"))
                .andExpect(status().is(302))
                .andExpect(view().name("user/list"));
    }
}
