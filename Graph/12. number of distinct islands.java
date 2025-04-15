// 
package Graph;

/*
    Note :- Slight variation of number of islands - here we need to find number of uniqe shape holding islands.
                - note how we are storing the shape of islands {row-baseRow || col-baseCol}

                1. Use DFS or BFS to explore an island and mark it as visited.
                2. Record each cell’s position relative to the first cell in the island (i.e., row - baseRow, col - baseCol).
                3. Store the shape of the island as a unique key in a HashSet to ensure only unique shapes are counted.
                4. Return the size of the HashSet, which represents the number of distinct island shapes.
 */

 /*
    An island’s shape is determined by the relative positions of its land cells (1s). Instead of storing absolute positions 
        of land cells, we normalize them by using the first encountered cell (the base cell) as a reference.

    How We Normalize the Shape:-

    When we start DFS or BFS from an unvisited land cell (row, col), we record its relative position with respect to the first (base) 
        cell (baseRow, baseCol).
    For each land cell (row, col) we store: (row - baseRow) + "," + (col - baseCol)
    This ensures that if two islands have the same shape but appear in different parts of the grid, they will still be considered identical.
  */


 /*
    Time & Space Complexity:-

        Time Complexity: O(m×n) since each cell is visited once.
        Space Complexity: O(m×n) for the visited array and HashSet storing island shapes.
  */



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    // Directions array to move in four possible directions (left, up, right, down)
    int[][] directions = new int[][]{{0,-1},{-1,0},{0,1},{1,0}};

    // DFS function to explore an island and record its shape
    void dfs(int row, int col, int baseRow, int baseCol, int m, int n, int[][] grid, boolean[][] visited, List<String> temp) {
        visited[row][col] = true; // Mark the current cell as visited

        // Store the relative position of the current cell compared to the starting cell of the island
        temp.add((row - baseRow) + "," + (col - baseCol));

        // Traverse all four possible directions
        for (int[] dir : directions) {
            int newRow = row - dir[0]; // Moving in the opposite direction (BUG: should be row + dir[0])
            int newCol = col - dir[1]; // Moving in the opposite direction (BUG: should be col + dir[1])

            // Check if the new cell is within bounds, not visited, and is part of the island
            if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && !visited[newRow][newCol] && grid[newRow][newCol] == 1) {
                dfs(newRow, newCol, baseRow, baseCol, m, n, grid, visited, temp);
            }
        }
    }

    // Function to count distinct islands based on their unique shapes
    int countDistinctIslands(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n]; // Visited array to track explored cells
        Set<List<String>> set = new HashSet<>(); // HashSet to store unique island shapes

        // Iterate through the entire grid
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If the cell is part of an island and not visited, start DFS
                if (grid[i][j] == 1 && !visited[i][j]) {
                    List<String> temp = new ArrayList<>(); // List to store the island shape
                    dfs(i, j, i, j, m, n, grid, visited, temp); // Perform DFS to explore the island
                    set.add(temp); // Store the shape in the set to count distinct islands
                }
            }
        }
        return set.size(); // Return the count of distinct island shapes
    }
}

