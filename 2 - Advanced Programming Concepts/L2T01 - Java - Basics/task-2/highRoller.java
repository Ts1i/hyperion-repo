import java.util.Random;
import java.util.Scanner;

// A simple dice game where the user and the computer roll a dice and the highest roll wins the round.
// Overall winner is the one who wins 2 rounds first.

public class highRoller {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int userWins = 0;
        int computerWins = 0;
        int rounds = 0;

        while (rounds < 3) {

            // Computer rolls the dice
            int computerRoll = random.nextInt(6) + 1;
            System.out.println("Computer rolled: " + computerRoll);

            // User is asked if they want to roll the dice
            System.out.print("Roll your dice? (y/n): ");
            String choice = scanner.nextLine();

            // If user doesn't want to roll the dice, the game ends
            if (choice.equalsIgnoreCase("n")) {
                System.out.println("Game Over");
                break;
            }

            // User rolls the dice
            int userRoll = random.nextInt(6) + 1;
            System.out.println("You rolled: " + userRoll);

            // Determines the winner of the round by which roleplayer rolled the highest
            // number. Adds an increment to the winner's score.
            if (userRoll > computerRoll) {
                System.out.println("User wins this round!");
                userWins++;
            } else if (computerRoll > userRoll) {
                System.out.println("Computer wins this round!");
                computerWins++;
            } else {
                System.out.println("It's a tie!");
            }

            // Increments the number of rounds played
            rounds++;

            // If either the user or the computer wins 2 rounds, the game ends
            if (userWins == 2 || computerWins == 2)
                break;

            // The user is asked if they want to continue playing the game after each round
            if (rounds < 3) {
                System.out.print("Would you like to continue? (y/n): ");
                if (!scanner.nextLine().equalsIgnoreCase("y")) {
                    System.out.println("Game Over");
                    break;
                };
            }
        }

        // Determines the overall winner of the game
        if (userWins > computerWins) {
            System.out.println("User wins the game!");
        } else if (computerWins > userWins) {
            System.out.println("Computer wins the game!");
        } else {
            System.out.println("Game ended in a tie!");
        }

        scanner.close();
    }
}
