package com.example.demo.services;

import com.example.demo.domains.Reader;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

public interface ReaderService {

    DataTablesOutput<Reader> findAllReadersForDataTable(DataTablesInput input);

    Reader findOne(Long id);

    void create(Reader reader);

    void update(Reader reader);

    void delete(Long id);

    boolean exists(Reader reader);

    boolean existsInDatabase(Long id);
}
