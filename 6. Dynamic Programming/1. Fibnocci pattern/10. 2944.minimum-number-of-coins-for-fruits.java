/* https://leetcode.com/problems/minimum-number-of-coins-for-fruits/description/

 * 
 * Note: How above method will take care of not_take case i.e skip case?
   Ans:  when we are at ith position we are directly calling solve with value of i = j where 
         'j' can be any value between i+1 to 2*i+1. so when you are calling j = i + 2 that means - not taken i + 1 value.
         If we are calling 'i+4' then ,it means we skipping 'i+1' , 'i+2' , 'i+3'. 
         And so on.
 */

import java.util.Arrays;

class Solution {
    int minimumCoinsRecursive(int idx,int n, int[] prices) {
        // Base case: If index exceeds the array bounds, no cost is incurred
        if (idx > n) return 0;
    
        // buy ith fruit and take minimum of next 'i' fruit.
        // next 'i' fruits i.e till index '2*i' free me buy kar sakte hain. i.e yahan tak skip kar sakte h .
        // '2*i + 1' pe har hal me hmko buy karna hoga agar pura i.e '2*i' tak skip karte h tb.

        // Initialize the minimum cost as infinity
        int minCost = Integer.MAX_VALUE;
    
        // Iterate over the valid range of indices to explore next steps
        for (int next = idx + 1; next <= 2*idx+1 && next < n; next++) {
            // Recursively calculate the minimum cost for each valid next position
            minCost = Math.min(minCost, minimumCoinsRecursive(next,n, prices));
        }
    
        // Add the price of the current fruit
        return prices[idx-1] + (minCost == Integer.MAX_VALUE ? 0 : minCost);
    }
    
    
    int minimumCoinsMemo(int idx,int[] prices,int[] dp){
        if (idx>prices.length) return 0;
        
        if(dp[idx]!=-1) return dp[idx];

        int minCost = Integer.MAX_VALUE;
        for(int next=idx+1;next<=2*idx+2;next++){
            minCost=Math.min(minCost, minimumCoinsMemo(next, prices, dp));
        }

        return dp[idx] = prices[idx] + (minCost == Integer.MAX_VALUE ? 0 : minCost);
    }


    int minimumCoinsTabulation(int[] prices) {
        int n = prices.length;
    
        // DP array to store the minimum cost up to each index
        int[] dp = new int[n + 1];
        dp[0] = 0; // Base case: no cost for 0 fruits
    
        // Fill the DP array from 1 to n
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + prices[i - 1]; // Default: add the current fruit's price
    
            // Apply the skipping rule
            for (int j = i - 1; 2 * j >= i; j--) {
                dp[i] = Math.min(dp[i], dp[j - 1] + prices[j - 1]);
            }
        }
    
        // Return the total minimum cost to buy all fruits
        return dp[n];
    }
    
    

    

    public int minimumCoins(int[] prices) {
        //return minimumCoinsRecursive(0, prices);
        int[] dp=new int[prices.length+1];
        Arrays.fill(dp, -1);
        return minimumCoinsMemo(0,prices,dp);

        //return minimumCoinsTabulation(prices);
    }
}

