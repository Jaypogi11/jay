
import java.util.Scanner;
import jjopia.config;



class Order {
    Scanner sc;
    config conf;

    public Order() {
        this.sc = new Scanner(System.in);
        this.conf = new config();
    }

    public void ortransaction(Scanner sc) {
        boolean exit = false;
        String response;

        while (!exit) {
            System.out.println("\n------------------------------");
            System.out.println("Order Operations:");
            System.out.println("");
            System.out.println("1. Place Order");
            System.out.println("2. View Orders");
            System.out.println("3. Update Order Status");
            System.out.println("4. Back to Main Menu");

            System.out.println("Enter your choice:");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline character
            Order or = new Order();
            switch (choice) {
                case 1:
                    placeOrder();
                    break;
                case 2:
                    viewOrders();
                    break;
                case 3:
                    updateOrderStatus();
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
    }

    private void placeOrder() {
        System.out.print("Enter Customer ID: ");
        int customerId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        while (conf.getSingleValue("SELECT c_id FROM tbl_customer WHERE c_id = ?", customerId) == 0) {
            System.out.println("Selected Customer ID doesn't exist!");
            System.out.println("Select Customer ID Again:");
            customerId = sc.nextInt();
            sc.nextLine(); // Consume newline character
        }

        System.out.println("Enter Order Items (format: product_id,quantity):");
        System.out.println("Enter 'done' to finish adding items.");

        String orderItemsInput;
        String[] orderItems;
        java.util.List<String> orderItemsList = new java.util.ArrayList<>();

        do {
            orderItemsInput = sc.nextLine();
            if (!orderItemsInput.equalsIgnoreCase("done")) {
                orderItems = orderItemsInput.split(",");
                if (orderItems.length == 2) {
                    int productId = Integer.parseInt(orderItems[0].trim());
                    int quantity = Integer.parseInt(orderItems[1].trim());

                    if (conf.getSingleValue("SELECT p_id FROM tbl_product WHERE p_id = ?", productId) != 0) {
                        orderItemsList.add(orderItemsInput);
                    } else {
                        System.out.println("Invalid Product ID. Please enter a valid ID.");
                    }
                } else {
                    System.out.println("Invalid input format. Please enter product_id,quantity.");
                }
            }
        } while (!orderItemsInput.equalsIgnoreCase("done"));

        if (orderItemsList.size() > 0) {
            String qry = "INSERT INTO tbl_order (o_date, o_status, c_id) VALUES (NOW(), 'Pending', ?)";
            int orderId = conf.insertDataAndGetId(qry, customerId);

            for (String orderItem : orderItemsList) {
                orderItems = orderItem.split(",");
                int productId = Integer.parseInt(orderItems[0].trim());
                int quantity = Integer.parseInt(orderItems[1].trim());

                qry = "INSERT INTO tbl_order_item (o_id, p_id, oi_quantity) VALUES (?, ?, ?)";
                conf.insertData(qry, orderId, productId, quantity);
            }

            System.out.println("Order placed successfully! Order ID: " + orderId);
        } else {
            System.out.println("No items added to the order.");
        }
    }

    private void viewOrders() {
        String qry = "SELECT o_id, o_date, o_status, c_name FROM tbl_order JOIN tbl_customer ON tbl_order.c_id = tbl_customer.c_id";
        String[] headers = {"o_id", "o_date", "o_status", "c_name"};
        String[] columns = {"o_id", "o_date", "o_status", "c_name"};
        conf.viewRecords(qry, headers, columns);
    }

    private void updateOrderStatus() {
        System.out.print("Enter Order ID to update: ");
        int orderId = sc.nextInt();
        sc.nextLine(); // Consume newline character

        while (conf.getSingleValue("SELECT o_id FROM tbl_order WHERE o_id = ?", orderId) == 0) {
            System.out.println("Selected Order ID doesn't exist!");
            System.out.println("Select Order ID Again:");
            orderId = sc.nextInt();
            sc.nextLine(); // Consume newline character
        }

        System.out.println("Enter New Order Status (Pending, Processed, Shipped, Delivered):");
        String newStatus = sc.nextLine();

        while (!newStatus.equalsIgnoreCase("Pending") && !newStatus.equalsIgnoreCase("Processed") &&
               !newStatus.equalsIgnoreCase("Shipped") && !newStatus.equalsIgnoreCase("Delivered")) {
            System.out.println("Invalid Order Status. Please enter a valid status.");
            System.out.println("Enter New Order Status (Pending, Processed, Shipped, Delivered):");

    }
    }
}
    