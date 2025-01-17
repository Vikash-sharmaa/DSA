// length of LPS inversely proportional to number of deletions to make it a palindromic subsequence
// min no. of deletions / min no. of insertions = length of string - LPS(s)  =>  LCS(s,reverse(s))

//User function Template for Java
class Solution 
{ 
    // Function to calculate the length of the Longest Common Subsequence (LCS) between two strings.
    int LCS(String s1, String s2,int len1,int len2) {
        // Create a 2D array `dp` where `dp[i][j]` stores the LCS length of substrings `s1[0..i-1]` and `s2[0..j-1]`.
        int[][] dp = new int[len1 + 1][len2 + 1];
    
        // Initialize the base cases: If either string is empty, the LCS length is 0.
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 || j == 0) dp[i][j] = 0; // LCS with an empty string is always 0.
            }
        }
    
        // Fill the DP table using the LCS relation:
        for (int i = 1; i <= len1; i++) { // Iterate over the characters of `s1`.
            for (int j = 1; j <= len2; j++) { // Iterate over the characters of `s2`.
                // If the characters match, include them in the LCS.
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1]; // Add 1 to the LCS length of previous substrings.
                } else {
                    // If the characters don't match, take the maximum LCS by excluding one character.
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
    
        // The final LCS length of the two full strings is stored in `dp[len1][len2]`.
        return dp[len1][len2];
    }

    
    // Function to find the minimum number of deletions required to make a string a palindrome.
    int minDeletions(String str, int n) {
        // Reverse the string `str` to create a new string `reverseStr`.
        String reverseStr = new StringBuilder(str).reverse().toString();
        int len1 = str.length(), len2 = reverseStr.length(); // Get the lengths of the two input strings.
    
        // The Longest Palindromic Subsequence (LPS) of `str` is the LCS of `str` and its reverse `reverseStr`.
        int LPS = LCS(str, reverseStr,len1,len2); // Calculate LPS using LCS of `str` and its reversed string.
    
        // The minimum number of deletions required to make `str` a palindrome is the difference between
        // the length of `str` and the LPS. The LPS is the longest subsequence that already forms a palindrome,
        // so we need to delete the rest of the characters.
        return str.length() - LPS; // Return the difference as the number of deletions needed.
    }
}