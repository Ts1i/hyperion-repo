
public class Person {

    private int height;
    private String hairColor;
    private boolean isAdult;

    // Constructor
    public Person(int height, String hairColor, boolean isAdult) {
        this.height = height;
        this.hairColor = hairColor;
        this.isAdult = isAdult;
    }

    // Getters
    public int getHeight() {
        return height;
    }

    public String getHairColor() {
        return hairColor;
    }

    public boolean isAdult() {
        return isAdult;
    }

    // Setters
    public void setHeight(int height) {
        this.height = height;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public void setAdult(boolean isAdult) {
        this.isAdult = isAdult;
    }

    // Method to return the person's attributes
    @Override
    public String toString() {
        return "Person Details:\n"
                + "Height: " + height + " cm\n"
                + "Hair Color: " + hairColor + "\n"
                + "Adult: " + isAdult;
    }
}
