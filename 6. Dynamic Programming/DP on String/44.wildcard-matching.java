/*
 * @lc app=leetcode id=44 lang=java
 *
 * [44] Wildcard Matching
 */

// @lc code=start
class Solution {

    boolean isMatchRec(String s, String p, int sLen, int pLen) {

        // Base Case 1: If both the string `s` and pattern `p` are exhausted, it's a match
        if (pLen == 0 && sLen == 0) return true;
    
        // Base Case 2: If the pattern is exhausted but `s` is not, it's not a match
        if (pLen == 0) return false;
    
        // Base Case 3: If `s` is exhausted but `p` still has characters
        if (sLen == 0) {
            // The remaining pattern must consist only of '*' for a match
            for (int i = 0; i < pLen; i++) {
                if (p.charAt(i) != '*') return false; // If any character isn't '*', no match
            }
            return true; // If only '*' remains, it's a match
        }
    
        // Case 1: If the current characters match OR the pattern has '?', move both pointers
        if (p.charAt(pLen - 1) == '?' || p.charAt(pLen - 1) == s.charAt(sLen - 1)) {
            return isMatchRec(s, p, sLen - 1, pLen - 1);
        }
    
        // Case 2: If the pattern has '*', it can either:
        // - Match zero characters (move pattern pointer left)
        // - Match one or more characters (move string pointer left)
        if (p.charAt(pLen - 1) == '*') {
            return isMatchRec(s, p, sLen - 1, pLen) || isMatchRec(s, p, sLen, pLen - 1);
        }
    
        // Case 3: Characters don't match, and there's no wildcard → No match
        return false;
    }

    boolean isMatchMemo(String s,String p,int sLen,int pLen,Boolean[][] dp){
        if(sLen==0 && pLen==0) return true;
        if(pLen==0) return false;

        if(sLen==0){
            for(int i=0;i<pLen;i++){
                if(p.charAt(i)!='*') return false;
            }
            return true;
        }

        if(dp[sLen][pLen]!=null) return dp[sLen][pLen];

        if(p.charAt(pLen-1)=='?' || p.charAt(pLen-1)==s.charAt(sLen-1)){
            return dp[sLen][pLen] = isMatchMemo(s, p, sLen-1, pLen-1, dp);
        }

        if(p.charAt(pLen-1)=='*'){
            return dp[sLen][pLen] = isMatchMemo(s, p, sLen, pLen-1, dp) || isMatchMemo(s, p, sLen-1, pLen, dp);
        }

        return dp[sLen][pLen] = false;
    }


    
    public boolean isMatch(String s, String p) {
        int sLen=s.length();
        int pLen=p.length();
        Boolean[][] dp=new Boolean[sLen+1][pLen+1];
        return isMatchMemo(s, p, sLen, pLen, dp);
         //return isMatchRec(s, p, sLen, pLen);
    }
}
// @lc code=end

