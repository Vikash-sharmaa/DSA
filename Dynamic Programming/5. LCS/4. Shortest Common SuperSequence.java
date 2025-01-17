/*
 * Shortest common subsequence = len1 + len2 - LCS(s1,s2)
 */

class Solution {
    // Function to compute the length of the Longest Common Subsequence (LCS) using dynamic programming
    static int LCS(String s1, String s2, int len1, int len2) {
        // Create a 2D dp array where dp[i][j] will store the length of LCS of s1[0..i-1] and s2[0..j-1]
        int[][] dp = new int[len1 + 1][len2 + 1];
        
        // Initialize the dp array with base cases where one of the strings is empty
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0; // If one string is empty, the LCS is 0
                }
            }
        }
    
        // Fill the dp array based on the comparison of characters
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // If characters match, LCS length increases by 1
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1]; // Include the matching character in LCS
                } else {
                    // Otherwise, take the maximum of excluding the current character from either string
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
    
        // Return the length of the LCS of s1[0..len1-1] and s2[0..len2-1]
        return dp[len1][len2];
    }
    
    // Function to compute the length of the Shortest Common Supersequence (SCS)
    public static int shortestCommonSupersequence(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        
        // Calculate the length of the LCS of s1 and s2
        // The length of the SCS is the sum of the lengths of both strings minus the length of their LCS
        return len1 + len2 - LCS(s1, s2, len1, len2);
    }
}