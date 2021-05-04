package com.hackerearth;

public class Board {
    private final int size;
    private final Cell[] cell;

    public int getSize() {
        return this.size;
    }

    // returns last successful position of the player
    public int move(int pos, int diceVal) {
        int newPos = pos + diceVal;

        // if dice value exceeds board length, do nothing
        if (newPos >= cell.length) {
            return pos;
        }

        return cell[newPos].jump();
    }

    private static class CellImpl implements Cell {
        int dest;

        CellImpl(int dest) {
            this.dest = dest;
        }

        public int jump() {
            return dest;
        }
    }

    public Board(int boardSize, int[][] snek, int[][] ladder) {
        this.size = boardSize;
        this.cell = new Cell[this.size + 1];
        for (int[] s : snek) {
            cell[s[0]] = new CellImpl(s[1]);
        }

        for (int[] l : ladder) {
            cell[l[0]] = new CellImpl(l[1]);
        }

        for (int i = 1; i <= this.size; i++) {
            if (cell[i] == null) {
                cell[i] = new CellImpl(i);
            }
        }
    }
}
