package com.thejas.user_service.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thejas.user_service.model.User;
import com.thejas.user_service.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User createUser(User user) {
        return repo.save(user);
    }

    public Optional<User> getUser(Long id) {
        return repo.findById(id);
    }
}
