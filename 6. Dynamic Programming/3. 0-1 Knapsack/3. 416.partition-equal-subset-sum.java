/* https://leetcode.com/problems/partition-equal-subset-sum/description/

 * @lc app=leetcode id=416 lang=java
 * s1 == s2 => s1-s2=0
 * 
 * If sum of all the elements is odd - we cant at all divide it to two parts having same sum.
 * else if we find one subset as half of the sum of the elements then another one definitely exists.
 * 
 */
class Solution {
    boolean isSubsetSum(int[] nums, int target) {
        int n = nums.length;
    
        // Initialize a DP table where dp[i][j] represents whether it's possible to achieve
        // a sum of 'j' using the first 'i' elements of the array.
        Boolean[][] dp = new Boolean[n + 1][target + 1];
    
        // Base case initialization
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                if (i == 0) dp[i][j] = false; // If no items, no positive sum is possible
                if (j == 0) dp[i][j] = true;  // A sum of 0 is always possible (by picking no elements)
            }
        }
    
        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                if (nums[i - 1] <= j) {
                    // Include the current element or skip it
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];
                } else {
                    // Skip the current element if it exceeds the remaining sum
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
    
        // Return whether it's possible to achieve the target sum using all elements
        return dp[n][target];
    }
    
    public boolean canPartition(int[] nums) {
        int count = 0;
    
        // Calculate the total sum of the array
        for (int i = 0; i < nums.length; i++) count += nums[i];
    
        // If the total sum is odd, it's impossible to divide into two subsets with equal sum
        if (count % 2 != 0) return false;
    
        // Check if there exists a subset with sum equal to half of the total sum
        return isSubsetSum(nums, count / 2);
    }
    
}


