package com.example.demo.repositories;

import com.example.demo.domains.User;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends DataTablesRepository<User, Long>, JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN FETCH u.userRoles WHERE u.username = (:username)")
    User findByUsernameAndFetchRolesEarly(@Param("username") String username);
}
