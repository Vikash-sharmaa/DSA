// Only change in tabulation code is - when chars are not matching will take dp[i][j] as 0


import java.util.HashMap;
import java.util.Map;

class Solution {
    int longestCommonSubstrRecursive(String s1,String s2,int len1,int len2,int count){
        if(len1==0 || len2==0) return count;
        if(s1.charAt(len1-1)==s2.charAt(len2-1)){
            count = longestCommonSubstrRecursive(s1,s2,len1-1,len2-1,count+1);
        }
        int skipFromFirst = longestCommonSubstrRecursive(s1,s2,len1-1,len2,0);
        int skipFromSecond = longestCommonSubstrRecursive(s1,s2,len1,len2-1,0);
        int result = Math.max(count,Math.max(skipFromFirst,skipFromSecond));
        return result;
    }
    
    public static int longestCommonSubstrMemo(String s1, String s2, int len1, int len2, int count, Map<String, Integer> memo) {
        // Base case: If either string is exhausted, return the current count (length of the substring)
        if (len1 == 0 || len2 == 0) return count;

        // Memoization key based on the current indices (len1, len2)
        String key = len1 + "," + len2 + "," + count;
        // Check if the result is already computed for this state
        if (memo.containsKey(key)) return memo.get(key);
        

        // If the characters match, extend the current common substring count
        if (s1.charAt(len1 - 1) == s2.charAt(len2 - 1)) {
            count = longestCommonSubstrMemo(s1, s2, len1 - 1, len2 - 1, count + 1, memo);
        }

        // Explore other possibilities: skip the current character in either string
        int skipFromFirst = longestCommonSubstrMemo(s1, s2, len1 - 1, len2, 0, memo);
        int skipFromSecond = longestCommonSubstrMemo(s1, s2, len1, len2 - 1, 0, memo);

        // Take the maximum of the current count, skipFromFirst, and skipFromSecond
        int result = Math.max(count, Math.max(skipFromFirst, skipFromSecond));

        // Store the result in the memo map before returning
        memo.put(key, result);

        return result;
    }
    
    int longestCommonSubstrTabulation(String s1,String s2){
        int len1=s1.length(),len2=s2.length();
        int [][] dp=new int[len1+1][len2+1];
        for(int i=0;i<=len1;i++){
            for(int j=0;j<=len2;j++){
                if(i==0 || j==0) dp[i][j]=0;
            }
        }
        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)) dp[i][j]=1+dp[i-1][j-1];
                else dp[i][j]=0;
            }
        }
        int maxLen=0;
        for(int i=0;i<=len1;i++){
            for(int j=0;j<=len2;j++){
                maxLen=Math.max(maxLen,dp[i][j]);
            }
        }
        return maxLen;
    }

    public int longestCommonSubstr(String s1, String s2) {
        int len1=s1.length(),len2=s2.length();
        Map<String,Integer> dp=new HashMap<>();
        //return longestCommonSubstrRecursive(s1,s2,len1,len2,0);
        //return longestCommonSubstrMemo(s1,s2,len1,len2,0,dp);
        return longestCommonSubstrTabulation(s1,s2);
        
    }
}