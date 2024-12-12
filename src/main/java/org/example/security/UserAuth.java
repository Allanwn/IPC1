package org.example.security;

import java.util.Scanner;

public class UserAuth {

    private  String username;
    private  String password;
    private final String CARNET = "201930494";

    public UserAuth() {
        this.username = "sensei_"+CARNET;
        this.password = "ipc1_"+CARNET;
    }

    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public boolean login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== SISTEMA DE AUTENTICACION COBRA KAI ===");
        System.out.println("===========================================");

        while (true) {
            System.out.print("Ingrese usuario: ");
            String username = scanner.nextLine();

            System.out.print("Ingrese contrasena: ");
            String password = scanner.nextLine();

            if (authenticate(username, password)) {
                System.out.println("Acceso concedido! Bienvenido al sistema, Sensei.");
                return true;
            } else {
                System.out.println("Error: Usuario o contrasena incorrectos. Intente nuevamente.");
            }
        }
    }
}
