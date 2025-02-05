/*
 * @lc app=leetcode id=80 lang=java
 *
 * [80] Remove Duplicates from Sorted Array II
 */

// @lc code=start

import java.util.LinkedHashMap;
import java.util.Map;

class Solution {

/********************************************************************************************************************************/

    // Time:- O(n)      ||      Space:- O(n)

    public int removeDuplicates1(int[] nums) {
        // Using LinkedHashMap to maintain insertion order while counting occurrences
        Map<Integer, Integer> map = new LinkedHashMap<>();
    
        // Step 1: Count occurrences of each number
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1); // Increase count for `n`, defaulting to 0 if absent
        }
    
        int i = 0; // Pointer `i` tracks the position for inserting unique (or at most twice) elements
    
        // Step 2: Iterate over the map to reconstruct the modified array
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            // Allow at most 2 occurrences of each unique number
            for (int j = 0; j < Math.min(entry.getValue(), 2); j++) {
                nums[i++] = entry.getKey(); // Assign the key (number) to `nums[i]` and increment `i`
            }
        }
    
        return i; // Return the new length of the modified array
    }

/********************************************************************************************************************************/

    // Time:- O(n)   ||     Space:- O(1)

    public int removeDuplicates2(int[] nums) {
        int i = 0; // Pointer `i` keeps track of the position to place the next allowed element.
    
        for (int n : nums) { // Iterate through each number `n` in the array `nums`
            // If `i` is less than 2, allow all elements to be added. 
            // Or if the current element `n` is not equal to the element at `nums[i-2]`,
            // it means this element can appear at most twice in the modified array.
            if (i < 2 || n != nums[i - 2]) {
                nums[i++] = n; // Assign `n` to `nums[i]` and increment `i` to move the pointer forward
            }
        }
    
        return i; // Return the count of elements in the modified array, with at most two occurrences per unique element
    }

/********************************************************************************************************************************/
    
    
    public int removeDuplicates(int[] nums) {
        return removeDuplicates2(nums);
    }
}
// @lc code=end

