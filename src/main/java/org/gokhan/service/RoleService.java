package org.gokhan.service;

import org.gokhan.model.Role;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleService {

    List<Role> findByRoleIn(List<String> roles);
}
