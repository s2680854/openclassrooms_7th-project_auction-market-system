package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(Long id) {

        return userRepository.getById(id);
    }

    public Collection<User> getUsers() {

        return userRepository.findAll();
    }

    public User createUser(User user) {

        return userRepository.save(user);
    }

    public Collection<User> createRules(Collection<User> users) {

        return userRepository.saveAll(users);
    }

    public User updateUser(User user) {

        Optional<User> optionalUser =
                userRepository.findById(user.getId());

        if (!optionalUser.isPresent()) {

            return new User();
        }
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {

        userRepository.deleteById(id);
    }

    public void deleteUsers() {

        userRepository.deleteAll();
    }
}
