/* https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/

 * @lc app=leetcode id=309 lang=java
 *
 * [309] Best Time to Buy and Sell Stock with Cooldown
 */

// @lc code=start

import java.util.Arrays;

class Solution {

/***********************************************************************************************************************/

// Time: O(2^n)     Space: O(n)
    public int maxProfit_Recursive(int[] prices, int idx, int bought) {
        // Base case: If the index exceeds the price array, no more transactions are possible
        if (idx >= prices.length) return 0;
    
        int profit = 0; // Initialize profit for the current state
    
        if (bought == 0) { // Buying is allowed
            // Option 1: Buy stock at the current price and move to the next day with `bought = 1`
            int buy = -prices[idx] + maxProfit_Recursive(prices, idx + 1, 1);
            
            // Option 2: Skip buying and check the next day
            int dontBuy = maxProfit_Recursive(prices, idx + 1, 0);
            
            // Maximize profit between buying and not buying
            profit = Math.max(buy, dontBuy);
        } else { // Selling is allowed
            // Option 1: Sell stock and move to `idx + 2` (due to cooldown)
            int sell = prices[idx] + maxProfit_Recursive(prices, idx + 2, 0);
            
            // Option 2: Skip selling and check the next day
            int dontSell = maxProfit_Recursive(prices, idx + 1, 1);
            
            // Maximize profit between selling and not selling
            profit = Math.max(sell, dontSell);
        }
    
        return profit; // Return the maximum profit for the current state
    }
    

/***********************************************************************************************************************/

// Time: O(n)     Space: O(n) + O(n)
    public int maxProfit_Memo(int[] prices,int idx,int bought,int[][] dp) {
        if(idx>=prices.length) return 0;

        int profit=0;

        if(dp[idx][bought]!=-1) return dp[idx][bought];
        if(bought==0){
            int buy = -prices[idx] + maxProfit_Memo(prices, idx+1, 1,dp);
            int dontBuy = maxProfit_Memo(prices, idx+1, 0,dp);
            profit=Math.max(buy, dontBuy);
        }else{
            int sell = prices[idx] + maxProfit_Memo(prices, idx+2, 0,dp);
            int dontSell = maxProfit_Memo(prices, idx+1, 1,dp);
            profit=Math.max(dontSell, sell);
        }
        return dp[idx][bought] = profit;
    }

/***********************************************************************************************************************/

// Time: O(n)     Space: O(n)
    public int maxProfit_Tabulation(int[] prices){
        int n=prices.length;
        int[][] dp=new int[n+1][2];

        for(int i=0;i<=n;i++){
            for(int j=0;j<=1;j++){
                if(i==n) dp[i][j]=0;
            }
        }

        for(int i=n-1;i>=0;i--){
            for(int j=0;j<=1;j++){
                int profit=0;
                if(j==0){
                    int buy = -prices[i] + dp[i+1][1];
                    int dontBuy = dp[i+1][0];
                    profit=Math.max(buy, dontBuy);
                }else{
                    int sell = prices[i] + (i + 2 <= n ? dp[i + 2][0] : 0);
                    int dontSell = dp[i+1][1];
                    profit=Math.max(dontSell, sell);
                }
                dp[i][j]=profit;
            }
        }
        return dp[0][0];
    }

/***********************************************************************************************************************/

    public int maxProfit(int[] prices) {
       // return maxProfit_Recursive(prices,0,0);

       int n=prices.length;
       int[][] dp=new int[n][2];
       for(int[] a:dp){
        Arrays.fill(a, -1);
       }
       //return maxProfit_Memo(prices, 0, 0, dp);
       return maxProfit_Tabulation(prices);
    }
}
// @lc code=end

