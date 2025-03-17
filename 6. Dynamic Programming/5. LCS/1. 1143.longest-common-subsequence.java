/*  https://leetcode.com/problems/longest-common-subsequence/description/

 * @lc app=leetcode id=1143 lang=java
 *
 * [1143] Longest Common Subsequence
 * 
 * 
 *   The state is represented by the 2D array dp[i][j], where:
        i represents the current length of the substring of text1 being considered (i.e., text1[0...i-1]).
        j represents the current length of the substring of text2 being considered (i.e., text2[0...j-1]).
        dp[i][j] stores the length of the LCS of text1[0...i-1] and text2[0...j-1].
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    int longestCommonSubsequenceRecursion(String text1, String text2, int len1, int len2) {
        // Base case: If either string is empty (length is 0), there is no common subsequence
        if (len1 == 0 || len2 == 0) return 0;
    
        // Check if the current characters from both strings are the same
        if (text1.charAt(len1 - 1) == text2.charAt(len2 - 1)) {
            // If they match, include this character in the LCS
            // Move to the previous characters in both strings
            return 1 + longestCommonSubsequenceRecursion(text1, text2, len1 - 1, len2 - 1);
        } else {
            // If the current characters do not match:
            // Option 1: Exclude the last character of `text1` and compute LCS
            int shiftText1 = longestCommonSubsequenceRecursion(text1, text2, len1 - 1, len2);
    
            // Option 2: Exclude the last character of `text2` and compute LCS
            int shiftText2 = longestCommonSubsequenceRecursion(text1, text2, len1, len2 - 1);
    
            // Take the maximum result from the two options
            return Math.max(shiftText1, shiftText2);
        }
    }
    


    int longestCommonSubsequenceMemo(String text1,String text2, int len1,int len2,int[][] dp){
        if(len1==0 || len2==0) return 0;

        if(dp[len1][len2]!=-1) return dp[len1][len2];

        if(text1.charAt(len1-1)==text2.charAt(len2-1)){
            return dp[len1][len2] = 1+longestCommonSubsequenceMemo(text1, text2, len1-1, len2-1, dp);
        }else{
            int shiftText1 = longestCommonSubsequenceMemo(text1, text2, len1-1, len2, dp);
            int shiftText2 = longestCommonSubsequenceMemo(text1, text2, len1, len2-1, dp);
            return dp[len1][len2] = Math.max(shiftText1, shiftText2);
        }
    }



    int longestCommonSubsequenceTabulation(String text1, String text2, int len1, int len2) {
        // Create a 2D DP array to store LCS values for substrings
        int[][] dp = new int[len1 + 1][len2 + 1];
    
        // Initialize the base cases
        for (int i = 0; i <= len1; i++) { // Iterate over all rows
            for (int j = 0; j <= len2; j++) { // Iterate over all columns
                // If either string is empty, LCS is 0
                if (i == 0 || j == 0) dp[i][j] = 0;
            }
        }
    
        // Fill the DP table using the iterative approach
        for (int i = 1; i <= len1; i++) { // Iterate over each character of text1
            for (int j = 1; j <= len2; j++) { // Iterate over each character of text2
                // If characters match, add 1 to the LCS of the previous substrings
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1]; // Include this matching character in LCS
                } else {
                    // If characters do not match, take the maximum LCS by:
                    // Excluding the current character of text1 or text2
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
    
        // The final LCS value for the full strings is stored in dp[len1][len2]
        return dp[len1][len2];
    }

    

    public int longestCommonSubsequence(String text1, String text2) {
        int len1=text1.length(),len2=text2.length();
        int[][] dp=new int[len1+1][len2+1];
        for(int[] row:dp) Arrays.fill(row, -1);
        //return longestCommonSubsequenceRecursion(text1, text2, len1, len2);
        //return longestCommonSubsequenceMemo(text1, text2, len1, len2, dp);
        return longestCommonSubsequenceTabulation(text1, text2, len1, len2);
    }
}
// @lc code=end

