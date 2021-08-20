package com.github.kbgro.mastermind;

import java.util.HashMap;
import java.util.Map;

public class ColorManager {
    final protected int nColors;
    final protected Map<Color, Color> successor = new HashMap<>();
    private Color first;

    public ColorManager(int nColors) {
        this.nColors = nColors;
        createOrdering();
    }

    private void createOrdering() {
        Color[] colors = createColors();
        first = colors[0];
        for (int i = 0; i < nColors - 1; i++) {
            successor.put(colors[i], colors[i + 1]);
        }
    }

    public Color newColor() {
        return new Color();
    }

    private Color[] createColors() {
        Color[] colors = new Color[nColors];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = newColor();
        }
        return colors;
    }

    public Color firstColor() {
        return first;
    }

    boolean thereIsNextColor(Color color) {
        return successor.containsKey(color);
    }

    public Color nextColor(Color color) {
        return successor.get(color);
    }
}
