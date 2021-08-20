package com.github.kbgro.mastermind;

import com.github.kbgro.mastermind.color.Color;

import java.util.HashMap;
import java.util.Map;

public class PrettyPrintRow {
    private static final Map<Color, Character> letterMapping = new HashMap<>();
    private static final String letters = "RGBYWb";
    private static int counter = 0;

    private static char colorToChar(Color color) {
        if (!letterMapping.containsKey(color)) {
            letterMapping.put(color, letters.charAt(counter));
            counter++;
        }
        return letterMapping.get(color);
    }

    public static String pprint(Row row) {
        StringBuilder string = new StringBuilder();
        var pRow = new PrintableRow(row);
        for (int i = 0; i < pRow.nOfColumns(); i++) {
            string.append(pRow.pos(i));
        }
        string.append(" ");
        string.append(pRow.full());
        string.append("/");
        string.append(pRow.partial());
        return string.toString();
    }

}
