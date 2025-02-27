/*
    Given an array of integers arr[]  and a number k. Return the maximum sum of a subarray of size k.
    Note: A subarray is a contiguous part of any given array.

    Examples:

    Input: arr[] = [100, 200, 300, 400] , k = 2
    Output: 700
    Explanation: arr3  + arr4 = 700, which is maximum.
 */


class Solution {
    
/********************************************************************************************************************************/

    // Time:- O(n*k)        ||      Space:- O(1)

    public int maximumSumSubarray1(int[] arr, int k) {
        int len = arr.length; // Get the length of the array
        int maxiSum = 0; // Variable to store the maximum sum found
    
        // Iterate through the array, considering subarrays of size 'k'
        for (int i = 0; i < len - k + 1; i++) {
            int currentSum = 0; // Stores the sum of the current subarray
    
            // Calculate the sum of the subarray starting at index 'i' and having size 'k'
            for (int j = i; j < i + k; j++) {
                currentSum += arr[j]; // Add the element to current sum
            }
    
            // Update the maximum sum if the current sum is greater
            maxiSum = Math.max(maxiSum, currentSum);
        }
        
        return maxiSum; // Return the maximum sum found
    }
    
/********************************************************************************************************************************/
    
    // Using Sliding window

    // Time:- O(n*k)        ||      Space:- O(1)
    
    public int maximumSumSubarray(int[] arr, int k) {
        int len = arr.length;  // Get the length of the array
        int start = 0, end = 0; // Two pointers to define the sliding window
        
        int maxiSum = 0, currentSum = 0; // Variables to track the maximum sum and the current sum
        
        while (end < len) { // Iterate through the array using the 'end' pointer
            currentSum += arr[end]; // Add the current element to the window sum
    
            // Check if the window size has reached 'k'
            if (end - start + 1 == k) {
                maxiSum = Math.max(maxiSum, currentSum); // Update the maximum sum
                
                currentSum -= arr[start]; // Remove the element at 'start' from the sum to slide the window
                start++; // Move the 'start' pointer ahead
            }
    
            end++; // Expand the window by moving the 'end' pointer
        }
    
        return maxiSum; // Return the maximum sum found
    }

}