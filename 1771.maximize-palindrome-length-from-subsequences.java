/*
 * @lc app=leetcode id=1771 lang=java
 *
 * [1771] Maximize Palindrome Length From Subsequences
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

    public int longestPalindrome(String word1, String word2) {
        String word=word1+word2;
        String wordReversed = new StringBuilder(word).reverse().toString();
        int n=word.length();
        int lcs = getLCS(word,wordReversed,n,n);
        
        return lcs;
    }
}
// @lc code=end

