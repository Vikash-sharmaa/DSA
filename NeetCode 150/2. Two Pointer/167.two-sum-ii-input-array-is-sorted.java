/*  https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/

 * @lc app=leetcode id=167 lang=java
 *
 * [167] Two Sum II - Input Array Is Sorted
 */

// @lc code=start
/* 
    Could use - hashmap -> Time-O(n)    Space: O(n)
    Could use - binary search -> Time-O(nlogn)  Space: O(1)
 */
class Solution {
// Time: O(n)           Space: O(1)
    public int[] twoSum(int[] numbers, int target) {
        int start=0,end=numbers.length-1;
        while(start<end){
            if(numbers[start]+numbers[end]==target) return new int[]{start+1,end+1};
            else if(numbers[start]+numbers[end] < target) start++;
            else end--;
        }
        return new int[]{0,0};
    }
}
// @lc code=end

