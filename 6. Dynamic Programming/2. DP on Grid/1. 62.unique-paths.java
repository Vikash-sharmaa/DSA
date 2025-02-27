/*
 * @lc app=leetcode id=62 lang=java
 *
 * [62] Unique Paths
 * 
 * if n - is index(0-based) --- consider n length array
 * if n - is size of array / or 1-based indexing use n+1 length array
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int uniquePathsRecursive(int m, int n) {
        // Base case: If we're at the bottom-right corner, there's exactly one path
        if (m == 1 && n == 1) return 1;
    
        // Base case: If we're outside the grid boundaries, there are no paths
        if (m < 1 || n < 1) return 0;
    
        // Recursive call to calculate the number of paths from the cell above
        int up = uniquePathsRecursive(m - 1, n);
    
        // Recursive call to calculate the number of paths from the cell to the left
        int left = uniquePathsRecursive(m, n - 1);
    
        // Total unique paths to the current cell is the sum of paths from above and left
        return up + left;
    }
    

    public int uniquePathsMemo(int m,int n,int[][] dp){
        if(m==1 && n==1) return 1;
        if(m<1 || n<1) return 0;

        if(dp[m][n]!=-1) return dp[m][n];
        int up = uniquePathsMemo(m-1, n,dp);
        int down = uniquePathsMemo(m, n-1,dp);

        return dp[m][n] = up + down;
    }

    public int uniquePathsTabulation(int m, int n) {
        // Create a 2D DP array to store the number of unique paths to each cell
        int[][] dp = new int[m + 1][n + 1];
    
        // Initialize the first row and column to 0 (extra boundary for easier indexing)
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) dp[i][j] = 0; // Boundary cells are set to 0
            }
        }
    
        // Fill the DP table for paths starting from (1, 1) to (m, n)
        for (int i = 1; i <= m; i++) { // Traverse rows
            for (int j = 1; j <= n; j++) { // Traverse columns
                if (i == 1 && j == 1) {
                    dp[i][j] = 1; // The start point has exactly one unique path
                } else {
                    // Calculate paths from top (down) and left (right)
                    int down = dp[i - 1][j]; // Paths coming from the cell above
                    int right = dp[i][j - 1]; // Paths coming from the cell to the left
    
                    // Sum up the paths from above and left to calculate total paths to this cell
                    dp[i][j] = down + right;
                }
            }
        }
    
        // Return the number of unique paths to the bottom-right cell
        return dp[m][n];
    }
    
    
    public int uniquePaths(int m, int n) {
        int[][] dp=new int[m+1][n+1];
        for(int[] t : dp){
            Arrays.fill(t, -1);
        }
        //return uniquePathsRecursive(m, n);
        //return uniquePathsMemo(m, n, dp);
        return uniquePathsTabulation(m, n);
    }
}
// @lc code=end

