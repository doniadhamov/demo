package com.example.demo.controllers;

import com.example.demo.constants.CommonMessages;
import com.example.demo.constants.ProjectTemplates;
import com.example.demo.constants.ProjectUrls;
import com.example.demo.domains.Category;
import com.example.demo.services.CategoryService;
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
public class CategoryController implements MessageSourceAware {

    @Autowired
    CategoryService categoryService;

    private MessageSource messageSource;

    @RequestMapping(ProjectUrls.UserCategoriesList)
    public String getCategoriesList(Model model) {
        model.addAttribute("breadcrumb", getBreadcrumbForTradePoints("label.list"));
        model.addAttribute("url_add", ProjectUrls.UserCategoriesEdit);
        model.addAttribute("url_edit", ProjectUrls.UserCategoriesEdit);
        model.addAttribute("url_delete", ProjectUrls.UserCategoriesDelete);
        model.addAttribute("url_ajax", ProjectUrls.UserCategoriesListDataTable);
        return ProjectTemplates.UserCategoriesList;
    }

    @RequestMapping(value = ProjectUrls.UserCategoriesListDataTable, produces = "application/json")
    @ResponseBody
    public DataTablesOutput<Category> getCategoriesAjaxList(@Valid DataTablesInput input) {
        return categoryService.findAllCategoriesForDataTable(input);
    }

    @RequestMapping(ProjectUrls.UserCategoriesEdit)
    public String editCategory(
            Model model,
            RedirectAttributes redirectAttributes,
            @RequestParam(name = "id", required = false) Long id
    ) {
        Category category = null;
        if (id == null) {
            category = new Category();
            model.addAttribute("breadcrumb", getBreadcrumbForTradePoints("action.add"));
        } else {
            category = categoryService.findOne(id);
            model.addAttribute("breadcrumb", getBreadcrumbForTradePoints("action.edit"));
        }
        if (category == null) {
            ToastrNotificationUtils.addWarning(redirectAttributes, messageSource.getMessage(CommonMessages.msgObjectNotFound, null, LocaleContextHolder.getLocale()));
            return "redirect:" + ProjectUrls.UserCategoriesList;
        }

        model.addAttribute("category", category);
        model.addAttribute("url_action", ProjectUrls.UserCategoriesSave);
        model.addAttribute("url_cancel", ProjectUrls.UserCategoriesList);
        return ProjectTemplates.UserCategoriesEdit;
    }

    @RequestMapping(ProjectUrls.UserCategoriesSave)
    public String updateCategory(
            RedirectAttributes model,
            Category category
    ) {
        if (!categoryService.exists(category)) {
            categoryService.create(category);
            ToastrNotificationUtils.addSuccess(model, messageSource.getMessage(CommonMessages.msgAddedSuccessfully, null, LocaleContextHolder.getLocale()));
        } else {
            categoryService.update(category);
            ToastrNotificationUtils.addSuccess(model, messageSource.getMessage(CommonMessages.msgSavedSuccessfully, null, LocaleContextHolder.getLocale()));
        }

        return "redirect:" + ProjectUrls.UserCategoriesList;
    }

    @RequestMapping(ProjectUrls.UserCategoriesDelete)
    public String deleteCategory(
            @RequestParam(name = "id", required = true) Long id,
            RedirectAttributes model
    ) {
        if (categoryService.existsInDatabase(id)) {
            categoryService.delete(id);
            ToastrNotificationUtils.addSuccess(model, messageSource.getMessage(CommonMessages.msgDeletedSuccessfully, null, LocaleContextHolder.getLocale()));
        }
        return "redirect:" + ProjectUrls.UserCategoriesList;
    }

    private Breadcrumb getBreadcrumbForTradePoints(String name) {
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.addLink("menu.categories", ProjectUrls.UserCategoriesList);
        breadcrumb.addLink(name, "#");
        return breadcrumb;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
