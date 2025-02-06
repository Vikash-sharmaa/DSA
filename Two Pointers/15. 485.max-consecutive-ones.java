/*
 * @lc app=leetcode id=485 lang=java
 *
 * [485] Max Consecutive Ones
 */

// @lc code=start
class Solution {

/********************************************************************************************************************************/

    // Time:- O(n)     ||      Space:- O(1)

    int findMaxConsecutiveOnes1(int[] nums) {
        int n = nums.length;
        int maxOnes = 0;  // Variable to store the maximum count of consecutive 1s
        int i = 0;  // Pointer to traverse the array
    
        while (i < n) {  // Iterate through the array
            int j = i;  // `j` will count the consecutive 1s
            int currentOnes = 0;  // Track the count of current consecutive 1s
    
            // Move `j` forward while it encounters `1`s
            while (j < n && nums[j++] == 1) {
                currentOnes++;  // Increase the count of consecutive 1s
            }
    
            // Update `maxOnes` if the current streak is longer
            maxOnes = Math.max(maxOnes, currentOnes);
    
            // Move `i` to `j` to skip the processed part
            i = j;
        }
        
        return maxOnes;  // Return the longest sequence of consecutive 1s
    }

/********************************************************************************************************************************/
    
    // Time:- O(n)     ||      Space:- O(1)

    int findMaxConsecutiveOnes2(int[] nums) {
        int n = nums.length;
        int maxOnes = 0;  // Variable to store the maximum count of consecutive 1s
        int currentOnes = 0;  // Variable to count the current streak of consecutive 1s

        // Iterate through the array
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {  
                // If the current element is 1, increase the count of consecutive 1s
                currentOnes++;
            } else {  
                // If a 0 is encountered, reset the count of consecutive 1s
                currentOnes = 0;
            }
            
            // Update `maxOnes` if the current streak is longer
            maxOnes = Math.max(maxOnes, currentOnes);
        }

        return maxOnes;  // Return the longest sequence of consecutive 1s
    }

/********************************************************************************************************************************/

    public int findMaxConsecutiveOnes(int[] nums) {
        return findMaxConsecutiveOnes2(nums);
    }
}
// @lc code=end

