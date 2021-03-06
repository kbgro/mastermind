package com.github.kbgro.mastermind.game;

import com.github.kbgro.mastermind.Guess;
import com.github.kbgro.mastermind.Row;
import com.github.kbgro.mastermind.Table;

public class Game {
    final Table table;
    final private Guess secret;
    private final int nrOfColumns;
    boolean finished = false;

    public Game(Table table, Guess secret) {
        this.table = table;
        this.secret = secret;
        this.nrOfColumns = this.secret.nrOfColumns();
    }

    private boolean itWasAWinningGuess(int positionMatch) {
        return positionMatch == nrOfColumns;
    }

    public Row addNewGuess(Guess guess) {
        assertNotFinished();
        final int full = secret.nrOfFullMatches(guess);
        final int partial = secret.nrOfPartialMatches(guess);
        final var row = new Row(guess, full, partial);
        table.addRow(row);
        if (itWasAWinningGuess(full)) {
            finished = true;
        }
        return row;
    }

    private void assertNotFinished() {
        if (isFinished()) {
            throw new IllegalArgumentException("You can not guess on a finished game.");
        }
    }

    public boolean isFinished() {
        return finished;
    }
}
