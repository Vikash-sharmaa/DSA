/* https://leetcode.com/problems/house-robber/description/

 * @lc app=leetcode id=198 lang=java
 *
 * [198] House Robber
 */

// @lc code=start



// dp[i] - amount we can rob till ith house 

class Solution {

    // n is the index of last house

    int robRecursive(int n, int[] nums) {
        if (n < 0) return 0; // Base case: No houses left to rob, so return 0.
        if (n == 0) return nums[n]; // Base case: Only one house to rob, return its value.
        if (n == 1) return Math.max(nums[0], nums[1]); // Base case: Two houses, return the max value of the two.
    
        // Option 1: Rob this house (add the current house value to the result of robbing houses up to n-2).
        int robThisHouse = nums[n] + robRecursive(n - 2, nums); // just add and think robRecursive(n - 2, nums) will be given by recursion - dont think how
    
        // Option 2: Don't rob this house (result is the maximum amount from robbing houses up to n-1).
        int dontRobThisHouse = 0 + robRecursive(n - 1, nums);
    
        // Return the maximum of the two options.
        return Math.max(robThisHouse, dontRobThisHouse);
    }
    

    int robMemo(int n, int[] nums, int[] dp) {
        if (n < 0) return 0; // Base case: No houses left to rob, so return 0.
        if (n == 0) return nums[0]; // Base case: Only one house to rob, return its value.
        if (n == 1) return Math.max(nums[0], nums[1]); // Base case: Two houses, return the max value of the two.
    
        if (dp[n] != -1) return dp[n]; // If the result for this state is already computed, return it.
    
        // Option 1: Rob this house (add the current house value to the result of robbing houses up to n-2).
        int robThisHouse = nums[n] + robMemo(n - 2, nums, dp);
    
        // Option 2: Don't rob this house (result is the maximum amount from robbing houses up to n-1).
        int dontRobThisHouse = 0 + robMemo(n - 1, nums, dp);
    
        // Store the result of the current state in the dp array and return it.
        return dp[n] = Math.max(robThisHouse, dontRobThisHouse);
    }
    

    int robTabulation(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0; // Edge case: No houses, so return 0.
        if (n == 1) return nums[0]; // Edge case: Only one house, return its value.
    
        int[] dp = new int[n]; // Create a DP array to store the maximum money that can be robbed up to each house.
        dp[0] = nums[0]; // Initialize: Maximum money robbed from the first house is its value.
        dp[1] = Math.max(nums[0], nums[1]); // Initialize: Maximum money robbed from the first two houses is the max of their values.
    
        for (int i = 2; i < n; i++) { // Iterate through the houses starting from the third house.
            // Option 1: Rob this house (add the current house value to dp[i-2]).
            int robThisHouse = nums[i] + dp[i - 2];
    
            // Option 2: Don't rob this house (take the result from dp[i-1]).
            int dontRobThisHouse = dp[i - 1];
    
            // Store the maximum of the two options in the DP array.
            dp[i] = Math.max(robThisHouse, dontRobThisHouse);
        }
    
        // Return the maximum money that can be robbed from all houses.
        return dp[n - 1];
    }
    

    public int rob(int[] nums) {
        return robTabulation(nums);
    }
}
// @lc code=end

