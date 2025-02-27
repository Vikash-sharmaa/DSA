/*
 * @lc app=leetcode id=39 lang=java
 * 
    Given an array of distinct integers candidates and a target integer target, return a list of all 
        unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

    The same number may be chosen from candidates an unlimited number of times. Two combinations are unique 
        if the frequency of at least one of the chosen numbers is different.

    The test cases are generated such that the number of unique combinations that sum up to target is less than 150
        combinations for the given input.

 
    Example 1:

    Input: candidates = [2,3,6,7], target = 7
    Output: [[2,2,3],[7]]
    Explanation:
    2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
    7 is a candidate, and 7 = 7.
    These are the only two combinations.

 */

// @lc code=start

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class Solution {
    void combinationSum1(int[] candidates, int n, int target, ArrayList<Integer> res, HashSet<ArrayList<Integer>> ans) {

        if (target == 0) {
            ans.add(new ArrayList<>(res)); // Add a new copy of the current combination to avoid reference issues
            return;
        }

        if(n==0) return;


        // Check if the current candidate can be included in the combination
        if (candidates[n - 1] <= target) {
            // Include the current candidate
            res.add(candidates[n - 1]);
            // Recursively call with the same candidate to allow repetitions
            combinationSum1(candidates, n, target - candidates[n - 1], res, ans);
            // Backtrack by removing the last element to explore other combinations
            res.remove(res.size() - 1);
        }

        // Exclude the current candidate and move to the next
        combinationSum1(candidates, n - 1, target, res, ans);
    }

    void combinationSum2(int[] candidates,int target,int idx,List<Integer> currentCombination,List<List<Integer>> res){
        if(idx==candidates.length){
            if(target==0){
                res.add(new ArrayList<>(currentCombination));
            }
            return;
        }

        for(int i=idx;i<candidates.length;i++){
            if(candidates[i]<=target){
                currentCombination.add(candidates[i]);
                combinationSum2(candidates, target-candidates[i], idx, currentCombination, res);
            }
        }
    } 

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // Use a HashSet to store unique combinations
        HashSet<ArrayList<Integer>> ans = new HashSet<>();
        // Temporary list to store the current combination
        ArrayList<Integer> res = new ArrayList<>();
        // Start the recursive process with the entire array
        combinationSum1(candidates, candidates.length, target, res, ans);
        // Convert the HashSet to a List before returning
        return new ArrayList<>(ans);
    }
}

// @lc code=end

