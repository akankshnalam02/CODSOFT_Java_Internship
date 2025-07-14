import java.util.*;

public class NumberGuessingGame {
    private static final int MAX_ATTEMPTS = 5;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;

    private static Scanner scanner = new Scanner(System.in);
    private static int totalScore = 0;
    private static int roundsPlayed = 0;
    private static int consecutiveWins = 0;

    public static void main(String[] args) {
        System.out.println("🎮 Welcome to the Number Guessing Game!");
        System.out.println("Guess the number between " + MIN_NUMBER + " and " + MAX_NUMBER);

        boolean playAgain;
        do {
            playGameRound();
            roundsPlayed++;
            playAgain = askToPlayAgain();
        } while (playAgain);

        showFinalResults();
        System.out.println("🎉 Thank you for playing!");
    }

    private static void playGameRound() {
        int targetNumber = new Random().nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
        int attemptsLeft = MAX_ATTEMPTS;
        boolean isGuessed = false;

        while (attemptsLeft > 0) {
            System.out.print("Enter your guess (" + attemptsLeft + " attempts left): ");
            int guess = getValidInput();

            if (guess == targetNumber) {
                int scoreThisRound = (attemptsLeft * 10);
                totalScore += scoreThisRound;
                consecutiveWins++;
                System.out.println("🎯 Correct! You've guessed the number.");
                System.out.println("✅ You earned " + scoreThisRound + " points this round.");
                if (consecutiveWins >= 3) {
                    System.out.println("🔥 Bonus: Win streak! +50 points!");
                    totalScore += 50;
                }
                isGuessed = true;
                break;
            } else if (guess > targetNumber) {
                System.out.println("📉 Too high!");
            } else {
                System.out.println("📈 Too low!");
            }
            attemptsLeft--;
        }

        if (!isGuessed) {
            consecutiveWins = 0;
            System.out.println("❌ Out of attempts! The number was: " + targetNumber);
        }
        System.out.println("🎮 End of this round.\n");
    }

    private static int getValidInput() {
        while (true) {
            try {
                int guess = Integer.parseInt(scanner.nextLine());
                if (guess >= MIN_NUMBER && guess <= MAX_NUMBER) {
                    return guess;
                } else {
                    System.out.print("⚠️ Please enter a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("⚠️ Invalid input. Please enter a number: ");
            }
        }
    }

    private static boolean askToPlayAgain() {
        System.out.print("Do you want to play another round? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.startsWith("y");
    }

    private static void showFinalResults() {
        System.out.println("\n====================");
        System.out.println("🏁 Game Summary:");
        System.out.println("🕹️ Rounds Played: " + roundsPlayed);
        System.out.println("⭐ Final Score: " + totalScore);
        if (roundsPlayed > 0) {
            System.out.printf("📊 Average Score per Round: %.2f\n", (totalScore * 1.0 / roundsPlayed));
        }
        System.out.println("====================\n");
    }
}
