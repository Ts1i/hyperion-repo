
public class Cat {
    //Attributes

    String name;
    String color;
    String eyeColor;
    Boolean pottyTrained;
    Boolean isAffectionate;

    //Constructor
    public Cat(String name, String color, String eyeColor, Boolean pottyTrained, Boolean isAffectionate) {
        this.name = name;
        this.color = color;
        this.eyeColor = eyeColor;
        this.pottyTrained = pottyTrained;
        this.isAffectionate = isAffectionate;
    }

    //Methods
    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    // Method to return the cat's attributes
    @Override
    public String toString() {
        String output = "Name: " + name;
        output += "\nColor: " + color;
        output += "\nEye color: " + eyeColor;
        output += "\nPotty Trained: " + pottyTrained;
        output += "\nAffectionate: " + isAffectionate;

        return output;
    }
}
