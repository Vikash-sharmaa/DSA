/*
 * @lc app=leetcode id=518 lang=java
 *
 * [518] Coin Change II
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    int changeRecursive(int[] coins, int n, int amount) {
        // Base case 1:
        // If the remaining amount is 0, there is exactly one way to make this amount: 
        // by using no coins. Hence, return 1.
        if (amount == 0) return 1;
    
        // Base case 2:
        // If no coins are left (`n == 0`) and the amount is not 0, 
        // it is impossible to make the amount. Hence, return 0.
        if (n == 0) return 0;
    
        // Recursive case:
        // Check if the current coin can be included in the solution:
        if (coins[n - 1] <= amount) {
            // Option 1: Pick the current coin
            // - Add the current coin (`coins[n-1]`) to the combination.
            // - Reduce the remaining amount (`amount - coins[n-1]`).
            // - Since the same coin can be used again (unbounded), we do not decrease `n`.
            int pick = changeRecursive(coins, n, amount - coins[n - 1]);
    
            // Option 2: Don't pick the current coin
            // - Skip the current coin.
            // - Reduce the number of coins available (`n-1`).
            int dontPick = changeRecursive(coins, n - 1, amount);
    
            // The total number of ways to make the amount is the sum of both options.
            return pick + dontPick;
        } else {
            // If the current coin is greater than the remaining amount:
            // We cannot pick this coin, so we skip it and move to the next coin (`n-1`).
            return changeRecursive(coins, n - 1, amount);
        }

    }
    


    int changeMemo(int[] coins,int n,int amount,int[][] dp){
        if(amount==0) return 1;
        if(n==0) return 0;

        if(dp[n][amount]!=-1) return dp[n][amount];

        if(coins[n-1]<=amount){
            int pick = changeMemo(coins, n, amount-coins[n-1], dp);
            int dontPick = changeMemo(coins, n-1, amount,dp);
            return dp[n][amount] = pick + dontPick;
        }else{
            return dp[n][amount] = changeMemo(coins, n-1, amount,dp);
        }
    }



    int changeTabulation(int[] coins, int n, int amount) {
        // Create a 2D DP table where dp[i][j] represents the number of ways to make
        // amount `j` using the first `i` coins.
        int[][] dp = new int[n + 1][amount + 1];

        // Initialize the base cases
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= amount; j++) {
                if (i == 0) dp[i][j] = 0; // If no coins are available, 0 ways to make any amount > 0
                if (j == 0) dp[i][j] = 1; // If the amount is 0, there is exactly 1 way to make it (using no coins)
            }
        }

        // Fill the DP table using the recurrence relation
        for (int i = 1; i <= n; i++) { // Iterate over coins
            for (int j = 1; j <= amount; j++) { // Iterate over amounts
                if (coins[i - 1] <= j) { 
                    // If the current coin can fit into the amount `j`:
                    // 1. Include the current coin: `dp[i][j - coins[i-1]]`
                    // 2. Exclude the current coin: `dp[i-1][j]`
                    dp[i][j] = dp[i][j - coins[i - 1]] + dp[i - 1][j];
                } else {
                    // If the current coin cannot fit, we only consider excluding it:
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // The result is the number of ways to make the `amount` using all `n` coins.
        return dp[n][amount];
    }


    public int change(int amount, int[] coins) {
        int n=coins.length;
        int [][] dp=new int[n+1][amount+1];
        for(int[] row:dp) Arrays.fill(row, -1);
        //return changeRecursive(coins, n, amount);

        //return changeMemo(coins, n, amount, dp);

        return changeTabulation(coins, n, amount);
    }
}
// @lc code=end

