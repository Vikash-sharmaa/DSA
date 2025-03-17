/*
 * @lc app=leetcode id=123 lang=java
 *
 * [123] Best Time to Buy and Sell Stock III
 */

// @lc code=start


class Solution {


/**************************************************************************************************************/
// Time: O(2^n)     Space: O(n)
    public int maxProfit_Recursive(int[] prices,int idx,int bought,int transaction) {
        if(transaction==2) return 0;

        if(idx==prices.length) return 0;

        int profit=0;
        if(bought==0){
            int buy = -prices[idx] + maxProfit_Recursive(prices, idx+1, 1, transaction);
            int dontBuy = maxProfit_Recursive(prices, idx+1, 0, transaction);
            profit = Math.max(buy, dontBuy);
        }else{
            int sell = prices[idx] + maxProfit_Recursive(prices, idx+1, 0, transaction+1);
            int dontSell = maxProfit_Recursive(prices, idx+1, 1, transaction);
            profit=Math.max(sell, dontSell);
        }
        return profit;
    }

/**************************************************************************************************************/

    // public int maxProfit_Memo(int[] prices,int idx,int bought,int transaction,Map<String,Integer> dp) {
    //     if(transaction==2) return 0;

    //     if(idx==prices.length) return 0;

    //     String key = idx+":"+bought+":"+transaction;
    //     if(dp.containsKey(key)) return dp.get(key);
    //     int profit=0;
    //     if(bought==0){
    //         int buy = -prices[idx] + maxProfit_Memo(prices, idx+1, 1, transaction,dp);
    //         int dontBuy = maxProfit_Memo(prices, idx+1, 0, transaction,dp);
    //         profit = Math.max(buy, dontBuy);
    //     }else{
    //         int sell = prices[idx] + maxProfit_Memo(prices, idx+1, 0, transaction+1,dp);
    //         int dontSell = maxProfit_Memo(prices, idx+1, 1, transaction,dp);
    //         profit=Math.max(sell, dontSell);
    //     }
    //     dp.put(key, profit);
    //     return profit;
    // }

// Time: O(6*n)     Space: O(6*n)+O(n)

    public int maxProfit_Memo(int[] prices,int idx,int bought,int transaction,int[][][] dp) {
        if(transaction==2) return 0;

        if(idx==prices.length) return 0;

        if(dp[idx][bought][transaction]!=-1) return dp[idx][bought][transaction];
        int profit=0;
        if(bought==0){
            int buy = -prices[idx] + maxProfit_Memo(prices, idx+1, 1, transaction,dp);
            int dontBuy = maxProfit_Memo(prices, idx+1, 0, transaction,dp);
            profit = Math.max(buy, dontBuy);
        }else{
            int sell = prices[idx] + maxProfit_Memo(prices, idx+1, 0, transaction+1,dp);
            int dontSell = maxProfit_Memo(prices, idx+1, 1, transaction,dp);
            profit=Math.max(sell, dontSell);
        }
        return dp[idx][bought][transaction] = profit;
    }

/**************************************************************************************************************/

// Time: O(6*n)     Space: O(6*n)

    public int maxProfit_Tabulation(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][3]; // (day, holding/not, transactions)

        // Base case: No need to explicitly initialize, as Java arrays default to 0

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= 1; j++) { // 0 = No stock held, 1 = Stock held
                for (int k = 0; k < 2; k++) { // Transactions used (0 or 1)
                    int profit = 0;
                    if (j == 0) { // Buying allowed
                        int buy = -prices[i] + dp[i + 1][1][k]; // Buy stock
                        int dontBuy = dp[i + 1][0][k]; // Skip buying
                        profit = Math.max(buy, dontBuy);
                    } else { // Selling allowed
                        int sell = prices[i] + dp[i + 1][0][k + 1]; // Sell stock
                        int dontSell = dp[i + 1][1][k]; // Skip selling
                        profit = Math.max(sell, dontSell);
                    }
                    dp[i][j][k] = profit;
                }
            }
        }

        return dp[0][0][0]; // Start with no stock and 0 transactions used
    }

/**************************************************************************************************************/

// Time: O(6*n)     Space: O(1)
    public int maxProfit_SpaceOptimized(int[] prices) {
        int n = prices.length;
        
        // Using only two 2D arrays to optimize space complexity from O(n) to O(1)
        int[][] after = new int[2][3]; // Represents dp[i+1] state (next day)
        int[][] curr = new int[2][3];  // Represents dp[i] state (current day)

        // No need for explicit base case initialization, as Java arrays default to 0

        // Start iterating from the last day backwards (bottom-up approach)
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= 1; j++) { // 0 = No stock held, 1 = Stock held
                for (int k = 0; k < 2; k++) { // Transactions used (0 or 1)
                    int profit = 0;
                    
                    if (j == 0) { // Buying is allowed when no stock is held
                        int buy = -prices[i] + after[1][k]; // Buy stock and move to "holding" state
                        int dontBuy = after[0][k]; // Skip buying and stay in "not holding" state
                        profit = Math.max(buy, dontBuy); // Max profit from either decision
                    } else { // Selling is allowed when stock is held
                        int sell = prices[i] + after[0][k + 1]; // Sell stock and use one transaction
                        int dontSell = after[1][k]; // Skip selling and keep holding
                        profit = Math.max(sell, dontSell); // Max profit from either decision
                    }
                    
                    curr[j][k] = profit; // Store the result for the current state
                }
            }
            
            // Move to the next day (update after → curr)
            after = curr; // This efficiently replaces dp[i+1] with dp[i]
        }

        return after[0][0]; // Maximum profit starting with no stock and 0 transactions used
    }

/**************************************************************************************************************/
    public int maxProfit(int[] prices) {
        
        //return maxProfit_Recursive(prices, 0, 0, 0);
        // Map<String,Integer> dp=new HashMap<>();
        // return maxProfit_Memo(prices, 0, 0, 0,dp);
        int[][][] dp = new int[prices.length][2][2]; // Using max idx, bought (0/1), transaction (0/1)
        for(int i=0;i<prices.length;i++){
            for(int j=0;j<=1;j++){
                for(int k=0;k<=1;k++){
                    dp[i][j][k]=-1;
                }
            }
        }

        //return maxProfit_Memo(prices, 0, 0, 0, dp);

        //return maxProfit_Tabulation(prices);

        return maxProfit_SpaceOptimized(prices);
    }
/**************************************************************************************************************/

}
// @lc code=end

