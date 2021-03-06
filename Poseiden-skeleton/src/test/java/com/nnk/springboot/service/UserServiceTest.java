package com.nnk.springboot.service;

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
    @Autowired
    private UserCreationService userCreationService;
    @Autowired
    private UserUpdateService userUpdateService;
    @Autowired
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
    public void shouldCreateUser() throws Exception {

        User user = new User();
        user.setUsername("granger@jkr.com");
        user.setPassword("12345678");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setFullname("Granger");
        user.setRole("ADMIN");
        userRepository.save(user);
        user.setId(userRepository.findByEmail(user.getUsername()).getId());
        user.setPassword(userRepository.findByEmail(user.getUsername()).getPassword());

        User actual = userReadService.getUser(user.getId()).get();

        assertEquals(user, actual);
    }

    @Test
    public void shouldGetUsers() throws Exception {

        User user = new User();
        user.setId(userRepository.findByEmail("grinngotts@jkr.com").getId());
        user.setUsername("grinngotts@jkr.com");
        user.setPassword("12345678");
        user.setPassword(userRepository.findByEmail("grinngotts@jkr.com").getPassword());
        user.setFullname("Grinngott's");
        user.setRole("ADMIN");
        Collection<User> expected = new ArrayList<>();
        expected.add(user);

        Collection<User> actual = userReadService.getUsers();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetUserById() throws Exception {

        User user = new User();
        user.setUsername("grinngotts@jkr.com");
        user.setId(userRepository.findByEmail(user.getUsername()).getId());
        user.setPassword(userRepository.findByEmail(user.getUsername()).getPassword());
        user.setFullname("Grinngott's");
        user.setRole("ADMIN");
        Long id = 1L;
        try {id = userRepository.findByEmail("grinngotts@jkr.com").getId();} catch (Exception e) {}

        Optional<User> optional = userReadService.getUser(id);
        User actual = new User();
        if (optional.isPresent()) {
            actual.setId(optional.get().getId());
            actual.setUsername(optional.get().getUsername());
            actual.setPassword(optional.get().getPassword());
            actual.setFullname(optional.get().getFullname());
            actual.setRole(optional.get().getRole());
        }

        assertEquals(user, actual);
    }

    @Test
    public void shouldUpdateUser() throws Exception {

        User user = new User();
        user.setUsername("wisley@jkr.com");
        user.setPassword("12345678");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setFullname("Wisley");
        user.setRole("ADMIN");
        userRepository.save(user);
        user.setId(userRepository.findByEmail(user.getUsername()).getId());
        user.setPassword(userRepository.findByEmail(user.getUsername()).getPassword());

        user.setFullname("Weasley");
        User userUpdated = userUpdateService.updateUser(user);

        assertEquals(user, userUpdated);
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
