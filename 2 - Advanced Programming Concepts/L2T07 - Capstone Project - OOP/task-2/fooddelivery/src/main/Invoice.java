
import java.time.LocalDateTime;
import java.util.HashMap;

public class Invoice {

    private Order order;
    private double totalAmount;
    private LocalDateTime timestamp;

    public Invoice(Order order) {
        this.order = order;
        this.timestamp = LocalDateTime.now();
        calculateTotal();
    }

    private void calculateTotal() {
        totalAmount = 0;
        HashMap<String, Integer> items = order.getOrderItems();
        Restaurant restaurant = order.getRestaurant();

        for (String item : items.keySet()) {
            totalAmount += restaurant.getItemPrice(item) * items.get(item);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== INVOICE ===\n");
        sb.append(order.getCustomer().toString()).append("\n\n");
        sb.append(order.getRestaurant().toString()).append("\n\n");
        sb.append("Items:\n");
        for (String item : order.getOrderItems().keySet()) {
            sb.append(item).append(" x").append(order.getOrderItems().get(item))
                    .append(" @ R").append(order.getRestaurant().getItemPrice(item))
                    .append("\n");
        }
        sb.append("\nTotal: R").append(String.format("%.2f", totalAmount));
        sb.append("\nTime: ").append(timestamp);
        return sb.toString();
    }
}
