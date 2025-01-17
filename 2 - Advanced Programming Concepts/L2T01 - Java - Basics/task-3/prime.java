import java.util.Scanner;

// Gets a positive integer from the user and checks if it is a prime number.
public class prime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a positive integer: ");
        int userNumber = scanner.nextInt();

        // Validate if the user input is a positive integer
        if (userNumber <= 1) {
            System.out.println(userNumber + " is not a prime number.");
            scanner.close();
            return;
        }

        // Check whether the number is prime by looping through all numbers from 2 to
        // the square root of the number.
        // If the number is divisible by any number up to the square root of itself, it
        // is not a prime number.
        boolean isPrime = true;
        for (int i = 2; i <= Math.sqrt(userNumber); i++) {
            if (userNumber % i == 0) {
                isPrime = false;
                break;
            }
        }

        // Output result
        if (isPrime) {
            System.out.println(userNumber + " is a prime number.");
        } else {
            System.out.println(userNumber + " is not a prime number.");
        }
        scanner.close();
    }
}