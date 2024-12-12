package org.example;

import org.example.security.UserAuth;
import org.example.ui.Menu;

public class Main {

    public static String PATH_EXPORT = "C:\\Users\\ajolo\\OneDrive\\Documentos\\NetBeansProjects\\proyecto1";
    public static String PATH_IMPORT = "C:\\Users\\ajolo\\OneDrive\\Documentos\\NetBeansProjects\\proyecto1\\products.txt";

    public static void main(String[] args) {

        UserAuth userAuth = new UserAuth();
        userAuth.login();

        Menu menu = new Menu();
        menu.displayMenu();


    }
}