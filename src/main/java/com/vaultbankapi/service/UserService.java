package com.vaultbankapi.service;

import com.vaultbankapi.model.User;
import com.vaultbankapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Criar usuário
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email já cadastrado!");
        }
        return userRepository.save(user);
    }

    // Listar todos
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Buscar por ID
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
