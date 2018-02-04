package Components;

public class Man {

    private String[] platform =
            {" --------- ",
            " |       | ",
            " | ",
            " | ",
            " | ",
            " | ",
            " | ",
            " --------------"};

    private String[] wrongGuesses = {
            " |       O",
            " |      /",
            " |      /|",
            " |      /|\\",
            " |       |",
            " |      /",
            " |      / \\"
    };

    public void getPlatform() {
        for (String line : this.platform) {
            System.out.println(line);
        }
    }

    public void wrongGuess(int wrongGuessCount) {
        int wrongGuessIndex = wrongGuessCount - 1;
        int platformIndex = 0;
        switch (wrongGuessCount) {
            case 1:
                platformIndex = 2;
                break;
            case 2:
            case 3:
            case 4:
                platformIndex = 3;
                break;
            case 5:
                platformIndex = 4;
                break;
            case 6:
            case 7:
                platformIndex = 5;
                break;
        }
        this.platform[platformIndex] = wrongGuesses[wrongGuessIndex];
    }

}
