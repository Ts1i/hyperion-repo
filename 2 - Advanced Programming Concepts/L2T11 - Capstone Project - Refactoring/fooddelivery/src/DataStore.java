import java.io.*;
import java.util.*;

public class DataStore {

  private List<Driver> drivers;
  private List<Restaurant> restaurants;
  private List<Customer> customers;

  public DataStore() {
    // Initialize lists
    drivers = new ArrayList<>();
    restaurants = new ArrayList<>();
    customers = new ArrayList<>();

    // Load data from files
    loadDrivers();
    loadRestaurants();
    loadCustomers();
  }

  public List<Driver> getDrivers() {
    return new ArrayList<>(drivers);
  }

  // ----------------------------------Data loading methods----------------------------------

  // Method to load drivers from file into Driver objects. Has error handling for valid driver objects.
  private void loadDrivers() {
    try (BufferedReader br = new BufferedReader(new FileReader("../data/drivers.txt"))) {
      String line;
      while ((line = br.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty()) {
          continue;
        }

        String[] parts = line.split(",");
        if (parts.length != 3) { // Error handling for invalid driver data
          System.out.println("Invalid driver data: " + line);
          continue;
        }

        try {
          drivers.add(new Driver( // Add driver to the list
            parts[0].trim(),
            parts[1].trim(),
            Integer.parseInt(parts[2].trim())
          ));
        } catch (NumberFormatException e) {
          System.out.println("Invalid load value for driver: " + line);
        }
      }
    } catch (IOException e) {
      System.out.println("Error loading drivers: " + e.getMessage());
    }
  }
  
  // Reads Restaurant clients into Restaurant objects
  private void loadRestaurants() {
    Restaurant currentRestaurant = null;
    try (BufferedReader br = new BufferedReader(new FileReader("../data/restaurantClients.txt"))) {
      String line;
      while ((line = br.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty()) {
          continue;
        }

        String[] parts = line.split(",");
        if (parts.length == 3) {  // Restaurant details
          currentRestaurant = new Restaurant(
            parts[0].trim(),
            parts[1].trim(),
            parts[2].trim()
          );
          restaurants.add(currentRestaurant);
        } else if (parts.length == 2 && currentRestaurant != null) {  // Menu item details
          try {
            String item = parts[0].trim();
            double price = Double.parseDouble(parts[1].trim());
            currentRestaurant.addMenuItem(item, price);
          } catch (NumberFormatException e) {
            System.out.println("Invalid price format: " + line);
          }
        }
      }
    } catch (IOException e) {
      System.out.println("Error loading restaurants: " + e.getMessage());
    }
  }
  
  // Read customers into Customer objects
  private void loadCustomers() {
    try (BufferedReader br = new BufferedReader(new FileReader("../data/activeUsers.txt"))) {
      String line;
      while ((line = br.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty()) {
          continue;
        }

        String[] parts = line.split(",");
        if (parts.length != 5) {
          System.out.println("Invalid customer data: " + line);
          continue;
        }

        try {
          customers.add(new Customer(
            parts[0].trim(),
            parts[1].trim(),
            parts[2].trim(),
            parts[3].trim(),
            parts[4].trim()
          ));
        } catch (Exception e) {
          System.out.println("Error creating customer from: " + line);
        }
      }
    } catch (IOException e) {
      System.out.println("Error loading customers: " + e.getMessage());
    }
  }

  // ----------------------------------Data access methods----------------------------------

  // Find customers on record from the entered email address
  public Customer findCustomerByEmail(String email) {
    return customers.stream()
      .filter(c -> c.getEmail().equalsIgnoreCase(email))
      .findFirst()
      .orElse(null);
  }

  // Checks for the customer in the list of customers
  public boolean isCustomerValid(Customer customer) {
    return customers.contains(customer);
  }
  
  // Checks for the restaurant in the list of restaurants
  public boolean isRestaurantValid(Restaurant restaurant) {
    return restaurants.contains(restaurant);
  }
  
  // Make Restaurants available to Public
  public List<Restaurant> getRestaurants() {
    return restaurants;
  }

  // ----------------------------------Data creation methods----------------------------------

  // Adds a new customer to the list of active users
  public void addCustomer(Customer customer) {
    if (customer == null) {
      throw new IllegalArgumentException("Customer cannot be null");
    }
    
    // Check if customer already exists
    if (findCustomerByEmail(customer.getEmail()) != null) {
      throw new IllegalArgumentException("Customer with this email already exists");
    }
    
    // Add to list
    customers.add(customer);
    
    // Append to file
    try (PrintWriter writer = new PrintWriter(new FileWriter("../data/activeUsers.txt", true))) {
    writer.printf("%s,%s,%s,%s,%s%n",
      customer.getName(),
      customer.getContactNumber(),
      customer.getAddress(),
      customer.getLocation(),
      customer.getEmail());
      writer.flush();
    } catch (IOException e) {
      System.out.println("Warning: Could not save customer to file: " + e.getMessage());
    }
  }
  
  // Adds a new restaurant to the list of restaurants
  public void addRestaurant(Restaurant restaurant) {
    if (restaurant == null) {
      throw new IllegalArgumentException("Restaurant cannot be null");
    }
    restaurants.add(restaurant);
    
    try (PrintWriter writer = new PrintWriter(new FileWriter("../data/restaurantClients.txt", true))) {
      // Prints a blank line before the new entry
      File file = new File("../data/restaurantClients.txt");
      if (file.length() > 0) {
        writer.println();
      }
        
      // Writes the new restaurant details
      writer.printf("%s,%s,%s%n", 
        restaurant.getName(),
        restaurant.getLocation(),
        restaurant.getContactNumber());
            
      // Write menu items
      restaurant.getMenu().forEach((item, price) -> 
          writer.printf("%s,%.2f%n", item, price));
            
      writer.println(); // Add blank line between restaurants
    } catch (IOException e) {
      System.out.println("Warning: Could not save restaurant to file: " + e.getMessage());
    }
  }

}