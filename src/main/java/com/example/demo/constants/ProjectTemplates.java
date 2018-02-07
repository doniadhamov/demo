package com.example.demo.constants;

public class ProjectTemplates {

    // ADMIN
    public static final String Admin                    = "admin";
    public static final String AdminDashboard           = Admin + "/dashboard";

    // Users
    public static final String AdminUsers               = Admin + "/users";
    public static final String AdminUsersList           = AdminUsers + "/list";
    public static final String AdminUsersEdit           = AdminUsers + "/edit";
    public static final String AdminUsersView           = AdminUsers + "/view";

    //Translations
    public static final String AdminTranslations        = Admin + "/translations";
    public static final String AdminTranslationsList    = AdminTranslations + "/list";
    public static final String AdminTranslationsEdit    = AdminTranslations + "/edit";

    // USER
    public static final String User                     = "user";

    //Categories
    public static final String UserCategories           = User + "/categories";
    public static final String UserCategoriesList       = UserCategories + "/list";
    public static final String UserCategoriesEdit       = UserCategories + "/edit";

    //Books
    public static final String UserBooks                = User + "/books";
    public static final String UserBooksList            = UserBooks + "/list";
    public static final String UserBooksEdit            = UserBooks + "/edit";

    //Readers
    public static final String UserReaders              = User + "/readers";
    public static final String UserReadersList          = UserReaders + "/list";
    public static final String UserReadersEdit          = UserReaders + "/edit";
}
