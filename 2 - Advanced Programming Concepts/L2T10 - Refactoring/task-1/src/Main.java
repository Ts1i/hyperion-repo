
public class Main {

  public static void main(String[] args) {
    try {

      AnimalFactory factory = new AnimalFactory();

      // Creates a kangaroo, cheetah, and lion
      Animal kangaroo = factory.createAnimal("Kangaroo");
      kangaroo.create();

      Animal cheetah = factory.createAnimal("Cheetah");
      cheetah.create();

      Animal lion = factory.createAnimal("Lion");
      lion.create();

    } catch (IllegalArgumentException e) {

      // Catches any errors that occur during the creation of an animal
      System.err.println("Error creating animal: " + e.getMessage());

    }
  }
}
