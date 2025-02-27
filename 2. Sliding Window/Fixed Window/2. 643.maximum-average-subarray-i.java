/*
 * @lc app=leetcode id=643 lang=java
 *
 * 
 */

// @lc code=start
class Solution {

/********************************************************************************************************************************/

    // Time:- O(n*k)        ||      Space:- O(1)

    public double findMaxAverage1(int[] nums, int k) {
        int n = nums.length; // Get the length of the array
        double maxSum = Integer.MIN_VALUE; // Track current sum and max sum

        for(int i=0;i<n-k+1;i++){
            double currentSum = 0;
            for(int j=i;j<i+k;j++){
                currentSum+=nums[j];
            }
            maxSum=Math.max(maxSum, currentSum);
        }

        return maxSum / k; // Return the maximum average
    }

/********************************************************************************************************************************/

    // Time:- O(n)        ||      Space:- O(1)

    public double findMaxAverage2(int[] nums, int k) {
        int n = nums.length; // Get the length of the array
        double currentSum = 0, maxSum = Integer.MIN_VALUE; // Track current sum and max sum
    
        int start = 0, end = 0; // Two pointers for the sliding window
    
        while (end < n) { // Iterate through the array
            currentSum += nums[end]; // Add the current element to the window sum
    
            // When the window size reaches 'k'
            if (end - start + 1 == k) {
                maxSum = Math.max(maxSum, currentSum); // Update the maximum sum found
    
                currentSum -= nums[start]; // Remove the element at 'start' to slide the window
                start++; // Move the 'start' pointer ahead
            }
    
            end++; // Expand the window by moving the 'end' pointer
        }
    
        return maxSum / k; // Return the maximum average
    }
    
}
// @lc code=end

