/*
 * @lc app=leetcode id=542 lang=java
 *
 * 
 * Similar as rotting oranges.
 * Use multisource BFS as distance difference might differ depending on cells
 * 
 * The problem requires finding the shortest distance of each 1 from the nearest 0 in a matrix. 
 * A multi-source BFS is used, where all 0s are enqueued first with a distance of 0, then BFS propagates distances to neighboring 1s. 
 * This ensures each cell is updated optimally in O(m * n) time. The approach avoids redundant recalculations and guarantees the shortest path. 🚀
 * 
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.Deque;

// Helper class to store row, column, and distance from the nearest 0
class Tuple {
    int row;
    int col;
    int distance;

    Tuple(int row, int col, int distance) {
        this.row = row;
        this.col = col;
        this.distance = distance;
    }
}

class Solution {
    // Helper method to check if a cell is within matrix bounds
    private boolean isSafe(int newRow, int newCol, int m, int n) {
        return newRow >= 0 && newRow < m && newCol >= 0 && newCol < n;
    }

    // BFS function to calculate the shortest distance for each cell
    private void BFS(Deque<Tuple> queue, int[][] mat, boolean[][] visited, int[][] dis) {
        int m = mat.length;
        int n = mat[0].length;
        
        // 4 possible directions (left, up, right, down) for BFS traversal
        int[][] directions = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

        // Process all cells in the queue using BFS
        while (!queue.isEmpty()) {
            Tuple front = queue.pollFirst(); // Dequeue the front cell
            int row = front.row;
            int col = front.col;
            int distance = front.distance;
            dis[row][col] = distance; // Store the minimum distance for this cell

            // Traverse all 4 possible directions
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check if the new cell is within bounds and is unvisited
                if (isSafe(newRow, newCol, m, n) && mat[newRow][newCol] == 1 && !visited[newRow][newCol]) {
                    queue.offerLast(new Tuple(newRow, newCol, distance + 1)); // Add new cell with updated distance
                    visited[newRow][newCol] = true; // Mark as visited to avoid duplicate processing
                }
            }
        }
    }

    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        Deque<Tuple> queue = new ArrayDeque<>(); // BFS queue
        int[][] dis = new int[m][n]; // Stores the shortest distances
        boolean[][] visited = new boolean[m][n]; // Keeps track of visited cells

        // Step 1: Enqueue all cells that have a 0 and mark them as visited
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.offerLast(new Tuple(i, j, 0)); // Add all 0s as BFS starting points
                    visited[i][j] = true; // Mark as visited
                }
            }
        }

        // Step 2: Perform BFS to calculate the shortest distances
        BFS(queue, mat, visited, dis);

        return dis; // Return the updated distance matrix
    }
}

// @lc code=end

