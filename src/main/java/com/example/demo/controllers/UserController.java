package com.example.demo.controllers;

import com.example.demo.constants.*;
import com.example.demo.domains.User;
import com.example.demo.domains.UserRole;
import com.example.demo.services.UserRoleService;
import com.example.demo.services.UserService;
import com.example.demo.utils.Breadcrumb;
import com.example.demo.utils.ToastrNotificationUtils;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController implements MessageSourceAware {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    private MessageSource messageSource;

    @RequestMapping(value = ProjectUrls.AdminUsersList)
    public String list(Model model) {

        model.addAttribute("breadcrumb", getBreadcrumbForUsers("label.list"));
        model.addAttribute("url_edit", ProjectUrls.AdminUsersEdit);
        model.addAttribute("url_view", ProjectUrls.AdminUsersView);
        model.addAttribute("url_ajax", ProjectUrls.AdminUsersListDataTable);
        model.addAttribute("url_add", ProjectUrls.AdminUsersCreate);
        model.addAttribute("url_delete", ProjectUrls.AdminUsersDelete);

        return ProjectTemplates.AdminUsersList;
    }

    @JsonView(DataTablesOutput.View.class)
    @RequestMapping(value = ProjectUrls.AdminUsersListDataTable, method = RequestMethod.GET)
    @ResponseBody
    public DataTablesOutput<User> listInDataTables(@Valid DataTablesInput input) {
        return userService.getUserListInDataTableOutput(input);
    }

    @RequestMapping(value = ProjectUrls.AdminUsersCreate, method = RequestMethod.GET)
    public String create(Model model) {

        model.addAttribute("user", new User());
        model.addAttribute("roles", Role.values());
        model.addAttribute("breadcrumb", getBreadcrumbForUsers("action.add"));
        model.addAttribute("url_action", ProjectUrls.AdminUsersSave);
        model.addAttribute("url_cancel", ProjectUrls.AdminUsersList);

        return ProjectTemplates.AdminUsersEdit;
    }

    @RequestMapping(value = ProjectUrls.AdminUsersEdit, method = RequestMethod.GET)
    public String edit(@RequestParam(name = "id", required = true) Long id, Model model) {

        User user = userService.findOne(id);
        if (user == null) {
            // object not found
            // TODO : must be add notification
            return "redirect:" + ProjectUrls.AdminUsersList;
        }

        UserRole userRole = userRoleService.findByUserId(id);
        user.setRoleName(userRole.getRole().toString());

        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("breadcrumb", getBreadcrumbForUsers("action.edit"));
        model.addAttribute("url_action", ProjectUrls.AdminUsersSave);
        model.addAttribute("url_cancel", ProjectUrls.AdminUsersList);

        return ProjectTemplates.AdminUsersEdit;
    }

    @RequestMapping(value = ProjectUrls.AdminUsersSave, method = RequestMethod.POST)
    public String save(
            User user,
            @RequestParam("password1") String password1,
            @RequestParam("password2") String password2,
            RedirectAttributes redirectAttributes
    ) {

        // TODO : must be add notification
        if (!password1.isEmpty() && password1.equals(password2)) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(password1));
        }
        Boolean operation = userService.createOrUpdate(user);
        if (operation == Boolean.TRUE) {
            // success save
            ToastrNotificationUtils.addSuccess(redirectAttributes, messageSource.getMessage(CommonMessages.msgAddedSuccessfully, null, LocaleContextHolder.getLocale()));
        } else {
            // error
//            ToastrNotificationUtils.addSuccess(model, messageSource.getMessage(CommonMessages.msgSavedSuccessfully, null, LocaleContextHolder.getLocale()));
        }

        return "redirect:" + ProjectUrls.AdminUsersList;
    }

    @RequestMapping(value = ProjectUrls.AdminUsersView, method = RequestMethod.GET)
    public String view(
            @RequestParam(name = "id", required = false) Long id,
            Model model
    ) {

        User user = userService.findOne(id);
        if (user == null) {
            // object not found
            // TODO : must be add notification
            return "redirect:" + ProjectUrls.AdminUsersList;
        }

        model.addAttribute("breadcrumb", getBreadcrumbForUsers("action.view"));
        model.addAttribute("user", user);

        return ProjectTemplates.AdminUsersView;
    }

    @RequestMapping(value = ProjectUrls.AdminUsersDelete)
    public String delete(
            @RequestParam(name = "id", required = true) Long id,
            RedirectAttributes redirectAttributes
    ) {

        User user = userService.findOne(id);
        if (user == null) {
            // not found
            ToastrNotificationUtils.addWarning(redirectAttributes, messageSource.getMessage(CommonMessages.msgObjectNotFound, null, LocaleContextHolder.getLocale()));
            return "redirect:" + ProjectUrls.AdminUsersList;
        }
        userService.delete(user);
        ToastrNotificationUtils.addSuccess(redirectAttributes, messageSource.getMessage(CommonMessages.msgDeletedSuccessfully, null, LocaleContextHolder.getLocale()));

        return "redirect:" + ProjectUrls.AdminUsersList;
    }

    /*
        User uchun ohirgi param beriladi (list, view, edit, create)
     */
    public Breadcrumb getBreadcrumbForUsers(String name) {
        Breadcrumb breadcrumb = new Breadcrumb();
        breadcrumb.addLink("menu.users", ProjectUrls.AdminUsersList);
        breadcrumb.addLink(name, "#");
        return breadcrumb;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
