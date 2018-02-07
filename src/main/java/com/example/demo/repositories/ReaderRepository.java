package com.example.demo.repositories;

import com.example.demo.domains.Reader;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends DataTablesRepository<Reader, Long>, JpaRepository<Reader, Long> {
}
