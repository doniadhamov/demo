package com.example.demo.services;

import com.example.demo.dto.datatables.DataTable;

public interface DataTableService {

    DataTable get(String language);
}
