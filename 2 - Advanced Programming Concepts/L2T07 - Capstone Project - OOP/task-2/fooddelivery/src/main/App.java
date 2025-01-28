import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      DeliverySystem system = new DeliverySystem();
      Customer customer = null;

      // Ask if new or returning customer
      System.out.print("Are you a new customer? (y/n): ");
      String isNew = scanner.nextLine().trim().toLowerCase();

      if (isNew.equals("y")) {
        // Get new customer details
        System.out.println("\nPlease enter your details:");
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Contact number: ");
        String contact = scanner.nextLine().trim();
        System.out.print("Address: ");
        String address = scanner.nextLine().trim();
        System.out.print("Location: ");
        String location = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim().toLowerCase();

        try {
          customer = new Customer(name, contact, address, location, email);
          system.addCustomer(customer);
        } catch (IllegalArgumentException e) {
          System.out.println("Error creating customer: " + e.getMessage());
          return;
        }
      } else {
        // Get returning customer email
        while (customer == null) {
          System.out.print("Enter your email: ");
          String email = scanner.nextLine().trim().toLowerCase();
          customer = system.findCustomerByEmail(email);
          if (customer == null) {
            System.out.println("Customer not found. Please try again.");
          }
        }
      }

      // Show restaurants
      List<Restaurant> restaurants = system.getRestaurants();
      final String customerLocation = customer.getLocation();
      List<Restaurant> localRestaurants = restaurants.stream()
        .filter(r -> r.getLocation().equalsIgnoreCase(customerLocation))
        .collect(Collectors.toList());

      System.out.println("\nAvailable Restaurants in " + customerLocation + ":");
      if (localRestaurants.isEmpty()) {
        System.out.println("No restaurants found in your area.");
      }

      for (int i = 0; i < localRestaurants.size(); i++) {
        Restaurant r = localRestaurants.get(i);
        System.out.printf("%d. %s%n", i + 1, r.getName());
      }

      System.out.println("0. Add new restaurant");
      System.out.print("\nSelect option: ");
      int choice = Integer.parseInt(scanner.nextLine().trim());

      Restaurant selectedRestaurant;
      if (choice == 0) {
        // Get new restaurant details
        System.out.println("\nEnter restaurant details:");
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Location: ");
        String location = scanner.nextLine().trim();
        // Checks whether the location is the same as the customer's location
        if (!location.equalsIgnoreCase(customer.getLocation())) {
          System.out.println("Error: restaurant location does not match your location");
          return;
        }
        System.out.print("Contact number: ");
        String contact = scanner.nextLine().trim();
        
        selectedRestaurant = new Restaurant(name, location, contact);
        
        // Add menu items
        System.out.println("\nAdd menu items (enter 'done' when finished)");
        while (true) {
          System.out.print("\nItem name (or 'done'): ");
          String item = scanner.nextLine().trim();
          if (item.equalsIgnoreCase("done")) break;
          
          System.out.print("Price: R");
          try {
              double price = Double.parseDouble(scanner.nextLine().trim());
              selectedRestaurant.addMenuItem(item, price);
          } catch (NumberFormatException e) {
              System.out.println("Invalid price. Please try again.");
          }
        }
        
        system.addRestaurant(selectedRestaurant);
        System.out.println("\nRestaurant added successfully!");
      } else {
        selectedRestaurant = restaurants.get(choice - 1);
      }

      // Create order
      Order order = new Order(customer, selectedRestaurant);

      // Show menu and get items
      HashMap<String, Double> menu = selectedRestaurant.getMenu();
      System.out.println("\nMenu Items:");
      menu.forEach((item, price) -> 
        System.out.printf("%s (R%.2f)%n", item, price));

      // Builds up the order for the user. The user can add multiple items to the order.
      // The user must first enter the item that they want to add to the order. Followed by the quantity.
      while (true) {
        System.out.print("\nEnter item name (or 'done' to finish): ");
        String item = scanner.nextLine().trim();
        
        if (item.equalsIgnoreCase("done")) {
          if (order.getOrderItems().isEmpty()) {
            System.out.println("Order must contain at least one item.");
            continue;
          }
          break;
        }
        
        if (!menu.containsKey(item)) {
          System.out.println("Item not found. Available items:");
          menu.keySet().forEach(menuItem -> System.out.println("- " + menuItem));
          continue;
        }
    
        System.out.print("Enter quantity: ");
        try {
          int quantity = Integer.parseInt(scanner.nextLine().trim());
          if (quantity > 0) {
            order.addItem(item, quantity);
            System.out.printf("Added: %d x %s%n", quantity, item);
          } else {
            System.out.println("Please enter a positive quantity.");
          }
        } catch (NumberFormatException e) {
          System.out.println("Invalid quantity. Please enter a number.");
        }
      }

      // Process order
      system.processOrder(order);
      System.out.println("\nOrder processed! Check: invoice-" + order.getUid() + ".txt");

    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }
}