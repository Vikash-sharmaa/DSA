/* https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

 * @lc app=leetcode id=153 lang=java
 *
 * [153] Find Minimum in Rotated Sorted Array
 */

// @lc code=start
class Solution {
    // mid bifurcates two halves
    // whichever half is sorted - take its minimum and eliminate that part

    public int findMin(int[] nums) {
        int start = 0, end = nums.length - 1;
        int res = Integer.MAX_VALUE; // initialize res with a very large number

        while (start <= end) {
            int mid = start + (end - start) / 2; // safe mid calculation
            
            // Case 1: Left half [start...mid] is sorted
            if (nums[start] <= nums[mid]) {
                res = Math.min(nums[start], res); // update result with nums[start] as it's the minimum in the left sorted part
                start = mid + 1; // discard left half
            } 
            // Case 2: Right half [mid...end] is sorted
            else {
                res = Math.min(nums[mid], res); // update result with nums[mid] as it's the minimum in the right sorted part
                end = mid - 1; // discard right half
            }
        }
        return res; // return the minimum found
    }

}
// @lc code=end

