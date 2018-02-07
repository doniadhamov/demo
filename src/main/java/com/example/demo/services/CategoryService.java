package com.example.demo.services;

import com.example.demo.domains.Category;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    DataTablesOutput<Category> findAllCategoriesForDataTable(DataTablesInput input);

    Category findOne(Long id);

    void create(Category category);

    void update(Category category);

    void delete(Long id);

    boolean exists(Category category);

    boolean existsInDatabase(Long id);
}
