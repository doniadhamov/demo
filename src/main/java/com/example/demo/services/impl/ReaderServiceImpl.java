package com.example.demo.services.impl;

import com.example.demo.domains.Reader;
import com.example.demo.repositories.ReaderRepository;
import com.example.demo.services.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReaderServiceImpl implements ReaderService {

    @Autowired
    ReaderRepository readerRepository;

    @Override
    public DataTablesOutput<Reader> findAllReadersForDataTable(DataTablesInput input) {
        return readerRepository.findAll(input);
    }

    @Override
    public Reader findOne(Long id) {
        return readerRepository.findOne(id);
    }

    @Override
    public void create(Reader reader) {
        readerRepository.saveAndFlush(reader);
    }

    @Override
    public void update(Reader reader) {
        Reader oldReader = readerRepository.findOne(reader.getId());
        oldReader.merge(reader);
        readerRepository.saveAndFlush(oldReader);
    }

    @Override
    public void delete(Long id) {
        readerRepository.delete(id);
    }

    @Override
    public boolean exists(Reader reader) {
        return reader != null && reader.getId() != null;
    }

    @Override
    public boolean existsInDatabase(Long id) {
        return readerRepository.exists(id);
    }
}
