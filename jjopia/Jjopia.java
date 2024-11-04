
package jjopia;

import java.util.Scanner;


public class Jjopia {

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        String response;

        while (!exit) {
            System.out.println("\n------------------------------");
            System.out.println("Welcome to the Coffee Shop:");
            System.out.println("");
            System.out.println("1. Customer");
            System.out.println("2. Product");
            System.out.println("3. Order");
            System.out.println("4. Exit");

            System.out.println("Enter your choice:");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    Customer cs = new Customer();
                    cs.transaction();
                    break;
                case 2:
                    Product pr = new Product();
                    pr.transaction();
                    break;
                case 3:
                    Order or = new Order();
                    or.transaction(sc);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

            if (!exit) {
                System.out.println("Do you want to continue? (yes/no):");
                response = sc.nextLine();
                if (response.equalsIgnoreCase("yes")) {
                    exit = false;
                } else {
                    exit = true;
                }
            }
        }
        sc.close();
    }
}




