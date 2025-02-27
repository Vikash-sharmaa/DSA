
/*
 *
 * 
 * Similar to last Question
 * 
 * 
 */
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
                }
                else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[len1][len2];
    }
    
    
    public int minOperations(String s1, String s2) {
        // Get the lengths of both strings
        int len1 = s1.length();
        int len2 = s2.length();
        
        // Calculate the length of the Longest Common Subsequence (LCS) between s1 and s2
        int lcs = getLCS(s1, s2, len1, len2);
        
        // Calculate the number of deletions needed in s1 to match s2
        // Deletions are the characters in s1 that are not part of the LCS
        int deletions = len1 - lcs;
        
        // Calculate the number of insertions needed in s1 to match s2
        // Insertions are the characters in s2 that are not part of the LCS
        int insertions = len2 - lcs;
        
        // The total number of operations is the sum of deletions and insertions
        return deletions + insertions;
    }

}
