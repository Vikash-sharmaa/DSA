/* https://leetcode.com/problems/remove-duplicates-from-sorted-array/

 * @lc app=leetcode id=26 lang=java
 *
 * [26] Remove Duplicates from Sorted Array
 */

// @lc code=start

import java.util.LinkedHashSet;
import java.util.Set;

class Solution {

/**********************************************************************************************************/

// Time:- O(n)             Space:- O(n)
    // Using HashSet Frequency
    /*
        1. Store the elements in a set.
        2. Store back the elements in the array.
        3. Return the size of the set.
     */
    public int removeDuplicates1(int[] nums) {
        Set<Integer> set=new LinkedHashSet<>();
        for(int num:nums) set.add(num);
        int i=0;
        for(int num:set) nums[i++]=num;
        return set.size();
    }

/**********************************************************************************************************/

    // // Using Two Pointers
    // /*
    //     1. iterate through the array.
    //     2. Store the current element in a variable
    //     2. keep one pointer for iterating the array while the same elements are encountered - to avoid them in next iteration
    //     3. use another pointer for storing this current element
    
    //  */
    // public int removeDuplicates(int[] nums) {
    //     int i=0,j=0;
    //     while(i<nums.length){
    //         int current=nums[i];
    //         while(i<nums.length && current==nums[i]) i++;
    //         nums[j++]=current;
    //     }
    //     return j;
    // }

/**********************************************************************************************************/

// Time:- O(n)             Space:- O(1)

public int removeDuplicates(int[] nums) {
    int i = 0; // Pointer to track the position of unique elements

    for (int n : nums) { // Iterate through each element in the array
        if (i == 0 || n != nums[i - 1]) { // If it's the first element OR a new unique element is found
            nums[i++] = n; // Store the unique element and move the pointer forward
        }
    }
    
    return i; // Return the count of unique elements
}

/**********************************************************************************************************/
}
// @lc code=end

