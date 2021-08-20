package com.github.kbgro.mastermind;

import com.github.kbgro.mastermind.color.Color;
import com.github.kbgro.mastermind.color.LetteredColorFactory;
import com.github.kbgro.mastermind.game.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntegrationTest {
    private static final int NR_COLORS = 10;
    final ColorManager manager = new ColorManager(NR_COLORS, new LetteredColorFactory());
    private static final int NR_COLUMNS = 6;

    private Guess createSecret() {
        Color[] colors = new Color[NR_COLUMNS];
        int count = 0;
        Color color = manager.firstColor();
        while (count < NR_COLORS - NR_COLUMNS) {
            color = manager.nextColor(color);
            count++;
        }
        for (int i = 0; i < NR_COLUMNS; i++) {
            colors[i] = color;
            color = manager.nextColor(color);
        }
        return new Guess(colors);
    }

    @Test
    public void testSimpleGame() {
        Table table = new Table(manager, NR_COLUMNS);
        Guess secret = createSecret();
        System.out.println(PrettyPrintRow.pprint(new Row(secret, 4, 0)));
        System.out.println();
        Game game = new Game(table, secret);

        Guesser guesser = new UniqueGuesser(table);
        while (!game.isFinished()) {
            Guess guess = guesser.guess();
            if (guess == Guess.none) {
                Assertions.fail();
            }
            Row row = game.addNewGuess(guess);
            System.out.println(PrettyPrintRow.pprint(row));
        }
    }
}