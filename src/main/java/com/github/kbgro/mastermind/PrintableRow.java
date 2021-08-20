package com.github.kbgro.mastermind;

import com.github.kbgro.mastermind.color.Color;

public class PrintableRow extends Row {

    protected PrintableRow(Row row) {
        super(row);
    }

    public Color pos(int i) {
        return guess.getColor(i);
    }

    public int full() {
        return full;
    }

    public int partial() {
        return partial;
    }
}
