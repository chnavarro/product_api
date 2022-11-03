package com.tizo.productapi.service;

import com.tizo.productapi.dto.NewUserDTO;
import com.tizo.productapi.dto.UserDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cnavarro
 * @version 1.0
 * @since 2022-11-03
 */
public interface UserService {

    @Transactional
    UserDTO create(NewUserDTO newUserDTO);

    @Transactional
    UserDTO update(NewUserDTO newUserDTO, String userName);

    @Transactional
    void delete(String userName);

    @Transactional(readOnly = true)
    List<UserDTO> getAll();

    @Transactional(readOnly = true)
    UserDTO findByUserName(String userName);
}
