package com.snek;

import java.util.HashMap;
import java.util.Map;

public class Game {
    // player -> progress
    private final Map<String, Integer> players;
    private final Board board;
    private final Dice dice;
    private final static int diceBound = 6;

    public Game(int boardSize, int[][] snekCell, int[][] ladderCell, String[] names) {
        dice = new DiceImpl(diceBound);
        players = new HashMap<>();
        for (var name: names) players.put(name, 0);
        this.board = new Board(boardSize, snekCell, ladderCell);
    }

    public boolean play(String player) {
        int previousPos = players.get(player);
        int diceVal = dice.roll();
        boolean isGameOver = play(player, diceVal);
        int currentPos = players.get(player);
        System.out.printf("%s rolled a %d and moved from %d to %d\n",
                player,
                diceVal,
                previousPos,
                currentPos);
        if (isGameOver) {
            System.out.printf("%s wins the game\n", player);
        }
        return isGameOver;
    }

    private boolean play(String player, int diceVal) {
        int currentPosition = players.get(player);
        int pos = board.move(currentPosition, diceVal);
        players.put(player, pos);
        return checkState(player);
    }

    private boolean checkState(String player) {
        int pos = players.get(player);
        return pos == board.getSize();
    }
}
