/*
 * @lc app=leetcode id=47 lang=java
 *
    Given a collection of numbers, nums, that might contain duplicates, return all possible 
        unique permutations in any order.

        Example 1:

        Input: nums = [1,1,2]
        Output: [[1,1,2],[1,2,1],[2,1,1]]
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {

    void swap(int[] nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

    void permute(int[] nums, int idx, List<List<Integer>> res) {
        // Base case: If the current index reaches the end of the array, we have a valid permutation
        if (idx == nums.length) {
            List<Integer> currentPermu = new ArrayList<>();
            // Convert the array into a list and store it in the result set
            for (int n : nums) currentPermu.add(n);
            res.add(currentPermu); // Add to the set to handle duplicate permutations
            return;
        }

        // Iterate over the array to swap elements and generate permutations
        HashMap<Integer,Integer> used=new HashMap<>();
        for (int i = idx; i < nums.length; i++) {
            if(!used.containsKey(nums[i])){
                used.put(nums[i], 1);
                swap(nums, i, idx); // Swap the current element with the index position
                permute(nums, idx + 1, res); // Recur for the next index
                swap(nums, i, idx); // Backtrack: Undo the swap to restore the original array
            }
        }
    }


    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();  // using set is not a optimal solution
        permute(nums, 0, res);
        return new ArrayList<>(res);
    }
}
// @lc code=end

