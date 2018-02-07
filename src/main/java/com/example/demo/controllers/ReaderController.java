package com.example.demo.controllers;

import com.example.demo.constants.CommonMessages;
import com.example.demo.constants.ProjectTemplates;
import com.example.demo.constants.ProjectUrls;
import com.example.demo.domains.Reader;
import com.example.demo.services.ReaderService;
import com.example.demo.utils.Breadcrumb;
import com.example.demo.utils.ToastrNotificationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ReaderController implements MessageSourceAware {

    @Autowired
    ReaderService readerService;

    private MessageSource messageSource;

    @RequestMapping(ProjectUrls.UserReadersList)
    public String getreadersList(Model model) {
        model.addAttribute("breadcrumb", getBreadcrumbForTradePoints("label.list"));
        model.addAttribute("url_add", ProjectUrls.UserReadersEdit);
        model.addAttribute("url_edit", ProjectUrls.UserReadersEdit);
        model.addAttribute("url_delete", ProjectUrls.UserReadersDelete);
        model.addAttribute("url_ajax", ProjectUrls.UserReadersListDataTable);
        return ProjectTemplates.UserReadersList;
    }

    @RequestMapping(value = ProjectUrls.UserReadersListDataTable, produces = "application/json")
    @ResponseBody
    public DataTablesOutput<Reader> getreadersAjaxList(@Valid DataTablesInput input) {
        return readerService.findAllReadersForDataTable(input);
    }

    @RequestMapping(ProjectUrls.UserReadersEdit)
    public String editReader(
            Model model,
            RedirectAttributes redirectAttributes,
            @RequestParam(name = "id", required = false) Long id
    ) {
        Reader reader = null;
        if (id == null) {
            reader = new Reader();
            model.addAttribute("breadcrumb", getBreadcrumbForTradePoints("action.add"));
        } else {
            reader = readerService.findOne(id);
            model.addAttribute("breadcrumb", getBreadcrumbForTradePoints("action.edit"));
        }
        if (reader == null) {
            ToastrNotificationUtils.addWarning(redirectAttributes, messageSource.getMessage(CommonMessages.msgObjectNotFound, null, LocaleContextHolder.getLocale()));
            return "redirect:" + ProjectUrls.UserReadersList;
        }

        model.addAttribute("reader", reader);
        model.addAttribute("url_action", ProjectUrls.UserReadersSave);
        model.addAttribute("url_cancel", ProjectUrls.UserReadersList);
        return ProjectTemplates.UserReadersEdit;
    }

    @RequestMapping(ProjectUrls.UserReadersSave)
    public String updateReader(
            RedirectAttributes model,
            Reader reader
    ) {
        if (!readerService.exists(reader)) {
            readerService.create(reader);
            ToastrNotificationUtils.addSuccess(model, messageSource.getMessage(CommonMessages.msgAddedSuccessfully, null, LocaleContextHolder.getLocale()));
        } else {
            readerService.update(reader);
            ToastrNotificationUtils.addSuccess(model, messageSource.getMessage(CommonMessages.msgSavedSuccessfully, null, LocaleContextHolder.getLocale()));
        }

        return "redirect:" + ProjectUrls.UserReadersList;
    }

    @RequestMapping(ProjectUrls.UserReadersDelete)
    public String deleteReader(
            @RequestParam(name = "id", required = true) Long id,
            RedirectAttributes model
    ) {
        if (readerService.existsInDatabase(id)) {
            readerService.delete(id);
            ToastrNotificationUtils.addSuccess(model, messageSource.getMessage(CommonMessages.msgDeletedSuccessfully, null, LocaleContextHolder.getLocale()));
        }
        return "redirect:" + ProjectUrls.UserReadersList;
    }

    private Breadcrumb getBreadcrumbForTradePoints(String name) {
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.addLink("menu.readers", ProjectUrls.UserReadersList);
        breadcrumb.addLink(name, "#");
        return breadcrumb;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
