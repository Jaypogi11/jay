
import java.util.Scanner;
import jjopia.config;


class Customer {
    Scanner sc;
    config conf;

    public Customer() {
        this.sc = new Scanner(System.in);
        this.conf = new config();
    }

    public void cstransaction(Scanner sc) {
        boolean exit = false;
        String response;

        while (!exit) {
            System.out.println("\n------------------------------");
            System.out.println("Customer Operations:");
            System.out.println("");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Back to Main Menu");

            System.out.println("Enter your choice:");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline character
            Customer cs = new Customer();
            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    viewCustomers();
                    break;
                case 3:
                    updateCustomer();
                    break;
                case 4:
                    deleteCustomer();
                    break;
                case 5:
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
    }

    private void addCustomer() {
        System.out.print("Enter Customer ID: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        System.out.print("Enter Customer Name: ");
        String customerName = sc.nextLine();

        System.out.print("Enter Customer Contact: ");
        String customerContact = sc.nextLine();

        System.out.print("Enter Customer Address: ");
        String customerAddress = sc.nextLine();

        System.out.print("Enter Customer Loyalty (true/false): ");
        boolean customerLoyalty = sc.nextBoolean();
        sc.nextLine(); // Consume newline character

        String qry = "INSERT INTO tbl_customer (c_id, c_name, c_contact, c_address, c_loyalty) VALUES (?, ?, ?, ?, ?)";
        conf.insertData(qry, customerId, customerName, customerContact, customerAddress, customerLoyalty);
    }

    private void viewCustomers() {
        String qry = "SELECT * FROM tbl_customer";
        String[] headers = {"c_id", "c_name", "c_contact", "c_address", "c_loyalty"};
        String[] columns = {"c_id", "c_name", "c_contact", "c_address", "c_loyalty"};
        conf.viewRecords(qry, headers, columns);
    }

    private void updateCustomer() {
        System.out.print("Enter Customer ID to update: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        while (conf.getSingleValue("SELECT c_id FROM tbl_customer WHERE c_id = ?", customerId) == 0) {
            System.out.println("Selected ID doesn't exist!");
            System.out.println("Select Customer ID Again:");
            customerId = sc.nextInt();
            sc.nextLine(); // Consume newline character
        }

        System.out.print("Enter New Customer Name: ");
        String customerName = sc.nextLine();

        System.out.print("Enter New Customer Contact: ");
        String customerContact = sc.nextLine();

        System.out.print("Enter New Customer Address: ");
        String customerAddress = sc.nextLine();

        System.out.print("Enter New Customer Loyalty (true/false): ");
        boolean customerLoyalty = sc.nextBoolean();
        sc.nextLine(); // Consume newline character

        String qry = "UPDATE tbl_customer SET c_name = ?, c_contact = ?, c_address = ?, c_loyalty = ? WHERE c_id = ?";
        conf.updateData(qry, customerName, customerContact, customerAddress, customerLoyalty, customerId);
    }

    private void deleteCustomer() {
        System.out.print("Enter Customer ID to delete: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        while (conf.getSingleValue("SELECT c_id FROM tbl_customer WHERE c_id = ?", customerId) == 0) {
            System.out.println("Selected ID doesn't exist!");
            System.out.println("Select Customer ID Again:");
            customerId = sc.nextInt();
            sc.nextLine(); // Consume newline character
        }

        String qry = "DELETE FROM tbl_customer WHERE c_id = ?";
        conf.deleteData(qry, customerId);
    }
}