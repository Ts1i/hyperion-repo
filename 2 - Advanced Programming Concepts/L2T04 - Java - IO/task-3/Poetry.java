
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Poetry {

    private static final int SHIFT = 15;

    public static void main(String[] args) {
        try {
            // Read and reverse lines
            String encrypted = readFromFile("capitalVowels.txt");
            String reversed = reverseLines(encrypted);

            // Decrypt reversed content
            String decrypted = decryptMessage(reversed, SHIFT);

            // Write to output file
            writeToFile("reversePoem.txt", decrypted);

            System.out.println("Message decoded successfully!");

        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }

    // Reverse lines of a string (input from file used above)
    private static String reverseLines(String content) {
        String[] lines = content.split("\n");
        StringBuilder reversed = new StringBuilder();

        for (String line : lines) {
            reversed.append(new StringBuilder(line).reverse()).append("\n");
        }

        return reversed.toString();
    }

    // Decrypt message using the Caesar cipher
    private static String decryptMessage(String message, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char lowerChar = Character.toLowerCase(c);
                int code = (int) lowerChar;
                char decryptedChar;

                // Decrypt using negative shift
                decryptedChar = (char) (((code - 97 - shift + 26) % 26) + 97);

                result.append(decryptedChar);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    // String builder to read file content line by line and return a string
    // Reference: harilearning1989; https://github.com/harilearning1989/JavaPracticed/blob/f76380616fe7356099abb822215063f5189cbb07/src/main/java/com/thread/FileReadingWithMultithread.java
    private static String readFromFile(String filename) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    // Write content to file with buffered writer for efficient writing to disk
    private static void writeToFile(String filename, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
        }
    }

    // Check if a character is a vowel
    // Reference: armangral; https://github.com/armangral/leetcode-problems/blob/8c0ef721e0d8870f0de06e52d2cdb5803617b637/0345-reverse-vowels-of-a-string/0345-reverse-vowels-of-a-string.java 
    private static boolean isVowel(char c) {
        char lower = Character.toLowerCase(c);
        return lower == 'a' || lower == 'e' || lower == 'i' || lower == 'o' || lower == 'u';
    }
}
