package com.github.kbgro.mastermind;

import com.github.kbgro.mastermind.color.Color;

import java.util.Arrays;
import java.util.HashSet;

public class Guess {
    public final static Guess none = new Guess(new Color[0]);
    private final Color[] colors;
    private boolean uniquenessWasNotCalculated = true;
    private boolean unique;

    public Guess(Color[] colors) {
        this.colors = Arrays.copyOf(colors, colors.length);
    }

    public Color getColor(int i) {
        return colors[i];
    }

    public int nrOfColumns() {
        return colors.length;
    }

    public Guess nextGuess(ColorManager manager) {
        final var colors = Arrays.copyOf(this.colors, nrOfColumns());

        int i = 0;
        var guessFound = false;
        while (i < colors.length && !guessFound) {
            if (manager.thereIsNextColor(getColor(i))) {
                colors[i] = manager.nextColor(colors[i]);
                guessFound = true;
            } else {
                colors[i] = manager.firstColor();
                i++;
            }
        }
        return guessFound ? new Guess(colors) : Guess.none;

    }

    private void assertCompatibility(Guess guess) {
        if (nrOfColumns() != guess.nrOfColumns()) {
            throw new IllegalArgumentException("Can not compare different length guesses");
        }
    }

    public int nrOfPartialMatches(Guess guess) {
        assertCompatibility(guess);
        int count = 0;
        for (int i = 0; i < nrOfColumns(); i++) {
            for (int j = 0; j < nrOfColumns(); j++) {
                if (i != j &&
                        guess.getColor(i) == this.getColor(j)) {
                    count++;
                }
            }
        }
        return count;
    }

    public int nrOfFullMatches(Guess guess) {
        assertCompatibility(guess);
        int count = 0;
        for (int i = 0; i < nrOfColumns(); i++) {
            if (guess.getColor(i) == this.getColor(i)) {
                count++;
            }
        }
        return count;
    }

    public boolean isUnique() {
        if (uniquenessWasNotCalculated) {
            final var alreadyPresent = new HashSet<Color>();
            unique = true;
            for (final var color : colors) {
                if (alreadyPresent.contains(color)) {
                    unique = false;
                    break;
                }
                alreadyPresent.add(color);
            }
            uniquenessWasNotCalculated = false;
        }
        return unique;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Guess)) return false;
        var guess = (Guess) o;
        return Arrays.equals(colors, guess.colors);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(colors);
    }

    @Override
    public String toString() {
        if (this == none) {
            return "none";
        } else {
            StringBuilder s = new StringBuilder();
            for (int i = colors.length - 1; i >= 0; i--) {
                s.append(colors[i]);
            }
            return s.toString();
        }
    }
}
