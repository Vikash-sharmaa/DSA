/*
 * @lc app=leetcode id=1312 lang=java
 *
 * // minimum insertion / minimum deletion === length of string - LPS
 * 
 */

// @lc code=start
class Solution {
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

    public int minInsertions(String s) {
        // Reverse the string `s` to create a new string `sReversed`.
        int n=s.length();
        String sReversed = new StringBuilder(s).reverse().toString();
        // The Longest Palindromic Subsequence (LPS) of `s` is the LCS of `s` and its reverse `sReversed`.
        int LPS = getLCS(s, sReversed,n,n); // Calculate LPS using LCS of `s` and its reversed string.

        // The minimum insertions required to make `s` a palindrome is the difference between the string's length and the LPS.
        // The LPS is the longest subsequence that already forms a palindrome, so we need to insert the rest of the characters.
        return s.length() - LPS; // Return the difference as the number of insertions needed.
    }
}
// @lc code=end

