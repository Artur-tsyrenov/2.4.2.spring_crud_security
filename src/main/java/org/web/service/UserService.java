package org.web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.web.models.Role;
import org.web.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> listUsers();

    void save(User user);

    User read(int id);

    void update(int id, User user);

    void delete(int id);

    User getUserByUsername(String username);

    List<Role> findAllRoles();
}
