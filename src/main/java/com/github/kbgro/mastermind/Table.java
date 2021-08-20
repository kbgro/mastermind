package com.github.kbgro.mastermind;

import java.util.LinkedList;
import java.util.List;

public class Table {
    final ColorManager manager;
    final int nColumns;
    final List<Row> rows;

    public Table(ColorManager manager, int nColumns) {
        this.manager = manager;
        this.nColumns = nColumns;
        this.rows = new LinkedList<>();
    }

    public void addRow(Row row) {
        rows.add(row);
    }

    public int nrOfColumns() {
        return nColumns;
    }
}
