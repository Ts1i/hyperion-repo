import java.util.HashMap;
import java.util.UUID;

public class Order {

  private String uid;
  private Customer customer;
  private Restaurant restaurant;
  private HashMap<String, Integer> orderItems;
  private String specialInstructions;

  // Constructor: Takes the customer and restaurant as parameters
  public Order(Customer customer, Restaurant restaurant) {
    this.uid = UUID.randomUUID().toString(); // Generate a random UUID for the order. Will be used for the invoice and naming conventions
    this.customer = customer;
    this.restaurant = restaurant;
    this.orderItems = new HashMap<>();
    this.specialInstructions = "";
  }

  // Methods
  // Add an item to the order with the quantity
  public void addItem(String item, int quantity) {
    orderItems.put(item, quantity);
  }

  // Compares locations of customer and restaurant, ignoring the cases (upper/lower)
  public boolean isValidLocation() {
    return customer.getLocation().equalsIgnoreCase(restaurant.getLocation());
  }

  // Getters
  public String getUid() {
    return uid;
  }

  public HashMap<String, Integer> getOrderItems() {
    return orderItems;
  }

  public Customer getCustomer() {
    return customer;
  }

  public Restaurant getRestaurant() {
    return restaurant;
  }

  public String getSpecialInstructions() {
  return specialInstructions;
}

  // Setters
  public void setSpecialInstructions(String instructions) {
    this.specialInstructions = instructions;
  }
}
