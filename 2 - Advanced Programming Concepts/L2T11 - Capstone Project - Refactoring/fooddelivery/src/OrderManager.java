
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class OrderManager {

  private final Scanner scanner;
  private final DeliverySystem system;

  public OrderManager(DeliverySystem system, Scanner scanner) {
    this.system = system;
    this.scanner = scanner;
  }

  // Create a new order once the customer and restaurant selections are clarified
  public Order createOrder(Customer customer, Restaurant restaurant) {
    Order order = new Order(customer, restaurant);
    displayMenu(restaurant);
    processOrderItems(order, restaurant);
    return order;
  }

  private void displayMenu(Restaurant restaurant) {
    HashMap<String, Double> menu = restaurant.getMenu();
    System.out.println("\nMenu Items:");
    List<String> menuItems = new ArrayList<>(menu.keySet());
    for (int i = 0; i < menuItems.size(); i++) {
      String item = menuItems.get(i);
      System.out.printf("%d. %s (R%.2f)%n", i + 1, item, menu.get(item));
    }
  }

  // Process the order items
  private void processOrderItems(Order order, Restaurant restaurant) {
    List<String> menuItems = new ArrayList<>(restaurant.getMenu().keySet());

    while (true) {
      try {
        System.out.print("\nEnter item number (or 0 to finish): ");
        int menuChoice = Integer.parseInt(scanner.nextLine().trim());

        if (menuChoice == 0) {
          if (order.getOrderItems().isEmpty()) {
            System.out.println("Order must contain at least one item.");
            continue;
          }
          break;
        }

        if (menuChoice < 1 || menuChoice > menuItems.size()) {
          System.out.println("Invalid selection. Please choose between 1 and " + menuItems.size());
          continue;
        }

        String selectedItem = menuItems.get(menuChoice - 1);
        processQuantity(order, selectedItem);

      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a number.");
      }
    }
  }

  // Handles the quantity input for the order
  private void processQuantity(Order order, String selectedItem) {
    try {
      System.out.print("Enter quantity: ");
      int quantity = Integer.parseInt(scanner.nextLine().trim());

      if (quantity > 0) {
        order.addItem(selectedItem, quantity);
        System.out.printf("Added: %d x %s%n", quantity, selectedItem);
      } else {
        System.out.println("Please enter a positive quantity.");
      }
    } catch (NumberFormatException e) {
      System.out.println("Invalid quantity. Please enter a number.");
    }
  }
}
