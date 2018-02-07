package com.example.demo.controllers;

import com.example.demo.constants.ProjectTemplates;
import com.example.demo.constants.ProjectUrls;
import com.example.demo.dto.datatables.DataTable;
import com.example.demo.services.DataTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

@Controller
public class DashboardController {

    @Autowired
    DataTableService dataTableService;

    @RequestMapping(value = ProjectUrls.AdminDashboard)
    public String dashboard() {
        return ProjectTemplates.AdminDashboard;
    }

    // {doni} this url is used to translate datatable's words
    @RequestMapping(value = "/lang", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public DataTable list(Locale locale) throws Exception {
        return dataTableService.get(locale.getLanguage());
    }
}

