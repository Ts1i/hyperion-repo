import java.util.HashMap;

public class Invoice {

  private Order order;
  private double totalAmount;
  private Driver driver;

  public Invoice(Order order, Driver driver) {
    this.order = order;
    this.driver = driver;
    calculateTotal();
  }

  // Calculates the total amount of the order
  private void calculateTotal() {
    totalAmount = 0;
    HashMap<String, Integer> items = order.getOrderItems();
    Restaurant restaurant = order.getRestaurant();

    for (String item : items.keySet()) {
        totalAmount += restaurant.getItemPrice(item) * items.get(item); // Loops through all items on the order and calculates the total amount
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Customer customer = order.getCustomer();
    Restaurant restaurant = order.getRestaurant();

    // Build the invoice string
    sb.append("Order number ").append(order.getUid()).append("\n");
    sb.append("Customer: ").append(customer.getName()).append("\n");
    sb.append("Email: ").append(customer.getEmail()).append("\n");
    sb.append("Phone number: ").append(customer.getContactNumber()).append("\n");
    sb.append("Location: ").append(customer.getLocation()).append("\n\n");

    sb.append("You have ordered the following from ").append(restaurant.getName())
      .append(" in ").append(restaurant.getLocation()).append(":\n\n");

    for (String item : order.getOrderItems().keySet()) {
      int quantity = order.getOrderItems().get(item);
      double price = restaurant.getItemPrice(item);
      sb.append(quantity).append(" x ").append(item)
        .append(" (R").append(String.format("%.2f", price)).append(")\n");
    }

    if (order.getSpecialInstructions() != null && !order.getSpecialInstructions().isEmpty()) {
      sb.append("\nSpecial instructions: ").append(order.getSpecialInstructions()).append("\n");
    }

    sb.append("\nTotal: R").append(String.format("%.2f", totalAmount)).append("\n\n");

    sb.append(driver.getName()).append(" is the nearest to the restaurant and so they will be delivering your order to you at:\n\n");
    sb.append(customer.getAddress()).append("\n\n");
    sb.append("If you need to contact the restaurant, their number is ").append(restaurant.getContactNumber()).append(".");

    return sb.toString();
  }
}
