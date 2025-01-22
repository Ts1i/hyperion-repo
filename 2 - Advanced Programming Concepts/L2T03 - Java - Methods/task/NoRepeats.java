
import java.util.LinkedHashSet;
import java.util.Scanner;

public class NoRepeats {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for a string
        System.out.print("Enter a string: ");
        // Read the string from the user (i.e. the next line)
        String input = scanner.nextLine();

        // Convert string to char array and remove duplicates (defined below)
        String result = removeDuplicates(input);

        // Output the result
        System.out.println("String with duplicates removed: " + result);

        // Close the scanner
        scanner.close();
    }

    // Method that removes duplicate characters from a string. Space characters are skipped. Method is case-insensitive.
    public static String removeDuplicates(String str) {
        // Convert string to char array
        char[] chars = str.toCharArray();

        // Use LinkedHashSet to automatically remove duplicates efficiently
        LinkedHashSet<Character> charSet = new LinkedHashSet<>();
        for (char c : chars) {
            if (c == ' ' || !Character.isLetter(c)) {
                continue;  // Skip spaces and punctuation
            }
            charSet.add(Character.toLowerCase(c));
        }

        // Build result string
        StringBuilder sb = new StringBuilder();
        // Loop through the original char array
        for (char c : chars) {
            if (c == ' ' || !Character.isLetter(c)) {
                sb.append(c);  // Keep spaces and punctuation
            } else if (charSet.contains(Character.toLowerCase(c))) {
                sb.append(c);  // Append orignal character to result string
                // Remove the appended character from the set
                charSet.remove(Character.toLowerCase(c));
            }
        }

        // Return the result string
        return sb.toString();
    }
}
