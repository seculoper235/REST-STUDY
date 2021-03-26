package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

@Service
@Transactional
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public User findUserById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public User getUserById(Integer id) {
        return userRepository.getOne(id);
    }

    @Transactional(readOnly = true)
    public List<User> findUserAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User createUser(User user) {
        System.out.println(TransactionSynchronizationManager.getCurrentTransactionName());
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
