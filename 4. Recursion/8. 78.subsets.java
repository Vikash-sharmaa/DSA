

/*
 
    Given an integer array nums of unique elements, return all possible subsets (the power set).
     The solution set must not contain duplicate subsets. Return the solution in any order.

        Example 1:

        Input: nums = [1,2,3]
        Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

 */


/*
 * @lc app=leetcode id=78 lang=java
 *
 * In addition with ip/op here , we need to use backtracking too
 * For emptying ip , we are passing idx , which will traverse the while ip array.
 * Every elemnt has two choices , inclusion or not 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
// Time:    O(2^n)      ||          Space: O(n*2^n)


    void subsets(int[] ip, int idx, List<Integer> op, List<List<Integer>> res) {
        // Base case: If we have processed all elements, add the current subset to the result list
        if (idx == ip.length) {
            res.add(new ArrayList<>(op)); // Add a copy of the current subset to avoid reference issues
            return;
        }
    
        int currentNum = ip[idx]; // Get the current element from the array
    
        // Exclude the current element (not taking it in the subset)
        subsets(ip, idx + 1, op, res);
    
        // Include the current element in the subset
        op.add(currentNum);
        subsets(ip, idx + 1, op, res);
    
        // Backtrack: Remove the last added element to restore the previous state
        op.remove(op.size() - 1);
    }
    
    
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>(); // Result list to store all subsets
        subsets(nums, 0, new ArrayList<>(), res); // Start the recursive process
        return res; // Return the final list of subsets
    }    
}
// @lc code=end

