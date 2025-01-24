public class Pets {
  
  public static void main(String[] args) {
      Dog Thor = new Dog("Thor", 6, "Pug", "Fawn", true);
      Dog Coffee = new Dog("Coffee", 1, "Poodle", "Brown", false);

      Cat Whisper = new Cat("Whisper", "Black", "Blue", true, true);

      // Display all pets
      System.out.println("Here are all your pets:\nDogs\n");
      System.out.println(Thor.toString() + "\n");
      System.out.println(Coffee.toString() + "\n");
      System.out.println("Cats\n");
      System.out.println(Whisper.toString());
  }
}
