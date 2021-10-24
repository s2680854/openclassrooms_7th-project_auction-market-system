package com.nnk.springboot.controller;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc(addFilters=false)
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
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
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
    public void shouldValidateUser() throws Exception {

        User user = new User();
        user.setUsername("grinngotts3@jkr.com");
        user.setPassword("1234567");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setFullname("Grinngott's");
        user.setRole("ADMIN");
        userRepository.save(user);

        user.setRole("USER");
        Long id = userRepository.findByEmail("grinngotts3@jkr.com").getId();
        user.setId(id);

        mockMvc.perform(post("/user/validate")
                .param("id", user.getId().toString())
                .param("username", user.getUsername())
                .param("password", user.getPassword())
                .param("fullame", user.getFullname())
                .param("role", user.getRole()))
                .andExpect(view().name("redirect:/user/list"));
    }

    @Test
    public void shouldUpdateUser() throws Exception {

        User user = new User();
        user.setUsername("grinngotts0@jkr.com");
        user.setPassword("1234567");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setFullname("Grinngott's");
        user.setRole("ADMIN");
        userRepository.save(user);

        user.setRole("USER");
        Long id = userRepository.findByEmail("grinngotts0@jkr.com").getId();
        user.setId(id);

        mockMvc.perform(post("/user/update/" + id)
                        .param("id", user.getId().toString())
                        .param("username", user.getUsername())
                        .param("password", user.getPassword())
                        .param("fullame", user.getFullname())
                        .param("role", user.getRole()))
                .andExpect(view().name("redirect:/user/list"));
    }

    @Test
    public void shouldDeleteUser() throws Exception {

        User user = new User();
        user.setUsername("grinngotts2@jkr.com");
        user.setPassword("1234567");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setFullname("Grinngott's");
        user.setRole("ADMIN");
        userRepository.save(user);
        Long id = userRepository.findByEmail("grinngotts2@jkr.com").getId();

        mockMvc.perform(delete("/user/delete/" + id))
                .andExpect(view().name("redirect:/user/list"));
    }

    @Test
    public void shouldDeleteUserList() throws Exception {

        mockMvc.perform(delete("/user/delete"))
                .andExpect(view().name("redirect:/user/list"));
    }
}
