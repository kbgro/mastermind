package com.github.kbgro.mastermind.game;

import com.github.kbgro.mastermind.ColorManager;
import com.github.kbgro.mastermind.Guess;
import com.github.kbgro.mastermind.Table;
import com.github.kbgro.mastermind.color.Color;
import com.github.kbgro.mastermind.color.LetteredColorFactory;
import com.github.kbgro.mastermind.game.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameTest {
    private static final int NR_COLORS = 6;
    private static final int NR_COLUMNS = 4;
    private Game game;
    private Guess secret;


    private void createTestGame() {
        final var manager = new ColorManager(NR_COLORS, new LetteredColorFactory());
        final var table = new Table( manager, NR_COLUMNS);
        final var colors = new Color[NR_COLUMNS];
        var color = manager.firstColor();
        for (int i = 0; i < NR_COLUMNS; i++) {
            colors[i] = color;
            color = manager.nextColor(color);
        }
        secret = new Guess(colors);
        game = new Game(table, secret);
    }

    @Test
    public void canAddNewGuess() {
        createTestGame();
        game.addNewGuess(secret);
    }

    @Test
    public void throwsExceptionForFinishedGame() {
        createTestGame();
        game.addNewGuess(secret);
        Assertions.assertThrows(
                IllegalArgumentException.class, () -> game.addNewGuess(secret)
        );

    }
}