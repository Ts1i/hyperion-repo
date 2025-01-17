import java.util.Arrays;

// An array of IQ test results is given. Calculate the average IQ score and determine the category of the average IQ score.
public class IQ {
    public static void main(String[] args) {
        int[] testResults = { 69, 80, 115, 125, 150 };

        // Calculate the sum of the array with the Arrays utility class
        int sum = Arrays.stream(testResults).sum();

        // Calculate the average of the array
        int averageOfResults = sum / testResults.length;

        // Determine the category of the average IQ score
        String category;
        if (averageOfResults <= 69) {
            category = "Below Average";
        } else if (averageOfResults >= 70 && averageOfResults <= 79) {
            category = "Borderline";
        } else if (averageOfResults >= 80 && averageOfResults <= 89) {
            category = "Low average";
        } else if (averageOfResults >= 90 && averageOfResults <= 109) {
            category = "Average";
        } else if (averageOfResults >= 110 && averageOfResults <= 119) {
            category = "Above Average";
        } else if (averageOfResults >= 120 && averageOfResults <= 129) {
            category = "Superior";
        } else {
            category = "Very Superior";
        }

        // Output the average IQ score and the category
        System.out.println("Average IQ Score: " + averageOfResults);
        System.out.println("Category: " + category);
    }
}
