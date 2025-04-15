/* https://leetcode.com/problems/surrounded-regions/

 * @lc app=leetcode id=130 lang=java
 *
 * Intuition :- 
        Traverse the boundaries - if 'O' run dfs from here and in any direction if O happens - move - more deep - DFS
 * 
 * 
 */

// @lc code=start
class Solution {
    // Directions array for moving left, up, right, and down
    int[][] directions = new int [][]{{0,-1},{-1,0},{0,1},{1,0}};


/******************************************************************************************************************/

    // DFS function to mark all 'O's connected to the boundary
    void dfs(int row, int col, int m, int n, char[][] board, boolean[][] visited) {
        visited[row][col] = true; // Mark the cell as visited

        // Explore all four possible directions
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            // Check if the new cell is within bounds, is an 'O', and has not been visited yet
            if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n 
                && board[newRow][newCol] == 'O' && !visited[newRow][newCol]) {
                dfs(newRow, newCol, m, n, board, visited); // Recursively visit the connected 'O's
            }
        }
    }


/******************************************************************************************************************/

    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        boolean[][] visited = new boolean[m][n]; // Visited array to track safe 'O' regions

        // Step 1: Perform DFS from all 'O's on the boundary to mark them as safe
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If the cell is on the boundary and contains 'O', start DFS
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    if (board[i][j] == 'O' && !visited[i][j]) {
                        dfs(i, j, m, n, board, visited);
                    }
                }
            }
        }

        // Step 2: Convert all unvisited 'O's to 'X' (since they are enclosed)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Fix: The condition should be `&&`, not `||`, to correctly check non-boundary cells
                if (i != 0 && i != m - 1 && j != 0 && j != n - 1) {
                    if (board[i][j] == 'O' && !visited[i][j]) {
                        board[i][j] = 'X'; // Convert enclosed 'O' to 'X'
                    }
                }
            }
        }

    }

/******************************************************************************************************************/

}
// @lc code=end

