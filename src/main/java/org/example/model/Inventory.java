package org.example.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Inventory {
    private Product[] products;
    private int size;
    private final int MAX_PRODUCTS = 100;

    public Inventory() {
        products = new Product[MAX_PRODUCTS];
        size = 0;
    }

    public boolean addProduct(String name, double price) {
        if (price <= 0 || productExists(name)) {
            return false;
        }

        if (size < MAX_PRODUCTS) {
            products[size++] = new Product(name, price);
            return true;
        } else {
            System.out.println("Error: El inventario está lleno.");
            return false;
        }
    }

    public boolean productExists(String name) {
        for (int i = 0; i < size; i++) {
            if (products[i].getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public Product getProductByName(String name) {
        for (int i = 0; i < size; i++) {
            if (products[i].getName().equalsIgnoreCase(name)) {
                return products[i];
            }
        }
        return null;
    }

    public Product[] getAllProducts() {
        Product[] currentProducts = new Product[size];
        for (int i = 0; i < size; i++) {
            currentProducts[i] = products[i];
        }
        return currentProducts;
    }
    public void loadProductFromFile(String filePath) {
        if(filePath == null || filePath.isEmpty()) {
            System.out.println("!!! Debe enviar una ruta");
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] parts = line.split(";");
                if (parts.length == 2) {
                    String productName = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());

                    if (price > 0 && !productExists(productName)) {
                        addProduct(productName, price);
                    } else {
                        System.out.println("Producto ignorado: " + productName + " (Precio inválido o ya existe)");
                    }
                } else {
                    System.out.println("Línea malformada: " + line);
                }
            }
            System.out.println("Carga masiva completada.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Precio no válido en el archivo.");
        }
    }

}

