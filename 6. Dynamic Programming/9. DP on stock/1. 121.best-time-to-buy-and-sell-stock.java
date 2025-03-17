/* https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/

 * @lc app=leetcode id=121 lang=java
 *
 * [121] Best Time to Buy and Sell Stock
 */

// @lc code=start
class Solution {

/***************************************************************************************/

// Time:- O(n^2)        Space:- O(1)

    public int maxProfit_1(int[] prices){
        int n=prices.length;
        int maxProfit=0;
        for(int i=0;i<n-1;i++){
            for(int j=i;j<n;j++){
                maxProfit=Math.max(maxProfit, prices[j]-prices[i]);
            }
        }
        return maxProfit;
    }

/***************************************************************************************/

// Time:- O(n)        Space:- O(n)

    public int maxProfit_2(int[] prices) {
        int n = prices.length;  
        int[] minToLeft =new int[n];
        minToLeft[0]=prices[0];
        for(int i=1;i<n;i++){
            minToLeft[i]=Math.min(minToLeft[i-1],prices[i]);
        }
        int maxProfit = 0;
        for(int i=0;i<n;i++){
            maxProfit=Math.max(maxProfit, prices[i]-minToLeft[i]);
        }
        return maxProfit;
    }

/***************************************************************************************/

// Time:- O(n)        Space:- O(1)

    public int maxProfit_3(int[] prices) {
        int n = prices.length;  
        int maxProfit = 0;  // Stores the maximum profit possible
        int minPriced = prices[0];  // Keeps track of the minimum price encountered so far
        
        for (int i = 1; i < n; i++) {  
            // Calculate profit if selling on day i (prices[i] - minPriced)
            maxProfit = Math.max(maxProfit, prices[i] - minPriced);
            // Update the minimum price encountered so far
            minPriced = Math.min(minPriced, prices[i]);
        }
        return maxProfit;  // Return the maximum profit found
    }

/***************************************************************************************/

    public int maxProfit(int[] prices) {
        return maxProfit_2(prices);
    }

/***************************************************************************************/
}
// @lc code=end

