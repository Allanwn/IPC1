package org.example.ui;

import org.example.Main;
import org.example.model.Inventory;
import org.example.model.Sale;
import org.example.model.Product;
import org.example.utils.HTMLGenerator;

import java.io.File;
import java.util.Scanner;

public class Menu {
    private final Inventory inventory;
    private final Sale[] salesHistory;
    private int saleCount;
    private final Scanner scanner;

    public Menu() {
        this.inventory = new Inventory();
        this.salesHistory = new Sale[100];
        this.saleCount = 0;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== MENU PRINCIPAL - COBRA KAI ===");
            System.out.println("1. Agregar producto individual");
            System.out.println("2. Carga masiva de productos");
            System.out.println("3. Realizar venta");
            System.out.println("4. Generar reporte Top 5 productos");
            System.out.println("5. Generar reporte historico de ventas");
            System.out.println("6. Salir");

            System.out.print("Seleccione una opcion: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    loadProduct();
                    break;
                case 3:
                    performSale();
                    break;
                case 4:
                    generateTop5Report();
                    break;
                case 5:
                    generateSalesHistoryReport();
                    break;
                case 6:
                    System.out.println("Saliendo del sistema. Gracias, Sensei!");
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        }
    }

    private void addProduct() {
        System.out.print("Ingrese el nombre del producto: ");
        String name = scanner.nextLine();

        System.out.print("Ingrese el precio del producto: ");
        double price = scanner.nextDouble();

        if (inventory.addProduct(name, price)) {
            System.out.println("Producto agregado exitosamente.");
        } else {
            System.out.println("Error: El producto ya existe o el precio no es valido.");
        }
    }

    private void performSale() {
        System.out.print("Nombre del cliente: ");
        String customerName = scanner.nextLine();

        System.out.print("NIT del cliente (C/F si no aplica): ");
        String customerNIT = scanner.nextLine();

        Sale sale = new Sale(customerName, customerNIT, 10);
        boolean buying = true;

        while (buying) {
            System.out.println("\n--- Productos Disponibles ---");
            Product[] products = inventory.getAllProducts();
            for (int i = 0; i < products.length; i++) {
                System.out.println((i + 1) + ". " + products[i].getName() + " - Q" + products[i].getPrice());
            }

            System.out.print("Seleccione el número del producto (0 para finalizar): ");
            int productOption = scanner.nextInt();
            if (productOption == 0) {
                buying = false;
                break;
            }

            System.out.print("Ingrese la cantidad a comprar: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            sale.addProduct(products[productOption - 1], quantity);
        }

        salesHistory[saleCount++] = sale;
        System.out.println("\nFactura:");
        sale.printInvoice();

        String pathExport = Main.PATH_EXPORT+ File.separator+ "factura_venta_" + saleCount + ".html";
        HTMLGenerator.generateInvoiceHTML(sale, pathExport);
    }

    private void generateTop5Report() {
        Product[] products = inventory.getAllProducts();
        String pathExport = Main.PATH_EXPORT+ File.separator +"reporte_top5.html";
        HTMLGenerator.generateTop5ProductsReport(products, pathExport);
    }

    private void generateSalesHistoryReport() {

        String pathExport = Main.PATH_EXPORT+ File.separator +"historial_ventas.html";
        HTMLGenerator.generateSalesHistoryReport(salesHistory, pathExport);
    }
    private void loadProduct() {
        System.out.print("Ingrese la ruta del archivo: ");
        String filePath = scanner.nextLine();
        if(filePath.isEmpty()) {
            filePath = Main.PATH_IMPORT;
        }
        inventory.loadProductFromFile(filePath);
    }

}
