package com.nnk.springboot.controllers.user;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserReadService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(Long id) {

        return userRepository.getById(id);
    }

    public Collection<User> getUsers() {

        return userRepository.findAll();
    }
}
