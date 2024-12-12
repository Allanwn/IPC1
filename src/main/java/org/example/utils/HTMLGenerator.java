package org.example.utils;

import org.example.model.*;

import java.io.FileWriter;
import java.io.IOException;

public class HTMLGenerator {

    public static void generateInvoiceHTML(Sale sale, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("<html><head>");
            writer.write("<title>Factura de Venta</title>");
            writer.write("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
            writer.write("</head><body class='container mt-5'>");

            writer.write("<h1 class='text-center mb-4'>Factura de Venta - Cobra Kai</h1>");
            writer.write("<p><strong>Cliente:</strong> " + sale.getCustomerName() + "</p>");
            writer.write("<p><strong>NIT:</strong> " + sale.getCustomerNIT() + "</p>");

            writer.write("<h2 class='mt-4'>Productos Comprados</h2>");
            writer.write("<table class='table table-bordered table-striped'>");
            writer.write("<thead class='thead-dark'><tr><th>Producto</th><th>Cantidad</th><th>Subtotal</th></tr></thead>");
            writer.write("<tbody>");

            for (int i = 0; i < sale.getProductCount(); i++) {
                Product product = sale.getProducts()[i];
                int quantity = sale.getQuantities()[i];
                double subtotal = product.getPrice() * quantity;

                writer.write("<tr>");
                writer.write("<td>" + product.getName() + "</td>");
                writer.write("<td>" + quantity + "</td>");
                writer.write("<td>Q" + String.format("%.2f", subtotal) + "</td>");
                writer.write("</tr>");
            }

            writer.write("</tbody></table>");
            writer.write("<h3 class='text-right mt-3'>Total: Q" + String.format("%.2f", sale.getTotalAmount()) + "</h3>");

            writer.write("</body></html>");
            System.out.println("Factura generada exitosamente en: " + filePath);
        } catch (IOException e) {
            System.out.println("Error al generar la factura HTML: " + e.getMessage());
        }
    }

    public static void generateTop5ProductsReport(Product[] products, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("<html><head>");
            writer.write("<title>Top 5 Productos Más Vendidos</title>");
            writer.write("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
            writer.write("</head><body class='container mt-5'>");

            writer.write("<h1 class='text-center mb-4'>Top 5 Productos Más Vendidos</h1>");
            writer.write("<table class='table table-bordered table-striped'>");
            writer.write("<thead class='thead-dark'><tr><th>#</th><th>Producto</th><th>Cantidad Vendida</th></tr></thead>");
            writer.write("<tbody>");

            for (int i = 0; i < Math.min(5, products.length); i++) {
                writer.write("<tr>");
                writer.write("<td>" + (i + 1) + "</td>");
                writer.write("<td>" + products[i].getName() + "</td>");
                writer.write("<td>" + products[i].getQuantitySold() + "</td>");
                writer.write("</tr>");
            }

            writer.write("</tbody></table>");
            writer.write("</body></html>");
            System.out.println("Reporte Top 5 generado exitosamente en: " + filePath);
        } catch (IOException e) {
            System.out.println("Error al generar el reporte HTML: " + e.getMessage());
        }
    }

    public static void generateSalesHistoryReport(Sale[] sales, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("<html><head>");
            writer.write("<title>Historial de Ventas</title>");
            writer.write("<link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
            writer.write("</head><body class='container mt-5'>");

            writer.write("<h1 class='text-center mb-4'>Historial de Ventas</h1>");
            writer.write("<table class='table table-bordered table-striped'>");
            writer.write("<thead class='thead-dark'><tr><th>Cliente</th><th>NIT</th><th>Total</th></tr></thead>");
            writer.write("<tbody>");

            for (Sale sale : sales) {
                if (sale == null) continue;

                writer.write("<tr>");
                writer.write("<td>" + sale.getCustomerName() + "</td>");
                writer.write("<td>" + sale.getCustomerNIT() + "</td>");
                writer.write("<td>Q" + String.format("%.2f", sale.getTotalAmount()) + "</td>");
                writer.write("</tr>");
            }

            writer.write("</tbody></table>");
            writer.write("</body></html>");
            System.out.println("Historial de ventas generado exitosamente en: " + filePath);
        } catch (IOException e) {
            System.out.println("Error al generar el historial de ventas HTML: " + e.getMessage());
        }
    }
}
