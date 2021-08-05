package org.web.service;

import org.web.models.User;

import java.util.List;

public interface UserService {
    List<User> listUsers();

    void save(User user);

    User read(int id);

    void update(int id, User user);


    void delete(int id);
}
