package com.example.demo.controllers;

import com.example.demo.constants.CommonMessages;
import com.example.demo.constants.ProjectTemplates;
import com.example.demo.constants.ProjectUrls;
import com.example.demo.domains.Book;
import com.example.demo.services.BookService;
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
public class BookController implements MessageSourceAware {

    @Autowired
    BookService bookService;
    @Autowired
    CategoryService categoryService;

    private MessageSource messageSource;

    @RequestMapping(ProjectUrls.UserBooksList)
    public String getBooksList(Model model) {
        model.addAttribute("breadcrumb", getBreadcrumbForTradePoints("label.list"));
        model.addAttribute("url_add", ProjectUrls.UserBooksEdit);
        model.addAttribute("url_edit", ProjectUrls.UserBooksEdit);
        model.addAttribute("url_delete", ProjectUrls.UserBooksDelete);
        model.addAttribute("url_ajax", ProjectUrls.UserBooksListDataTable);
        return ProjectTemplates.UserBooksList;
    }

    @RequestMapping(value = ProjectUrls.UserBooksListDataTable, produces = "application/json")
    @ResponseBody
    public DataTablesOutput<Book> getBooksAjaxList(@Valid DataTablesInput input) {
        return bookService.findAllBooksForDataTable(input);
    }

    @RequestMapping(ProjectUrls.UserBooksEdit)
    public String editBook(
            Model model,
            RedirectAttributes redirectAttributes,
            @RequestParam(name = "id", required = false) Long id
    ) {
        Book book = null;
        if (id == null) {
            book = new Book();
            model.addAttribute("breadcrumb", getBreadcrumbForTradePoints("action.add"));
        } else {
            book = bookService.findOne(id);
            model.addAttribute("breadcrumb", getBreadcrumbForTradePoints("action.edit"));
        }
        if (book == null) {
            ToastrNotificationUtils.addWarning(redirectAttributes, messageSource.getMessage(CommonMessages.msgObjectNotFound, null, LocaleContextHolder.getLocale()));
            return "redirect:" + ProjectUrls.UserBooksList;
        }

        model.addAttribute("book", book);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("url_action", ProjectUrls.UserBooksSave);
        model.addAttribute("url_cancel", ProjectUrls.UserBooksList);
        return ProjectTemplates.UserBooksEdit;
    }

    @RequestMapping(ProjectUrls.UserBooksSave)
    public String updateBook(
            RedirectAttributes redirectAttributes,
            Book book
    ) {
        if (!bookService.exists(book)) {
            bookService.create(book);
            ToastrNotificationUtils.addSuccess(redirectAttributes, messageSource.getMessage(CommonMessages.msgAddedSuccessfully, null, LocaleContextHolder.getLocale()));
        } else {
            bookService.update(book);
            ToastrNotificationUtils.addSuccess(redirectAttributes, messageSource.getMessage(CommonMessages.msgSavedSuccessfully, null, LocaleContextHolder.getLocale()));
        }
        return "redirect:" + ProjectUrls.UserBooksList;
    }

    @RequestMapping(ProjectUrls.UserBooksDelete)
    public String deleteBook(
            @RequestParam(name = "id", required = true) Long id,
            RedirectAttributes redirectAttributes
    ) {
        if (bookService.existsInDatabase(id)) {
            bookService.delete(id);
            ToastrNotificationUtils.addSuccess(redirectAttributes, messageSource.getMessage(CommonMessages.msgDeletedSuccessfully, null, LocaleContextHolder.getLocale()));
        }
        return "redirect:" + ProjectUrls.UserBooksList;
    }

    private Breadcrumb getBreadcrumbForTradePoints(String name) {
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.addLink("menu.books", ProjectUrls.UserBooksList);
        breadcrumb.addLink(name, "#");
        return breadcrumb;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
