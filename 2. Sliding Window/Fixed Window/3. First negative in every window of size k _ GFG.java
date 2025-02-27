import java.util.*;

/**
    Given an array arr[]  and a positive integer k, find the first negative integer for each and every window(contiguous subarray) of size k.

    Note: If a window does not contain a negative integer, then return 0 for that window.

    Examples:

        Input: arr[] = [-8, 2, 3, -6, 10] , k = 2
        Output: [-8, 0, -6, -6]
            Explanation:
            Window {-8, 2}: First negative integer is -8.
            Window {2, 3}: No negative integers, output is 0.
            Window {3, -6}: First negative integer is -6.
            Window {-6, 10}: First negative integer is -6.
 */

class Solution {
   
/********************************************************************************************************************************/

    // Time:- O(n*k)        ||      Space:- O(1)

    static List<Integer> FirstNegativeInteger1(int nums[], int k) {
        List<Integer> res = new ArrayList<>(); // List to store the first negative integers
        int n = nums.length; // Get the length of the array
    
        // Iterate over all possible subarrays of size 'k'
        for (int i = 0; i < n - k + 1; i++) {
            int currentNegative = 0; // Stores the first negative number in the window, default 0 if none
    
            // Iterate over the current subarray of size 'k'
            for (int j = i; j < i + k; j++) {
                if (nums[j] < 0) { // If a negative number is found
                    currentNegative = nums[j]; // Store the first negative number
                    break; // Stop checking further as we only need the first negative
                }
            }
    
            res.add(currentNegative); // Add the first negative number (or 0 if none) to the result list
        }
        
        return res; // Return the list of first negative numbers for each window
    }

    
/********************************************************************************************************************************/

    // Time:- O(n)        ||      Space:- O(k)

    static List<Integer> FirstNegativeInteger2(int nums[], int k) {
        List<Integer> res = new ArrayList<>(); // List to store the first negative integers
        Deque<Integer> queue = new ArrayDeque<>(); // Queue to track negative numbers in the current window
    
        int n = nums.length; // Get the length of the array
        int start = 0, end = 0; // Two pointers for the sliding window
    
        while (end < n) {
            // If the current element is negative, add it to the queue
            if (nums[end] < 0) queue.offerLast(nums[end]);
    
            // When the window size reaches 'k'
            if (end - start + 1 == k) {
                // Check if there is a negative number in the queue
                if (queue.isEmpty()) {
                    res.add(0); // If no negative number is found, add 0
                } else {
                    int front = queue.peekFirst(); // Get the first negative number in the window
                    res.add(front); // Add it to the result list
    
                    // If the outgoing element (nums[start]) is the same as queue's front, remove it
                    if (front == nums[start]) queue.pollFirst();
                }
                
                // Slide the window forward
                start++;
            }
    
            end++; // Expand the window by moving 'end' pointer
        }
    
        return res; // Return the list of first negative numbers for each window
    }

}
