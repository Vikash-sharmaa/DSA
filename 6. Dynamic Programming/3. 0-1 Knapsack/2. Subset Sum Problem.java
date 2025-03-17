/*
    Given an array arr[] of non-negative integers and a value sum, the task is to check if there is a subset of the given array whose sum is equal to the given sum. 

        Examples: 

            Input: arr[] = {3, 34, 4, 12, 5, 2}, sum = 9
            Output: True
            Explanation: There is a subset (4, 5) with sum 9.


            Input: arr[] = {3, 34, 4, 12, 5, 2}, sum = 30
            Output: False
            Explanation: There is no subset that add up to 30.
 */

class Solution {
    static boolean isSubsetSumRecursive(int[] a, int target, int n) {
            // Base case 1: If the target is 0, there is always a subset with sum 0 (the empty subset).
            if (target == 0) return true;
            // Base case 2: If no elements are left but the target is not 0, it's not possible to form the target sum.
            if (n == 0) return false;
            // Case 1: If the current element is less than or equal to the target, we have two choices:
            // 1. Include the current element and check for the remaining target (target - a[n-1]).
            // 2. Exclude the current element and check for the same target.
            if (a[n - 1] <= target) return isSubsetSumRecursive(a, target - a[n - 1], n - 1) || isSubsetSumRecursive(a, target, n - 1);
            // Case 2: If the current element is greater than the target, we cannot include it in the subset,
            // so we move to the next element without changing the target.
            else return isSubsetSumRecursive(a, target, n - 1);
    }
    
    static Boolean isSubsetSumMemo(int[] arr,int n,int target,Boolean[][] dp){
        if(target==0) return true;
        if(n==0) return false;
        
        if(dp[n][target]!=null) return dp[n][target];
        if(arr[n-1]<=target){
            Boolean pick = isSubsetSumMemo(arr,n-1,target-arr[n-1],dp);
            Boolean dontPick = isSubsetSumMemo(arr,n-1,target,dp);
            
            return dp[n][target] = pick || dontPick;
        }else{
            return dp[n][target] = isSubsetSumMemo(arr,n-1,target,dp);
        }
    }
    
    static boolean isSubsetSumTabulation(int[] a, int target) {
            int n = a.length;
            // dp[i][j] will indicate whether it's possible to achieve sum `j` using the first `i` elements.
            boolean[][] dp = new boolean[n + 1][target + 1];
            // Initialize the DP table:
            for (int i = 0; i <= n; i++) { // Iterate over the number of elements.
                for (int j = 0; j <= target; j++) { // Iterate over possible target sums.
                    if (i == 0) dp[i][j] = false; // If no elements are available and target > 0, it's impossible to achieve the target.
                    if (j == 0) dp[i][j] = true; // A target sum of 0 is always possible by taking an empty subset.
                }
            }
            // Fill the DP table using a bottom-up approach:
            for (int i = 1; i <= n; i++) { // Loop over each element in the array.
                for (int j = 1; j <= target; j++) { // Loop over all possible target sums.
                    if (a[i - 1] <= j) { 
                        // If the current element can be included (its value <= current target `j`): Two possibilities:
                        // 1. Include the current element, reducing the target by `a[i-1]`.
                        // 2. Exclude the current element and use the result of previous elements for the same target.
                        dp[i][j] = dp[i - 1][j - a[i - 1]] || dp[i - 1][j];
                    } else {
                        // If the current element cannot be included (its value > current target `j`):
                        // Inherit the result from the previous row (excluding the current element).
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
        
            // The final result is in `dp[n][target]`:
            // This indicates whether it's possible to achieve the `target` sum using all `n` elements.
            return dp[n][target];
    }



    static Boolean isSubsetSum(int arr[], int target) {
        // code here
        //return isSubsetSumRecursive(arr,target,arr.length);
        Boolean [][] dp=new Boolean[arr.length+1][target+1];
        
        //return isSubsetSumMemo(arr,target,arr.length,dp);
        return isSubsetSumTabulation(arr,target);
    }
}