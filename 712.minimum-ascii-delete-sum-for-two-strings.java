/*
 * @lc app=leetcode id=712 lang=java
 *
 * [712] Minimum ASCII Delete Sum for Two Strings
 */

// @lc code=start
class Solution {
    int[][] getLCS(String s1,String s2,int len1,int len2){
        int [][] dp=new int[len1+1][len2+1];
        for(int i=0;i<=len1;i++){
            for(int j=0;j<=len2;j++){
                if(i==0 || j==0) dp[i][j]=0;
            }
        }
        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    dp[i][j]=1+dp[i-1][j-1];
                }else{
                    dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp;
    }
    public int minimumDeleteSum(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
    
        // Calculate the total ASCII values of both strings
        int s1Ascii = 0;
        int s2Ascii = 0;
        for (int i = 0; i < len1; i++) s1Ascii += s1.charAt(i);
        for (int i = 0; i < len2; i++) s2Ascii += s2.charAt(i);
    
        // Generate the LCS DP table
        int[][] dp = getLCS(s1, s2, len1, len2);
    
        // Calculate the ASCII sum of the LCS
        int i = len1, j = len2, lcsAscii = 0;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                // If characters match, add their ASCII value
                lcsAscii += s1.charAt(i - 1);
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                // Move towards the direction of the greater LCS value
                i--;
            } else {
                j--;
            }
        }
    
        // The result is the sum of ASCII values of characters not in the LCS
        return (s1Ascii - lcsAscii) + (s2Ascii - lcsAscii);
    }
}
// @lc code=end

