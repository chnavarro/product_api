package com.example.productapi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.productapi.dto.NewUserDTO;
import com.example.productapi.dto.UserDTO;
import com.example.productapi.exception.ResourceNotFoundException;
import com.example.productapi.model.User;
import com.example.productapi.repository.UserRepository;
import com.example.productapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cnavarro
 * @version 1.0
 * @since 2022-11-03
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserRepository repository;
    private ObjectMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Create and persist User
     * @param newUserDTO - DTO represent a new User
     * @return UserDTO
     */
    @Override
    public UserDTO create(NewUserDTO newUserDTO) {
        User user = new User();
        user.setUserName(newUserDTO.getUserName());
        user.setUserFullName(newUserDTO.getUserFullName());
        user.setUserPassword(passwordEncoder.encode(newUserDTO.getUserPassword()));
        if (newUserDTO.getUserRole() != null) {
            user.setUserRole(User.UserRole.valueOf(newUserDTO.getUserRole()));
        }
        if (newUserDTO.getUserStatus() != null) {
            user.setUserStatus(newUserDTO.getUserStatus());
        }
        user = repository.save(user);
        return mapper.convertValue(user, UserDTO.class);
    }

    /**
     * Update a User
     * @param newUserDTO - DTO represent a Use
     * @param userName - User name (Id)
     * @return UserDTO
     */
    @Override
    @Transactional
    public UserDTO update(NewUserDTO newUserDTO, String userName) {
        User user = repository.findByUserName(userName).orElseThrow(
                () -> new ResourceNotFoundException("User not found"));
        user.setUserFullName(newUserDTO.getUserFullName());
        user.setUserPassword(passwordEncoder.encode(newUserDTO.getUserPassword()));
        if (newUserDTO.getUserRole() != null) {
            user.setUserRole(User.UserRole.valueOf(newUserDTO.getUserRole()));
        }
        if (newUserDTO.getUserStatus() != null) {
            user.setUserStatus(newUserDTO.getUserStatus());
        }
        user = repository.save(user);
        return mapper.convertValue(user, UserDTO.class);
    }

    /**
     * Delete a User
     * @param userName - User name (Id)
     */
    @Override
    @Transactional
    public void delete(String userName) {
        User user = repository.findByUserName(userName).orElseThrow(
                () -> new ResourceNotFoundException("User not found"));
        repository.delete(user);
    }

    /**
     * List all users
     * @return - List<userDTO>
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAll() {
        List<User> users = repository.findAll();
        return users.stream().map(user -> mapper.convertValue(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Find a user by Name (Id)
     * @param userName - User name (Id)
     * @return UserDTO
     */
    @Override
    @Transactional(readOnly = true)
    public UserDTO findByUserName(String userName) {
        User user = repository.findByUserName(userName).orElseThrow(
                () -> new ResourceNotFoundException("User not found"));
        return mapper.convertValue(user, UserDTO.class);
    }
}
