
import java.io.*;
import java.util.*;

public class DeliverySystem {

    private List<Driver> drivers;
    private Scanner scanner;

    public DeliverySystem() {
        drivers = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadDrivers();
    }

    // Load drivers from file
    private void loadDrivers() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/drivers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                drivers.add(new Driver(parts[0], parts[1], Integer.parseInt(parts[2])));
            }
        } catch (IOException e) {
            System.out.println("Error loading drivers: " + e.getMessage());
        }
    }

    // Find an available driver for the order
    public Driver findAvailableDriver(String location) {
        return drivers.stream()
                .filter(d -> d.canTakeOrder() && d.matchesLocation(location))
                .findFirst()
                .orElse(null);
    }

    // Process the order, with the order class as the input
    public void processOrder(Order order) {
        try {
            String filename = "src/output/" + order.getUid() + ".txt";
            PrintWriter writer = new PrintWriter(new FileWriter(filename));

            // Check if the order location is valid
            // If not, write "Sorry!" to the file
            if (!order.isValidLocation()) {
                writer.println("Sorry!");
            } else { // If the location is valid
                Driver driver = findAvailableDriver(order.getCustomer().getLocation());
                if (driver != null) {
                    Invoice invoice = new Invoice(order);
                    writer.println("Success!");
                    writer.println(invoice.toString());
                } else {
                    writer.println("Sorry!");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
