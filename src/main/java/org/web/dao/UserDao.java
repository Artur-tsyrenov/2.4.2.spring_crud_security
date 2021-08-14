package org.web.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.web.models.User;

import java.util.List;

public interface UserDao {
    List<User> listUsers();

    void save(User user);

    User read(int id);

    void update(int id, User user);


    void delete(int id);

    UserDetails getUserByName(String username);

    User getUserByUsername(String username);
}
