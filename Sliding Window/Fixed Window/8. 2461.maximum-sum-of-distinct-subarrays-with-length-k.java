/*
 * @lc app=leetcode id=2461 lang=java
 *
 * [2461] Maximum Sum of Distinct Subarrays With Length K
 */

// @lc code=start

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {

/********************************************************************************************************************************/

    // Time:- O(n*k)        ||      Space:- O(k)

    public long maximumSubarraySum1(int[] nums, int k) {
        
        int n = nums.length;  // Length of the input array
        long maxSum = 0;  // Variable to store the maximum unique subarray sum
    
        // Iterate over all possible subarrays of length 'k'
        for (int i = 0; i < n - k + 1; i++) {
            Set<Integer> set = new HashSet<>();  // HashSet to check for duplicate elements in the window
            long windowSum = 0;  // Variable to store the sum of the current window
    
            // Process the subarray starting at index 'i'
            for (int j = i; j < k + i; j++) {
                windowSum += nums[j];  // Add the current element to the sum
                
                // If the element is already in the set, it's a duplicate
                if (set.contains(nums[j])) {
                    windowSum = 0;  // Reset sum to 0 since we cannot consider this subarray
                    break;  // Exit the loop early since this window is invalid
                }
    
                set.add(nums[j]);  // Add the element to the set
            }
    
            // Update the maximum sum found so far
            maxSum = Math.max(maxSum, windowSum);
        }
    
        return maxSum;  // Return the maximum sum of a valid unique subarray of size 'k'
    }

/********************************************************************************************************************************/

    // Time:- O(n)        ||      Space:- O(k)


// note how map size is used to look up whether all the elements in window are unique or not.

// A Set only tracks presence but does not handle duplicate frequencies properly. Since we need to count occurrences when 
//  adding/removing elements in the sliding window, a Map (HashMap) is the best choice.
    
    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;  // Length of the input array
        long maxSum = 0;  // Variable to store the maximum sum of a valid subarray
        long windowSum = 0;  // Variable to store the current window's sum
    
        Map<Integer, Integer> map = new HashMap<>();  // HashMap to track the frequency of elements in the window
        int start = 0, end = 0;  // Two pointers to define the sliding window
    
        while (end < n) {  // Iterate through the array
            int current = nums[end];  // Current element being added to the window
            windowSum += current;  // Add it to the window sum
            map.put(current, map.getOrDefault(current, 0) + 1);  // Update frequency count
    
            // When the window size reaches 'k'
            if (end - start + 1 == k) {
                // If all elements are unique (i.e., no duplicates), update maxSum
                if (map.size() == k) {
                    maxSum = Math.max(maxSum, windowSum);
                }
    
                // Prepare to slide the window forward
                int toBeRemoved = nums[start];  // Element that will be removed from the window
                windowSum -= toBeRemoved;  // Subtract it from the sum
                map.put(toBeRemoved, map.get(toBeRemoved) - 1);  // Reduce its frequency
    
                // If the removed element's count reaches 0, remove it from the map
                if (map.get(toBeRemoved) == 0) map.remove(toBeRemoved);
    
                start++;  // Move the window forward
            }
            end++;  // Expand the window
        }
        
        return maxSum;  // Return the maximum valid subarray sum
    }
    
}
// @lc code=end

