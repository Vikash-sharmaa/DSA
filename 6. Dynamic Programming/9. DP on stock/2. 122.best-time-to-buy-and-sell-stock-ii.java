/*  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/

 * @lc app=leetcode id=122 lang=java
 *
 * [122] Best Time to Buy and Sell Stock II
 */

// @lc code=start

import java.util.Arrays;

class Solution {

/********************************************************************************************************************/
    
        // Time:- O(2^n)        Space:- O(n)

    public int maxProfit_Recursive(int[] prices, int idx, int bought) {
        // Base case: If we reach the end of the array, no more transactions possible
        if (idx == prices.length) return 0;

        if (bought == 0) { // If we haven't bought a stock yet
            // Option 1: Buy the stock at current index and subtract its price
            int buy = -prices[idx] + maxProfit_Recursive(prices, idx + 1, 1);

            // Option 2: Skip buying and move to the next day
            int dontBuy = maxProfit_Recursive(prices, idx + 1, 0);

            // Return the maximum of both choices
            return Math.max(buy, dontBuy);
        } else { // If we have already bought a stock
            // Option 1: Sell the stock at the current index and add its price
            int sell = prices[idx] + maxProfit_Recursive(prices, idx + 1, 0);

            // Option 2: Skip selling and move to the next day
            int dontSell = maxProfit_Recursive(prices, idx + 1, 1);

            // Return the maximum of both choices
            return Math.max(sell, dontSell);
        }
    }


/********************************************************************************************************************/

    // public int maxProfit_Memo(int[] prices,int idx,int bought,int[][] dp) {
    //     if(idx==prices.length) return 0;

    //     if(dp[idx][bought]!=-1) return dp[idx][bought];
    //     if(bought==0){
    //         int buy = -prices[idx] + maxProfit_Memo(prices,idx+1,1,dp);
    //         int dontBuy =  maxProfit_Memo(prices,idx+1,0,dp);
    //         return dp[idx][bought] = Math.max(buy, dontBuy);
    //     }else{
    //         int sell = prices[idx] + maxProfit_Memo(prices, idx+1, 0,dp);
    //         int dontSell = maxProfit_Memo(prices, idx+1, 1,dp);
    //         return dp[idx][bought] = Math.max(sell, dontSell);
    //     }
    // }


    // Time:- O(2n)        Space:- O(2n)

    public int maxProfit_Memo(int[] prices,int idx,int bought,int[][] dp) {
        if(idx==prices.length) return 0;

        int profit=0;
        if(dp[idx][bought]!=-1) return dp[idx][bought];
        if(bought==0){
            int buy = -prices[idx] + maxProfit_Memo(prices,idx+1,1,dp);
            int dontBuy =  maxProfit_Memo(prices,idx+1,0,dp);
            profit = Math.max(buy, dontBuy);
        }else{
            int sell = prices[idx] + maxProfit_Memo(prices, idx+1, 0,dp);
            int dontSell = maxProfit_Memo(prices, idx+1, 1,dp);
            profit = Math.max(sell, dontSell);
        }
        return dp[idx][bought] = profit;
    }

/********************************************************************************************************************/

    // Time:- O(2n)        Space:- O(2n)

    public int maxProfit_Tabulation(int[] prices) {
        int n=prices.length;
        //return maxProfit_Recursive(prices,0,0);
        int[][] dp=new int[n+1][2];
        dp[n][0] = dp[n][1] = 0;

        for(int idx=n-1;idx>=0;idx--){
            for(int bought=0;bought<=1;bought++){
                int profit=0;
                if(bought==0){
                    int buy = -prices[idx] + dp[idx+1][1];
                    int dontBuy =  dp[idx+1][0];
                    profit = Math.max(buy, dontBuy);
                }else{
                    int sell = prices[idx] + dp[idx+1][0];
                    int dontSell = dp[idx+1][1];
                    profit = Math.max(sell, dontSell);
                }
                dp[idx][bought]=profit;
            }
        }
        return dp[0][0];
    }

/********************************************************************************************************************/

    // Time:- O(n)        Space:- O(1)

    public int maxProfit_SpaceOptimized(int[] prices) {
        int n=prices.length;
        //return maxProfit_Recursive(prices,0,0);
        int[] ahead=new int[2];
        int[] curr=new int[2];

        ahead[0] = ahead[1] = 0;

        for(int idx=n-1;idx>=0;idx--){
            for(int bought=0;bought<=1;bought++){
                int profit=0;
                if(bought==0){
                    int buy = -prices[idx] + ahead[1];
                    int dontBuy =  ahead[0];
                    profit = Math.max(buy, dontBuy);
                }else{
                    int sell = prices[idx] + ahead[0];
                    int dontSell = ahead[1];
                    profit = Math.max(sell, dontSell);
                }
                curr[bought]=profit;
            }
            ahead=curr;
        }
        return ahead[0];
    }

/********************************************************************************************************************/


    public int maxProfit_greedy(int[] prices) {
        int maxProfit=0;
        for(int i=1;i<prices.length;i++){
            if(prices[i]>prices[i-1]) maxProfit+=prices[i]-prices[i-1];
        }
        return maxProfit;
    }

/********************************************************************************************************************/
    public int maxProfit(int[] prices) {
        int n=prices.length;
        //return maxProfit_Recursive(prices,0,0);
        int[][] dp=new int[n][2];
        for(int[] a : dp){
            Arrays.fill(a, -1);
        }
        //return maxProfit_Memo(prices, 0, 0, dp);

        //return maxProfit_Tabulation(prices);
        return maxProfit_SpaceOptimized(prices);
    }


}
// @lc code=end

