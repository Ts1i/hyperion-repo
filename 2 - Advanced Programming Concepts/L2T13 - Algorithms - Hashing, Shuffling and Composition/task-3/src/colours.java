import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class colours {
  private ArrayList<String> names;
  private ArrayList<String> colors;
  private Scanner scanner;

  // Constructor: Colours object that initializes the names and colors lists
  public colours() {
    names = new ArrayList<>();
    colors = new ArrayList<>();
    scanner = new Scanner(System.in);
  }

  // Gets the number of names and colors to enter from the user
  // Then gets the names and colors from the user
  // Adds the names and colors to the names and colors lists
  private void getInput() {
    System.out.print("How many names/colors do you want to enter? ");
    int count = Integer.parseInt(scanner.nextLine().trim());

    System.out.println("\nEnter " + count + " names:");
    for (int i = 0; i < count; i++) {
      System.out.print("Name " + (i + 1) + ": ");
      names.add(scanner.nextLine().trim());
    }

    System.out.println("\nEnter " + count + " colors:");
    for (int i = 0; i < count; i++) {
      System.out.print("Color " + (i + 1) + ": ");
      colors.add(scanner.nextLine().trim());
    }
  }

  private void assignColors() {
    // Shuffle the colors list for random assignment
    Collections.shuffle(colors);
    
    System.out.println("\nRandom Color Assignments:");
    for (int i = 0; i < names.size(); i++) {
      System.out.printf("%s - %s%n", names.get(i), colors.get(i));
    }
  }

  public static void main(String[] args) {
    try {
      colours program = new colours();
      program.getInput();
      program.assignColors();
    } catch (NumberFormatException e) {
      System.out.println("Error: Please enter a valid number.");
    } catch (Exception e) {
      System.out.println("An error occurred: " + e.getMessage());
    }
  }
}