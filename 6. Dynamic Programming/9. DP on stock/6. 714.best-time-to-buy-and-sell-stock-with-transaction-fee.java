/* https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/

 * @lc app=leetcode id=714 lang=java
 *
 * [714] Best Time to Buy and Sell Stock with Transaction Fee
 */

// @lc code=start

import java.util.Arrays;

class Solution {

/************************************************************************************************************/
// Time: O(2^n)     Space: O(n)

    public int maxProfit_Recursive(int[] prices, int idx, int bought, int fee) {
        // Base case: If we reach the end of the price array, no more transactions can be made
        if (idx == prices.length) return 0;
    
        int profit = 0; // Variable to store the maximum profit for the current state
    
        if (bought == 0) { // If we have not bought a stock yet
            // Option 1: Buy the stock at the current price (subtract the price from profit)
            int buy = -prices[idx] + maxProfit_Recursive(prices, idx + 1, 1, fee);
            // Option 2: Skip buying and move to the next day
            int dontBuy = maxProfit_Recursive(prices, idx + 1, 0, fee);
            // Take the maximum profit between buying and skipping
            profit = Math.max(buy, dontBuy);
        } else { // If we have already bought a stock
            // Option 1: Sell the stock at the current price (add price to profit, minus fee)
            int sell = prices[idx] + maxProfit_Recursive(prices, idx + 1, 0, fee);
            // Option 2: Hold the stock and move to the next day
            int dontSell = maxProfit_Recursive(prices, idx + 1, 1, fee);
            // Take the maximum profit between selling and holding
            profit = Math.max(sell - fee, dontSell);
        }
    
        return profit; // Return the maximum profit possible from the current state
    }
    

/************************************************************************************************************/
// Time: O(n)     Space: O(n)+O(n)

    public int maxProfit_Memo(int[] prices,int idx,int bought, int fee,int[][] dp) {
        if(idx==prices.length) return 0;

        if(dp[idx][bought]!=-1) return dp[idx][bought];
        int profit=0;
        if(bought==0){
            int buy = -prices[idx] + maxProfit_Memo(prices, idx+1, 1, fee,dp);
            int dontBuy = maxProfit_Memo(prices, idx+1, 0, fee,dp);
            profit=Math.max(buy, dontBuy);
        }else{
            int sell = prices[idx] + maxProfit_Memo(prices, idx+1, 0, fee,dp); // fee only when U sell
            int dontSell = maxProfit_Memo(prices, idx+1, 1, fee,dp);
            profit=Math.max(sell-fee, dontSell);
        }
        return dp[idx][bought] = profit;
    }


/************************************************************************************************************/
// Time: O(n)     Space: O(n)

public int maxProfit_Tabulation(int[] prices, int fee) {
    int n = prices.length;
    
    // Create a DP table where dp[i][0] represents the max profit at day i with no stock,
    // and dp[i][1] represents the max profit at day i with a stock in hand.
    int[][] dp = new int[n + 1][2]; 
    
    // Base case: If we are beyond the last index, profit is zero for both states.
    dp[n][0] = dp[n][1] = 0;

    // Iterate backward from the last valid index to fill the DP table bottom-up.
    for (int i = n - 1; i >= 0; i--) {
        for (int j = 0; j <= 1; j++) { // j = 0 means no stock in hand, j = 1 means holding a stock
            int profit = 0;
            if (j == 0) { // If we haven't bought a stock yet
                // Option 1: Buy the stock today and transition to "holding" state
                int buy = -prices[i] + dp[i + 1][1]; 
                // Option 2: Skip buying and move to the next day
                int dontBuy = dp[i + 1][0]; 
                // Store the maximum profit for this state
                profit = Math.max(buy, dontBuy);
            } else { // If we already own a stock
                // Option 1: Sell the stock today, pay the fee, and transition to "no stock" state
                int sell = prices[i] + dp[i + 1][0]; 
                // Option 2: Keep holding the stock and move to the next day
                int dontSell = dp[i + 1][1];
                // Store the maximum profit for this state
                profit = Math.max(sell - fee, dontSell);
            }
            dp[i][j] = profit; // Save the computed profit for this state
        }
    }

    return dp[0][0]; // The maximum profit starting from day 0 without holding any stock
}



/************************************************************************************************************/
// Time: O(n)     Space: O(1)

public int maxProfit_SpaceOptimized(int[] prices, int fee) {
    int n = prices.length;

    // Instead of a full DP table, use two 1D arrays to store results:
    // `after` represents dp[i+1] (next day's results).
    // `curr` represents dp[i] (current day's results being computed).
    int[] after = new int[2];
    int[] curr = new int[2];

    // Base case: If we are beyond the last index, profit is zero for both states.
    after[0] = after[1] = 0;

    // Iterate backward from the last valid index to compute profit bottom-up.
    for (int i = n - 1; i >= 0; i--) {
        for (int j = 0; j <= 1; j++) { // j = 0 means no stock in hand, j = 1 means holding a stock
            int profit = 0;
            if (j == 0) { // If we haven't bought a stock yet
                // Option 1: Buy the stock today and transition to "holding" state
                int buy = -prices[i] + after[1];
                // Option 2: Skip buying and move to the next day
                int dontBuy = after[0];
                // Store the maximum profit for this state
                profit = Math.max(buy, dontBuy);
            } else { // If we already own a stock
                // Option 1: Sell the stock today, pay the fee, and transition to "no stock" state
                int sell = prices[i] + after[0];
                // Option 2: Keep holding the stock and move to the next day
                int dontSell = after[1];
                // Store the maximum profit for this state
                profit = Math.max(sell - fee, dontSell);
            }
            curr[j] = profit; // Store the result for the current state
        }

        // Move `curr` to `after` for the next iteration
        after = curr;
    }

    return after[0]; // The maximum profit starting from day 0 without holding any stock
}



/************************************************************************************************************/

public int maxProfit(int[] prices, int fee) {
        int n=prices.length;
        // return maxProfit_Recursive(prices, 0, 0, fee);
        int [][] dp=new int[n][2];
        for(int[] a:dp){
            Arrays.fill(a, -1);
        }
        //return maxProfit_Memo(prices, 0, 0, fee, dp);
        //return maxProfit_Tabulation(prices, fee);

        return maxProfit_SpaceOptimized(prices, fee);
    }
}
// @lc code=end

