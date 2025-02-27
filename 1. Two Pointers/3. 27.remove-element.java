/*
 * @lc app=leetcode id=27 lang=java
 *
 * [27] Remove Element
 */

// @lc code=start
class Solution {
    // Time:- O(n)      ||      Space:- O(1)
    
    public int removeElement(int[] nums, int val) {
        int i = 0; // Pointer `i` keeps track of the position where non-`val` elements should be placed.
    
        for (int n : nums) { // Iterate through each number `n` in the array `nums`
            if (n != val) {  // If the current element `n` is not equal to `val`
                nums[i++] = n; // Assign `n` to `nums[i]` and increment `i` to move the pointer forward
            }
        }
        return i; // Return the count of elements that are not equal to `val`
    }
}
// @lc code=end

