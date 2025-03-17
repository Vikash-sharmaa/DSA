/*
 * @lc app=leetcode id=217 lang=java
 *
 * [217] Contains Duplicate
 */

// @lc code=start

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {

/********************************************************************************************************************/
   // Time: O(n^2)        Space: O(1)
    public boolean containsDuplicateNaive(int[] nums) {
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]==nums[j]) return true;
            }
        }
        return false;
    }

/********************************************************************************************************************/
   // Time: O(nlogn)        Space: O(1)

    public boolean containsDuplicateSorting(int[] nums) {
        Arrays.sort(nums);
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]==nums[i+1]) return true;
        }
        return false;
    }

/********************************************************************************************************************/
   // Time: O(n)        Space: O(n)
    public boolean containsDuplicateOptimized(int[] nums) {
        Set<Integer> set=new HashSet<>();
        for(int i=0;i<nums.length;i++){
            if(set.contains(nums[i])) return true;
            set.add(nums[i]);
        }
        return false;
    }

/********************************************************************************************************************/
    //Handler
    
    public boolean containsDuplicate(int[] nums) {
        //return containsDuplicateNaive(nums);
        //return containsDuplicateSorting(nums);
        return containsDuplicateOptimized(nums);
    }
}
// @lc code=end

