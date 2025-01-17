import java.util.Scanner;

public class multipleElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize 3x4 array
        int[][] numbers = new int[3][4];

        // Get user input for first row
        System.out.println("Enter 4 numbers. These will populate the first row of a 3x4 matrix:");
        for (int i = 0; i < 4; i++) {
            System.out.print("Enter number " + (i + 1) + ": ");
            numbers[0][i] = scanner.nextInt();
        }

        // Populate second row (add first row numbers to themselves)
        for (int i = 0; i < 4; i++) {
            numbers[1][i] = numbers[0][i] + numbers[0][i];
        }

        // Populate third row (add first row numbers to second row numbers)
        for (int i = 0; i < 4; i++) {
            numbers[2][i] = numbers[0][i] + numbers[1][i];
        }

        // Print the array
        System.out.println("\nResulting 3x4 Matrix:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(numbers[i][j] + "\t");
            }
            System.out.println();
        }

        scanner.close();
    }
}
