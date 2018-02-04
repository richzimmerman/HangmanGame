import Components.Man;

import java.io.Console;
import java.util.*;

public class Engine {

    private String word;

    public Engine(String word) {
        this.word = word;
    }

    public void startGame() {

        Console console = System.console();
        int totalGuesses = 0;
        int correctGuessCount = 0;
        int MAX_CORRECT_GUESSES = getAnswers().size();
        int incorrectGuessCount = 0;
        int MAX_WRONG_GUESSES = 7;
        ArrayList<String> correctGuesses = new ArrayList<>();
        ArrayList<String> incorrectGuesses = new ArrayList<>();
        Man man = new Man();

        // Game Loop.
        while (incorrectGuessCount < MAX_WRONG_GUESSES &&
                correctGuessCount < MAX_CORRECT_GUESSES) {
            // Clear screen.
            clearScreen();

            correctGuesses.sort(Comparator.naturalOrder());
            incorrectGuesses.sort(Comparator.naturalOrder());

            System.out.print("Correct guesses: ");
            correctGuesses.forEach(x -> System.out.print(x));
            System.out.println();
            System.out.print("Incorrect guesses: ");
            incorrectGuesses.forEach(x -> System.out.print(x));
            System.out.println();

            // Print guessing progess.
            System.out.print("Word: ");
            for (int i = 0; i < word.length(); i++) {
                String letter = Character.toString(word.charAt(i));
                if (correctGuesses.contains(letter)) {
                    System.out.print(letter);
                } else {
                    System.out.print("_");
                }
            }
            System.out.println("\n");

            // Print the platform thus far.
            man.getPlatform();

            System.out.println(" ");
            System.out.println("Enter a letter or type \"quit\" to quit.");
            String guess = console.readLine("What is your next guess? : ").toLowerCase();

            if (guess.equals("quit")) {
                System.out.println("Quitting game...");
                System.exit(0);
            }

            if (guess.length() > 1) {
                clearScreen();
                System.out.println("You can only have 1 guess.");
                sleep(5000);
                continue;
            }

            if (isNumeric(guess)) {
                System.out.println("You can only guess a letter, not numbers.");
                sleep(3000);
                continue;
            }

            if (correctGuesses.contains(guess) || incorrectGuesses.contains(guess)) {
                System.out.printf("You've already guessed %s, try again.\n", guess);
                sleep(3000);
                continue;
            }

            totalGuesses += 1;

            if (word.contains(guess)) {
                correctGuesses.add(guess);
                correctGuessCount += 1;
            } else {
                incorrectGuesses.add(guess);
                incorrectGuessCount += 1;
                man.wrongGuess(incorrectGuessCount);
            }

        }

        clearScreen();
        man.getPlatform();
        if (incorrectGuessCount == MAX_WRONG_GUESSES) {
            System.out.println("You lost!");
        } else {
            System.out.println("You win!");
        }
        System.out.println(" ");
        System.out.printf("Number of guesses: %s \n", totalGuesses);
        System.out.printf("Answer: %s \n", word);

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // Do nothing.
        }
    }

    private void clearScreen() {
        for (int i = 0; i < 26; i++) {
            System.out.println();
        }
    }

    private boolean isNumeric(String guess) {
        try {
            Integer.parseInt(guess);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Set<String> getAnswers() {
        String[] wordArray = this.word.split("");
        Set<String> set = new HashSet<>();
        for (String letter : wordArray) {
            set.add(letter);
        }
        return set;
    }

}
