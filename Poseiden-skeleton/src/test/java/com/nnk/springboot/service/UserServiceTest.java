package com.nnk.springboot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.user.UserCreationService;
import com.nnk.springboot.service.user.UserDeletionService;
import com.nnk.springboot.service.user.UserReadService;
import com.nnk.springboot.service.user.UserUpdateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

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

    @BeforeEach
    public void shouldInitialize() throws Exception {

        userRepository.deleteAll();

        User user = new User();
        user.setUsername("grinngotts@jkr.com");
        user.setPassword("1234567");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setFullname("Grinngott's");
        user.setRole("ADMIN");
        userRepository.save(user);
    }

    @Test
    public void shouldGetUsers() throws Exception {

        User user = new User();
        user.setUsername("grinngotts@jkr.com");
        user.setPassword("1234567");
        user.setFullname("Grinngott's");
        user.setRole("ADMIN");
        user.setId(userRepository.findByEmail("grinngotts@jkr.com").getId());

        List<User> users = new ArrayList<>();
        users.add(user);

        Mockito.when(userReadService.getUsers()).thenReturn(users);

        MvcResult mvcResult = mockMvc.perform(get("/user")).andExpect(status().isOk()).andReturn();

        String actualResponse = mvcResult.getResponse().getContentAsString();
        String expectedResponse = objectMapper.writeValueAsString(users);

        assertEquals(actualResponse, expectedResponse);
    }

    @Test
    public void shouldGetUserById() throws Exception {

        Long userId = userRepository.findByEmail("grinngotts@jkr.com").getId();

        User user = new User();
        user.setUsername("grinngotts@jkr.com");
        user.setPassword("1234567");
        user.setFullname("Grinngott's");
        user.setRole("ADMIN");

        Mockito.when(userReadService.getUser(userId)).thenReturn(user);

        MvcResult mvcResult = mockMvc.perform(get("/user/" + userId)).andExpect(status().isOk()).andReturn();

        String actualResponse = mvcResult.getResponse().getContentAsString();
        String expectedResponse = objectMapper.writeValueAsString(user);

        assertEquals(actualResponse, expectedResponse);
    }


    @Test
    public void shouldDeleteUser() throws Exception {

        Long userId = userRepository.findByEmail("grinngotts@jkr.com").getId();

        Mockito.doNothing().when(userDeletionService).deleteUserById(userId);

        mockMvc.perform(delete("/user/" + userId)).andExpect(status().isOk());

        Mockito.verify(userDeletionService, Mockito.times(1)).deleteUserById(userId);
    }

    @Test
    public void shouldDeleteUsers() throws Exception {

        Mockito.doNothing().when(userDeletionService).deleteUsers();

        mockMvc.perform(delete("/user")).andExpect(status().isOk());

        Mockito.verify(userDeletionService, Mockito.times(1)).deleteUsers();
    }
}
