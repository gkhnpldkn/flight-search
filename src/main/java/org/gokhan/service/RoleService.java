package org.gokhan.service;

import org.gokhan.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAllByIds(List<String> ids);
}
