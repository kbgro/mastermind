package com.github.kbgro.mastermind;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ColorManagerTest {
    private static final int NR_COLORS = 6;

    @Test
    public void noColorHasNoNextColor() {
        ColorManager manager = new ColorManager(NR_COLORS);
        Assertions.assertNull(manager.nextColor(Color.none));
    }

    @Test
    public void thereIsAFirstColor() {
        ColorManager manager = new ColorManager(NR_COLORS);
        System.out.println(manager.firstColor());
        Assertions.assertNotNull(manager.firstColor());
    }

    @Test
    public void noNextColorIsNullWhenThereIsOne() {
        ColorManager manager = new ColorManager(NR_COLORS);
        Color color = manager.firstColor();
        while (manager.thereIsNextColor(color)) {
            Assertions.assertNotNull(color = manager.nextColor(color));
        }
    }
}