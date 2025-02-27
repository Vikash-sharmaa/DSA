/*
 * @lc app=leetcode id=90 lang=java
 *
 * [90] Subsets II
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {

/************************************************************************************************/
// Use this - best 
// Using set to handle duplicacy
// Time Complexity: O(2^n * n)
// Space Complexity: O(2^n * n)

    void subsets1(int[] ip,int idx,List<Integer> op,Set<List<Integer>> res){
        if(idx==ip.length){
            res.add(new ArrayList<>(op));
            return;
        }

        int currentNum = ip[idx];

        subsets1(ip, idx+1, op, res);

        op.add(currentNum);
        subsets1(ip, idx+1, op, res);
        op.remove(op.size()-1);
    }

/************************************************************************************************/
// Use this - best 
// Using set to handle duplicacy
// Time Complexity: O(2^n * n)
// Space Complexity: O(2^n * n)

void subsets2(int[] ip,int idx,List<Integer> op,Set<List<Integer>> res){
    if(idx==ip.length){
        res.add(new ArrayList<>(op));
        return;
    }

    int currentNum = ip[idx];

    op.add(currentNum);
    subsets2(ip, idx+1, op, res);
    op.remove(op.size()-1);

    while(idx+1<ip.length  && ip[idx]==ip[idx+1]) idx++; // when we are skipping an element , we should skip all its occurences
    subsets2(ip, idx+1, op, res);
}

/************************************************************************************************/

// Time Complexity: O(2^n * n)
// Space Complexity: O(2^n * n)

    void subsets3(int[] ip, int idx, List<Integer> op, List<List<Integer>> res) {
        // Add the current subset to the result list
        res.add(new ArrayList<>(op));
    
        // Iterate through the remaining elements
        for (int i = idx; i < ip.length; i++) {
            // Skip duplicates: Only process the first occurrence in a recursive call
            if (i > idx && ip[i] == ip[i - 1]) continue;
    
            int currentNum = ip[i];
            // Include the current element in the subset
            op.add(currentNum);
    
            // Recur for the next elements
            subsets3(ip, i + 1, op, res);
    
            // Backtrack: Remove the last added element
            op.remove(op.size() - 1);
        }
    }


    /************************************************************************************************/

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Set<List<Integer>> set=new HashSet<>();
        Arrays.sort(nums);
        subsets2(nums, 0, new ArrayList<>(), set);
        return new ArrayList<>(set);
    }
}
// @lc code=end

