/*  https://leetcode.com/problems/longest-consecutive-sequence/description/

 * @lc app=leetcode id=128 lang=java
 *
 * [128] Longest Consecutive Sequence
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
// Time: O(nlogn)       Space: O(n)
    public int longestConsecutive_1(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0; // If the array is empty, return 0 since no sequence exists.
        
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num); // Add all elements to a HashSet to remove duplicates.
    
        List<Integer> lis = new ArrayList<>(set); // Convert the unique elements to a list.
    
        Collections.sort(lis); // Sort the list to ensure numbers are in ascending order.
    
        int count = 1, maxLen = 1; // Initialize count for current sequence and max sequence length.
        
        for (int i = 0; i < lis.size() - 1; i++) {
            if (lis.get(i) + 1 == lis.get(i + 1)) { // Check if the next number is consecutive.
                count++; // Increase count for consecutive sequence.
                maxLen = Math.max(maxLen, count); // Update max sequence length if needed.
            } else {
                count = 1; // Reset count if the sequence is broken.
            }
        }
        return maxLen; // Return the length of the longest consecutive sequence.
                /*
                    int maxLen = 1, count = 1;
                    for (int i = 0; i < lis.size() - 1; i++) {
                        if (lis.get(i) + 1 == lis.get(i + 1)) {
                            count++; // Increase the count if consecutive
                        } else {
                            maxLen = Math.max(maxLen, count); // Update max length
                            count = 1; // Reset count for new sequence
                        }
                    }
                    return Math.max(maxLen, count);
                */ 
    }
    
    public int longestConsecutive(int[] nums) {
        return longestConsecutive_1(nums);
    }
}
// @lc code=end

