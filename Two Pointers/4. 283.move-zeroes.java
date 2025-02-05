/*
 * @lc app=leetcode id=283 lang=java
 *
 * [283] Move Zeroes
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {

/********************************************************************************************************************************/

    // Time:- O(n)      ||      Space:- O(n)
    public void moveZeroes1(int[] nums) {
        int n = nums.length; // Get the length of the array
    
        List<Integer> temp = new ArrayList<>(); // Create a temporary list to store non-zero elements
    
        // Step 1: Store all non-zero elements in the temp list
        for (int num : nums) {
            if (num != 0) {
                temp.add(num);
            }
        }
    
        int i = 0; // Pointer `i` to update the array
    
        // Step 2: Copy the non-zero elements back to `nums` in the same order
        while (i < temp.size()) nums[i] = temp.get(i++);
    
        // Step 3: Fill the remaining positions in `nums` with zeroes
        while (i < n) nums[i] = 0;
            
    }

/********************************************************************************************************************************/

    // Time:- O(n)      ||      Space:- O(1)
    public void moveZeroes2(int[] nums) {
        int i = 0; // Pointer `i` keeps track of the position to place the next non-zero element.
    
        // Step 1: Move all non-zero elements to the beginning of the array
        for (int n : nums) { 
            if (n != 0) { // If the element is non-zero
                nums[i++] = n; // Place it at index `i` and increment `i`
            }
        }
    
        // Step 2: Fill the remaining positions with zeroes
        while (i < nums.length) nums[i++] = 0; // Set the remaining elements to zero
    }

/********************************************************************************************************************************/
    

    public void moveZeroes(int[] nums){
        moveZeroes2(nums);
    }
    
}
// @lc code=end

