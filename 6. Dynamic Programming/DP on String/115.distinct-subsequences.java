/* https://leetcode.com/problems/distinct-subsequences/

 * @lc app=leetcode id=115 lang=java
 *
 * [115] Distinct Subsequences
 */

// @lc code=start
class Solution {

    int numDistinct(String s, String t, int sLen, int tLen) {
        // Base Case 1: If `tLen` becomes 0, we've found a valid subsequence
        if (tLen == 0) return 1;  
    
        // Base Case 2: If `sLen` becomes 0 but `tLen` is still non-zero, it's not possible
        if (sLen == 0) return 0;  
    
        // Check if the current characters in `s` and `t` match
        if (s.charAt(sLen - 1) == t.charAt(tLen - 1)) {
            // Case 1: Pick this character (match it with `t` and move both pointers)
            int pick = numDistinct(s, t, sLen - 1, tLen - 1);
    
            // Case 2: Don't pick this character from `s`, move `s` pointer only
            int dontPick = numDistinct(s, t, sLen - 1, tLen);
    
            // Total ways = picking the character + not picking it
            return pick + dontPick;
        } else {
            // If characters don't match, we can only move `s` pointer
            return numDistinct(s, t, sLen - 1, tLen);
        }
    }
    

    int numDistinctMemo(String s, String t, int sLen, int tLen,int[][] dp) {
        // Base Cases
        if (tLen == 0) return 1;  // If t is empty, one valid subsequence (empty string)
        if (sLen == 0) return 0;  // If s is empty but t is not, no valid subsequence

        if(dp[sLen][tLen]!=-1) return dp[sLen][tLen];
        if (s.charAt(sLen - 1) == t.charAt(tLen - 1)) {
            int pick = numDistinctMemo(s, t, sLen - 1, tLen - 1,dp);
            int dontPick = numDistinctMemo(s, t, sLen - 1, tLen,dp);

            return dp[sLen][tLen] = pick + dontPick;
        } else {
            return dp[sLen][tLen] = numDistinctMemo(s, t, sLen - 1, tLen,dp);
        }
    }

    int numDistinctTabulation(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        
        // Create a DP table where dp[i][j] represents the number of distinct subsequences
        // of the first `i` characters of `s` that match the first `j` characters of `t`.
        int[][] dp = new int[sLen + 1][tLen + 1];
    
        // Base Case 1: If `t` is an empty string, there is always **one** valid subsequence (empty subsequence)
        for (int i = 0; i <= sLen; i++) {
            dp[i][0] = 1; 
        }
    
        // Base Case 2: If `s` is empty but `t` is **not** empty, we can't form any subsequence → 0 ways
        for (int j = 1; j <= tLen; j++) {
            dp[0][j] = 0;
        }
    
        // Fill the DP table bottom-up
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= tLen; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    // Case 1: Pick this character (match it and move both pointers)
                    // Case 2: Don't pick this character from `s` (move `s` pointer only)
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    // If characters don't match, we can only move `s` pointer
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
    
        // The result is stored in dp[sLen][tLen], representing the number of ways
        // to form `t` as a subsequence of `s`
        return dp[sLen][tLen];
    }
    

    public int numDistinct(String s, String t) {
        //return numDistinct(s,t,s.length(),t.length());
        int sLen=s.length();
        int tLen=t.length();
        int[][] dp=new int[sLen+1][tLen+1];
        for(int i=0;i<=sLen;i++){
            for(int j=0;j<=tLen;j++){
                dp[i][j]=-1;
            }
        }

        //return numDistinctMemo(s, t, sLen, tLen,dp);

        return numDistinctTabulation(s,t);
    }
}
// @lc code=end

