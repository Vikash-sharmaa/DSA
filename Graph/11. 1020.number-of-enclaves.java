/*
 * @lc app=leetcode id=1020 lang=java
 *
 *
 * 
 * 
 * 
 */


 /*

    Time Complexity: O(m×n) - Each cell is processed at most once in DFS, leading to O(m×n) in the worst case.
    Space Complexity: O(m×n) - The visited matrix takes O(m×n) space, and DFS recursion stack can go up to O(m×n) in the worst case (for a grid full of land).

*/

import java.util.ArrayDeque;
import java.util.Deque;

class Pair{
    int row,col;
    Pair(int row,int col){
        this.row=row;
        this.col=col;
    }
}

 class Solution {

    // Define possible movement directions: left, up, right, down
    int[][] directions = new int[][]{{0,-1},{-1,0},{0,1},{1,0}};

/*************************************************************************************************************************************/


    // DFS function to mark all reachable land cells from the boundary
    void dfs(int row, int col, int m, int n, int[][] grid, boolean[][] visited) {
        visited[row][col] = true; // Mark the current cell as visited

        // Traverse in all four possible directions
        for (int[] dir : directions) {
            int newRow = row + dir[0]; // Calculate new row index
            int newCol = col + dir[1]; // Calculate new column index

            // Check if the new position is within bounds and is land (1) and not yet visited
            if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && grid[newRow][newCol] == 1 && !visited[newRow][newCol]){
                dfs(newRow, newCol, m, n, grid, visited); // Recur for the next land cell
            }
        }
    }


/*************************************************************************************************************************************/


void bfs(Pair start,int[][] grid,int[][] visited){

    int m=grid.length;
    int n=grid[0].length;
    Deque<Pair> queue = new ArrayDeque<>();
    queue.add(start);
    visited[start.row][start.col]=1;
    while (!queue.isEmpty()) {
        Pair front = queue.pollFirst();
        int row = front.row;
        int col = front.col;
        for(int[] dir:directions){
            int newRow = row+dir[0];
            int newCol = col+dir[1];
            if(newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && grid[newRow][newCol] == 1 && visited[newRow][newCol]==0){
                visited[newRow][newCol]=1;
                queue.offerLast(new Pair(newRow, newCol));
            }
        }
    }
}


/*************************************************************************************************************************************/

    public int numEnclaves(int[][] grid) {
        int m = grid.length; // Number of rows
        int n = grid[0].length; // Number of columns

        //boolean[][] visited = new boolean[m][n]; // To keep track of visited land cells
        int[][] visited = new int[m][n];

        // Step 1: Perform DFS from all boundary land cells and mark reachable land cells
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Check if the cell is on the boundary and is land (1)
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    // if (grid[i][j] == 1 && !visited[i][j]) {
                    //     dfs(i, j, m, n, grid, visited); // Start DFS from the boundary cell
                    // }

                    if (grid[i][j] == 1 && visited[i][j]==0) {
                        bfs(new Pair(i, j),grid,visited);
                    }
                }
            }
        }

        int count = 0; // Variable to count the number of enclaves

        // Step 2: Count the remaining land cells (1s) that were NOT visited
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If the cell is land and was NOT visited by DFS, it's an enclave
                if (i != 0 && j != 0 && i != m - 1 && j != n - 1) { // Ensure it's not a boundary cell
                    if (grid[i][j] == 1 && visited[i][j]==0) {
                        count++; // Increment enclave count
                    }
                }
            }
        }

        return count; // Return the number of enclaves
    }


/*************************************************************************************************************************************/


}

// @lc code=end

