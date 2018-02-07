package com.example.demo.services;

import com.example.demo.domains.UserRole;

public interface UserRoleService {

    UserRole findByUserId(Long userId);
}
