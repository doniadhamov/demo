package com.example.demo.services.impl;

import com.example.demo.domains.Category;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public DataTablesOutput<Category> findAllCategoriesForDataTable(DataTablesInput input) {
        return categoryRepository.findAll(input);
    }

    @Override
    public Category findOne(Long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public void create(Category Category) {
        categoryRepository.saveAndFlush(Category);
    }

    @Override
    public void update(Category category) {
        Category oldCategory = categoryRepository.findOne(category.getId());
        oldCategory.merge(category);
        categoryRepository.saveAndFlush(oldCategory);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.delete(id);
    }

    @Override
    public boolean exists(Category category) {
        return category != null && category.getId() != null;
    }

    @Override
    public boolean existsInDatabase(Long id) {
        return categoryRepository.exists(id);
    }
}
