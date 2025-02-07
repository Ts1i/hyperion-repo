import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RestaurantManager {

  private DeliverySystem system;
  private DataStore dataStore;
  private Scanner scanner;

  public RestaurantManager(DeliverySystem system, Scanner scanner) {
    this.system = system;
    this.scanner = scanner;
  }

  // Display the list of restaurants dependent on the customer's location
  public Restaurant selectRestaurant(String customerLocation) {
    List<Restaurant> localRestaurants = getLocalRestaurants(customerLocation);
    displayRestaurants(localRestaurants, customerLocation);
    return handleRestaurantSelection(localRestaurants, customerLocation);
  }

  private List<Restaurant> getLocalRestaurants(String customerLocation) {
    return dataStore.getRestaurants().stream()
      .filter(r -> r.getLocation().equalsIgnoreCase(customerLocation))
      .collect(Collectors.toList());
  }

  // Display all restaurants and give the user the option to add a new restaurant
  private void displayRestaurants(List<Restaurant> localRestaurants, String location) {
    System.out.println("\nAvailable Restaurants in " + location + ":");
    if (localRestaurants.isEmpty()) {
      System.out.println("No restaurants found in your area.");
    }

    for (int i = 0; i < localRestaurants.size(); i++) {
      Restaurant r = localRestaurants.get(i);
      System.out.printf("%d. %s%n", i + 1, r.getName());
    }
    System.out.println("0. Add new restaurant");
    System.out.print("\nSelect option: ");
  }

  // Handles the user's restaurant selection
  private Restaurant handleRestaurantSelection(List<Restaurant> localRestaurants, String customerLocation) {
    try {
      int choice = Integer.parseInt(scanner.nextLine().trim());

      if (choice == 0) {
        return createNewRestaurant(customerLocation);
      } else if (choice > 0 && choice <= localRestaurants.size()) {
        return localRestaurants.get(choice - 1);
      } else {
        throw new IllegalArgumentException("Invalid selection");
      }
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid input. Please enter a number.");
    }
  }

  // Create a new restaurant
  private Restaurant createNewRestaurant(String customerLocation) {
    System.out.println("\nEnter restaurant details:");
    System.out.print("Name: ");
    String name = scanner.nextLine().trim();
    
    System.out.print("Location: ");
    String location = scanner.nextLine().trim();
    if (!location.equalsIgnoreCase(customerLocation)) {
      throw new IllegalArgumentException("Restaurant location does not match your location");
    }
    
    System.out.print("Contact number: ");
    String contact = scanner.nextLine().trim();
    
    Restaurant newRestaurant = new Restaurant(name, location, contact);
    
    // Add menu items
    System.out.println("\nAdd menu items (enter 'done' when finished)");
    while (true) {
      System.out.print("\nItem name (or 'done'): ");
      String item = scanner.nextLine().trim();
      if (item.equalsIgnoreCase("done")) {
        if (newRestaurant.getMenu().isEmpty()) {
          throw new IllegalArgumentException("Restaurant must have at least one menu item");
        }
        break;
      }
      
      System.out.print("Price: R");
      try {
        double price = Double.parseDouble(scanner.nextLine().trim());
        if (price <= 0) {
          throw new IllegalArgumentException("Price must be greater than 0");
        }
        newRestaurant.addMenuItem(item, price);
      } catch (NumberFormatException e) {
        System.out.println("Invalid price. Please try again.");
      }
    }
    
    dataStore.addRestaurant(newRestaurant);
    System.out.println("\nRestaurant added successfully!");
    return newRestaurant;
  }

}
