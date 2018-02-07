package com.example.demo.services;

import com.example.demo.domains.Book;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

public interface BookService {

    DataTablesOutput<Book> findAllBooksForDataTable(DataTablesInput input);

    Book findOne(Long id);

    void create(Book book);

    void update(Book book);

    void delete(Long id);

    boolean exists(Book book);

    boolean existsInDatabase(Long id);
}
