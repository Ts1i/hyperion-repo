
public class AnimalFactory {

  public Animal createAnimal(String type) {
    if (type == null) {
      throw new IllegalArgumentException("Animal type cannot be null");
    }

    // Creates the appropriate animal. Throws an error if an unknown animal type is provided.
    switch (type.toLowerCase()) {
      case "kangaroo":
        return new Kangaroo();
      case "cheetah":
        return new Cheetah();
      case "lion":
        return new Lion();
      default:
        throw new IllegalArgumentException("Unknown animal type: " + type);
    }
  }
}
