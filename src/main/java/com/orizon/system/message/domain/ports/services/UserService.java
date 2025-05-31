package com.orizon.system.message.domain.ports.services;

import com.orizon.system.message.domain.model.User;

public interface UserService {

    void create(String username, String password);

    void delete(Long id);

    void findAll();

    User login(String username, String password);

    User findById(Long id);

}