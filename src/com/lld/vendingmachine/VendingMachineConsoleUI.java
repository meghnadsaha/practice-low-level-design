package com.lld.vendingmachine;

import java.util.Scanner;

public class VendingMachineConsoleUI {

    public static void main ( String[] args ) {
        VendingMachine vendingMachine = new VendingMachine();
        Scanner scanner = new Scanner(System.in);

        // Add some initial products
        vendingMachine.addProduct(new Product("Coke" , 1.25 , 10));
        vendingMachine.addProduct(new Product("Pepsi" , 1.25 , 10));
        vendingMachine.addProduct(new Product("Water" , 1.00 , 20));


        while (true) {
            System.out.println("1. Buy Product");
            System.out.println("2. Restock Product");
            System.out.println("3. Collect Money");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter payment amount: ");
                    double payment = scanner.nextDouble();
                    scanner.nextLine();  // Consume newline
                    String result = vendingMachine.dispenseProduct(productName, payment);
                    System.out.println(result);
                    break;
                case 2:
                    System.out.print("Enter product name: ");
                    productName = scanner.nextLine();
                    System.out.print("Enter quantity to restock: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    vendingMachine.restockProduct(productName, quantity);
                    break;
                case 3:
                    double collectedMoney = vendingMachine.collectMoney();
                    System.out.println("Collected money: " + collectedMoney);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
