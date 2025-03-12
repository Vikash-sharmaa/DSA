/*  https://leetcode.com/problems/maximum-count-of-positive-integer-and-negative-integer/?envType=daily-question&envId=2025-03-12

 * @lc app=leetcode id=2529 lang=java
 *
 * [2529] Maximum Count of Positive Integer and Negative Integer
 */

// @lc code=start
class Solution {
    public int maximumCountNaive(int[] nums) {
        int neg=0,pos=0;
        for(int n:nums){
            if(n<0) neg++;
            else if(n>0) pos++;
        }
        return Math.max(neg,pos);
    }


    public int upper_bound(int[] nums) {
        int start = 0, end = nums.length - 1;
        int ub = nums.length; // Default to nums.length if no positive number is found

        while (start <= end) {
            int mid = start + (end - start) / 2;
            
            if (nums[mid] > 0) {  
                // We found a positive number, update upper bound
                ub = mid;
                end = mid - 1; // Look for an earlier positive number
            } else {
                start = mid + 1; // Search in the right half
            }
        }
        return ub;
    }

    public int lower_bound(int[] nums) {
        int start = 0, end = nums.length - 1;
        int lb = nums.length; // Default to nums.length if no zero is found

        while (start <= end) {
            int mid = start + (end - start) / 2;
            
            if (nums[mid] < 0) {
                // Negative number, move right
                start = mid + 1;
            } else {  
                // Zero or positive, update lower bound
                lb = mid;
                end = mid - 1;
            }
        }
        return lb;
    }

    public int maximumCount(int[] nums) {
        int neg = lower_bound(nums); // Count of negative numbers
        int pos = nums.length - upper_bound(nums); // Count of positive numbers
        return Math.max(neg, pos);
    }
}
// @lc code=end

