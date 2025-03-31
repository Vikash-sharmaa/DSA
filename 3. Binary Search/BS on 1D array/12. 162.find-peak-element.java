/*
 * @lc app=leetcode id=162 lang=java
 *
 * [162] Find Peak Element
 */
// works same as find peak in mountain array
// just in case there is a valley - earlier code would fail
// so in case of a valley - move either side 
// 1 5 1 2 1 => start=1 end=3 and mid = 2

// @lc code=start
class Solution {
    public int findPeakElement(int[] nums) {
        int n = nums.length;
    
        // If array has only one element, it is the peak
        if (n == 1) return 0;
    
        // Edge case: if the peak is at index 0
        if (nums[0] > nums[1]) return 0;
    
        // Edge case: if the peak is at the last index
        if (nums[n - 1] > nums[n - 2]) return n - 1;
    
        // Apply binary search between index 1 and n - 2
        int start = 1, end = n - 2;
    
        while (start <= end) {
            // Calculate mid safely to avoid overflow
            int mid = start + (end - start) / 2;
    
            // Case 1: Check if mid is a peak (greater than both neighbors)
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) 
                return mid; // found the peak
    
            // Case 2: If mid is on an increasing slope (nums[mid] > nums[mid - 1])
            // Then the peak must be on the right side
            else if (nums[mid] > nums[mid - 1]) 
                start = mid + 1;
    
            // Case 3: If mid is on a decreasing slope (nums[mid] <= nums[mid - 1])
            // Then the peak must be on the left side
            else 
                end = mid - 1;
        }
    
        // Return -1 as a fallback, but as per problem, this will never be reached
        return -1;
    }    
}
// @lc code=end

