/*
 * @lc app=leetcode id=63 lang=java
 *
 * 
 * 
 * when dealing with 1 - based indexing (length of array) - index will be at length-1
 * 
 * Always do like this only(False condition + out of bound cases all together first then base cases).
   write the code for normal Q(without obstacle) just treat 'obstacle' in invalid base case in any Q .
 * 
 * 
 * 
 * Checking at first only , if the first or last position already blocked return 0 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    int uniquePathsWithObstaclesRecursive(int m, int n, int[][] obstacleGrid) {
        // Base case 1: If out of bounds or the current cell is an obstacle, no path is possible
        if (m < 1 || n < 1 || obstacleGrid[m - 1][n - 1] == 1) return 0;

        // Base case 2: If at the top-left corner (start point), return 1 as there's exactly one way
        if (m == 1 && n == 1) return 1;
    
        // Recursive call: Calculate paths from the cell above (up)
        int up = uniquePathsWithObstaclesRecursive(m - 1, n, obstacleGrid);
    
        // Recursive call: Calculate paths from the cell to the left (left)
        int left = uniquePathsWithObstaclesRecursive(m, n - 1, obstacleGrid);
    
        // Return the total number of unique paths to reach the current cell
        return up + left;
    }
    


    int uniquePathsWithObstaclesMemo(int m,int n,int[][] obstacleGrid,int[][] dp){
        if(m<1 || n<1 || obstacleGrid[m-1][n-1]==1) return 0;
        if(m==1 && n==1) return 1;

        if(dp[m][n]!=-1) return dp[m][n];
        
        int up=uniquePathsWithObstaclesMemo(m-1,n,obstacleGrid,dp);
        int left=uniquePathsWithObstaclesMemo(m, n-1, obstacleGrid, dp);
        return dp[m][n] = up+left;
    }



    // dp[i][j]:
    // Represents the number of unique paths to reach the cell (i, j) (1-based indexing) from the starting cell (1, 1).
    // It includes all valid paths that navigate around any obstacles in the grid.

    int uniquePathsWithObstaclesTabulation(int[][] obstacleGrid) {
        int m = obstacleGrid.length; // Number of rows in the grid
        int n = obstacleGrid[0].length; // Number of columns in the grid
    
        // Create a DP table with 1-based indexing and initialize it with zeros
        int[][] dp = new int[m + 1][n + 1];
    
        // Initialize the first row and first column of the DP table
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // Setting dp[0][*] and dp[*][0] to 0 for easier boundary handling
                if (i == 0 || j == 0) dp[i][j] = 0;
            }
        }
    
        // Populate the DP table
        for (int i = 1; i <= m; i++) { // Iterate through all rows
            for (int j = 1; j <= n; j++) { // Iterate through all columns
                if (i == 1 && j == 1) dp[i][j] = 1 ;
                else if (obstacleGrid[i - 1][j - 1] == 1) dp[i][j] = 0;
                else {
                    // Sum of paths coming from the top cell and the left cell
                    int down = dp[i - 1][j];   // Paths coming from above
                    int right = dp[i][j - 1]; // Paths coming from the left
                    dp[i][j] = down + right;
                }
            }
        }
    
        // Return the number of unique paths to the bottom-right corner
        return dp[m][n];
    }
    
    
    // handler method
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m=obstacleGrid.length;
        int n=obstacleGrid[0].length;
        if(obstacleGrid[0][0]==1 || obstacleGrid[m-1][n-1]==1) return 0; //Checking at first only , if the first or last position already blocked return 0 
        //return uniquePathsWithObstaclesRecursive(m, n, obstacleGrid);
        int [][] dp=new int[m+1][n+1];
        for(int[] arr : dp) Arrays.fill(arr, -1);
        //return  uniquePathsWithObstaclesMemo(m, n, obstacleGrid, dp);

        return uniquePathsWithObstaclesTabulation(obstacleGrid);
    }
}
// @lc code=end

