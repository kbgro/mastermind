package com.github.kbgro.mastermind;

public class Game {
    final Table table;
    final private Row secretRow;
    private final int nOfColumns;
    boolean finished = false;

    public Game(Table table, Color[] secret) {
        this.table = table;
        this.secretRow = new Row(secret);
        this.nOfColumns = secretRow.nOfColumns();
    }

    public void addNewGuess(Row row) {
        if (isFinished()) {
            throw new IllegalArgumentException("You can not guess on a finished game.");
        }
        final int positionMatch = secretRow.nMatchingPositions(row.positions);
        final int positionColor = secretRow.nMatchingColors(row.positions);
        row.setMatch(positionMatch, positionColor);

        table.addRow(row);
        if (positionMatch == nOfColumns)
            finished = true;
    }

    public boolean isFinished() {
        return finished;
    }

}
