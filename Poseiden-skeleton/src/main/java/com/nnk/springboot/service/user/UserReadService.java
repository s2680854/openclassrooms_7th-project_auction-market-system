package com.nnk.springboot.service.user;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserReadService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(Long id) {

        return userRepository.findById(id);
    }

    public Collection<User> getUsers() {

        return userRepository.findAll();
    }
}
