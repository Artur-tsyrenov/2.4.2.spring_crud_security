package org.web.dao;

import org.web.models.Role;

import java.util.List;

public interface RoleDao {
    List<Role> findAllRoles();
}
