package com.github.kbgro.mastermind;

import com.github.kbgro.mastermind.color.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RowTest {
    private static final int NR_COLORS = 6;
    private static final int NR_COLUMNS = 4;
    private static final ColorManager manager = new ColorManager(NR_COLORS, Color::new);

    private Color[] firstColors() {
        final var colors = new Color[NR_COLUMNS];
        int i = 0;
        for (var color = manager.firstColor();
             i < colors.length;
             color = manager.nextColor(color)) {
            colors[i++] = color;
        }
        return colors;
    }

    private void stepTheLastColor(Color[] colors) {
        colors[NR_COLUMNS - 1] = manager.nextColor(colors[NR_COLUMNS - 1]);
    }

    @Test
    public void allColorsAndPositionsMatch() {
        final var colors = firstColors();
        final var guess = new Guess(colors);
        final var row = new Row(guess, NR_COLUMNS, 0);
        Assertions.assertTrue(row.matches(guess));
    }

    @Test
    public void allButOneColorsAndPositionsMatch() {
        final var colors = firstColors();
        final var row = new Row(new Guess(colors), NR_COLUMNS - 1, 0);
        stepTheLastColor(colors);
        Assertions.assertTrue(row.matches(new Guess(colors)));
    }

    @Test
    public void twoColorsWrongPositionOtherGoodPosition() {
        ColorManager manager = new ColorManager(NR_COLORS, Color::new);
        Color[] colors = firstColors();
        Row row = new Row(new Guess(colors), NR_COLUMNS - 2, 2);
        var swap = colors[0];
        colors[0] = colors[1];
        colors[1] = swap;
        Assertions.assertTrue(row.matches(new Guess(colors)));
    }
}