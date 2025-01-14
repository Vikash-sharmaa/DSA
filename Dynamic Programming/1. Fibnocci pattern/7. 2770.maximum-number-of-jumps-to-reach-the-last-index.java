/*
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
    int maximumJumpsRecursive(int i, int[] nums, int target) {
        if (i == nums.length - 1) return 0;
        int maxiJump = -1;
        for (int j = i + 1; j < nums.length; j++) {
            if (Math.abs(nums[j] - nums[i]) <= target) {
                int jumpsFromThisIndex = maximumJumpsRecursive(j, nums, target);
                if (jumpsFromThisIndex != -1) {
                    maxiJump = Math.max(maxiJump, jumpsFromThisIndex);
                }
            }
        }
        return maxiJump == -1 ? -1 : 1 + maxiJump;
    }

    int maximumJumpsMemo(int i,int[] nums,int target,int[] dp){
        if(i==nums.length-1) return 0;

        if (dp[i]!=-2) return dp[i];
        int maxJumps=-1;
        for(int j=i+1;j<nums.length;j++){
            if(Math.abs(nums[j]-nums[i])<=target){
                int jumpsFromThisIndex = maximumJumpsMemo(j, nums, target, dp);

                if(jumpsFromThisIndex!=-1){
                    maxJumps = Math.max(maxJumps, jumpsFromThisIndex);
                }
            }
        }
        return dp[i] = maxJumps==-1 ? -1 : 1+maxJumps;
    }

    int maxiJumpTabulation(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE); // Initialize DP array to represent no valid jumps.
        dp[n - 1] = 0; // Base case: No jumps needed from the last index.
    
        for (int i = n - 2; i >= 0; i--) { // Start from the second-last index and move backward.
            for (int j = i + 1; j < n; j++) { // Check all possible jumps from i to j.
                if (Math.abs(nums[j] - nums[i]) <= target) { // Valid jump condition.
                    if (dp[j] != Integer.MIN_VALUE) { // If a valid path exists from j.
                        dp[i] = Math.max(dp[i], 1 + dp[j]); // Update dp[i] with the maximum jumps.
                    }
                }
            }
        }
    
        // If no valid jumps are found starting from index 0, return -1; otherwise, return dp[0].
        return dp[0] == Integer.MIN_VALUE ? -1 : dp[0];
    }
    

    public int maximumJumps(int[] nums, int target) {
        //int maxJumpsToReachLastIndex = maximumJumpsRecursive(0, nums, target);
        int[] dp=new int[nums.length];
        Arrays.fill(dp, -2);
        //int maxJumpsToReachLastIndex = maximumJumpsMemo(0, nums, target, dp);
        int maxJumpsToReachLastIndex = maxiJumpTabulation(nums, target);
        return maxJumpsToReachLastIndex;
    }
}
// @lc code=end