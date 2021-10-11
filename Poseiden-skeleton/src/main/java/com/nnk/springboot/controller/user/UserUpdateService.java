package com.nnk.springboot.controller.user;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserUpdateService {

    @Autowired
    private UserRepository userRepository;

    public User updateUser(User user) {

        Optional<User> optionalUser =
                userRepository.findById(user.getId());

        if (!optionalUser.isPresent()) {

            return new User();
        }
        return userRepository.save(user);
    }
}
