package com.example.demo.repositories;

import com.example.demo.domains.Translation;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslationRepository extends DataTablesRepository<Translation, Long>, JpaRepository<Translation, Long> {

    Translation findByName(String name);
}
