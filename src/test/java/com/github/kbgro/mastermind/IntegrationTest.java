package com.github.kbgro.mastermind;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntegrationTest {
    final int nColors = 6;
    final int nColumns = 4;
    final ColorManager manager = new ColorManager(nColors);

    private Color[] createSecret() {
        Color[] secret = new Color[nColumns];
        int count = 0;
        Color color = manager.firstColor();
        while (count < nColors - nColumns) {
            color = manager.nextColor(color);
            count++;
        }
        for (int i = 0; i < nColumns; i++) {
            secret[i] = color;
            color = manager.nextColor(color);
        }
        return secret;
    }

    @Test
    public void testSimpleGame() {
        Table table = new Table(manager, nColumns);
        Color[] secret = createSecret();
        System.out.println(PrettyPrintRow.pprint(new Row(secret)));
        System.out.println();
        Game game = new Game(table, secret);
        Guesser guesser = new UniqueGuesser(table);
        while (!game.isFinished()) {
            Row guess = guesser.guess();
            if (guess == Row.none) {
                Assertions.fail();
            }
            game.addNewGuess(guess);
            System.out.println(PrettyPrintRow.pprint(guess));
        }
    }
}
