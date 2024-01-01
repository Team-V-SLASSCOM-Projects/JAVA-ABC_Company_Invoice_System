import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleInterface {

    private final Scanner scanner;
    private final Connection conn;

    public ConsoleInterface() {
        this.scanner = new Scanner(System.in);
        this.conn = DBConnect.connect();
    }

    public void showMenu() {
        System.out.println("Main Menu:");
        System.out.println("1. Manage Products");
        System.out.println("2. Manage Customers");
        System.out.println("3. Invoice Generation");
        System.out.println("4. Admin Tasks");
        System.out.println("0. Exit");
    }

    //SHOW MANAGE PRODUCT MENU
    public void manageProducts() {
        while (true) {
            System.out.println("Manage Products Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. Search Product");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("0. Back to Main Menu");

            System.out.print("Enter your choice (0-4): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    searchProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 0:
                    return; // Back to the main menu
                default:
                    System.out.println("Invalid choice. Please enter a number between 0 and 4.");
            }
        }
    }

    //SUB MENU FEATURES OF MANAGE PRODUCT
    //ADD PRODUCT FEATURES
    private void addProduct() {

        do {
            System.out.println("Adding Product:");

            System.out.print("Enter Product Name: ");
            String name = scanner.next();

            System.out.print("Enter Product Price: ");
            float price = scanner.nextFloat();

            System.out.print("Enter Product Description: ");
            scanner.nextLine();
            String desc = scanner.nextLine();

            System.out.print("Enter Product Quantity: ");
            int quantity = scanner.nextInt();

            System.out.print("Enter Selling Price: ");
            float sellingPrice = scanner.nextFloat();

            Product product = new Product();
            product.setProductName(name);
            product.setDescription(desc);
            product.setQuantity(quantity);
            product.setPurchasePrice(price);
            product.setSellingPrice(sellingPrice);

            boolean result = product.addProduct(product, conn);
            if (result) {
                System.out.println("Product Added Successfully");
            } else {
                System.out.println("Something wrong");
            }
            System.out.print("Do you want to add another product? Yes(y) or No(n): ");
            scanner.nextLine();  // Consume the newline character
            String option = scanner.nextLine();

            if (!option.equalsIgnoreCase("y")) {
                break; // Exit the loop if the user doesn't want to add another product
            }

        } while (true);

    }


    //SUB MENU FEATURES OF MANAGE PRODUCT
    //SEARCH PRODUCT FEATURES


    private void searchProduct() {
        int i = 1;
        String searchTag = "";
        while (true) {

            System.out.println("Enter  Product Name or Number You Want to Search");
            if (i == 1) {
                scanner.nextLine();
            }
            searchTag = scanner.nextLine();


            List<Product> foundProducts = Product.searchAndShowProduct(conn, searchTag);


            if (!foundProducts.isEmpty()) {
                System.out.println("Product Information:");
                System.out.printf("%-10s%-20s%-30s%-20s%-20s%-10s\n",
                        "ID", "Name", "Description", "Purchase Price", "Selling Price", "Quantity");

                for (Product product : foundProducts) {
                    System.out.printf("%-10s%-20s%-30s%-20s%-20s%-10s\n",
                            product.getProductId(),
                            product.getProductName(),
                            product.getDescription(),
                            product.getPurchasePrice(),
                            product.getSellingPrice(),
                            product.getQuantity());
                }


            } else {
                System.out.println("Product not found.");
            }

            System.out.println("Do You Want To Search Another Items Yes(y) No(n)");
            //scanner.nextLine();  // Consume the newline character
            String option = scanner.nextLine();
            if (!option.equalsIgnoreCase("y")) {
                break; // Exit the loop if the user doesn't want to add another product
            }


            i++;


        }

    }

    //SUB MENU FEATURES OF MANAGE PRODUCT
    //UPDATE PRODUCT FEATURES
    public void updateProduct() {
        System.out.println("Update Product:");
        System.out.print("Enter the ID of the product you want to update: ");
        int productId = scanner.nextInt();

        // Check if the product with the given ID exists
        Product existingProduct = Product.getProductById(conn, productId);


        if (existingProduct != null) {
            int id = existingProduct.getProductId();
            System.out.println("Existing Product Information:");
            System.out.printf("%-10s%-20s%-30s%-20s%-20s%-10s\n",
                    "ID", "Name", "Description", "Purchase Price", "Selling Price", "Quantity");
            System.out.printf("%-10s%-20s%-30s%-20s%-20s%-10s\n",
                    existingProduct.getProductId(),
                    existingProduct.getProductName(),
                    existingProduct.getDescription(),
                    existingProduct.getPurchasePrice(),
                    existingProduct.getSellingPrice(),
                    existingProduct.getQuantity());

            // Get updated information from the user
            System.out.println("Enter new information for the product:");

            System.out.print("Enter Product Name: ");
            String name = scanner.next();

            System.out.print("Enter Product Price: ");
            float price = scanner.nextFloat();

            System.out.print("Enter Product Description: ");
            scanner.nextLine();
            String desc = scanner.nextLine();

            System.out.print("Enter Product Quantity: ");
            int quantity = scanner.nextInt();

            System.out.print("Enter Selling Price: ");
            float sellingPrice = scanner.nextFloat();

            // Update the product object
            existingProduct.setProductName(name);
            existingProduct.setDescription(desc);
            existingProduct.setQuantity(quantity);
            existingProduct.setPurchasePrice(price);
            existingProduct.setSellingPrice(sellingPrice);

            // Update the product in the database
            boolean result = existingProduct.updateProductById(existingProduct, conn, id);

            if (result) {
                System.out.println("Product Updated Successfully");
            } else {
                System.out.println("Something went wrong while updating the product.");
            }

        } else {
            System.out.println("Product with ID " + productId + " not found.");
        }
    }

    public void deleteProduct() {
        boolean result = false;
        System.out.println("Delete Product:");
        System.out.print("Enter the ID of the product you want to delete: ");
        int productId = scanner.nextInt();

        Product existingProduct = Product.getProductById(conn, productId);


        if (existingProduct != null) {
            int id = existingProduct.getProductId();
            System.out.println("Existing Product Information:");
            System.out.printf("%-10s%-20s%-30s%-20s%-20s%-10s\n",
                    "ID", "Name", "Description", "Purchase Price", "Selling Price", "Quantity");
            System.out.printf("%-10s%-20s%-30s%-20s%-20s%-10s\n",
                    existingProduct.getProductId(),
                    existingProduct.getProductName(),
                    existingProduct.getDescription(),
                    existingProduct.getPurchasePrice(),
                    existingProduct.getSellingPrice(),
                    existingProduct.getQuantity());

            // Get updated information from the user
            System.out.println("Are you sure Yes(y)/No(n):");
            scanner.nextLine();

            String option = scanner.nextLine();
            if (option.equalsIgnoreCase("y")) {
                result = Product.deleteProduct(conn, id);
            } else {
                return;
            }


            if (result) {
                System.out.println("Product Deleted Successfully");
            } else {
                System.out.println("Something went wrong while deleting the product.");
            }

        } else {
            System.out.println("Product with ID " + productId + " not found.");
        }
    }


    public void manageCustomers() throws SQLException {
        // Add functionality for managing customers
        while (true) {
            System.out.println("Manage Products Menu:");
            System.out.println("1. Add Customer");
            System.out.println("2. Search Customer");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("0. Back to Main Menu");

            System.out.print("Enter your choice (0-4): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    searchCustomer();
                    break;
                case 3:
                    updateCustomer();
                    break;
                case 4:
                    deleteCustomer();
                    break;
                case 0:
                    return; // Back to the main menu
                default:
                    System.out.println("Invalid choice. Please enter a number between 0 and 4.");
            }
        }
    }

    public void addCustomer() {
        do {
            System.out.println("Adding Customer:");

            System.out.print("Enter Customer Name: ");
            scanner.nextLine();
            String name = scanner.nextLine();

            System.out.print("Enter Customer email: ");
            String email = scanner.next();

            System.out.print("Enter Customer Address: ");
            scanner.nextLine();
            String address = scanner.nextLine();

            System.out.print("Enter Customer Contact Number: ");
            String contactNumber = scanner.next();

            System.out.print("Enter Customer Contact DOB: ");
            String dob = scanner.next();

            System.out.print("Enter Customer Gender: ");
            String gender = scanner.next();

            Customer customer = new Customer();
            customer.setCustomerName(name);
            customer.setEmail(email);
            customer.setAddress(address);
            customer.setContactNumber(contactNumber);
            customer.setDateOfBirth(dob);
            customer.setGender(gender);

            boolean result = customer.addNewCustomer(customer, conn);
            if (result) {
                System.out.println("Product Added Successfully");
            } else {
                System.out.println("Something wrong");
            }
            System.out.print("Do you want to add another Customer? Yes(y) or No(n): ");
            scanner.nextLine();  // Consume the newline character
            String option = scanner.nextLine();

            if (!option.equalsIgnoreCase("y")) {
                break; // Exit the loop if the user doesn't want to add another product
            }

        } while (true);

    }

    private void searchCustomer() throws SQLException {
        int i = 1;
        String searchTag = "";
        while (true) {

            System.out.println("Enter  Customer Name or Id You Want to Search");
            if (i == 1) {
                scanner.nextLine();
            }
            searchTag = scanner.nextLine();


            List<Customer> foundCustomers = Customer.searchAndShowCustomer(conn, searchTag);


            if (!foundCustomers.isEmpty()) {
                System.out.println("Product Information:");
                System.out.printf("%-10s%-20s%-30s%-20s%-20s%-10s\n",
                        "ID", "Name", "Description", "Purchase Price", "Selling Price", "Quantity");

                for (Customer customer : foundCustomers) {
                    System.out.printf("%-10s%-20s%-30s%-20s%-20s%-10s\n",
                            customer.getId(),
                            customer.getCustomerName(),
                            customer.getEmail(),
                            customer.getAddress(),
                            customer.getContactNumber(),
                            customer.getDateOfBirth(),
                            customer.getDateOfBirth());
                }

            } else {
                System.out.println("Product not found.");
            }

            System.out.println("Do You Want To Search Another Items Yes(y) No(n)");
            //scanner.nextLine();  // Consume the newline character
            String option = scanner.nextLine();
            if (!option.equalsIgnoreCase("y")) {
                break; // Exit the loop if the user doesn't want to add another product
            }


            i++;


        }

    }

    public void updateCustomer() {
        System.out.println("Update Customer:");
        System.out.print("Enter the ID of the Customer you want to update: ");
        int customerId = scanner.nextInt();

        // Check if the product with the given ID exists
        Customer existingCustomer = Customer.getCustomerById(conn, customerId);


        String id = null;
        if (existingCustomer != null) {
            id = existingCustomer.getId();
            System.out.println("Existing Product Information:");
            System.out.printf("%-10s%-20s%-30s%-20s%-20s%-10s\n",
                    "ID", "Name", "Email", "Address", "Contact No", "DOB", "Gender");
            System.out.printf("%-10s%-20s%-30s%-20s%-20s%-10s\n",
                    existingCustomer.getId(),
                    existingCustomer.getCustomerName(),
                    existingCustomer.getEmail(),
                    existingCustomer.getAddress(),
                    existingCustomer.getContactNumber(),
                    existingCustomer.getDateOfBirth(),
                    existingCustomer.getGender());

            // Get updated information from the user
            System.out.println("Enter new information for the product:");

            System.out.print("Enter Customer Name: ");
            scanner.nextLine();
            String name = scanner.nextLine();

            System.out.print("Enter Email Address: ");
            String email = scanner.next();

            System.out.print("Enter Customer Address: ");
            scanner.nextLine();
            String address = scanner.nextLine();

            System.out.print("Enter Customer Conatct No: ");
            String contactNo = scanner.next();

            System.out.print("Enter Cutomer DOB: ");
            String dob = scanner.next();


            System.out.print("Enter Cutomer Gender: ");
            String gender = scanner.next();

            // Update the product object
            existingCustomer.setCustomerName(name);
            existingCustomer.setEmail(email);
            existingCustomer.setAddress(address);
            existingCustomer.setContactNumber(contactNo);
            existingCustomer.setDateOfBirth(dob);
            existingCustomer.setGender(gender);

            // Update the product in the database
            boolean result = existingCustomer.updateCustomerById(existingCustomer, conn, id);

            if (result) {
                System.out.println("Customer Updated Successfully");
            } else {
                System.out.println("Something went wrong while updating the product.");
            }

        } else {
            System.out.println("Customer with ID " + id + " not found.");
        }
    }


    public void deleteCustomer() throws SQLException {
        boolean result = false;
        System.out.println("Delete Customer:");
        System.out.print("Enter the ID of the customer you want to delete: ");
        int customerID = scanner.nextInt();

        Customer existingCustomer = Customer.getCustomerById(conn, customerID);


        String id = null;
        if (existingCustomer != null) {
            id = existingCustomer.getId();
            System.out.println("Existing Customer Information:");
            System.out.printf("%-10s%-20s%-30s%-20s%-20s%-10s\n",
                    "ID", "Name", "Email", "Address", "Contact No", "DOB", "Gender");
            System.out.printf("%-10s%-20s%-30s%-20s%-20s%-10s\n",
                    existingCustomer.getId(),
                    existingCustomer.getCustomerName(),
                    existingCustomer.getEmail(),
                    existingCustomer.getAddress(),
                    existingCustomer.getContactNumber(),
                    existingCustomer.getDateOfBirth(),
                    existingCustomer.getGender());

            // Get updated information from the user
            System.out.println("Are you sure Yes(y)/No(n):");
            scanner.nextLine();

            String option = scanner.nextLine();
            if (option.equalsIgnoreCase("y")) {
                result = Customer.deleteCustomer(conn, id);
            } else {
                return;
            }


            if (result) {
                System.out.println("Product Deleted Successfully");
            } else {
                System.out.println("Something went wrong while deleting the product.");
            }

        } else {
            System.out.println("Product with ID " + id + " not found.");
        }
    }

    public void generateInvoice() {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter Customer Name : ");
        String customerName = scn.nextLine();

        System.out.println("-Product Selection Section-");
        List<InvoiceItem> items = new ArrayList<>();

        while (true) {
            System.out.println("Enter Product id or name :");
            String productIDorName = scn.nextLine();
            List<Product> currentProduct = Product.searchAndShowProduct(conn, productIDorName); //Always populate with one product each time.

            if (currentProduct.isEmpty()) {
                System.out.println("Product not found. Please try again.");
                continue;
            }

            System.out.println("Enter Quantity :");
            int qty = scn.nextInt();

            // Check if the requested quantity is available in stock
            if (qty > currentProduct.get(0).getQuantity()) {
//                System.out.println(currentProduct.get(0).getQuantity());
                System.out.println("Not enough stock available. Please enter a lower quantity.");
                continue;
            }

            double unitPrice = 0.0; // Default value, handle errors accordingly

            try {
                String selectPriceSQL = "SELECT selling_price FROM currentProduct WHERE product_id = ?";
                try (PreparedStatement statement = conn.prepareStatement(selectPriceSQL)) {
                    statement.setInt(1, currentProduct.get(0).getProductId());

                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            unitPrice = resultSet.getDouble("selling_price");
                        } else {
                            System.out.println("Error retrieving unit price from the database.");
                            // You can throw an exception or handle the error in an appropriate way
                        }
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                // You can throw an exception or handle the error in an appropriate way
            }

            System.out.println("Unit Price: " + unitPrice);

            System.out.println("Enter Discount :");
            double discount = scn.nextDouble();

            // Create an InvoiceItem and add it to the list
            items.add(new InvoiceItem(currentProduct.get(0), qty, unitPrice, discount));

            System.out.println("Would you like to add another currentProduct to the invoice? yes(y) or no(n)");
            scn.nextLine(); // Consume the newline character left by nextBigDecimal()
            String userChoiceAddProductContinue = scn.nextLine();

            if (userChoiceAddProductContinue.equalsIgnoreCase("n")) {
                break;
            }
        }

        //Using the items List in above to build invoice and database actions.
        Invoice invoice = new Invoice(customerName);
        for (InvoiceItem item : items) {
            invoice.addItem(item.getProduct(), item.getUnits(), item.getUnitPrice(), item.getDiscount());
        }

        // Perform other actions like calculating total price, applying discounts, etc.
        invoice.generateInvoice();

    }


    public void adminTasks() {
        // Add functionality for admin tasks
        System.out.println("Admin Tasks");
    }

    public void startSystem() throws SQLException {
        while (true) {
            showMenu();
            System.out.print("Enter your choice (0-4): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    manageProducts();
                    break;
                case 2:
                    manageCustomers();
                    break;
                case 3:
                    generateInvoice();
                    break;
                case 4:
                    adminTasks();
                    break;
                case 0:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    //conn.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 0 and 4.");
            }
        }
    }


}

