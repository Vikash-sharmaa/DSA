/*
 * @lc app=leetcode id=673 lang=java
 * 
 *     
        
        array:  1 5 4 3 2 6 7 10 8 9 
        length: 1 2 2 2 2 3 4 5 5 6 
        count:  1 1 1 1 1 4 4 4 4 4

 */

import java.lang.reflect.Array;
import java.util.Arrays;

class Solution {

    /*
      
        Dynamic Programming Array (dp):     Tracks the length of the longest increasing subsequence ending at each index.
        Count Array (count):    Tracks the number of ways to form the LIS for each index.
        Two Loops:      The outer loop processes each element, while the inner loop looks back at previous elements to check if they can extend the LIS.
        Final Calculation:      Sums up the counts of all LIS with the maximum length.

     */
    
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
    
        // `dp[i]` represents the length of the longest increasing subsequence (LIS) ending at index `i`.
        int[] dp = new int[n];
    
        // `count[i]` represents the number of LIS ending at index `i`.
        int[] count = new int[n];
    
        // Initialize `dp` and `count` arrays with 1 because the LIS at any single element is the element itself.
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);
    
        int maxLIS = 0; // To track the maximum length of LIS found.
    
        // Iterate through each element in the array.
        for (int i = 0; i < n; i++) {
            // Check all previous elements to find possible subsequences ending at `i`.
            for (int j = 0; j < i; j++) {
                // If the current element `nums[i]` is greater than a previous element `nums[j]`,
                // it can extend the LIS ending at `j`.
                if (nums[j] < nums[i]) {
                    // Case 1: Extending the LIS from `j` results in a longer LIS at `i`.
                    if (1 + dp[j] > dp[i]) {
                        dp[i] = 1 + dp[j]; // Update the LIS length at `i`.
                        count[i] = count[j]; // Inherit the count of LIS from `j`.
                    }
                    // Case 2: Extending the LIS from `j` results in the same LIS length at `i`.
                    else if (1 + dp[j] == dp[i]) {
                        count[i] += count[j]; // Add the count of LIS from `j`.
                    }
                }
            }
    
            // Update the maximum LIS length found so far.
            if (dp[i] > maxLIS) {
                maxLIS = dp[i];
            }
        }
    
        int totalLIS = 0; // To count the total number of LIS of maximum length.
    
        // Iterate through the `dp` array to count all LIS with length equal to `maxLIS`.
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxLIS) {
                totalLIS += count[i];
            }
        }
    
        return totalLIS; // Return the total number of LIS of maximum length.
    }
    
}

