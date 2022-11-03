package com.tizo.productapi.repository;

import com.tizo.productapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author cnavarro
 * @version 1.0
 * @since 2022-11-03
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);

}
