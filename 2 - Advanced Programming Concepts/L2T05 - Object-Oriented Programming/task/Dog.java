
public class Dog {

    //Attributes
    String name;
    int age;
    String breed;
    String color;
    Boolean pottyTrained;

    //Constructor
    public Dog(String name, int age, String breed, String color, Boolean pottyTrained) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.color = color;
        this.pottyTrained = pottyTrained;
    }

    //Methods
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Method to return the dog's attributes
    @Override
    public String toString() {
        String output = "Name: " + name;
        output += "\nAge: " + age;
        output += "\nBreed: " + breed;
        output += "\nColor: " + color;
        output += "\nPotty Trained: " + pottyTrained;

        return output;
    }
}
