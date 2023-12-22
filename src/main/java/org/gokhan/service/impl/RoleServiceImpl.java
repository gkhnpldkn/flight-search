package org.gokhan.service.impl;

import lombok.RequiredArgsConstructor;
import org.gokhan.model.Role;
import org.gokhan.repository.RoleRepository;
import org.gokhan.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> findAllByIds(List<String> ids) {
        return roleRepository.findAllById(ids);
    }
}
