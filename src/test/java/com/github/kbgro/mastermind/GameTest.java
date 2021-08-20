package com.github.kbgro.mastermind;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameTest {
    private Game game;
    private Color[] secret;

    private void createTestGame() {
        final int nrColors = 6;
        ColorManager manager = new ColorManager(nrColors);
        final int nrColumns = 4;
        Table table = new Table(manager, nrColumns);
        secret = new Color[nrColumns];
        Color color = manager.firstColor();
        for (int i = 0; i < nrColumns; i++) {
            secret[i] = color;
            color = manager.nextColor(color);
        }
        game = new Game(table, secret);
    }

    @Test
    public void canAddNewGuess() {
        createTestGame();
        game.addNewGuess(new Row(secret));
    }

    @Test
    public void throwsExceptionForFinishedGame() {
        createTestGame();
        game.addNewGuess(new Row(secret));
        Assertions.assertThrows(
                IllegalArgumentException.class, () -> game.addNewGuess(new Row(secret))
        );

    }
}