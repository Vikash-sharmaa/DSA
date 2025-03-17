/*
    Given an array arr of non-negative integers and an integer target, the task is to count all subsets of the array whose sum is equal to the given target.

        Examples:

            Input: arr[] = [5, 2, 3, 10, 6, 8], target = 10
            Output: 3
            Explanation: The subsets {5, 2, 3}, {2, 8}, and {10} sum up to the target 10.


 * Similar to Equal sum - just add all the take and not take options 
 */

import java.util.Arrays;

class Solution {
    int perfectSumRecursive(int[] nums, int n, int target) {
        if (target == 0) return 1;  // Base Case: If the target is exactly 0, one subset is found.
        if (n == 0) return 0;   // Base Case: If no elements left and target is not 0, no subset exists.
        // Recursive case: Check if the current element can be included.
        if (nums[n - 1] <= target) {
            // Include the current element in the subset or exclude it.
            return perfectSumRecursive(nums, n - 1, target - nums[n - 1]) + perfectSumRecursive(nums, n - 1, target);
        } else {
            // Exclude the current element if it exceeds the target.
            return perfectSumRecursive(nums, n - 1, target);
        }
    }
    
    
    
    public static int perfectSumMemo(int[] nums, int n, int target, int[][] dp) {
        if (target == 0) return 1; // If target is 0, one valid subset exists: the empty subset
        if (n == 0) return 0; // If no elements left but target > 0, no subset is possible
        if (dp[n][target] != -1) return dp[n][target];
        // If current element is less than or equal to the target, include or exclude it
        if (nums[n - 1] <= target) {
            return dp[n][target] = perfectSumMemo(nums, n - 1, target - nums[n - 1], dp) +perfectSumMemo(nums, n - 1, target, dp);
        } else {
            // Exclude the current element
            return dp[n][target] = perfectSumMemo(nums, n - 1, target, dp);
        } 
    }
    
    
    
   public static int perfectSumTabulation(int[] nums, int n, int target) {
        // Create a DP table where dp[i][j] represents the number of subsets 
        int[][] dp = new int[n + 1][target + 1];
        // Initialize the DP table
        for (int i = 0; i <= n; i++) dp[i][0] = 1; // There's one way to get a sum of 0: the empty subset
        for (int j = 1; j <= target; j++) dp[0][j] = 0; // There's no way to get a positive sum with 0 elements
        // Fill the DP table
        for (int i = 1; i <= n; i++) { // Loop over elements
            for (int j = 1; j <= target; j++) { // Loop over target values
                if (nums[i - 1] <= j) { // If the current element can be included
                    int take = dp[i - 1][j - nums[i - 1]]; // Include the element
                    int notTake = dp[i - 1][j]; // Exclude the element
                    dp[i][j] = take + notTake; // Total ways to achieve sum j
                } else {
                    dp[i][j] = dp[i - 1][j]; // Element cannot be included
                }
            }
        }
        return dp[n][target];   // Return the result: the number of subsets that sum to the target
    }
    
    
    
    public int perfectSum(int[] nums, int target) {
        // code here
        int n=nums.length;
        int zeroCount = 0;
        for (int num : nums) if (num == 0) zeroCount++;
         // Remove zeroes from consideration for the subset-sum logic
        int[] filteredNums = Arrays.stream(nums).filter(num -> num != 0).toArray();
        int filteredN = filteredNums.length;
        int [][]dp=new int[filteredN+1][target+1];
        for(int i=0;i<=filteredN;i++){
            for(int j=0;j<=target;j++) dp[i][j]=-1;
        }
        int result = perfectSumTabulation(filteredNums,filteredN,target);
        return result * (int) Math.pow(2,zeroCount);  // considering subsets having 0 along, 
    }
}