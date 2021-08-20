package com.github.kbgro.mastermind;

import com.github.kbgro.mastermind.color.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UniqueGuesserTest {
    static final int N_COLORS = 6;
    static final int N_COLUMNS = 4;

    @Test
    public void generateAllGuesses() {
        int numberOfGuesses = 0;
        ColorManager manager = new ColorManager(N_COLORS, Color::new);
        Table table = new Table(manager, N_COLUMNS);
        Guesser guesser = new UniqueGuesser(table);
        while (guesser.nextGuess() != Guess.none) {
            numberOfGuesses++;
        }
        Assertions.assertEquals(6 * 5 * 4 * 3, numberOfGuesses);
    }

    @Test
    public void generates360Guesses() {
        int numberOfGuesses = 0;
        ColorManager manager = new ColorManager(N_COLORS, Color::new);
        Table table = new Table(manager, N_COLUMNS);
        Guesser guesser = new UniqueGuesser(table);
        // ...
        while (guesser.guess() != Guess.none) {
            numberOfGuesses++;
        }
        Assertions.assertEquals(6 * 5 * 4 * 3, numberOfGuesses);
    }
}