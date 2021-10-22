package com.nnk.springboot;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void userTest() {
        User user = new User();
        user.setUsername("test@test.com");
        user.setPassword("12345678");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setFullname("Test");
        user.setRole("ADMIN");

        // Save
        user = userRepository.save(user);
        assertEquals(passwordEncoder.matches("12345678", user.getPassword()), true);

        // Update
        user.setRole("USER");
        user = userRepository.save(user);
        assertEquals(user.getRole(), "USER");

        // Find
        List<User> listResult = userRepository.findAll();
        assertTrue(listResult.size() > 0);

        // Delete
        Long id = user.getId();
        userRepository.delete(user);
        Optional<User> userList = userRepository.findById(id);
        assertFalse(userList.isPresent());
    }
}
