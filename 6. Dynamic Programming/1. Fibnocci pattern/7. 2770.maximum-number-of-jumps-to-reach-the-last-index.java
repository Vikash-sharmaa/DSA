/* https://leetcode.com/problems/maximum-number-of-jumps-to-reach-the-last-index/description/
 * @lc app=leetcode id=2770 lang=java
 *
 * 
 **>  If we are at any index i then we can have multiple indexes to which we can jump.So we need to explore all 
      possible choices which satisfies the condition and we find maxJumps to last from this index.
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;

class Solution {

    // Recursive + Memoization approach (not used in final function)
    private int maximumJumps(int i, int[] nums, int tar, int[] dp) {
        if (i == 0) return 0; // Base case: If we're at index 0, no jumps are needed.

        if (dp[i] != -1) return dp[i]; // If already computed, return stored result.

        int maxi = Integer.MIN_VALUE; // Initialize max jumps with a very small value.

        // Iterate backwards to find a valid jump
        // for each i - i has - i-1 to 0 choices 
        // maxi will hold the max value upto that choice

        for (int j = i - 1; j >= 0; j--) {
            // Check if we can jump from index j to i
            if (Math.abs(nums[j] - nums[i]) <= tar) {
                int jumps = maximumJumps(j, nums, tar, dp); // Recursive call to check jumps for index j
                if (jumps != -1) { // Only consider valid jumps
                    maxi = Math.max(maxi, jumps); // Update max jumps
                }
            }
        }

        // If no valid jump was found, return -1
        return dp[i] = (maxi == Integer.MIN_VALUE) ? -1 : 1+maxi;       // found max before ith added 1 step 
    }


    /*
        Create a DP array dp[i], where dp[i] stores the maximum jumps to reach index i.
        Initialize dp[0] = 0 since we start from index 0 and no jumps are needed.
        Iterate over each index i (destination) and check all previous indices j (possible sources).
        Update dp[i] using valid jumps from j to i.
     */


    // Tabulation (Bottom-Up Dynamic Programming) approach
    public int maximumJumpsT(int[] nums, int tar) {
        int n = nums.length;
        int[] dp = new int[n]; // DP array to store max jumps to reach each index
        Arrays.fill(dp, -1); // Initialize all values as -1 (unreachable)
        dp[0] = 0; // No jumps needed to stay at index 0

        // Process each index as the destination
        for (int i = 1; i < n; i++) {
            // Check previous indices to find a valid jump
            for (int j = i - 1; j >= 0; j--) {
                if (Math.abs(nums[j] - nums[i]) <= tar && dp[j] != -1) { // Valid jump condition
                    dp[i] = Math.max(dp[i], dp[j] + 1); // Update max jumps
                }
            }
        }

        return dp[n - 1]; // Return max jumps required to reach last index
    }

    // Wrapper function to switch between recursion + memoization and tabulation
    public int maximumJumps(int[] nums, int tar) {
       int n = nums.length;
       int[] dp = new int[n];
       Arrays.fill(dp, -1);

       // Recursive + Memoization approach (commented out)
        return maximumJumps(n - 1, nums, tar, dp);

       // Tabulation approach (preferred due to efficiency)
       //return maximumJumpsT(nums, tar);
    }
}
    
