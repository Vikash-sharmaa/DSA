/*

    Q. Given a sequence of matrices, find the most efficient way to multiply these matrices together. 
        The efficient way is the one that involves the least number of multiplications. The dimensions of the matrices are given in an array arr[] of size n 
        (such that n = number of matrices + 1) where the ith matrix has the dimensions (arr[i-1] x arr[i]).
 */

// (a*b) * (b*c) => a*b*c - the matrix so form will have dimensions a and c

/*
    When multiplying two matrices, the resulting dimensions are determined by:First dimension of the first matrix & Second dimension of the second matrix.
    The cost of multiplication involves the product of all three dimensions: (rows of first matrix)×(common dimension)×(columns of second matrix)

    * matrixMultiplicationMemo(a,i,k,dp) + matrixMultiplicationMemo(a,k+1,j,dp) + a[i-1]*a[k]*a[j]
    * Left Subproblem (matrixMultiplicationMemo(a, i, k, dp)):This computes the minimum cost of multiplying matrices from index i to k.
        The resulting matrix will have dimensions:
            First dimension: a[i−1] (comes from the start of the range of matrices being multiplied).
            Second dimension: a[k] (comes from the split point).
    * Right Subproblem (matrixMultiplicationMemo(a, k+1, j, dp)):This computes the minimum cost of multiplying matrices from index k+1 to j.
        The resulting matrix will have dimensions:
            First dimension: a[k] (comes from the split point).
            Second dimension:a[j] (comes from the end of the range of matrices being multiplied).
    * Combining Left and Right:
        When the resulting matrices from the left and right subproblems are multiplied:
        The first dimension of the resulting matrix is from the left subproblem: a[i−1].
        The second dimension of the resulting matrix is from the right subproblem: a[j].
        The common dimension used for the multiplication cost calculation is a[k], the dimension shared by both subproblems.
*/


class Solution {

    // Recursive function to find the minimum number of multiplications needed to multiply the chain of matrices
    static int matrixMultiplicationRecursive(int[] a, int i, int j) {
        // Base case: If the chain has one or no matrix, no multiplication is needed
        if (i >= j) return 0;

        // Initialize the minimum number of multiplications to a large value
        int mini = Integer.MAX_VALUE;

        // Try placing the parenthesis at different positions between i and j
        for (int k = i; k < j; k++) {
            // Calculate the number of multiplications needed for the current partition
            int steps = a[i-1] * a[k] * a[j] // Cost of multiplying the two resulting matrices
                        + matrixMultiplicationRecursive(a, i, k) // Cost of multiplying matrices from i to k
                        + matrixMultiplicationRecursive(a, k+1, j); // Cost of multiplying matrices from k+1 to j

            // Update the minimum number of multiplications
            mini = Math.min(mini, steps);
        }

        // Return the minimum number of multiplications needed
        return mini;
    }
 
    static int matrixMultiplicationMemo(int[] a, int i, int j, int[][] dp) {
        // Function to find the minimum cost of matrix chain multiplication for matrices from index i to j.
        // `a[]`: Array of dimensions where a[i-1] x a[i] is the size of the i-th matrix.
        // `dp[][]`: Memoization table to store results of subproblems.
        if (i >= j) return 0; 
        // Base case: If there's one or no matrix in the range (i >= j), no multiplication is needed, so the cost is 0.
        if (dp[i][j] != -1) return dp[i][j];
        // If the cost for the range [i, j] is already computed and stored in dp, return it directly to save computation.
        int minAns = Integer.MAX_VALUE; 
        // Initialize the minimum cost for this range to the maximum possible value.
        // This ensures we correctly find the minimum during the comparisons.
        for (int k = i; k <= j - 1; k++) { 
            // Iterate over all possible points `k` to split the matrix sequence into two parts.
            // Split ranges: [i, k] and [k+1, j].
            int tempAns = matrixMultiplicationMemo(a, i, k, dp) // Cost of multiplying matrices in the left range [i, k].
                         + matrixMultiplicationMemo(a, k + 1, j, dp) // Cost of multiplying matrices in the right range [k+1, j].
                         + a[i - 1] * a[k] * a[j]; 
            // Cost of combining the two resulting matrices:
            // Left matrix dimensions: a[i-1] x a[k].
            // Right matrix dimensions: a[k] x a[j].
            // Multiplication cost: a[i-1] * a[k] * a[j].
    
            minAns = Math.min(minAns, tempAns); 
            // Update `minAns` to store the minimum cost found so far for the range [i, j].
        }
    
        return dp[i][j] = minAns; 
        // Store the computed minimum cost for range [i, j] in the memoization table and return it.
    }

    static int matrixMultiplicationTabulation(int[] a) {
        int n = a.length;
        int[][] dp = new int[n][n];

        // Fill the dp array in a bottom-up manner
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j < n; j++) {
                int mini = Integer.MAX_VALUE;
                // Try placing the parenthesis at different positions between i and j
                for (int k = i; k < j; k++) {
                    // Calculate the number of multiplications needed for the current partition
                    int steps = a[i - 1] * a[k] * a[j] // Cost of multiplying the two resulting matrices
                            + dp[i][k] // Cost of multiplying matrices from i to k
                            + dp[k + 1][j]; // Cost of multiplying matrices from k+1 to j

                    // Update the minimum number of multiplications
                    if (steps < mini) mini = steps;
                }
                // Store the minimum number of multiplications needed to multiply matrices from i to j
                dp[i][j] = mini;
            }
        }

        // Return the minimum number of multiplications needed to multiply the entire chain of matrices
        return dp[1][n - 1];
    }


    static int matrixMultiplication(int arr[]) {
        // code here
        int n= arr.length;
        int[][] dp=new int[n+1][n+1];
        for(int i=0;i<=n;i++){
            for(int j=0;j<=n;j++){
                dp[i][j]=-1;
            }
        }
        //return matrixMultiplicationRecursive(arr,1,n-1);
        return matrixMultiplicationMemo(arr,1,n-1,dp);
    }
}