/* https://leetcode.com/problems/search-in-rotated-sorted-array-ii/

 * @lc app=leetcode id=81 lang=java
 *
 * [81] Search in Rotated Sorted Array II
 */

// @lc code=start
class Solution {
    // worst case - a lot of duplicates - o(n)
    // [3,1,2,3,3,3,3] => we cant say which side is sorted 

    public boolean search(int[] nums, int target) {
        int start = 0, end = nums.length - 1; // Initialize search range
    
        while (start <= end) {
            int mid = start + (end - start) / 2; // Safe mid calculation
    
            // Found target directly at mid
            if (nums[mid] == target) return true;
    
            // Case when there are duplicates, and we cannot determine the sorted half
            else if (nums[start] == nums[mid] && nums[mid] == nums[end]) {
                // Reduce the search space by ignoring duplicates
                start++;
                end--;
            }
            
            // Left half is sorted
            else if (nums[start] <= nums[mid]) {
                // Check if target lies in the left sorted half
                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid - 1; // Search in left half
                } else {
                    start = mid + 1; // Search in right half
                }
            }
            
            // Right half is sorted
            else if (nums[mid] <= nums[end]) {
                // Check if target lies in the right sorted half
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid + 1; // Search in right half
                } else {
                    end = mid - 1; // Search in left half
                }
            }
        }
    
        // Target not found
        return false;
    }    
}
// @lc code=end

