
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class passwordAuth {

  private static final int MIN_LENGTH = 8;
  private static final int MAX_LENGTH = 40;

    // Check whether the user has entered a password that contains a number
    private static boolean containsNumber(String password) {
      for (char c : password.toCharArray()) {
        if (Character.isDigit(c)) {
          return true;
        }
      }
      return false;
    }

    // Checks whether the password contains a special character
    private static boolean containsSpecialCharacter(String password) {
      String specialChars = "!@#$%^&*(),.?\":{}|<>";
      for (char c : password.toCharArray()) {
        if (specialChars.contains(String.valueOf(c))) {
          return true;
        }
      }
      return false;
    }

    // Check whether password meets length requirements. 
    // No stipulated requirements for uppercase or special characters.

    private static boolean isValidPassword(String password) {
      if (password == null || password.trim().isEmpty()) {
        throw new IllegalArgumentException("Password cannot be empty");
      }

      // Trim input
      password = password.trim();

      // Check password length
      if (password.length() < MIN_LENGTH) {
        throw new IllegalArgumentException("Password must be at least " + MIN_LENGTH + " characters");
      }
      if (password.length() > MAX_LENGTH) {
        throw new IllegalArgumentException("Password cannot exceed " + MAX_LENGTH + " characters");
      }

      // Check if password contains a number
      if (!containsNumber(password)) {
        throw new IllegalArgumentException("Password must contain at least one number");
      }

      // Check if password contains a special character
      if (!containsSpecialCharacter(password)) {
        throw new IllegalArgumentException("Password must contain at least one special character (!@#$%^&*(),.?\":{}|<>)");
      }

      return true;
    }

    // Write a successfully confirmed password to a file.
    private static void writePassword(String password) throws IOException {
      File dataDir = new File("../data");
      if (!dataDir.exists()) {
        dataDir.mkdir();
      }

      // Use timestamp to create a unique file name
      LocalDateTime now = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
      String timestamp = now.format(formatter);
      String filename = String.format("../data/password_created_%s.txt", timestamp);
      

      // Try printing the password to a text file
      try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
        writer.println("Password created; " + timestamp);  
        writer.println("Password: " + password);
        if (writer.checkError()) {
          throw new IOException("Error writing to file");
        }
      }
    }

    public static void main(String[] args) {
      try (Scanner scanner = new Scanner(System.in)) {

        // Ask the user to enter a password that contains a number.
        // Keep asking the user to enter a password until they enter a password that contains a number.
        while (true) {
          try{
            System.out.print("Enter a password. Must contain at least one number and special character, minimum length "
              + MIN_LENGTH + " chars, maximum length " + MAX_LENGTH + " chars: ");
            String password = scanner.nextLine().trim();

            // Validate password
            isValidPassword(password);

            System.out.print("Confirm password: ");
            String confirmPassword = scanner.nextLine().trim();

            if (!password.equals(confirmPassword)) {
              System.out.println("Error: Passwords do not match.");
              continue;
            }

            writePassword(password);
            System.out.println("Password successfully confirmed and saved to file.");
            break;
          } catch (IllegalArgumentException e) {
            System.out.println("Invalid password: " + e.getMessage());
          }
        }

      } catch (IOException e) {
        System.out.println("Error writing to file: " + e.getMessage());
      }
    }

}
