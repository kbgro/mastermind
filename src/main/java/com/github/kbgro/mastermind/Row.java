package com.github.kbgro.mastermind;

import java.util.Arrays;

public class Row {
    final Color[] positions;
    protected int matchedPositions;
    protected int matchedColors;
    public static final Row none = new Row(Guesser.none);

    public Row(Color[] positions) {
        this.positions = Arrays.copyOf(positions, positions.length);
    }
    protected Row(Row row) {
        this(row.positions);
        setMatch(row.matchedPositions, row.matchedColors);
    }

    public void setMatch(int matchedPositions, int matchedColors) {
        if (matchedColors + matchedPositions > positions.length)
            throw new IllegalArgumentException("Number of matches cannot be more than position.");
        this.matchedColors = matchedColors;
        this.matchedPositions = matchedPositions;
    }

    public boolean guessMatches(Color[] guess) {
        return nMatchingColors(guess) == matchedColors && nMatchingPositions(guess) == matchedPositions;
    }

    public int nMatchingPositions(Color[] guess) {
        int count = 0;
        for (int i = 0; i < guess.length; i++) {
            if (guess[i] == positions[i])
                count++;
        }
        return count;
    }

    public int nMatchingColors(Color[] guess) {
        int count = 0;
        for (int i = 0; i < guess.length; i++) {
            for (int j = 0; j < positions.length; j++) {
                if (i != j && guess[i] == positions[j])
                    count++;
            }
        }
        return count;
    }

    public int nOfColumns() {
        return positions.length;
    }
}
