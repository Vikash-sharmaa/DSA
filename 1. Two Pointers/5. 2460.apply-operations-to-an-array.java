/*
 * @lc app=leetcode id=2460 lang=java
 *
 * Extension of move zeroes question.
 */

// @lc code=start
class Solution {

/********************************************************************************************************************************/

    // Time:- O(n)      ||      Space:- O(1)
    
    public int[] applyOperations1(int[] nums) {
        int n = nums.length; // Get the length of the array
    
        // Step 1: Merge adjacent equal elements
        for (int i = 0; i < n - 1; i++) { // Iterate through the array up to the second-last element
            if (nums[i] == nums[i + 1]) { // If two consecutive elements are equal
                nums[i] *= 2; // Double the value of the first element
                nums[i + 1] = 0; // Set the next element to zero
            }
        }
    
        int i = 0; // Pointer `i` keeps track of the position to place the next non-zero element
    
        // Step 2: Move all non-zero elements to the beginning of the array
        for (int num : nums) {
            if (num != 0) { // If the element is non-zero
                nums[i++] = num; // Place it at index `i` and increment `i`
            }
        }
    
        // Step 3: Fill the remaining positions with zeroes
        while (i < n) nums[i++] = 0; // Set the remaining elements to zero

        return nums; // Return the modified array
    }

/********************************************************************************************************************************/
    
    public int[] applyOperations(int[] nums) {
            return applyOperations1(nums);
    }
}
// @lc code=end

