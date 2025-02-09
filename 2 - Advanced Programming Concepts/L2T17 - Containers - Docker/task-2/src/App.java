import java.io.IOException;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    try (Scanner scanner = new Scanner(System.in)) {
      DeliverySystem system = new DeliverySystem();
      CustomerManager customerManager = new CustomerManager(system, scanner);
      RestaurantManager restaurantManager = new RestaurantManager(system, scanner);
      OrderManager orderManager = new OrderManager(system, scanner);

      try {
        // Get customer details
        Customer customer = customerManager.getCustomer();
        if (customer == null) {
          return;
        }

        // Select or create restaurant
        Restaurant restaurant = restaurantManager.selectRestaurant(customer.getLocation());
        if (restaurant == null) {
          return;
        }

        // Create and process order
        Order order = orderManager.createOrder(customer, restaurant);
        if (order != null) {
          system.processOrder(order);
          System.out.println("\nOrder processed! Check: invoice-" + order.getUid() + ".txt");
        }

      } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
      } catch (IOException e) {
        System.out.println("Error processing order: " + e.getMessage());
      }
      
    }
  }
}