/*
    BFS will be used - since we want to rotten the oranges simultaneously
    also - here they have asked for minimum time to rotten the oranges.
 */

 /*
    ✅ All initially rotten oranges start with time = 0 because:

        * They are already rotten and should not take extra time.
        * Multi-source BFS ensures simultaneous rotting instead of sequential.
        * It correctly models real-world spread, where all rotten oranges start affecting fresh ones at the same time.
  */

  /*
        Time:- O(m*n)
        Space:- O(m*n)
   */

import java.util.*;

class Tuple {
    int row, col,time;
    Tuple(int row, int col,int time) {
        this.row = row;
        this.col = col;
        this.time = time;
    }
}

class Solution {

    // Helper function to check if the given cell (row, col) is within grid bounds
    boolean isSafe(int row, int col, int m, int n) {
        return row >= 0 && row < m && col >= 0 && col < n;
    }

    // BFS function to spread the rotting process across fresh oranges
    int BFS(int[][] grid, Deque<Tuple> queue, boolean[][] visited, int countFresh) {
        int m = grid.length;  // Get number of rows
        int n = grid[0].length;  // Get number of columns

        int minTime = 0;  // Stores the minimum time required to rot all oranges
        int[][] directions = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}}; // Left, Up, Right, Down


        while (!queue.isEmpty()) {
            Tuple front = queue.pollFirst();  // Dequeue the front element
            int row = front.row;
            int col = front.col;
            int time = front.time;

            minTime = Math.max(minTime, time);  // Track the maximum time taken for any orange to rot

            // Iterate in all 4 possible directions (Left, Up, Right, Down)
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check if the new cell is within bounds, is a fresh orange, and hasn't been visited
                if (isSafe(newRow, newCol, m, n) && grid[newRow][newCol] == 1 && !visited[newRow][newCol]) {
                    queue.offerLast(new Tuple(newRow, newCol, time + 1)); // Add the fresh orange to the queue with increased time
                    grid[newRow][newCol]=2;
                    visited[newRow][newCol] = true; // Mark as visited
                    countFresh--; // Increase the count of newly rotten oranges
                }
            }
        }


        // If all fresh oranges are rotted, return the time taken; otherwise, return -1
        return countFresh==0 ? minTime : -1;
    }

    // Main function to find the minimum time required to rot all oranges
    public int orangesRotting(int[][] grid) {
        int m = grid.length;  // Get number of rows
        int n = grid[0].length;  // Get number of columns
        boolean[][] visited = new boolean[m][n]; // Keep track of visited cells
        Deque<Tuple> queue = new ArrayDeque<>(); // Queue for BFS traversal
        int countFresh = 0;  // Count of fresh oranges

        // Step 1: Initialize the queue with all initially rotten oranges and count fresh oranges
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) { // Rotten orange found
                    queue.offerLast(new Tuple(i, j, 0)); // Add rotten orange to queue
                    visited[i][j] = true; // Mark it as visited
                }
                if (grid[i][j] == 1) countFresh++; // Count fresh oranges
            }
        }

        // Step 2: Call BFS to process the rotting of oranges and return the minimum time
        return BFS(grid, queue, visited, countFresh);
    }

}


