package com.github.kbgro.mastermind;

import com.github.kbgro.mastermind.color.Color;

public class UniqueGuesser extends Guesser {
    public UniqueGuesser(Table table) {
        super(table);
    }

    @Override
    protected Guess firstGuess() {
        final var colors = new Color[table.nrOfColumns()];
        int i = colors.length - 1;
        for (var color = manager.firstColor();
             i >= 0;
             color = manager.nextColor(color)) {
            colors[i--] = color;
        }
        return new Guess(colors);
    }

    @Override
    protected Guess nextGuess() {
        var guess = super.nextGuess();
        while (!guess.isUnique()) {
            guess = super.nextGuess();
        }
        return guess;
    }
}
