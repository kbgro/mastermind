package com.github.kbgro.mastermind;

public abstract class Guesser {
    protected final Table table;
    private final ColorManager manager;
    protected final Color[] lastGuess;
    public static final Color[] none = new Color[]{Color.none};

    public Guesser(Table table) {
        this.table = table;
        this.lastGuess = new Color[table.nColumns];
        this.manager = table.manager;
    }

    abstract protected void setFirstGuess();

    protected Color[] nextGuess() {
        if (lastGuess[0] == null) {
            setFirstGuess();
            return lastGuess;
        } else {
            return nextNonFirstGuess();
        }
    }

    private Color[] nextNonFirstGuess() {
        int i = 0;
        boolean guessFound = false;
        while (i < table.nColumns && !guessFound) {
            if (manager.thereIsNextColor(lastGuess[i])) {
                lastGuess[i] = manager.nextColor(lastGuess[i]);
                guessFound = true;
            } else {
                lastGuess[i] = manager.firstColor();
                i++;
            }
        }
        return guessFound ? lastGuess : none;
    }

    private boolean guessMatch(Color[] guess) {
        for (Row row : table.rows) {
            if (!row.guessMatches(guess)) {
                return false;
            }
        }
        return true;
    }

    public Row guess() {
        Color[] guess = nextGuess();
        while (guess != none && guessDoesNotMatch(guess)) {
            guess = nextGuess();
        }
        return guess == none ? Row.none : new Row(guess);
    }

    private boolean guessDoesNotMatch(Color[] guess) {
        return !guessMatch(guess);
    }
}