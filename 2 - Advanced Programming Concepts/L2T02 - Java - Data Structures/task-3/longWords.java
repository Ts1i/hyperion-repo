import java.util.Scanner;
import java.util.ArrayList;

public class longWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create an ArrayList to store the words with a dynamic size
        ArrayList<String> wordsList = new ArrayList<>();

        System.out.println("Enter words (enter '0' to stop):");

        // Get user input until '0' is entered
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            wordsList.add(input);
        }

        // Convert ArrayList to array
        String[] words = wordsList.toArray(new String[0]);

        // Count words longer than 4 characters
        int fourLetterWordsCount = 0;
        int totalLength = 0;

        for (String word : words) {
            if (word.length() > 4) {
                fourLetterWordsCount++;
            }
            totalLength += word.length();
        }

        // Calculate average word length
        double averageLength = words.length > 0 ? (double) totalLength / words.length : 0;

        // Print results
        System.out.println("\nNumber of words longer than 4 characters: " + fourLetterWordsCount);
        System.out.printf("Average word length: %.2f characters\n", averageLength);

        scanner.close();
    }
}