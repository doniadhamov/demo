package com.example.demo.services;

import com.example.demo.domains.Translation;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

public interface TranslationService {

    DataTablesOutput<Translation> findAllTranslationsForDataTable(DataTablesInput input);

    Translation findOne(Long id);

    void create(Translation translation);

    void update(Translation translation);

    void delete(Long id);

    boolean exists(Translation translation);

    boolean existsInDatabase(Long id);

    Translation findByName(String name);
}
