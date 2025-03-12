/* https://leetcode.com/problems/triangle/description/

 *
 * @lc app=leetcode id=120 lang=java
 *
 * Destination not fixed Pattern
 * 
 */

// @lc code=start

import java.util.Arrays;
import java.util.List;

class Solution {

    // time: O(2^(m*n))  # for every n^2 cell we have two choices
    int minimumTotalRecursive(int i,int j,int n,List<List<Integer>> triangle){
        if(i==n-1) return triangle.get(i).get(j);

        int down = minimumTotalRecursive(i+1, j, n, triangle);
        int diagonal = minimumTotalRecursive(i+1, j+1, n, triangle);

        return triangle.get(i).get(j) + Math.min(down, diagonal);
    }

    // time: O(n^2), n^2 subproblems 
    // time: O(n^2), n^2: dp space + o(n): Recursion depth
    int minimumTotalMemo(int i,int j,int n,List<List<Integer>> triangle,int[][] dp){
        if(i==n-1) return triangle.get(i).get(j);

        if(dp[i][j]!=-1) return dp[i][j];
        int down = minimumTotalMemo(i+1, j, n, triangle, dp);
        int diagonal = minimumTotalMemo(i+1, j+1, n, triangle, dp);

        return dp[i][j] = triangle.get(i).get(j) + Math.min(down, diagonal);
    }

    // State of dp[i][j]: Represents the minimum path sum from the element at position (i, j) to the bottom row of the triangle.
    int minimumTotalTabulation(List<List<Integer>> triangle) {
        int n = triangle.size();  // Number of rows in the triangle
    
        // Create a DP table to store the minimum path sum to each element
        int[][] dp = new int[n][n];
    
        // Initialize the last row of the DP table with the values from the triangle
        // As the last row has no further rows to move to, it directly holds the values
        for (int i = 0; i < n; i++) {
            dp[n - 1][i] = triangle.get(n - 1).get(i);
        }
    
        // Fill the DP table from bottom to top
        for (int i = n - 2; i >= 0; i--) {  // Start from the second last row upwards
            for (int j = 0; j <=i; j++) {  // Iterate through each element in the row
                // Calculate the minimum path sum from the current element
                int down = dp[i + 1][j];        // Path going straight down
                int diagonal = dp[i + 1][j + 1]; // Path going diagonally to the right
                // Store the result by adding the current element to the minimum of the two paths
                dp[i][j] = triangle.get(i).get(j) + Math.min(down, diagonal);
            }
        }
    
        // Return the minimum path sum from the top of the triangle
        return dp[0][0];  // State dp[0][0] holds the minimum path sum to reach the top
    }
    

    public int minimumTotal(List<List<Integer>> triangle) {
        int n=triangle.size();
        //return minimumTotalRecursive(0, 0, m, triangle);

        int[][] dp=new int[n][n];
        for(int[] row:dp) Arrays.fill(row, -1);
        //return minimumTotalMemo(0, 0, n, triangle, dp);
        return minimumTotalTabulation(triangle);
    }
}
// @lc code=end

