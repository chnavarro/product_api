package com.tizo.productapi.controller;

import com.tizo.productapi.dto.NewUserDTO;
import com.tizo.productapi.dto.UserDTO;
import com.tizo.productapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author cnavarro
 * @version 1.0
 * @since 2022-11-03
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody @Valid NewUserDTO newUser) {
        UserDTO user = service.create(newUser);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody @Valid NewUserDTO newUser) {
        UserDTO user = service.update(newUser, newUser.getUserName());
        return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("{user_name}")
    public ResponseEntity<UserDTO> getById(@PathVariable("user_name") String userName) {
        UserDTO bagDTO = service.findByUserName(userName);
        return new ResponseEntity<>(bagDTO, HttpStatus.OK);
    }
}
