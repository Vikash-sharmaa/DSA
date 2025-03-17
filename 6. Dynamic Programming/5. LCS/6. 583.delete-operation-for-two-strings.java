/*  https://leetcode.com/problems/delete-operation-for-two-strings/description/

 * @lc app=leetcode id=583 lang=java
 *
 * Deletions Needed:
        To make the two words the same, you need to delete all characters that are not part of the LCS from both words. This is calculated as:
        Deletions in word1: len1 - lcs
        Deletions in word2: len2 - lcs
        Total deletions: (len1 - lcs) + (len2 - lcs) = (len1 + len2) - 2 * lcs.
 *
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
                if(s1.charAt(i-1)==s2.charAt(j-1)) {
                    dp[i][j]=1+dp[i-1][j-1];
                }else{
                    dp[i][j]= Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[len1][len2];
    }


    public int minDistance(String word1, String word2) {
        // Get the lengths of both words
        int len1 = word1.length();
        int len2 = word2.length();
    
        // Calculate the length of the Longest Common Subsequence (LCS) between word1 and word2
        int lcs = getLCS(word1, word2, len1, len2);
    
        // The minimum number of deletions required to make the two words the same
        // is the total length of both words minus twice the LCS length
        return (len1 + len2) - 2 * lcs;
    }
    
}
// @lc code=end

