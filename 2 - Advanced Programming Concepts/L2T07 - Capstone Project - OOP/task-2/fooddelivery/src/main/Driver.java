
public class Driver {

    private String name;
    private String location;
    private int currentLoad;

    // Assume maximum load is 5
    private static final int MAX_LOAD = 30;

    public Driver(String name, String location, int currentLoad) {
        this.name = name;
        this.location = location;
        this.currentLoad = currentLoad;
    }

    public boolean canTakeOrder() {
        return currentLoad < MAX_LOAD;
    }

    public boolean matchesLocation(String orderLocation) {
        return this.location.equalsIgnoreCase(orderLocation);
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }
}
