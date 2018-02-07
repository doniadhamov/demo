package com.example.demo.services.impl;

import com.example.demo.domains.Book;
import com.example.demo.repositories.BookRepository;
import com.example.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public DataTablesOutput<Book> findAllBooksForDataTable(DataTablesInput input) {
        return bookRepository.findAll(input);
    }

    @Override
    public Book findOne(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public void create(Book book) {
        //{doni} when we uncheck, it will send null value to inLibrary field, so need to assign false to null value
        if (book.getInLibrary() == null) {
            book.setInLibrary(Boolean.FALSE);
        } else {
            book.setInLibrary(Boolean.TRUE);
        }
        bookRepository.saveAndFlush(book);
    }

    @Override
    public void update(Book book) {
        //{doni} when we uncheck, it will send null value to inLibrary field, so need to assign false to null value
        if (book.getInLibrary() == null) {
            book.setInLibrary(Boolean.FALSE);
        } else {
            book.setInLibrary(Boolean.TRUE);
        }
        Book oldBook = bookRepository.findOne(book.getId());
        oldBook.merge(book);
        bookRepository.saveAndFlush(oldBook);
    }

    @Override
    public void delete(Long id) {
        bookRepository.delete(id);
    }

    @Override
    public boolean exists(Book book) {
        return book != null && book.getId() != null;
    }

    @Override
    public boolean existsInDatabase(Long id) {
        return bookRepository.exists(id);
    }
}
