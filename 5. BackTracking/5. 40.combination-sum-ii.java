/*
 * @lc app=leetcode id=40 lang=java
 *
 * [40] Combination Sum II
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

class Solution {

    void combinationSum_Take_NotTake(int[] candidates, int target, int n, List<Integer> currentCombination, Set<List<Integer>> res) {
        // Base case: If target is met, store the combination
        if (target == 0) {
            res.add(new ArrayList<>(currentCombination));
            return;
        }
    
        // Base case: If no elements left, return
        if (n == 0) return;

    
        // Choice 1: Take the current element (if it's within the target)
        if (candidates[n - 1] <= target) {
            currentCombination.add(candidates[n - 1]);
            combinationSum_Take_NotTake(candidates, target - candidates[n - 1], n - 1, currentCombination, res);
            currentCombination.remove(currentCombination.size() - 1); // Backtrack
        }
    
        // Choice 2: Not take the current element
        combinationSum_Take_NotTake(candidates, target, n - 1, currentCombination, res);
    }


    // Best
    
    void combinationSum_Take_NotTake_2(int[] candidates, int target, int n, List<Integer> currentCombination, List<List<Integer>> res) {
        // Base case: If target is met, store the combination
        if (target == 0) {
            res.add(new ArrayList<>(currentCombination)); // Store a valid combination
            return;
        }
    
        // Base case: If no elements left, return
        if (n == 0) return;
    
        // Choice 1: Take the current element (if it's within the target)
        if (candidates[n - 1] <= target) {
            currentCombination.add(candidates[n - 1]); // Include the current number
            combinationSum_Take_NotTake_2(candidates, target - candidates[n - 1], n - 1, currentCombination, res);
            currentCombination.remove(currentCombination.size() - 1); // Backtrack
        }
    
        // Choice 2: Not take the current element
        // Handling duplicates: Skip duplicate numbers to avoid duplicate subsets
        while (n - 2 >= 0 && candidates[n - 1] == candidates[n - 2]) n--; 
        
        combinationSum_Take_NotTake_2(candidates, target, n - 1, currentCombination, res);
    }
    
    
    

    // void combinationSum(int[] candidates, int target, int start, List<Integer> currentCombination, List<List<Integer>> res) {
    //     // Base case: If target becomes 0, we found a valid combination
    //     if (target == 0) {
    //         res.add(new ArrayList<>(currentCombination)); // Store a copy of the valid combination
    //         return;
    //     }
    
    //     // Base case: If we have processed all elements, return
    //     if (start == candidates.length) return;
    
    //     // Iterate over the candidates starting from the current index
    //     for (int i = start; i < candidates.length; i++) {
    //         // Skip duplicate elements to ensure unique combinations (only for sorted arrays)
    //         if (i > start && candidates[i] == candidates[i - 1]) continue;
    
    //         // Check if the current candidate can be part of the combination
    //         if (candidates[i] <= target) {
    //             currentCombination.add(candidates[i]); // Choose the current candidate
    //             // Recur with reduced target, moving to the next index to avoid duplicate usage
    //             combinationSum(candidates, target - candidates[i], i + 1, currentCombination, res);
    //             currentCombination.remove(currentCombination.size() - 1); // Backtrack to explore other combinations
    //         }
    //     }
    // }
    
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        
        Arrays.sort(candidates);
        List<List<Integer>> res=new ArrayList<>();

        //combinationSum(candidates, target,0,new ArrayList<>(),res);

        //Set<List<Integer>> set = new HashSet<>();
        combinationSum_Take_NotTake_2(candidates, target, candidates.length, new ArrayList<>(), res);
        return res;
    }
}
// @lc code=end

