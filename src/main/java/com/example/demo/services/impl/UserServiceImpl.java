package com.example.demo.services.impl;

import com.example.demo.constants.Role;
import com.example.demo.domains.User;
import com.example.demo.domains.UserRole;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.UserRoleRepository;
import com.example.demo.services.UserService;
import com.example.demo.specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public DataTablesOutput<User> getUserListInDataTableOutput(DataTablesInput input) {
        return userRepository.findAll(input, UserSpecification.deletedFalseUsers());
    }

    @Override
    public Boolean createOrUpdate(User user) {
        Role existUserRole = getRole(user.getRoleName());
        if (user.exist()) {
            // update
            User old = userRepository.findOne(user.getId());
            old.merge(user);
            user = old;
            User existUser = userRepository.saveAndFlush(user);
            if (existUser != null) {
                UserRole userRole = userRoleRepository.findByUserId(existUser.getId());

                if (userRole != null) {
                    if (existUserRole != userRole.getRole()) {
                        userRole.setRole(existUserRole);
                        return userRoleRepository.saveAndFlush(userRole) != null;
                    }
                } else {
                    userRole.setRole(existUserRole);
                    userRole.setEnabled(Boolean.TRUE);
                    userRole.setUser(existUser);
                    return userRoleRepository.saveAndFlush(userRole) != null;
                }
            }
            return Boolean.FALSE;
        } else {
            // create
            User newUser = userRepository.saveAndFlush(user);
            if (newUser != null) {
                UserRole userRole = new UserRole();
                userRole.setRole(existUserRole);
                userRole.setEnabled(Boolean.TRUE);
                userRole.setUser(newUser);
                return userRoleRepository.save(userRole) != null;
            }
            return Boolean.FALSE;
        }
    }

    @Override
    public void delete(User user) {
        user.setEnabled(Boolean.FALSE);
        user.setDeleted(Boolean.TRUE);
        UserRole userRole = userRoleRepository.findByUserId(user.getId());
        userRepository.save(user);
        userRoleRepository.delete(userRole);
    }

    private Role getRole(String roleName) {
        for (Role role : Role.values()) {
            if (role.toString().equals(roleName)) {
                return role;
            }
        }
        return Role.USER;
    }
}
