package com.github.kbgro.mastermind;

import com.github.kbgro.mastermind.color.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ColorManagerTest {
    private static final int NR_COLORS = 6;

    @Test
    public void noColorHasNoNextColor() {
        var manager = new ColorManager(NR_COLORS, Color::new);
        Assertions.assertNull(manager.nextColor(Color.none));
    }

    @Test
    public void thereIsAFirstColor() {
        var manager = new ColorManager(NR_COLORS, Color::new);
        System.out.println(manager.firstColor());
        Assertions.assertNotNull(manager.firstColor());
    }

    @Test
    public void noNextColorIsNullWhenThereIsOne() {
        var manager = new ColorManager(NR_COLORS, Color::new);
        Color color = manager.firstColor();
        while (manager.thereIsNextColor(color)) {
            Assertions.assertNotNull(color = manager.nextColor(color));
        }
    }
}