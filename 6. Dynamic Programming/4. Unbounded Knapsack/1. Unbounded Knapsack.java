// Unbounded knapsack -> take weight and dont decrease number of element
// Each item can be taken any number of times.

// code similar to 0/1 knapsack - just diff - we take the element but will not decrease the count of the number as we can take it again as it is available in surplus amount

import java.util.Arrays;

class Solution {
    
    static int unboundedKnapsackRecursive(int val[], int wt[], int n, int capacity) {
        // Base case: If the capacity is 0 or there are no items left to consider, the maximum value is 0.
        if (capacity == 0 || n == 0) return 0;
    
        // Check if the current item's weight is less than or equal to the remaining capacity
        if (wt[n - 1] <= capacity) {
            // If the current item can be included:
            
            // 1. Pick the current item:
            //    Add its value (val[n-1]) to the maximum value obtained by allowing the same item 
            //    to be considered again (hence n remains the same) with the reduced capacity.
            int pick = val[n - 1] + unboundedKnapsackRecursive(val, wt, n, capacity - wt[n - 1]);
            
            // 2. Don't pick the current item:
            //    Move to the next item (n-1) while keeping the capacity unchanged.
            int dontPick = unboundedKnapsackRecursive(val, wt, n - 1, capacity);
            
            // Return the maximum value obtained by either picking or not picking the current item.
            return Math.max(pick, dontPick);
        } else {
            // If the current item's weight is greater than the remaining capacity:
            // Skip the current item and move to the next item (n-1).
            return unboundedKnapsackRecursive(val, wt, n - 1, capacity);
        }
    }

    
    static int unboundedKnapsackTabulation(int val[], int wt[], int n, int capacity) {
        // Create a DP table with dimensions (n+1) x (capacity+1)
        int[][] dp = new int[n + 1][capacity + 1];
    
        // Initialize the base cases:
        // If either the number of items is 0 (i == 0) or capacity is 0 (j == 0),
        // the maximum value that can be obtained is 0.
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (i == 0 || j == 0) dp[i][j] = 0; // No items or no capacity results in 0 value.
            }
        }
    
        // Fill the DP table using the given weights and values
        for (int i = 1; i <= n; i++) { // Loop through all items
            for (int j = 1; j <= capacity; j++) { // Loop through all capacities from 1 to the given capacity
                if (wt[i - 1] <= j) {
                    // If the current item's weight is less than or equal to the current capacity 'j':
                    // Two options:
                    // 1. Take the current item: Add its value (val[i-1]) and check the remaining capacity (j - wt[i-1]) for the same item (unbounded).
                    // 2. Don't take the current item: Use the previous item's result (dp[i-1][j]).
                    dp[i][j] = Math.max(val[i - 1] + dp[i][j - wt[i - 1]], dp[i - 1][j]);
                } else {
                    // If the current item's weight is greater than the current capacity 'j',
                    // we cannot include the current item, so carry forward the value from the previous item.
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
    
        // Return the maximum value that can be obtained for the given capacity
        return dp[n][capacity];
    }

    
    static int knapSack(int val[], int wt[], int capacity) {
        // code here
        int n=val.length;
        int[][] dp=new int[n+1][capacity+1];
        for(int[] rows : dp) Arrays.fill(rows,-1);
        //return unboundedKnapsackRecursive(val,wt,n,capacity);
        //return unboundedKnapsackMemo(val,wt,n,capacity,dp);
        return unboundedKnapsackTabulation(val,wt,n,capacity);
    }
}