import Components.RandomWords;

import java.util.Random;

public class HangmanGame {

    public static void main(String[] args) {

        int random = new Random().nextInt(RandomWords.RandomWords.length);
        String word = RandomWords.RandomWords[random];

        System.out.println(word);

        Engine engine = new Engine(word);
        engine.startGame();

    }

}
