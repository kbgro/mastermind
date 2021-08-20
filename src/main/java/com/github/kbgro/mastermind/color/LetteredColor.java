package com.github.kbgro.mastermind.color;

public class LetteredColor extends Color {
    private final String letter;
    public LetteredColor(String letter){
        this.letter = letter;
    }

    @Override
    public String toString(){
        return letter;
    }
}
