/*
 * @lc app=leetcode id=392 lang=java
 *
 * [392] Is Subsequence
 */

// @lc code=start
class Solution {

    /*******************************************************************************************/

    int getLCS(String s1,String s2,int len1,int len2){
        int[][] dp=new int[len1+1][len2+1];
        for(int i=0;i<=len1;i++){
            for(int j=0;j<=len2;j++){
                if(i==0 || j==0) dp[i][j]=0;
            }
        }

        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)) dp[i][j]=1+dp[i-1][j-1];
                else dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[len1][len2];
    }

    // Time:- O(n^2)  || space:- O(n^2)
    public boolean isSubsequenceDP(String s1, String s2) {
        // Get the lengths of both strings
        int len1 = s1.length();
        int len2 = s2.length();
        
        // Calculate the length of the Longest Common Subsequence (LCS) between s1 and s2
        int lcs = getLCS(s1, s2, len1, len2);
        
        // Check if the length of the LCS is equal to the length of s1
        // If true, it means all characters of s1 appear in s2 in the same order,
        // making s1 a subsequence of s2
        return lcs == s1.length();
    }
    
    /*******************************************************************************************/





    // Time:- O(n)  || Space:- O(1)
    public boolean isSubsequence(String s1, String s2) {
        // Get the lengths of both strings
        int len1 = s1.length();
        int len2 = s2.length();
    
        // Initialize pointers for both strings and a counter for the subsequence length
        int i = len1 - 1; // Pointer for the last character of s1
        int j = len2 - 1; // Pointer for the last character of s2
        int subsequenceLength = 0; // Tracks the length of the matching subsequence
    
        // Traverse both strings from the end to the beginning
        while (i >= 0 && j >= 0) {
            // If characters match, increment the subsequence length and move both pointers
            if (s1.charAt(i) == s2.charAt(j)) {
                subsequenceLength++;
                i--; // Move pointer for s1
                j--; // Move pointer for s2
            } else {
                // If characters don't match, move only the pointer for s2
                j--;
            }
        }
    
        // Check if the length of the subsequence matches the length of s1
        // If true, s1 is a subsequence of s2
        return len1 == subsequenceLength;
    }
    
}
// @lc code=end

