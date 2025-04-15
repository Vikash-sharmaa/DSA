/* https://leetcode.com/problems/edit-distance/description/

 * @lc app=leetcode id=72 lang=java
 *
 * [72] Edit Distance
 */

// @lc code=start
class Solution {

    int minDistanceRecu(String word1, String word2, int len1, int len2) {
        // Base Cases:
        
        // If word1 is empty, we need `len2` insertions to convert "" to word2
        // Example: word1 = "", word2 = "ab" → Insert 'a' → Insert 'b' → "ab"
        if (len1 == 0) return len2;  
    
        // If word2 is empty, we need `len1` deletions to remove all characters from word1
        // Example: word1 = "abc", word2 = "" → Delete 'c' → Delete 'b' → Delete 'a' → ""
        if (len2 == 0) return len1;  
    
        // If last characters of both words match, no edit is needed
        // We simply move both pointers to check the remaining substring
        if (word1.charAt(len1 - 1) == word2.charAt(len2 - 1)) {
            return minDistanceRecu(word1, word2, len1 - 1, len2 - 1);
        } else {
            // If last characters do not match, we have three possible operations:
            
            // 1. Insert the last character of word2 into word1
            //    This means matching word1[0..len1-1] with word2[0..len2-2]
            int insert = minDistanceRecu(word1, word2, len1, len2 - 1);
    
            // 2. Delete the last character of word1
            //    This means matching word1[0..len1-2] with word2[0..len2-1]
            int delete = minDistanceRecu(word1, word2, len1 - 1, len2);
    
            // 3. Replace the last character of word1 with the last character of word2
            //    This means matching word1[0..len1-2] with word2[0..len2-2]
            int replace = minDistanceRecu(word1, word2, len1 - 1, len2 - 1);
    
            // Take the minimum of all three operations and add 1 (for the current operation)
            return 1 + Math.min(insert, Math.min(replace, delete));
        }
    }
    

    int minDistanceMemo(String word1, String word2, int len1,int len2,int[][] dp){
        if(len1==0) return len2;
        if(len2==0) return len1;

        if(dp[len1][len2]!=-1) return dp[len1][len2];

        if(word1.charAt(len1-1)==word2.charAt(len2-1)){
            return  dp[len1][len2] = minDistanceMemo(word1, word2, len1-1, len2-1, dp);
        }else{
            int insert = minDistanceMemo(word1, word2, len1, len2-1, dp);
            int delete = minDistanceMemo(word1, word2, len1-1, len2, dp);
            int replace = minDistanceMemo(word1, word2, len1-1, len2-1, dp);
            return dp[len1][len2] = 1 + Math.min(replace, Math.min(delete, insert));
        }
    }
    

    int minDistanceTabulation(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
    
        // Create a DP table where dp[i][j] represents the minimum operations 
        // required to convert word1[0..i-1] to word2[0..j-1].
        int[][] dp = new int[len1 + 1][len2 + 1];
    
        // Base case 1: If word2 is empty, we need i deletions to remove all characters from word1
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
    
        // Base case 2: If word1 is empty, we need j insertions to convert "" to word2
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }
    
        // Fill the DP table bottom-up
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                // If the current characters match, no operation is needed, so take the previous result
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // If the characters don't match, we consider the three possible operations:
                    
                    int insert = dp[i][j - 1];   // Insert the current character of word2 into word1
                    int delete = dp[i - 1][j];   // Delete the current character from word1
                    int replace = dp[i - 1][j - 1]; // Replace the current character of word1 with word2
    
                    // Take the minimum of all three operations and add 1 (for the current operation)
                    dp[i][j] = 1 + Math.min(replace, Math.min(insert, delete));
                }
            }
        }
    
        // The final answer is stored in dp[len1][len2], which represents the edit distance
        return dp[len1][len2];
    }
    
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        int [][] dp=new int[len1+1][len2+1];
        for(int i=0;i<=len1;i++){
            for(int j=0;j<=len2;j++){
                dp[i][j]=-1;
            }
        }
        return minDistanceTabulation(word1, word2);
        //return minDistanceMemo(word1, word2, len1, len2, dp);
        //return minDistanceRecu(word1, word2, word1.length(), word2.length());
    }
}
// @lc code=end

