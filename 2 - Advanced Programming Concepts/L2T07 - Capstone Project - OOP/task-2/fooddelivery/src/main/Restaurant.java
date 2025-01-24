
import java.util.HashMap;

public class Restaurant {

    private String name;
    private String location;
    private String contactNumber;
    private HashMap<String, Double> menu;

    public Restaurant(String name, String location, String contactNumber) {
        this.name = name;
        this.location = location;
        this.contactNumber = contactNumber;
        this.menu = new HashMap<>();
    }

    // Methods
    // Adds an item to the menu with the price
    public void addMenuItem(String item, double price) {
        menu.put(item, price);
    }

    // Returns the price of an item. If the item is not found, return 0.0
    public double getItemPrice(String item) {
        return menu.getOrDefault(item, 0.0);
    }

    // Getters
    public String getLocation() {
        return location;
    }

    public HashMap<String, Double> getMenu() {
        return menu;
    }

    @Override
    public String toString() {
        return "Restaurant: " + name + "\nLocation: " + location
                + "\nContact: " + contactNumber;
    }
}
