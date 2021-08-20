package com.github.kbgro.mastermind.game;

import com.github.kbgro.mastermind.Guess;

public interface Secret {
    Guess createSecret(int nrColumns);
}
