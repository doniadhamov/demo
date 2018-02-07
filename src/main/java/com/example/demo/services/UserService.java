package com.example.demo.services;

import com.example.demo.domains.User;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface UserService {

    User findOne(Long id);

    DataTablesOutput<User> getUserListInDataTableOutput(DataTablesInput input);

    Boolean createOrUpdate(User user);

    void delete(User user);
}
