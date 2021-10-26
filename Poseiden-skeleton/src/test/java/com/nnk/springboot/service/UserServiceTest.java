package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.user.UserDeletionService;
import com.nnk.springboot.service.user.UserReadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@SpringBootTest
@AutoConfigureMockMvc(addFilters=false)
public class UserServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserReadService userReadService;
    @MockBean
    private UserDeletionService userDeletionService;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void shouldInitialize() throws Exception {

        userRepository.deleteAll();

        User user = new User();
        user.setUsername("grinngotts@jkr.com");
        user.setPassword("12345678");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setFullname("Grinngott's");
        user.setRole("ADMIN");
        userRepository.save(user);
    }

    @Test
    public void shouldGetUsers() throws Exception {

        Collection<User> actualList = new ArrayList<>();

        Collection<User> expectedList = userReadService.getUsers();

        assertEquals(actualList, expectedList);
    }

    @Test
    public void shouldGetUserById() throws Exception {

        Long id = 1L;
        try {id = userRepository.findByEmail("grinngotts@jkr.com").getId();} catch (Exception e) {}

        User expected = new User();

        Optional<User> optional = userReadService.getUser(id);
        User actual = new User();
        if (optional.isPresent()) {
            actual.setId(optional.get().getId());
            actual.setUsername(optional.get().getUsername());
            actual.setPassword(optional.get().getPassword());
            actual.setFullname(optional.get().getFullname());
            actual.setRole(optional.get().getRole());
        }

        assertEquals(expected, actual);
    }


    @Test
    public void shouldDeleteUser() throws Exception {

        Long userId = userRepository.findByEmail("grinngotts@jkr.com").getId();

        Mockito.doNothing().when(userDeletionService).deleteUserById(userId);

        mockMvc.perform(delete("/user/delete/" + userId));

        Mockito.verify(userDeletionService, Mockito.times(1)).deleteUserById(userId);
    }

    @Test
    public void shouldDeleteUsers() throws Exception {

        Mockito.doNothing().when(userDeletionService).deleteUsers();

        mockMvc.perform(delete("/user/delete"));

        Mockito.verify(userDeletionService, Mockito.times(1)).deleteUsers();
    }
}
