import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Poetry {
    
    // Shift value for Caesar encryption
    private static final int SHIFT = 15;

    public static void main(String[] args) {
        try {
            // Read message from input file
            String message = readFromFile("../17-004 Java - IO/poem.txt");
            
            // Encrypt message using Caesar cypher (below)
            String encryptedMessage = encryptMessage(message, SHIFT);
            
            // Write to output file
            writeToFile("encodedPoem.txt", encryptedMessage);
            
            System.out.println("Message encrypted successfully!");
            
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
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

    // Encrypt message using Caesar cipher (from Capstone2)
    private static String encryptMessage(String message, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                int code = (int) c;
                if (Character.isUpperCase(c)) {
                    c = (char) (((code - 65 + shift) % 26) + 65);
                } else {
                    c = (char) (((code - 97 + shift) % 26) + 97);
                }
            }
            result.append(c);
        }
        return result.toString();
    }
}