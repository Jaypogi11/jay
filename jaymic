
import java.util.Scanner;
import jjopia.config;


class Product {
    Scanner sc;
    config conf;

    public Product() {
        this.sc = new Scanner(System.in);
        this.conf = new config();
    }

    public void prtransaction(Scanner sc) {
        boolean exit = false;
        String response;

        while (!exit) {
            System.out.println("\n------------------------------");
            System.out.println("Product Operations:");
            System.out.println("");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Back to Main Menu");

            System.out.println("Enter your choice:");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline character
            Product pr = new Product();
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewProducts();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
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

    private void addProduct() {
        System.out.print("Enter Product ID: ");
        int productId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        System.out.print("Enter Product Name: ");
        String productName = sc.nextLine();

        System.out.print("Enter Product Description: ");
        String productDescription = sc.nextLine();

        System.out.print("Enter Product Price: ");
        double productPrice = sc.nextDouble();
        sc.nextLine(); // Consume newline character

        System.out.print("Enter Product Image URL: ");
        String productImage = sc.nextLine();

        System.out.print("Enter Product Availability (true/false): ");
        boolean productAvailability = sc.nextBoolean();
        sc.nextLine(); // Consume newline character

        String qry = "INSERT INTO tbl_product (p_id, p_name, p_description, p_price, p_image, p_availability) VALUES (?, ?, ?, ?, ?, ?)";
        conf.insertData(qry, productId, productName, productDescription, productPrice, productImage, productAvailability);
    }

    private void viewProducts() {
        String qry = "SELECT * FROM tbl_product";
        String[] headers = {"p_id", "p_name", "p_description", "p_price", "p_image", "p_availability"};
        String[] columns = {"p_id", "p_name", "p_description", "p_price", "p_image", "p_availability"};
        conf.viewRecords(qry, headers, columns);
    }

    private void updateProduct() {
        System.out.print("Enter Product ID to update: ");
        int productId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        while (conf.getSingleValue("SELECT p_id FROM tbl_product WHERE p_id = ?", productId) == 0) {
            System.out.println("Selected ID doesn't exist!");
            System.out.println("Select Product ID Again:");
            productId = sc.nextInt();
            sc.nextLine(); // Consume newline character
        }

        System.out.print("Enter New Product Name: ");
        String productName = sc.nextLine();

        System.out.print("Enter New Product Description: ");
        String productDescription = sc.nextLine();

        System.out.print("Enter New Product Price: ");
        double productPrice = sc.nextDouble();
        sc.nextLine(); // Consume newline character

        System.out.print("Enter New Product Image URL: ");
        String productImage = sc.nextLine();

        System.out.print("Enter New Product Availability (true/false): ");
        boolean productAvailability = sc.nextBoolean();
        sc.nextLine(); // Consume newline character

        String qry = "UPDATE tbl_product SET p_name = ?, p_description = ?, p_price = ?, p_image = ?, p_availability = ? WHERE p_id = ?";
        conf.updateData(qry, productName, productDescription, productPrice, productImage, productAvailability, productId);
    }

    private void deleteProduct() {
        System.out.print("Enter Product ID to delete: ");
        int productId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        while (conf.getSingleValue("SELECT p_id FROM tbl_product WHERE p_id = ?", productId) == 0) {
            System.out.println("Selected ID doesn't exist!");
            System.out.println("Select Product ID Again:");
            productId = sc.nextInt();
            sc.nextLine(); // Consume newline character
        }

        String qry = "DELETE FROM tbl_product WHERE p_id = ?";
        conf.deleteData(qry, productId);
    }
}
