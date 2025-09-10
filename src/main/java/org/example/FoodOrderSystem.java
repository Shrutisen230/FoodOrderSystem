package org.example;

import java.util.*;

public class FoodOrderSystem {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<FoodItem> menu = new ArrayList<>();

    public static void main(String[] args) {
        // Preloaded Menu Items
        menu.add(new FoodItem(1, "Burger", 120));
        menu.add(new FoodItem(2, "Pizza", 250));
        menu.add(new FoodItem(3, "Pasta", 180));
        menu.add(new FoodItem(4, "Sandwich", 90));

        int choice;
        do {
            try {
                System.out.println("\n===== Food Order System =====");
                System.out.println("1. Display Menu");
                System.out.println("2. Add Food Item");
                System.out.println("3. Update Food Item");
                System.out.println("4. Delete Food Item");
                System.out.println("5. Place an Order");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1 -> displayMenu();
                    case 2 -> addFoodItem();
                    case 3 -> updateFoodItem();
                    case 4 -> deleteFoodItem();
                    case 5 -> placeOrder();
                    case 6 -> System.out.println("Thank you for visiting!");
                    default -> System.out.println("Invalid choice! Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1-6.");
                choice = 0; // reset
            }
        } while (choice != 6);

        sc.close();
    }

    // Display all menu items
    private static void displayMenu() {
        if (menu.isEmpty()) {
            System.out.println("Menu is empty.");
        } else {
            System.out.println("\n--- Menu ---");
            for (FoodItem item : menu) {
                System.out.println(item);
            }
        }
    }

    // Add new food item
    private static void addFoodItem() {
        try {
            System.out.print("Enter Food ID: ");
            int id = Integer.parseInt(sc.nextLine());

            for (FoodItem item : menu) {
                if (item.getId() == id) {
                    System.out.println("Food Item with ID " + id + " already exists!");
                    return;
                }
            }

            System.out.print("Enter Food Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Food Price: ");
            double price = Double.parseDouble(sc.nextLine());

            menu.add(new FoodItem(id, name, price));
            System.out.println("Food Item added successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! ID and Price must be numeric.");
        }
    }

    // Update food item by ID
    private static void updateFoodItem() {
        try {
            System.out.print("Enter Food ID to Update: ");
            int id = Integer.parseInt(sc.nextLine());

            FoodItem found = null;
            for (FoodItem item : menu) {
                if (item.getId() == id) {
                    found = item;
                    break;
                }
            }

            if (found == null) {
                System.out.println("Food Item not found with ID: " + id);
                return;
            }

            System.out.print("Enter New Name: ");
            String newName = sc.nextLine();
            System.out.print("Enter New Price: ");
            double newPrice = Double.parseDouble(sc.nextLine());

            found.setName(newName);
            found.setPrice(newPrice);

            System.out.println("Food Item updated successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter numeric values where required.");
        }
    }

    // Delete food item
    private static void deleteFoodItem() {
        try {
            System.out.print("Enter Food ID to Delete: ");
            int id = Integer.parseInt(sc.nextLine());

            FoodItem found = null;
            for (FoodItem item : menu) {
                if (item.getId() == id) {
                    found = item;
                    break;
                }
            }

            if (found != null) {
                menu.remove(found);
                System.out.println("Food Item deleted successfully!");
            } else {
                System.out.println("No food item found with ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a numeric ID.");
        }
    }

    // Place order
    private static void placeOrder() {
        try {
            if (menu.isEmpty()) {
                System.out.println("Menu is empty. Cannot place an order.");
                return;
            }

            displayMenu();
            System.out.print("Enter Food ID to Order: ");
            int id = Integer.parseInt(sc.nextLine());

            FoodItem found = null;
            for (FoodItem item : menu) {
                if (item.getId() == id) {
                    found = item;
                    break;
                }
            }

            if (found != null) {
                System.out.print("Enter Quantity: ");
                int qty = Integer.parseInt(sc.nextLine());
                double total = found.getPrice() * qty;
                System.out.println("Order Placed: " + qty + " x " + found.getName() + " = ₹" + total);
            } else {
                System.out.println("No food item found with ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter numeric values for ID/Quantity.");
        }
    }
}
