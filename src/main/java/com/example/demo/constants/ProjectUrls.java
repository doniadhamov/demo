package com.example.demo.constants;

public class ProjectUrls {

    public static final String Login                    = "/login";
    public static final String Logout                   = "/logout";
    public static final String ErrorPage403             = "/403";
    public static final String ErrorPage404             = "/404";
    public static final String ErrorPage500             = "/500";

    // ADMIN
    public static final String Admin                    = "/admin";
    public static final String AdminDashboard           = Admin + "/dashboard";

    // Users
    public static final String AdminUsers               = Admin + "/users";
    public static final String AdminUsersList           = AdminUsers + "/list";
    public static final String AdminUsersListDataTable  = AdminUsers + "/list_datatable";
    public static final String AdminUsersCreate         = AdminUsers + "/create";
    public static final String AdminUsersEdit           = AdminUsers + "/edit";
    public static final String AdminUsersDelete         = AdminUsers + "/delete";
    public static final String AdminUsersView           = AdminUsers + "/view";
    public static final String AdminUsersSave           = AdminUsers + "/save";

    //Translations
    public static final String AdminTranslations        = Admin + "/translations";
    public static final String AdminTranslationsList    = AdminTranslations + "/list";
    public static final String AdminTranslationsListDataTable = AdminTranslations + "/list_datatable";
    public static final String AdminTranslationsEdit    = AdminTranslations + "/edit";
    public static final String AdminTranslationsSave    = AdminTranslations + "/save";
    public static final String AdminTranslationsDelete  = AdminTranslations + "/delete";

    // USER
    public static final String User                     = "/user";

    //Categories
    public static final String UserCategories           = User + "/categories";
    public static final String UserCategoriesList       = UserCategories + "/list";
    public static final String UserCategoriesListDataTable = UserCategories + "/list_datatable";
    public static final String UserCategoriesEdit       = UserCategories + "/edit";
    public static final String UserCategoriesDelete     = UserCategories + "/delete";
    public static final String UserCategoriesSave       = UserCategories + "/save";

    //Books
    public static final String UserBooks           = User + "/books";
    public static final String UserBooksList       = UserBooks + "/list";
    public static final String UserBooksListDataTable = UserBooks + "/list_datatable";
    public static final String UserBooksEdit       = UserBooks + "/edit";
    public static final String UserBooksDelete     = UserBooks + "/delete";
    public static final String UserBooksSave       = UserBooks + "/save";

    //Readers
    public static final String UserReaders           = User + "/readers";
    public static final String UserReadersList       = UserReaders + "/list";
    public static final String UserReadersListDataTable = UserReaders + "/list_datatable";
    public static final String UserReadersEdit       = UserReaders + "/edit";
    public static final String UserReadersDelete     = UserReaders + "/delete";
    public static final String UserReadersSave       = UserReaders + "/save";
}
