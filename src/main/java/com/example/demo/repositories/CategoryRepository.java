package com.example.demo.repositories;

import com.example.demo.domains.Category;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends DataTablesRepository<Category, Long>, JpaRepository<Category, Long> {
}
