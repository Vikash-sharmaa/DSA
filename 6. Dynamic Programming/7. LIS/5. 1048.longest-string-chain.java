/*
 * @lc app=leetcode id=1048 lang=java
 *
 * [1048] Longest String Chain
 */

// @lc code=start

import java.util.Arrays;

class Solution {

        // Function to check if s1 is a valid predecessor of s2
        boolean isPredecessor(String s1, String s2) {
            if (s2.length() != s1.length() + 1) return false;
    
            int i = 0, j = 0;
            while (i < s1.length() && j < s2.length()) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    i++;
                }
                j++;
            }
            return i == s1.length();
        }
    
        // Recursive function to find the longest chain starting from index
        int findLongestChain(String[] words, int index, String prevWord) {
            // Base case: If we've processed all words
            if (index == words.length) return 0;
    
            // Skip the current word
            int skip = findLongestChain(words, index + 1, prevWord);
    
            // Take the current word if it can follow the previous word in the chain
            int take = 0;
            if (prevWord.isEmpty() || isPredecessor(prevWord, words[index])) {
                take = 1 + findLongestChain(words, index + 1, words[index]);
            }
    
            // Return the maximum of the two choices
            return Math.max(skip, take);
        }
    
        public int longestStrChainNaive(String[] words) {
            // Sort words by length (necessary for the chain order)
            Arrays.sort(words, (a, b) -> a.length() - b.length());
            // Start the recursion with no previous word
            return findLongestChain(words, 0, "");
        }
    
    
    /***********************************************************************************************************/

    boolean chainPossible(String s1, String s2) {
        // s2 must be exactly one character longer than s1 to form a valid chain
        // If not, it's impossible for s1 to be a valid predecessor of s2
        if (s2.length() != s1.length() + 1) return false;
    
        int left = 0, right = 0;
    
        // Traverse both strings to check if s1 is a subsequence of s2
        while (left < s1.length() && right < s2.length()) {
            if (s1.charAt(left) == s2.charAt(right)) {
                left++; // If characters match, move both pointers
            }
            right++; // Always move the pointer for s2 to check all its characters
        }
    
        // If we've traversed all of s1, it's a subsequence of s2
        return left == s1.length(); // abc ===> adef
    }
    
    int longestStrChainmodifiedLIS(String[] words) {
        int n = words.length;
        int[] dp = new int[n]; // dp[i] stores the length of the longest chain ending at words[i]
        Arrays.fill(dp, 1); // Initialize all dp values to 1 (each word can be a chain of length 1)
    
        int ans = 0; // Variable to store the maximum chain length
    
        // Process each word
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // Check if words[j] is a valid predecessor of words[i]
                if (chainPossible(words[j], words[i])) {
                    // If it is, update dp[i] to reflect the longest chain ending at words[i]
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            // Update the overall maximum chain length
            ans = Math.max(ans, dp[i]);
        }
        return ans; // Return the maximum chain length
    }
    
    public int longestStrChain(String[] words) {
        // Sort words by their lengths in ascending order
        // Sorting ensures that we only process potential predecessors before their successors
        Arrays.sort(words, (a, b) -> a.length() - b.length());
    
        // Compute the longest chain using the modified LIS approach
        return longestStrChainmodifiedLIS(words);
    }
}
// @lc code=end

