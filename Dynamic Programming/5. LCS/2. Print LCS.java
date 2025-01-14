class Solution {

    public static String findLCS(int n, int m, String s1, String s2) {
        // Create a DP table to store the length of LCS for substrings of s1 and s2
        int[][] dp = new int[n + 1][m + 1];

        // Initialize the DP table for the base case
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0; // LCS of an empty string with any other string is 0
                }
            }
        }

        // Fill the DP table using the recurrence relation
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    // Characters match, add 1 to the LCS of the previous substrings
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    // Characters don't match, take the max of excluding current char of either string
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Backtrack to construct the LCS string
        StringBuilder lcs = new StringBuilder();
        int i = n, j = m;
        
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                // If characters match, include them in LCS and move diagonally up-left
                lcs.append(s1.charAt(i - 1));
                i--;
                j--;
            } else {
                // Move in the direction of the larger value in the DP table
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    i--; // Move up
                } else {
                    j--; // Move left
                }
            }
        }

        // Reverse the result as we constructed it in reverse order
        return lcs.reverse().toString();
    }

    public static void main(String[] args) {
        String s1 = "abcab";
        String s2 = "cbab";
        int n = s1.length();
        int m = s2.length();

        String lcs = findLCS(n, m, s1, s2);
        System.out.println("Longest Common Subsequence: " + lcs);
    }
}
