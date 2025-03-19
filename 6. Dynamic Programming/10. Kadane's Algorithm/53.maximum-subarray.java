/*
 * @lc app=leetcode id=53 lang=java
 *
 * [53] Maximum Subarray
 */
/*
    If the array would have only positive numbers - whole array will be the subarray with max sum
    But when array has negative numbers - we should think of DP(KA)
    Here, Sliding window can't be applied - give wrong results 
 */

// @lc code=start
class Solution {

/********************************************************************************************************************************/

    // Time:- O(n^2)        ||      Space:- O(1)

    public int maxSubArray_1(int[] nums) {
        int n = nums.length;
        int maxSubarraySum = Integer.MIN_VALUE;  // Initialize max sum to the smallest possible value
    
        // Iterate over all possible starting indices of subarrays
        for (int i = 0; i < n; i++) {
            int currentSum = 0;  // Initialize sum for subarray starting at index 'i'
    
            // Iterate over all possible ending indices of subarrays
            for (int j = i; j < n; j++) {
                currentSum += nums[j];  // Expand the subarray by adding nums[j]
    
                // Update maxSubarraySum if currentSum is greater
                maxSubarraySum = Math.max(maxSubarraySum, currentSum);
            }
        }
        return maxSubarraySum;  // Return the maximum subarray sum found
    }
    

/********************************************************************************************************************************/

    // Time: O(n)           Space: O(1)

    public int maxSubArray_2(int[] nums) {
        int n = nums.length;
        
        // Variable to track the maximum sum ending at the current position
        int currentSum = nums[0];
        
        // Variable to track the maximum subarray sum found so far
        int maxSumTillNow = nums[0];
    
        // Loop through the array, starting from index 1
        for (int i = 1; i < n; i++) {
            // Decide whether to start a new subarray or continue the existing one
            currentSum = Math.max(nums[i], currentSum + nums[i]);
    
            // Update the global maximum if the current subarray sum is greater
            maxSumTillNow = Math.max(maxSumTillNow, currentSum);
        }
        
        // Return the maximum subarray sum found
        return maxSumTillNow;
    }

/********************************************************************************************************************************/

    public int maxSubArray(int[] nums) {
        return maxSubArray_1(nums);
    }
    
/********************************************************************************************************************************/

}
// @lc code=end

