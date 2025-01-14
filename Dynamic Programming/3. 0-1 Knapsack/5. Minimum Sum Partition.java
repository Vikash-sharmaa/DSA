 
 /*
        Our question wants us to minimize( S2 - S1)
            We know that,
                Total sum = S1 + S2
            Therefore,
                Total sum - S1 = S2
            Substitute value of S2 in minimization equation.
                min(Total sum - S1 - S1)
                min(Total sum - 2 * S1).  -> this is the difference only -> s2-s1
            
            
        the column represent the total sum, each cell represent if the column number can be obtained using numbers from the list
            
        Take those columns numbers that are marked as True, this will be S1. Subtract the double of this value from the total sum. 
            The minimum value among these obtained numbers is the final answer.
            
 */

class Solution {
    int subsetSum(int nums[], int n, int target) {
        // This function calculates the maximum subset sum less than or equal to half the total sum (`target`).This helps in balancing the two subsets to minimize their sum difference.
        boolean[][] dp = new boolean[n + 1][target + 1]; 
        // Create a 2D DP table where `dp[i][j]` indicates if a subset with sum `j` can be formed using the first `i` elements.To  determine which subset sums are possible for various numbers of elements.

        for (int i = 0; i <= n; i++) { 
            for (int j = 0; j <= target; j++) {
                if (i == 0) dp[i][j] = false;  
                // If there are no elements (`i == 0`), it is impossible to form any subset with a positive sum.
                // Why? No numbers means no subset except an empty one.
    
                if (j == 0) dp[i][j] = true;   
                // A subset sum of 0 is always possible using an empty subset.
                // Why? This forms the base case for dynamic programming.
            }
        }
    
        // Populate the DP table.
        for (int i = 1; i <= n; i++) { 
            for (int j = 1; j <= target; j++) {
                if (nums[i - 1] <= j) { 
                    // If the current element (`nums[i-1]`) is less than or equal to the target sum `j`,
                    // we check two possibilities:
                    // 1. Include the current element in the subset (dp[i-1][j-nums[i-1]]).
                    // 2. Exclude the current element from the subset (dp[i-1][j]).
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];
                    // Why? This ensures that we consider all subsets that can form sum `j`.
                } else {
                    // If the current element is greater than the target sum `j`, it cannot be included.
                    dp[i][j] = dp[i - 1][j];
                    // Why? Only subsets excluding this element are valid for sum `j`.
                }
            }
        }
    
        // Find the maximum subset sum less than or equal to half the total sum (target / 2).
        int maxi = Integer.MIN_VALUE; // Initialize the maximum subset sum to a very small value.
        for (int i = 0; i <= target / 2; i++) { 
            if (dp[n][i]) maxi = Math.max(maxi, i); 
            // Update `maxi` if a subset with sum `i` is possible.
            // Why? To balance the subsets, we want the largest subset sum ≤ target / 2.
        }
        return maxi; // Return the largest possible subset sum that fits the criteria.
    }

    public int minDifference(int a[]) {
        int n = a.length; // Get the number of elements in the array.
        int range = 0;    // Variable to store the total sum of the array
        // Calculate the total sum of the array elements.
        for (int i = 0; i < n; i++) range += a[i]; 
        // Why? The total sum helps define the problem range and is used to calculate the difference.
        // Find the maximum subset sum (`s1`) that is ≤ range / 2.
        int s1 = subsetSum(a, n, range);
        // Why? This ensures one subset has the largest possible sum without exceeding half the total sum,
        // leading to a balanced partition.
        // The minimum difference is calculated as:
        // Total sum - 2 * s1, where s1 is the sum of one subset.
        return range - 2 * s1; 
        // Why? The difference between subsets is minimized when one subset is as large as possible (≤ range / 2).
    }   

}