package com.example.demo.controllers;

import com.example.demo.constants.CommonMessages;
import com.example.demo.constants.ProjectTemplates;
import com.example.demo.constants.ProjectUrls;
import com.example.demo.domains.Translation;
import com.example.demo.services.TranslationService;
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
import java.util.List;

@Controller
public class TranslationController implements MessageSourceAware {

    @Autowired
    private TranslationService translationService;

    private MessageSource messageSource;

    @RequestMapping(ProjectUrls.AdminTranslationsList)
    public String getTranslationsList(Model model) {
        model.addAttribute("breadcrumb", getBreadcrumbForTradePoints("label.list"));
        model.addAttribute("url_add", ProjectUrls.AdminTranslationsEdit);
        model.addAttribute("url_edit", ProjectUrls.AdminTranslationsEdit);
        model.addAttribute("url_delete", ProjectUrls.AdminTranslationsDelete);
        model.addAttribute("url_ajax", ProjectUrls.AdminTranslationsListDataTable);
        return ProjectTemplates.AdminTranslationsList;
    }

    @RequestMapping(value = ProjectUrls.AdminTranslationsListDataTable, produces = "application/json")
    @ResponseBody
    public DataTablesOutput<Translation> getTranslationsAjaxList(@Valid DataTablesInput input) {
        return translationService.findAllTranslationsForDataTable(input);
    }

    @RequestMapping(ProjectUrls.AdminTranslationsEdit)
    public String editTranslation(
            Model model,
            RedirectAttributes redirectAttributes,
            @RequestParam(name = "id", required = false) Long id
    ) {
        Translation translation = null;
        if (id == null) {
            translation = new Translation();
            model.addAttribute("breadcrumb", getBreadcrumbForTradePoints("action.add"));
        } else {
            translation = translationService.findOne(id);
            model.addAttribute("breadcrumb", getBreadcrumbForTradePoints("action.edit"));
        }
        if (translation == null) {
            ToastrNotificationUtils.addWarning(redirectAttributes, messageSource.getMessage(CommonMessages.msgObjectNotFound, null, LocaleContextHolder.getLocale()));
            return "redirect:" + ProjectUrls.AdminTranslationsList;
        }

        model.addAttribute("translation", translation);
        model.addAttribute("url_action", ProjectUrls.AdminTranslationsSave);
        model.addAttribute("url_cancel", ProjectUrls.AdminTranslationsList);
        return ProjectTemplates.AdminTranslationsEdit;
    }

    @RequestMapping(ProjectUrls.AdminTranslationsSave)
    public String updateTranslation(
            RedirectAttributes model,
            Translation translation
    ) {
        translation.setName(translation.getName().trim());
        translation.setUzbek(translation.getUzbek().trim());
        translation.setEnglish(translation.getEnglish().trim());
        translation.setRussian(translation.getRussian().trim());

        //Adding new translation
        if (!translationService.exists(translation)) {
            translationService.create(translation);
            ToastrNotificationUtils.addSuccess(model, messageSource.getMessage(CommonMessages.msgAddedSuccessfully, null, LocaleContextHolder.getLocale()));
        } else {
            translationService.update(translation);
            ToastrNotificationUtils.addSuccess(model, messageSource.getMessage(CommonMessages.msgSavedSuccessfully, null, LocaleContextHolder.getLocale()));
        }

        return "redirect:" + ProjectUrls.AdminTranslationsList;
    }

    @RequestMapping(ProjectUrls.AdminTranslationsDelete)
    public String deleteTranslation(
            @RequestParam(name = "id", required = true) Long id,
            RedirectAttributes model
    ) {
        if (translationService.existsInDatabase(id)) {
            translationService.delete(id);
            ToastrNotificationUtils.addSuccess(model, messageSource.getMessage(CommonMessages.msgDeletedSuccessfully, null, LocaleContextHolder.getLocale()));
        }
        return "redirect:" + ProjectUrls.AdminTranslationsList;
    }

    private Breadcrumb getBreadcrumbForTradePoints(String name) {
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.addLink("menu.translations", ProjectUrls.AdminTranslations);
        breadcrumb.addLink(name, "#");
        return breadcrumb;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
