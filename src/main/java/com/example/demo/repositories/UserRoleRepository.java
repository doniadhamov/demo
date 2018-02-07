package com.example.demo.repositories;

import com.example.demo.domains.UserRole;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends DataTablesRepository<UserRole, Long>, JpaRepository<UserRole, Long> {

    UserRole findByUserId(Long id);
}
