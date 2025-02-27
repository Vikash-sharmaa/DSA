/*
 *
 *    
     Logic: 
        Since first and last element are neighbour so both can't be the part of ans simultaneously.
        i.e ans will be condition only one of them so ans will equal to the max(when we don't rob the first house, when we don't rob the last house).
 * 
 * 
 */

class Solution {
    public int robTabulation(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0; // No houses to rob.
        if (n == 1) return nums[0]; // Only one house to rob.
     
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
    
        for (int i = 2; i < n; i++) {
            int robCurrent = nums[i] + dp[i - 2]; // Rob this house and add dp[i-2].
            int dontRobCurrent = dp[i - 1]; // Skip this house, take dp[i-1].
            dp[i] = Math.max(robCurrent, dontRobCurrent); // Maximum of both options.
        }
    
        return dp[n - 1]; // Maximum money that can be robbed.
    }
    
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0; // No houses to rob.
        if (n == 1) return nums[0]; // Only one house to rob.
    
        // Exclude the first house and consider robbing the rest.
        int[] leaveFirst = new int[n - 1];
        for (int i = 1; i < n; i++) leaveFirst[i - 1] = nums[i];

        // Exclude the last house and consider robbing the rest.
        int[] leaveLast = new int[n - 1];
        for (int i = 0; i < n - 1; i++) leaveLast[i] = nums[i];
    
        // Calculate the maximum profit for both cases and return the maximum.
        int maxLeavingFirst = robTabulation(leaveFirst);
        int maxLeavingLast = robTabulation(leaveLast);
    
        return Math.max(maxLeavingFirst, maxLeavingLast);
    }
    
}

