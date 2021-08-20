package com.github.kbgro.mastermind;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RowTest {
    static final int N_COLORS = 6;
    static final int N_COLUMNS = 4;


    @Test
    public void allColorsAndPositionsMatch() {
        ColorManager manager = new ColorManager(N_COLORS);
        Color[] positions = new Color[N_COLUMNS];
        int i = 0;
        for (Color color = manager.firstColor(); i < positions.length; color = manager.nextColor(color)) {
            positions[i++] = color;
        }
        Row row = new Row(positions);
        row.setMatch(N_COLUMNS, 0);
        Assertions.assertTrue(row.guessMatches(positions));
    }

    @Test
    public void allButOneColorsAndPositionsMatch() {
        ColorManager manager = new ColorManager(N_COLORS);
        Color[] positions = new Color[N_COLUMNS];
        int i = 0;
        for (Color color = manager.firstColor(); i < positions.length; color = manager.nextColor(color)) {
            positions[i++] = color;
        }
        Row row = new Row(positions);
        positions[N_COLUMNS - 1] = manager.nextColor(positions[N_COLUMNS - 1]);
        row.setMatch(N_COLUMNS - 1, 0);
        Assertions.assertTrue(row.guessMatches(positions));
    }

    @Test
    public void twoColorsWrongPositionOtherGoodPosition() {
        ColorManager manager = new ColorManager(N_COLORS);
        Color[] positions = new Color[N_COLUMNS];
        int i = 0;
        for (Color color = manager.firstColor(); i < positions.length; color = manager.nextColor(color)) {
            positions[i++] = color;
        }
        Row row = new Row(positions);
        Color swap = positions[0];
        positions[0] = positions[1];
        positions[1] = swap;
        row.setMatch(N_COLUMNS - 2, 2);
        Assertions.assertTrue(row.guessMatches(positions));
    }

    @Test
    public void throwsIAEForInvalidMatchParameters() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Row row = new Row(new Color[N_COLUMNS]);
            row.setMatch(N_COLUMNS, 1);
        });
    }
}