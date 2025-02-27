/*
 * @lc app=leetcode id=368 lang=java
 *
 * [368] Largest Divisible Subset
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    int largestDivisibleSubsetRecursion(int[] nums, int n, int last, List<Integer> temp, List<List<Integer>> res) {
        // Base case: when we have checked all elements
        if (n == 0) {
            // If the current subset `temp` is larger than the previously stored subset in `res`
            if (res.size() == 0 || temp.size() > res.get(0).size()) {
                res.clear(); // Clear the previous result (only one subset is stored at a time)
                res.add(new ArrayList<>(temp)); // Add the current subset `temp` to `res`
            }
            return 0; // Return 0 as no further elements can be added
        }
    
        // Recursive call for the case where we do NOT include nums[n-1] in the subset
        int notTake = largestDivisibleSubsetRecursion(nums, n - 1, last, temp, res);
    
        int take = 0; // Initialize the result for including nums[n-1] as 0
    
        // Check if nums[n-1] can be included in the subset
        if (last == nums.length || nums[last] % nums[n - 1] == 0) { 
            // `last == nums.length`: No element has been taken yet, so we can always take nums[n-1]
            // `nums[last] % nums[n-1] == 0`: Divisibility condition for inclusion in the subset
    
            temp.add(nums[n - 1]); // Add nums[n-1] to the current subset `temp`
            
            // Recursive call for the case where nums[n-1] is included in the subset
            take = 1 + largestDivisibleSubsetRecursion(nums, n - 1, n - 1, temp, res);
            
            temp.remove(temp.size() - 1); // Backtrack: remove nums[n-1] from `temp` after recursion
        }
    
        // Return the maximum size of the subset between including and not including nums[n-1]
        return Math.max(take, notTake);
    }
    

    // private int largestDivisibleSubsetMemo(int[] nums, int index, int lastIndex, List<Integer> currentSubset, 
    //                                        List<Integer> result, Integer[][] dp) {
    //     // Base case: If we have processed all elements
    //     if (index < 0) {
    //         if (currentSubset.size() > result.size()) {
    //             result.clear();
    //             result.addAll(currentSubset);
    //         }
    //         return 0;
    //     }

    //     // Check memoization table
    //     if (dp[index][lastIndex] != null) {
    //         return dp[index][lastIndex];
    //     }

    //     // Option 1: Exclude the current element
    //     int notTake = largestDivisibleSubsetMemo(nums, index - 1, lastIndex, currentSubset, result, dp);

    //     // Option 2: Include the current element if it's divisible by the last included element
    //     int take = 0;
    //     if (lastIndex == nums.length || nums[lastIndex] % nums[index] == 0) {
    //         currentSubset.add(nums[index]); // Include the current element
    //         take = 1 + largestDivisibleSubsetMemo(nums, index - 1, index, currentSubset, result, dp);
    //         currentSubset.remove(currentSubset.size() - 1); // Backtrack
    //     }

    //     // Store the result in the memoization table
    //     return dp[index][lastIndex] = Math.max(take, notTake);
    // }

    private List<Integer> largestDivisibleSubsetTabulation(int[] nums) {
            int n = nums.length;
            int[] dp = new int[n]; // dp[i] stores the length of the largest divisible subset ending at index i
            int[] hash = new int[n]; // hash[i] stores the index of the previous element in the subset for backtracking
            Arrays.fill(dp, 1); // Initialize all dp values to 1, as the smallest subset contains at least one element
            for (int i = 0; i < n; i++) hash[i] = i; // Initialize hash values; each element points to itself initially
        
            int maxi = 0; // Stores the length of the largest divisible subset found so far
            int lastIndex = 0; // Stores the index of the last element in the largest subset
        
            // Iterate over all pairs to calculate the largest divisible subset
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) { // Check all elements before `i`
                    if (nums[i] % nums[j] == 0) { // Check divisibility condition
                        if (1 + dp[j] > dp[i]) { // If adding nums[j] increases the subset length ending at nums[i]
                            dp[i] = 1 + dp[j]; // Update dp[i] with the new subset length
                            hash[i] = j; // Update hash[i] to point to j for backtracking
                        }
                    }
                }
                // Update the overall maximum subset length and the lastIndex
                if (dp[i] > maxi) {
                    maxi = dp[i]; // Update maxi to the new maximum subset length
                    lastIndex = i; // Update lastIndex to the index of the last element in the subset
                }
            }
        
            // Backtrack to build the result list
            List<Integer> lis = new ArrayList<>(); // List to store the largest divisible subset
            lis.add(nums[lastIndex]); // Add the last element of the subset to the result list
        
            // Continue backtracking until we reach the starting element of the subset
            while (hash[lastIndex] != lastIndex) {
                lastIndex = hash[lastIndex]; // Move to the previous element in the subset
                lis.add(nums[lastIndex]); // Add it to the result list
            }
        
            return lis; // Return the largest divisible subset
    }
     
    

    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n=nums.length;
        // List<Integer> temp=new ArrayList<>();
        // List<Integer> res=new ArrayList<>();
        //largestDivisibleSubsetRecursion(nums,n,n,temp,res);
        // Integer[][] dp = new Integer[n+1][n+1];
        //largestDivisibleSubsetMemo(nums,n-1,n,temp,res,dp);
        //return res;

        return largestDivisibleSubsetTabulation(nums);
    }
}
// @lc code=end

