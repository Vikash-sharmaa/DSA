/* https://leetcode.com/problems/minimum-size-subarray-sum/description/

 * @lc app=leetcode id=209 lang=java
 *
 * [209] Minimum Size Subarray Sum
 */

// @lc code=start
class Solution {

/********************************************************************************************************************************/

    // Time:- O(n^2)        ||      Space:- O(1)

    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;  // Get the length of the input array
        int minLen = Integer.MAX_VALUE;  // Initialize minLen with a large value (to track the minimum subarray length)
        
        // Iterate over each starting point of the subarray
        for (int i = 0; i < n; i++) {
            int currentSum = 0;  // Initialize sum for the current subarray
            
            // Expand the subarray from i to j
            for (int j = i; j < n; j++) {
                currentSum += nums[j];  // Add current element to sum
                
                // If the sum is at least the target, update minLen and break
                if (currentSum >= target) {
                    minLen = Math.min(minLen, j - i + 1);  // Update minLen if a smaller valid subarray is found
                    break;  // No need to check longer subarrays starting from i
                }
            }
        }
        
        // If no valid subarray was found, return 0; otherwise, return minLen
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
    

/********************************************************************************************************************************/

    // Time:- O(n)        ||      Space:- O(1)

    
    public int minSubArrayLen2(int target, int[] nums) {
        int n = nums.length;  // Get the length of the array
        int minLen = Integer.MAX_VALUE;  // Initialize minLen with a large value (to track the minimum subarray length)
        
        int currentSum = 0;  // Stores the sum of the current window
        int start = 0, end = 0;  // Two pointers to define the sliding window
    
        // Expand the window by moving 'end' pointer
        while (end < n) {
            currentSum += nums[end];  // Add current element to the sum
    
            // Shrink the window from the left while the sum is >= target
            while (currentSum >= target) {
                minLen = Math.min(minLen, end - start + 1);  // Update minLen if a smaller valid subarray is found
    
                // Remove the leftmost element from the window to try for a smaller subarray
                int toBeRemoved = nums[start];
                currentSum -= toBeRemoved;
                start++;  // Move the start pointer to the right
            }
    
            end++;  // Move the end pointer to expand the window
        }
    
        // If no valid subarray was found, return 0; otherwise, return minLen
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
    
}
// @lc code=end

