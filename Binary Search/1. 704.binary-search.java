/*
 * @lc app=leetcode id=704 lang=java
 *
 * [704] Binary Search
 */

// @lc code=start
class Solution {

/************************************************************************************************/

    // Time:- O(log(n))      ||      Space:- O(log(n))

    public int searchRecursive(int[] nums, int start, int end, int target) {
        // Base case: If start index exceeds end, target is not found
        if (start > end) return -1;
    
        // Find the middle index to divide the array into two halves
        int mid = start + (end - start) / 2;
    
        // If the middle element is the target, return its index
        if (nums[mid] == target) return mid;
    
        // If target is greater than middle element, search in the right half
        else if (nums[mid] < target) return searchRecursive(nums, mid + 1, end, target);
    
        // Otherwise, search in the left half
        else return searchRecursive(nums, start, mid - 1, target);
    }
   
    
/************************************************************************************************/

    // Time:- O(log(n))      ||      Space:- O(1)

    public int searchIterative(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
    
        // Loop until the search space is exhausted
        while (start <= end) {
            // Find the middle index to divide the array into two halves
            int mid = start + (end - start) / 2;
    
            // If the middle element is the target, return its index
            if (nums[mid] == target) return mid;
    
            // If target is greater than middle element, search in the right half
            else if (nums[mid] < target) start = mid + 1;
    
            // Otherwise, search in the left half
            else end = mid - 1;
        }
    
        // If target is not found, return -1
        return -1;
    }
    

/************************************************************************************************/


    public int search(int[] nums,int target){
        return searchRecursive(nums, 0, nums.length-1, target);
    }
}
// @lc code=end

