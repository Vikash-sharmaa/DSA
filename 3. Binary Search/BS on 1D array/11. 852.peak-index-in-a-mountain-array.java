/*
 * @lc app=leetcode id=852 lang=java
 *
 * [852] Peak Index in a Mountain Array
 */

// @lc code=start
class Solution {
    public int peakIndexInMountainArray(int[] nums) {
        int n = nums.length;

        if(n==1) return 0;
    
        // Edge case: if the peak is at the very beginning
        if (nums[0] > nums[1]) return 0;
    
        // Edge case: if the peak is at the very end
        if (nums[n - 1] > nums[n - 2]) return n - 1;
    
        // Binary Search between index 1 and n-2
        int start = 1, end = n - 2;
    
        while (start <= end) {
            int mid = start + (end - start) / 2; // safe mid calculation to avoid overflow
    
            // Case 1: nums[mid] is greater than both neighbors -> peak found
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) 
                return mid;
    
            // Case 2: mid is part of an increasing slope, so the peak lies to the right
            else if (nums[mid] > nums[mid - 1]) 
                start = mid + 1;
    
            // Case 3: mid is part of a decreasing slope, so the peak lies to the left
            else if (nums[mid] > nums[mid + 1]) 
                end = mid - 1;
        }
    
        return -1; // fallback, though question guarantees there is always a peak
    }
    
}
// @lc code=end

