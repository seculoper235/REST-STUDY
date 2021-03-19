package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findUserById(Integer id) {
        return userRepository.findById(id).get();
    }

    public User getUserById(Integer id) {
        return userRepository.getOne(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
