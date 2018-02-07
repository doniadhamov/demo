package com.example.demo.services.impl;

import com.example.demo.domains.UserRole;
import com.example.demo.repositories.UserRoleRepository;
import com.example.demo.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public UserRole findByUserId(Long userId) {
        return userRoleRepository.findByUserId(userId);
    }
}
