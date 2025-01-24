public class Scientist extends Person {
  private String labCoatColor;
  private String typeOfScientist;

  public Scientist(int height, String hairColor, boolean isAdult, String labCoatColor) {
      super(height, hairColor, isAdult);
      this.labCoatColor = labCoatColor;
      this.typeOfScientist = determineScientistType(labCoatColor);
  }

  // Method to determine the type of scientist based on the lab coat color
  private String determineScientistType(String color) {
      return switch (color.toLowerCase()) {
          case "green" -> "Biologist";
          case "purple" -> "Physicist";
          case "white" -> "Pharmacist";
          case "blue" -> "Climatologist";
          case "yellow" -> "Geneticist";
          case "red" -> "Zoologist";
          default -> "Unknown Scientist";
      };
  }

  // Getters
  public String getLabCoatColor() {
      return labCoatColor;
  }

  public String getTypeOfScientist() {
      return typeOfScientist;
  }

  // Setters
  public void setLabCoatColor(String labCoatColor) {
      this.labCoatColor = labCoatColor;
  }

  // Method to return the scientist's attributes
  @Override
  public String toString() {
      return "Scientist Details:\n" +
             super.toString() + "\n" +
             "Lab Coat Color: " + labCoatColor + "\n" +
             "Type of Scientist: " + typeOfScientist;
  }
}