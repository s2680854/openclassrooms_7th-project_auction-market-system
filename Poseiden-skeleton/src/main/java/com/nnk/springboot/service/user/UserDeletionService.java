package com.nnk.springboot.service.user;

import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDeletionService {

    @Autowired
    private UserRepository userRepository;

    public void deleteUserById(Long id) {

        userRepository.deleteById(id);
    }

    public void deleteUsers() {

        userRepository.deleteAll();
    }
}
