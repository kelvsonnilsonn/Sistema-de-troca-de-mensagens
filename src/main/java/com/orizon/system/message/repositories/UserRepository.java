package com.orizon.system.message.repositories;

import com.orizon.system.message.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username.username = :username AND u.password.pass = :password")
    Optional<User> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}