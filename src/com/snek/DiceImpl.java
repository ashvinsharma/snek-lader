package com.snek;

import java.util.Random;

public class DiceImpl implements Dice {
    private final Random r;
    private final int bound;

    public DiceImpl(int b) {
        r = new Random();
        bound = b;
    }

    public int roll() {
        return r.nextInt(bound) + 1;
    }
}
