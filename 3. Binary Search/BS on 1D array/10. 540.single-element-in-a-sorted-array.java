/* https://leetcode.com/problems/single-element-in-a-sorted-array/description/

 * @lc app=leetcode id=540 lang=java
 *
 * [540] Single Element in a Sorted Array
 */

// @lc code=start

import java.util.*;

class Solution {
/*********************************************************************************************************************************/
    // Time -> O(n)
    // Space -> O(n)
    int singleNonDuplicate_using_Map(int[] nums) {
        // Create a hashmap to store frequency of each number
        HashMap<Integer, Integer> map = new HashMap<>();

        // Count frequency of each number
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Find the number which occurred only once
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey(); // return the unique element
            }
        }

        return -1; // if no unique element found
    }

/*********************************************************************************************************************************/
    // Time -> O(n)
    // Space -> O(1)
    int singleNonDuplicate_using_XOR(int[] nums) {
        int result = 0;

        // XOR of all elements gives the unique element
        for (int num : nums) {
            result ^= num; // XOR operation
        }

        return result; // return the unique element
    }

/*********************************************************************************************************************************/

/*
    The index sequence of the duplicate numbers in the left half is always (even, odd). That means one of the following conditions will be satisfied if we are in the left half:
    If the current index is even, the element at the next odd index will be the same as the current element.
    Similarly, If the current index is odd, the element at the preceding even index will be the same as the current element.
    The index sequence of the duplicate numbers in the right half is always (odd, even). That means one of the following conditions will be satisfied if we are in the right half:
    If the current index is even, the element at the preceding odd index will be the same as the current element.
    Similarly, If the current index is odd, the element at the next even index will be the same as the current element.
 */

    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
    
        // Base cases: if the array has only one element, or the unique element is at the ends
        if (n == 1) return nums[0]; // only one element
        if (nums[0] != nums[1]) return nums[0]; // unique is at the start
        if (nums[n - 1] != nums[n - 2]) return nums[n - 1]; // unique is at the end
    
        // Binary search setup (skip the first and last element)
        int start = 1, end = n - 2;
    
        while (start <= end) {
            int mid = start + (end - start) / 2; // safe mid calculation
    
            // If mid is the unique element
            if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) 
                return nums[mid]; // found the unique element
    
            // Check which side to go:
            // Case 1: mid is in correct pair (odd-even pattern is maintained)
            if ((mid % 2 != 0 && nums[mid] == nums[mid - 1]) || 
                (mid % 2 == 0 && nums[mid] == nums[mid + 1])) {
                start = mid + 1; // unique is on the right
            } 
            // Case 2: pattern breaks, unique is on the left
            else {
                end = mid - 1;
            }
        }
    
        return -1; // fallback (problem guarantees there is always one unique)
    }
    
/*********************************************************************************************************************************/
}
// @lc code=end

