/*
 * @lc app=leetcode id=45 lang=java
 *
 * [45] Jump Game II
 * can be done in O(n) - using gready
 */ 

// @lc code=start

import java.util.Arrays;

class Solution {

    int jumpRecursive(int idx, int[] nums) {
        // Base case: If we've reached or passed the last index, no more jumps are needed
        if (idx >= nums.length - 1) return 0;
    
        // If the current position has no jumps possible (nums[idx] == 0), it's impossible to move forward
        if (nums[idx] == 0) return -1;
    
        int minJumps = Integer.MAX_VALUE; // Initialize to maximum value, looking for the minimum number of jumps
    
        // Loop through all possible steps from the current index (up to nums[idx] steps)
        for (int step = 1; step <= nums[idx]; step++) {
            // Recursively calculate the number of jumps needed from the new index (idx + step)
            int jumpsFromThisIndex = jumpRecursive(idx + step, nums);
    
            // If a valid jump path is found (not -1), update minJumps with the minimum value
            if (jumpsFromThisIndex != -1) {
                minJumps = Math.min(minJumps, jumpsFromThisIndex);
            }
        }
    
        // If no valid path was found (minJumps is still Integer.MAX_VALUE), return -1 (no solution)
        return minJumps == Integer.MAX_VALUE ? -1 : 1 + minJumps; // Add 1 to account for the current jump
    }


    
    

    int jumpMemo(int idx, int[] nums,int[] dp){
        if(idx>=nums.length-1) return 0;
        if (nums[idx]==0) return -1;
        
        if(dp[idx]!=-2) return dp[idx];

        int minJumps = Integer.MAX_VALUE;

        for (int step = 1; step <= nums[idx]; step++) {
            int jumpsFromThisIndex = jumpMemo(idx+step, nums, dp);
            if(jumpsFromThisIndex!=-1){
                minJumps=Math.min(minJumps, jumpsFromThisIndex);
            }
        }

        return dp[idx] = minJumps==Integer.MAX_VALUE ? -1 : 1+minJumps;
    }

    int jumpTabulation(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE); // Initialize with MAX_VALUE to indicate unreachable
        dp[n - 1] = 0; // The last index requires 0 jumps to reach itself
    
        for (int i = n - 2; i >= 0; i--) { // Start from second last index
            for (int j = 1; j <= nums[i] && i + j < n; j++) { // Loop through possible jumps
                if (dp[i + j] != Integer.MAX_VALUE) { // Check if the next index is reachable
                    dp[i] = Math.min(dp[i], 1 + dp[i + j]); // Update the dp[i] with the minimum jumps
                }
            }
        }
        return dp[0] == Integer.MAX_VALUE ? -1 : dp[0]; // If the first index is unreachable, return -1
    }
    
    



    public int jump(int[] nums) {
        //return jumpRecursive(0, nums);
        int n=nums.length;
        int[] dp=new int[n];
        Arrays.fill(dp, -2);
        //return jumpMemo(0, nums, dp);
        return jumpTabulation(nums);
    }
}
// @lc code=end

