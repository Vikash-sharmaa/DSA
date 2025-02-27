/*
    845. Longest Mountain in Array
    852. Peak Index in a Mountain Array
    1671. Minimum Number of Removals to Make Mountain Array

    941. Valid Mountain Array (This is Easy prob) Does not require this pattern to solve. But give a look.
 */

import java.util.ArrayList;
import java.util.List;

class Solution {
    /************************************* Naive *****************************************************************************/
    /*
        Steps for Naive Solution:
            1. Generate all possible subsequences of the array.
            2. For each subsequence, check if it is bitonic.
            3. Track the length of the longest bitonic subsequence.
     */
    // Function to generate all subsequences of an array
    static void generateSubsequences(int[] nums, int index, List<Integer> current, List<List<Integer>> subsequences) {
        if (index == nums.length) {
            subsequences.add(new ArrayList<>(current));
            return;
        }

        // Include the current element in the subsequence
        current.add(nums[index]);
        generateSubsequences(nums, index + 1, current, subsequences);

        // Exclude the current element from the subsequence
        current.remove(current.size() - 1);
        generateSubsequences(nums, index + 1, current, subsequences);
    }

    // Function to check if a subsequence is bitonic
    static boolean isBitonic(List<Integer> subsequence) {
        int n = subsequence.size();
        if (n < 3) return false; // A bitonic sequence must have at least 3 elements

        int i = 0;

        // Check increasing part
        while (i < n - 1 && subsequence.get(i) < subsequence.get(i + 1)) {
            i++;
        }

        // If there is no increasing part or the whole sequence is increasing
        if (i == 0 || i == n - 1) return false;

        // Check decreasing part
        while (i < n - 1 && subsequence.get(i) > subsequence.get(i + 1)) {
            i++;
        }

        // If we reached the end, the sequence is bitonic
        return i == n - 1;
    }

    // Main function to find the longest bitonic subsequence
    public static int LongestBitonicSequenceNaive(int[] nums) {
        List<List<Integer>> subsequences = new ArrayList<>();
        generateSubsequences(nums, 0, new ArrayList<>(), subsequences);

        int maxLength = 0;

        // Check all subsequences
        for (List<Integer> subsequence : subsequences) {
            if (isBitonic(subsequence)) {
                maxLength = Math.max(maxLength, subsequence.size());
            }
        }

        return maxLength;
    }
    

/****************************************************** Using DP ************************************************************/
    
    public static int LongestBitonicSequence(int n, int[] nums) {
        // Step 1: Calculate LIS (Longest Increasing Subsequence) for each element in the array
        int[] left = calculateLIS(nums);
    
        // Step 2: Reverse the array to calculate LDS (Longest Decreasing Subsequence)
        reverse(nums);
    
        // Step 3: Calculate LIS on the reversed array, which effectively gives us LDS for the original array
        int[] right = calculateLIS(nums);
    
        // Step 4: Reverse the `right` array back to align with the original order of `nums`
        reverse(right);
    
        int result = 0;
    
        // Step 5: Combine LIS and LDS values to calculate the Longest Bitonic Subsequence
        for (int i = 0; i < n; i++) {
            // Both LIS and LDS for a valid bitonic sequence must be > 1
            if (left[i] > 1 && right[i] > 1) {
                // Calculate the bitonic sequence length for the peak at `i` and update the result
                result = Math.max(result, left[i] + right[i] - 1);
            }
        }
    
        return result; // Return the maximum bitonic sequence length
    }
    
    private static int[] calculateLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n]; // dp[i] stores the LIS ending at index `i`
    
        // Initialize LIS for all elements as 1 (each element alone is a subsequence)
        for (int i = 0; i < n; i++) dp[i] = 1;
    
        // Compute LIS for each element
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // If nums[j] is less than nums[i], it can contribute to LIS ending at `i`
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp; // Return the LIS array
    }
    
    private static void reverse(int[] arr) {
        int start = 0, end = arr.length - 1;
    
        // Swap elements from the start and end, moving towards the middle
        while (start < end) {
            int temp = arr[start];
            arr[start++] = arr[end];
            arr[end--] = temp;
        }
    }

    
    // static boolean isBitonic(int[] nums) {
    //     int n = nums.length;
    //     if (n <= 2) return false; // A bitonic sequence requires at least 3 elements
    
    //     // Check for strictly increasing or decreasing sequence
    //     boolean increasing = false, decreasing = false;
    //     for (int i = 1; i < n; i++) {
    //         if (nums[i] > nums[i - 1]) increasing = true;
    //         if (nums[i] < nums[i - 1]) decreasing = true;
    //         if (increasing && decreasing) return true; // Sequence is bitonic
    //     }
    
    //     return false; // Not bitonic
    // }
    
    // static int[] frontLIS(int[] nums) {
    //     int n = nums.length;
    //     int[] dp = new int[n];
    
    //     // Calculate LIS ending at each index
    //     for (int i = 0; i < n; i++) {
    //         dp[i] = 1; // Each element alone forms an LIS of length 1
    //         for (int j = 0; j < i; j++) {
    //             if (nums[j] < nums[i]) {
    //                 dp[i] = Math.max(dp[i], dp[j] + 1);
    //             }
    //         }
    //     }
    //     return dp;
    // }
    
    // static int[] backLIS(int[] nums) {
    //     int n = nums.length;
    //     int[] dp = new int[n];
    
    //     // Calculate LIS starting at each index (reversed order)
    //     for (int i = n - 1; i >= 0; i--) {
    //         dp[i] = 1; // Each element alone forms an LIS of length 1
    //         for (int j = n - 1; j > i; j--) {
    //             if (nums[j] < nums[i]) {
    //                 dp[i] = Math.max(dp[i], dp[j] + 1);
    //             }
    //         }
    //     }
    //     return dp;
    // }
    
    // public static int LongestBitonicSequence(int n, int[] nums) {
    //     if (!isBitonic(nums)) return 0;
    
    //     int[] dp1 = frontLIS(nums); // LIS ending at each index
    //     int[] dp2 = backLIS(nums);  // LIS starting at each index
    
    //     int ans = 0;
    //     for (int i = 0; i < n; i++) {
    //         // Combine LIS from both sides, subtract 1 to avoid double-counting the peak
    //         ans = Math.max(ans, dp1[i] + dp2[i] - 1);
    //     }
    //     return ans;
    // }

}