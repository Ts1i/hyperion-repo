import java.io.*;
import java.util.*;

public class DeliverySystem {

  private DataStore dataStore;

  private static final String OUTPUT_DIR = "../output";
  private static final String INVOICE_PREFIX = "invoice-";

  public DeliverySystem() {
      this.dataStore = new DataStore();
  }

  // ----------------------------------Helper methods----------------------------------

  // Find an available driver for the order. Takes the driver with the least load and the same location as the order.
  // Load must be less than the MAX_LOAD
  // Returns the driver object if found, otherwise null
  
  public Driver findAvailableDriver(String location) {
    return dataStore.getDrivers().stream()
      .filter(d -> d.canTakeOrder() && d.matchesLocation(location))
      .min(Comparator.comparingInt(Driver::getCurrentLoad))
      .orElse(null);
  }

  // ----------------------------------Processing----------------------------------

  // Process the order, with the order class as the input
  
  public void processOrder(Order order) throws IOException {
    // Validate order
    if (order == null || order.getOrderItems().isEmpty()) {
      throw new IllegalArgumentException("Order cannot be null or empty");
    }
    if (!dataStore.isCustomerValid(order.getCustomer())) {
      throw new IllegalArgumentException("Invalid customer");
    }
    if (!dataStore.isRestaurantValid(order.getRestaurant())) {
      throw new IllegalArgumentException("Invalid restaurant");
    }

    // Create output directory if it doesn't exist
    File outputDir = new File(OUTPUT_DIR);
    if (!outputDir.exists()) {
      outputDir.mkdirs();
    }

    String filename = OUTPUT_DIR + "/" + INVOICE_PREFIX + order.getUid() + ".txt";

    // Generates the invoice if there's a driver and location in the customer's desired order location
    try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
      if (!order.isValidLocation()) {
        writer.println("Sorry!");
        writer.println("Our drivers are too far away from you to be able to deliver to your location.");
        return;
      }

      Driver driver = findAvailableDriver(order.getCustomer().getLocation());
      if (driver == null) {
        writer.println("Sorry!");
        writer.println("No available drivers in your area at this time.");
        return;
      }

      try {
        Invoice invoice = new Invoice(order, driver);
        writer.println("Success!");
        writer.println(invoice.toString()); // Generate invoice if successfull
      } catch (Exception e) {
        writer.println("Sorry!");
        writer.println("Error generating invoice: " + e.getMessage());
      }
    } catch (IOException e) {
      throw new IOException("Failed to write order file: " + e.getMessage());
    }
  }

}
