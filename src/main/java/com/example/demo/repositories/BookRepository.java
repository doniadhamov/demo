package com.example.demo.repositories;

import com.example.demo.domains.Book;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends DataTablesRepository<Book, Long>, JpaRepository<Book, Long> {
}
