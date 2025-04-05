/*  https://leetcode.com/problems/jump-game/


 * @lc app=leetcode id=55 lang=java
 *
 * [55] Jump Game
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    boolean canJumpRecursive(int idx,int[] nums){
        if(idx==nums.length-1) return true;
        if(nums[idx]==0) return false;

        for(int step=1;step<=nums[idx];step++){
            boolean canJumpFromIfThisStepIsTaken = canJumpRecursive(idx+step, nums);
            if(canJumpFromIfThisStepIsTaken) return true;
        }
        return false;
    }

    boolean canJumpMemo(int idx, int[] nums,Boolean[] dp){
        if(idx==nums.length-1) return true;
        if(nums[idx]==0) return false;

        if(dp[idx]!=null) return dp[idx];

        for(int step=1;step<=nums[idx];step++){
            boolean canJumpFromIfThisStepIsTaken = canJumpMemo(idx+step, nums, dp);
            if(canJumpFromIfThisStepIsTaken) return dp[idx] = true;
        }

        return dp[idx] = false;
    }
/*
 
    Start from the last index, which is always reachable from itself, and work backward to determine if each index can eventually lead to the last.
    Use a dp array to track whether each index can reach the end, leveraging previously computed results for efficient decision-making.
    For each index, explore all possible jumps up to the value at that index and check if any jump lands on a reachable position (true in dp).
    If any valid jump exists for an index, mark it as reachable (true) and use this information to evaluate earlier indices.

 */
    boolean canJumpTabulation(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[n - 1] = true; // Base case: last index is always reachable from itself
    
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 1; j <= nums[i]; j++) { // Loop through valid jumps
                if (i + j < n && dp[i + j]) { // Check if any jump leads to a reachable index
                    dp[i] = true;
                    break; // No need to check further
                }
            }
        }
        return dp[0]; // Return whether the first index can reach the last
    }
    
    public boolean canJump(int[] nums) {
        int n=nums.length;
        Boolean[] dp=new Boolean[n];
        //return canJumpRecursive(0, nums);
        //return canJumpMemo(0, nums, dp);
        return canJumpTabulation(nums);
    }
}
// @lc code=end

