/* https://leetcode.com/problems/search-in-rotated-sorted-array/

 * @lc app=leetcode id=33 lang=java
 *
 * [33] Search in Rotated Sorted Array
 */
   

 /*
   
    *  If the left half is sorted (nums[start] <= nums[mid]), check if the target is within this range (nums[start] <= target <= nums[mid]), and adjust end if 
            it is, or adjust start if it isn't.
    *  If the right half is sorted (nums[mid] <= nums[end]), check if the target is within this range (nums[mid] <= target <= nums[end]), and adjust start if 
            it is, or adjust end if it isn't.

  */
// @lc code=start
class Solution {
    public int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1; // initialize search space
        
        while (start <= end) {
            int mid = start + (end - start) / 2; // safe mid calculation to avoid overflow
            
            // Found the target directly
            if (nums[mid] == target) return mid;
    
            // Check if the left half [start...mid] is sorted
            if (nums[start] <= nums[mid]) {
                // If target lies within the sorted left half
                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid - 1; // search in left half
                } else {
                    start = mid + 1; // search in right half
                }
            }
            // Else the right half [mid...end] is sorted
            else if (nums[mid] <= nums[end]) {
                // If target lies within the sorted right half
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid + 1; // search in right half
                } else {
                    end = mid - 1; // search in left half
                }
            }
        }
    
        // If not found, return -1
        return -1;
    }
    
}
// @lc code=end

