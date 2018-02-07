package com.example.demo.services.impl;

import com.example.demo.domains.Translation;
import com.example.demo.repositories.TranslationRepository;
import com.example.demo.services.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TranslationServiceImpl implements TranslationService {

    @Autowired
    TranslationRepository translationRepository;

    @Override
    public DataTablesOutput<Translation> findAllTranslationsForDataTable(DataTablesInput input) {
        return translationRepository.findAll(input);
    }

    @Override
    public Translation findOne(Long id) {
        return translationRepository.findOne(id);
    }

    @Override
    public void create(Translation translation) {
        translationRepository.saveAndFlush(translation);
    }

    @Override
    public void update(Translation translation) {
        Translation oldTranslation = translationRepository.findOne(translation.getId());
        oldTranslation.merge(translation);
        translationRepository.saveAndFlush(oldTranslation);
    }

    @Override
    public void delete(Long id) {
        translationRepository.delete(id);
    }

    @Override
    public boolean exists(Translation translation) {
        return translation != null && translation.getId() != null;
    }

    @Override
    public boolean existsInDatabase(Long id) {
        return translationRepository.exists(id);
    }

    @Override
    public Translation findByName(String name) {
        return translationRepository.findByName(name);
    }
}
