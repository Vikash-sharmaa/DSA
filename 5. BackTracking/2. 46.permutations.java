/*

   Also see Subsets in recursion
   
 * @lc app=leetcode id=46 lang=java
 *
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {

    void swap(int[] nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }


    void permutation(int[] nums, int idx, List<List<Integer>> res) {
        // Base case: If idx reaches the end of the array, add the current permutation to the result list
        if (idx == nums.length) {
            List<Integer> currentPermutation = new ArrayList<>();  
            for (int n : nums) currentPermutation.add(n);          
            res.add(currentPermutation);                           
            return;
        }

        // Iterate through the array, swapping each element to generate permutations
        for (int i = idx; i < nums.length; i++) {
            swap(nums, i, idx);          // Swap the current element with the element at 'idx'
            permutation(nums, idx + 1, res);  // Recursively generate permutations for the next index
            swap(nums, i, idx);          // Backtrack: Revert the swap to restore the original array
        }
    }



    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        permutation(nums, 0, res);
        return res;
    }
}
// @lc code=end

