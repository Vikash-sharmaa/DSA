/*  Printing Shortest Common Supersequence
 *
 * @lc app=leetcode id=1092 lang=java
 *
 * 
 * Traverse the LCS DP table backward. If the characters at the current indices match, include that character in the result. 
    Otherwise, compare the LCS values in the DP table and include the character from the string corresponding to the larger value.
 *
 * 
 */

// @lc code=start
class Solution {

    void lcs(String s1,String s2,int len1,int len2,int[][] dp){
        for(int i=0;i<=len1;i++){
            for(int j=0;j<=len2;j++){
                if(i==0 || j==0) dp[i][j]=0;
            }
        }

        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    dp[i][j]=1+dp[i-1][j-1];
                }
                else{
                    dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
                } 
            }
        }
    }


    public String shortestCommonSupersequence(String s1, String s2) {
        // Get the lengths of both strings
        int len1 = s1.length();
        int len2 = s2.length();

        // Initialize a DP table to store the length of the longest common subsequence (LCS)
        int[][] dp = new int[len1 + 1][len2 + 1];

        // Fill the DP table using the LCS logic
        lcs(s1, s2, len1, len2, dp);

        // StringBuilder to store the result (shortest common supersequence)
        StringBuilder res = new StringBuilder();

        // Start from the bottom-right corner of the DP table
        int i = len1, j = len2;

        // Traverse the table to reconstruct the shortest common supersequence
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                // If characters match, include it in the result
                res.append(s1.charAt(i - 1));
                i--;
                j--;
            } else {
                // If characters don't match, include the character from the string
                // that contributes to the larger LCS value in the DP table
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    res.append(s1.charAt(i - 1));
                    i--;
                } else {
                    res.append(s2.charAt(j - 1));
                    j--;
                }
            }
        }

        // If there are remaining characters in s1, add them to the result
        while (i > 0) {
            res.append(s1.charAt(i - 1));
            i--;
        }

        // If there are remaining characters in s2, add them to the result
        while (j > 0) {
            res.append(s2.charAt(j - 1));
            j--;
        }

        // Reverse the result since it was constructed backwards
        return res.reverse().toString();
    }

}
// @lc code=end

