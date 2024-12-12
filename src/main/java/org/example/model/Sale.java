package org.example.model;

public class Sale {
    private String customerName;
    private String customerNIT;
    private Product[] products;
    private int[] quantities;
    private int productCount;
    private double totalAmount;

    public Sale(String customerName, String customerNIT, int maxProducts) {
        this.customerName = customerName;
        this.customerNIT = customerNIT;
        this.products = new Product[maxProducts];
        this.quantities = new int[maxProducts];
        this.productCount = 0;
        this.totalAmount = 0;
    }

    public void addProduct(Product product, int quantity) {
        products[productCount] = product;
        quantities[productCount] = quantity;
        totalAmount += product.getPrice() * quantity;
        product.incrementQuantitySold(quantity);
        productCount++;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerNIT() {
        return customerNIT;
    }

    public void printInvoice() {
        System.out.println("Factura de Venta");
        System.out.println("Cliente: " + customerName);
        System.out.println("NIT: " + customerNIT);
        System.out.println("Productos comprados:");
        for (int i = 0; i < productCount; i++) {
            System.out.println("- " + products[i].getName() + " x" + quantities[i] + " - Q" + (products[i].getPrice() * quantities[i]));
        }
        System.out.println("Total: Q" + totalAmount);
    }
    public int getProductCount() {
        return productCount;
    }
    public Product[] getProducts() {
        return products;
    }

    public int[] getQuantities() {
        return quantities;
    }

}
