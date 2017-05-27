package com.link.twotonth;

/**
 * Created by link on 27.05.17.
 */

public class Game {
    private final RNG rng;
    private final int size;
    int[][] board;

    public void shift(Directions direction) {
        boolean moved = false;
        switch (direction) {
            case LEFT:
                for (int i = 1; i < board.length; i++) {
                    for (int j = 0; j < board[i].length; j++) {
                        if ((board[i][j] != 0) && (board[i - 1][j] == 0)) {
                            board[i - 1][j] = board[i][j];
                            board[i][j] = 0;
                            moved = true;
                        }
                    }
                }
                break;
            case RIGHT:
                for (int i = 0; i < board.length - 1; i++) {
                    for (int j = 0; j < board[i].length; j++) {
                        if ((board[i][j] != 0) && (board[i + 1][j] == 0)) {
                            board[i + 1][j] = board[i][j];
                            board[i][j] = 0;
                            moved = true;
                        }
                    }
                }
                break;
        }

        if(moved) generateNewBlock(countZeros());
    }

    enum Directions {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public Game(int size, RNG rng) {
        this.size = size;
        this.board = new int[this.size][this.size];
        this.rng = rng;
        generateNewBlock((this.size * this.size));
    }

    private int countZeros() {
        int zeros = 0;
        for(int[] row : this.board) {
            for(int block : row) {
                if (block == 0) {
                    zeros++;
                }
            }
        }
        return zeros;
    }

    private void generateNewBlock(int num_of_zeros) {
        final double initialOdds = 1.0 / num_of_zeros;
        double odds = initialOdds;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0, rowLength = board[i].length; j < rowLength; j++) {
                if ((board[i][j] == 0) && (this.rng.generate() < odds)) {
                    board[i][j] = 2;
                    i = board.length;
                    break;
                } else {
                    odds += initialOdds;
                }
            }
        }
    }

    public int score() {
        return 0;
    }

    public int[][] getBoard() {
        return board;
    }
}
