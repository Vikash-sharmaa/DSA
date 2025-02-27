/*
 * @lc app=leetcode id=26 lang=java
 *
 * [26] Remove Duplicates from Sorted Array
 */

// @lc code=start

import java.util.LinkedHashSet;
import java.util.Set;

class Solution {

/************************************************************************************************/

    // Using LinkedHashSet to store Unique elements of nums array.
    // Time:- O(n)      ||      Space:- O(n)

    int removeDuplicates1(int[] nums){
        Set<Integer> set = new LinkedHashSet<>();
        
        for(int n:nums) set.add(n);
        int i=0;
        for(int n:set) nums[i++]=n;
        return set.size();
    }

//************************************************************************************************/
    
    // Without any extra space 
    // Time:- O(n)      ||      Space:- O(1)
    // Below algo is working b'coz elements are sorted 

    

    // until the elements are same - we will not move i pointer 
    // if elements are not same - we will move i pointer and will assign this new number to the new i index

    int removeDuplicates2(int[] nums) {
        int i = 0; // Pointer `i` keeps track of the position where unique elements are placed.
    
        for (int n : nums) { // Iterate through each number `n` in the array `nums`
            if (i == 0 || n != nums[i]) { // If `i` is 0 (first element) or `n` is different from the last inserted unique element
                nums[i++] = n; // Assign `n` to `nums[i]` and increment `i` to move the pointer forward
            }
        }
        return i; // Return the count of unique elements
    }
    
/************************************************************************************************/

    public int removeDuplicates(int[] nums) {
        return removeDuplicates2(nums);
    }
}
// @lc code=end

