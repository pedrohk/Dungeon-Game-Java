package com.pedrohk.DungeonGameJava.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DungeonGameSolverTest {

    private DungeonGameSolver solver;

    @BeforeEach
    void setUp() {
        solver = new DungeonGameSolver();
    }

    @Test
    @DisplayName("Test case 1: Example from LeetCode")
    void testExampleCase() {
        int[][] dungeon = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        };
        assertEquals(7, solver.calculateMinimumHP(dungeon));
    }

    @Test
    @DisplayName("Test case 2: Single cell dungeon (positive)")
    void testSingleCellPositive() {
        int[][] dungeon = {{10}};
        assertEquals(1, solver.calculateMinimumHP(dungeon));
    }

    @Test
    @DisplayName("Test case 3: Single cell dungeon (negative)")
    void testSingleCellNegative() {
        int[][] dungeon = {{-10}};
        assertEquals(11, solver.calculateMinimumHP(dungeon));
    }

    @Test
    @DisplayName("Test case 4: Dungeon with all negative values")
    void testAllNegativeValues() {
        int[][] dungeon = {
                {-1, -2},
                {-3, -4}
        };
        assertEquals(8, solver.calculateMinimumHP(dungeon));
    }

    @Test
    @DisplayName("Test case 5: Dungeon with all positive values")
    void testAllPositiveValues() {
        int[][] dungeon = {
                {1, 2},
                {3, 4}
        };
        assertEquals(1, solver.calculateMinimumHP(dungeon));
    }

    @Test
    @DisplayName("Test case 6: Dungeon with zero value")
    void testZeroValue() {
        int[][] dungeon = {{0}};
        assertEquals(1, solver.calculateMinimumHP(dungeon));
    }

    @Test
    @DisplayName("Test case 7: More complex dungeon")
    void testComplexDungeon() {
        int[][] dungeon = {
                {1, -3, 3},
                {0, -2, 0},
                {-3, -3, -3}
        };
        assertEquals(3, solver.calculateMinimumHP(dungeon));
    }

    @Test
    @DisplayName("Test case 8: Large negative values")
    void testLargeNegativeValues() {
        int[][] dungeon = {
                {-100, -1, -1},
                {-1, -100, -1},
                {-1, -1, -100}
        };
        assertEquals(204, solver.calculateMinimumHP(dungeon));
    }
}
