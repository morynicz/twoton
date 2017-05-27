package com.link.twotonth;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GameTest {
    Game game;
    private RNGMock rng;

    public class RNGMock implements RNG {
        public List<Integer> generatedValues;
        @Override
        public double generate() {
            return generatedValues.remove(0);
        }
    }

    @Before
    public void setUp() {
        rng = new RNGMock();
        rng.generatedValues = new ArrayList<Integer>(Arrays.asList(1, 1, 0, 1));
        this.game = new Game(2, rng);
    }

    @Test
    public void newGameHasScoreOfZero() {
        assertEquals(game.score(), 0);
    }

    @Test
    public void newGameStartsWithOneTwoBlockAndThreeZeros() {
        int[][] array = game.getBoard();

        int zeros = 0;
        int twos = 0;

        for(int[] row : array) {
            for (int block : row) {
                if(block == 0) {
                    zeros++;
                } else if (block == 2) {
                    twos++;
                } else {
                    fail();
                }
            }
        }

        assertEquals(zeros, 3);
        assertEquals(twos, 1);
    }

    @Test
    public void impossibleShiftRightDoesNotMoveAnything() {
        game.shift(Game.Directions.RIGHT);
        int[][] board = game.getBoard();
        int[][] expected = {{0,0}, {2,0}};

        assertArrayEquals(expected, board);

    }

    @Test
    public void possibleShiftLeftDoesMoveBlockAndCreateANewOne() {
        rng.generatedValues.add(1);
        rng.generatedValues.add(1);
        rng.generatedValues.add(0);
        game.shift(Game.Directions.LEFT);
        int[][] board = game.getBoard();
        int[][] expected = {{2,0}, {0,2}};

        assertArrayEquals(expected, board);
    }
}