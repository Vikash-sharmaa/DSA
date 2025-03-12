/* https://leetcode.com/problems/minimum-path-sum/description/

 * @lc app=leetcode id=64 lang=java
 *
 * just same as Q n: '62' 
    # for every cell minimum path = current cell val + min(path_sum of its left adjacent, path_sum of its upper adjacent)
    # if you go bottom-up in recursive.
 */

 /*
  * 
    When calculating the minimum path sum, if the row or column index goes out of bounds, 
    returning 0 for invalid directions can lead to incorrect results, as it would consider an invalid 
    path as valid. To avoid this, we initialize the boundaries with a very large value 
    like Integer.MAX_VALUE. This ensures that invalid paths (out of bounds) do not 
    contribute to the minimum path sum, and the algorithm avoids considering them.
  *
  */

// @lc code=start

import java.util.Arrays;

class Solution {

    int minPathSumRecursive(int m, int n, int[][] grid) {
        // Base case 1: If out of bounds (invalid grid position), return a large value (Infinity equivalent)
        // This ensures that paths leading out of bounds are ignored as they contribute no valid path
        if (m < 1 || n < 1) return Integer.MAX_VALUE;
    
        // Base case 2: If at the starting point (top-left corner), return its value
        // This serves as the initial path sum for the journey
        if (m == 1 && n == 1) return grid[0][0];
    
        // Recursive call: Calculate the minimum path sum from the cell above (upward movement)
        int up = minPathSumRecursive(m - 1, n, grid);
    
        // Recursive call: Calculate the minimum path sum from the cell to the left (leftward movement)
        int left = minPathSumRecursive(m, n - 1, grid);
    
        // Add the current cell's value to the minimum of the two possible paths (up and left)
        return grid[m - 1][n - 1] + Math.min(up, left);
    }
    

    int minPathSumMemo(int m,int n,int[][] grid,int[][] dp){
        if(m<1 && n<1) return Integer.MAX_VALUE;
        if(m==1 && n==1) return grid[0][0];

        if(dp[m][n]!=-1) return dp[m][n];
        int up=minPathSumMemo(m-1, n, grid, dp);
        int left=minPathSumMemo(m, n-1, grid, dp);
        return dp[m][n] = grid[m-1][n-1] + Math.min(up, left);
    }

    int minPathSumTabulation(int[][] grid) {
        int m = grid.length; // Number of rows
        int n = grid[0].length; // Number of columns
    
        // Create a DP array with extra space for easier boundary handling
        int[][] dp = new int[m + 1][n + 1];
    
        // Initialize the boundaries with Integer.MAX_VALUE
        // This ensures out-of-bound cases are handled as invalid paths
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) dp[i][j] = Integer.MAX_VALUE; // Invalid cells
            }
        }
    
        // Fill the DP table to calculate the minimum path sum
        for (int i = 1; i <= m; i++) { // Iterate through all rows
            for (int j = 1; j <= n; j++) { // Iterate through all columns
                if (i == 1 && j == 1) {
                    // Base case: Initialize the top-left corner with the grid value
                    dp[1][1] = grid[0][0];
                } else {
                    // Calculate the minimum path sum for each cell
                    int up = dp[i - 1][j];   // Path from the cell directly above
                    int left = dp[i][j - 1]; // Path from the cell directly to the left
                    // Current cell's path sum is grid value + minimum of the two paths
                    dp[i][j] = grid[i - 1][j - 1] + Math.min(up, left);
                }
            }
        }
    
        // Return the result from the bottom-right corner of the DP table
        return dp[m][n];
    }
    
    
    public int minPathSum(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        //return minPathSumRecursive(m, n, grid);
        // int[][] dp=new int[m+1][n+1];  
        // for(int[] arr:dp) Arrays.fill(dp, -1);
        //return minPathSumMemo(m, n, grid,dp);

        return minPathSumTabulation(grid);
    }
}
// @lc code=end

