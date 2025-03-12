/*  https://leetcode.com/problems/decode-ways/


 * @lc app=leetcode id=91 lang=java
 *
 * [91] Decode Ways
 * 
 * // similar to fib
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    
        int numDecodings_Rec(String s, int n, int i) {
            // Base case: If we have processed the entire string, return 1 (valid decoding found)
            if (i == n) return 1;

            // If the current character is '0', it can't be decoded, so return 0
            if (s.charAt(i) == '0') return 0;

            int res = 0;
            
            // Single-digit decoding: Move to the next character
            res += numDecodings_Rec(s, n, i + 1);

            // Two-digit decoding: Check if the next two characters form a valid number (10-26)
            if (i < n - 1 && ((s.charAt(i) == '1') || (s.charAt(i) == '2' && s.charAt(i + 1) < '7'))) {
                res += numDecodings_Rec(s, n, i + 2);  // Move forward by 2 positions
            }

            return res;
        }


    // dp[i] stores the number of ways to decode from index i onward

        int numDecodings_Memo(String s, int n, int i,int[] dp) {
            // Base case: If we have processed the entire string, return 1 (valid decoding found)
            if (i == n) return 1;

            // If the current character is '0', it can't be decoded, so return 0
            if (s.charAt(i) == '0') return 0;

            if(dp[i]!=-1) return dp[i];

            int res = 0;
            
            // Single-digit decoding: Move to the next character
            res += numDecodings_Memo(s, n, i + 1,dp);

            // Two-digit decoding: Check if the next two characters form a valid number (10-26)
            if (i < n - 1 && ((s.charAt(i) == '1') || (s.charAt(i) == '2' && s.charAt(i + 1) < '7'))) {
                res += numDecodings_Memo(s, n, i + 2,dp);  // Move forward by 2 positions
            }

            return dp[i]=res;
        }

        // int numDecodings_Tabu(String s){
        //     int n=s.length();
        //     int[] dp=new int[n];
        //     Arrays.fill(dp,0);

        //     dp[0]=1;

        //     for(int i=1;i<n;i++){
                
        //     }

        // }


        
                
    public int numDecodings(String s) {
        int n=s.length();
        //return s.length()==0 ? 0 : numDecodings(s,s.length(),0);
        int[] dp=new int[n];
        Arrays.fill(dp,-1);
        return s.length()==0 ? 0 : numDecodings_Memo(s,n,0,dp);
    }
}
// @lc code=end

