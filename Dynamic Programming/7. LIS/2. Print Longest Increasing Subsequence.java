import java.util.ArrayList;
import java.util.Collections;

/*
  # The solution is similar to the LIS approach because:
        Dynamic Programming Array (dp): It calculates the length of the LIS ending at each index.
        Hash Array (hash): Tracks the predecessor index to reconstruct the subsequence.
        Double Loop: Iterates over pairs of indices to update dp and hash based on conditions.
        Reconstruction: Uses the hash array to backtrack and build the LIS after determining its length.
  # See the last solution of LIC - Prob 1

 */
class Solution {

    public ArrayList<Integer> longestIncreasingSubsequence(int n, int nums[]) {
        // `dp[i]` represents the length of the longest increasing subsequence (LIS) ending at index `i`.
        int dp[] = new int[n];
        // `hash[i]` stores the index of the previous element in the LIS ending at index `i`.
        int hash[] = new int[n];

        // Initialize `dp` to 1 because each element alone is a subsequence of length 1.
        for (int i = 0; i < n; i++) dp[i] = 1;
        // Initialize `hash[i]` to point to itself, indicating no previous element yet.
        for (int i = 0; i < n; i++) hash[i] = i;
        
        // Variables to track the maximum length of LIS and the index of its last element.
        int maxi = 0, lastIndex = 0;
    
        // Build the `dp` array by iterating through each element.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // If `nums[j]` is less than `nums[i]`, a valid increasing subsequence can be formed.
                if (nums[j] < nums[i]) {
                    // Check if including `nums[j]` in the subsequence ending at `i` increases its length.
                    if (1 + dp[j] > dp[i]) {
                        // Update `dp[i]` to the new maximum length.
                        dp[i] = 1 + dp[j];
                        // Update `hash[i]` to point to `j`, indicating the previous element in the LIS.
                        hash[i] = j;
                    }
                }
            }
            // Update the overall maximum LIS length and the last index of this LIS.
            if (dp[i] > maxi) {
                maxi = dp[i];
                lastIndex = i;
            }
        }
    
        // Create a result list to store the elements of the LIS.
        ArrayList<Integer> res = new ArrayList<>();
    
        // Start from the last index of the LIS and trace back using the `hash` array.
        res.add(nums[lastIndex]);
        while (hash[lastIndex] != lastIndex) { // Continue until we reach the starting element of the LIS.
            lastIndex = hash[lastIndex]; // Move to the previous element in the LIS.
            res.add(nums[lastIndex]);   // Add the element to the result list.
        }
    
        // Reverse the result list because we traced the LIS from the last element to the first.
        Collections.reverse(res);
    
        // Return the final LIS.
        return res;
    }

}