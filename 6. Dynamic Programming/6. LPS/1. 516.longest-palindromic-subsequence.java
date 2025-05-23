/*  https://leetcode.com/problems/longest-palindromic-subsequence/description/


 * @lc app=leetcode id=516 lang=java
 *
 * The LCS between a string and its reversed version gives the length of the longest palindromic subsequence
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

    public int longestPalindromeSubseq(String s) {
        // Get the length of the input string
        int n = s.length();
    
        // Reverse the input string to find the longest common subsequence (LCS)
        String sReversed = new StringBuilder(s).reverse().toString();
    
        // Calculate the LCS of the input string and its reversed version
        // The LCS between a string and its reversed version gives the length of the longest palindromic subsequence
        int lcs = getLCS(s, sReversed, n, n);
    
        // Return the length of the longest palindromic subsequence
        return lcs;
    }    
}
// @lc code=end

