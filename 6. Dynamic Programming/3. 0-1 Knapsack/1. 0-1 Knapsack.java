/*
 *      
    # given two integer arrays val[0..n-1] and wt[0..n-1] 
        # which represent values and weights associated with n items respectively.
        #  Also given an integer W which represents knapsack capacity, 
        # find out the maximum value subset of val[] such that sum of the weights of 
        # this subset is smaller than or equal to W. 
        # Note: You cannot break an item, either pick the complete item or don’t pick it (0-1) property
 * 
 * 
 */

class Solution {
    /*
        # method1: by recursion

        # just like subsequence and subset. basically we are finding this only from the value array
        # Logic: For every ele we have two choice : 1) include that or 2) Not include that
        # we can only include if sufficient weight in bag is available.
        # Time Complexity: O(2^n), no extra space but recursion  O(n)
     */
    static int knapSackRecursive(int[] val, int[] wt, int capacity, int n) {
        // Base case: If there are no items left (n == 0) or the remaining capacity is zero,the maximum value we can obtain is 0.
        if (n == 0 || capacity == 0) return 0;

        // Check if the current item's weight is less than or equal to the remaining capacity.
        if (wt[n - 1] <= capacity) {

            // The current item's weight and value.
            int currentWeight = wt[n - 1];
            int currentValue = val[n - 1];


            // Case 1: Pick the current item & Reduce the remaining capacity by the weight of the current item and recursively solve the problem for the remaining items.
            int pickedUp = currentValue + knapSackRecursive(val, wt, capacity - currentWeight, n - 1);
            // Case 2: Do not pick the current item & Solve the problem for the remaining items without reducing the capacity.
            int notPicked = knapSackRecursive(val, wt, capacity, n - 1);
            // Return the maximum value obtained by either picking or not picking the current item.


            return Math.max(pickedUp, notPicked);
        } else {

            // If the current item's weight exceeds the remaining capacity & skip the current item and recursively solve for the remaining items.
            return knapSackRecursive(val, wt, capacity, n - 1);

        }
    }

    static int knapSackMemo(int[] val,int[] wt,int capacity,int n,int[][]dp){
        if(capacity==0 || n==0) return 0;
        if(dp[n][capacity]!=-1) return dp[n][capacity];
        if(wt[n-1]<=capacity){
            int currentWeight = wt[n-1];
            int currentValue = val[n-1];
            int pickedUp = currentValue + knapSackMemo(val,wt,capacity-currentWeight,n-1,dp);
            int notPicked = knapSackMemo(val,wt,capacity,n-1,dp);
            return dp[n][capacity] = Math.max(pickedUp,notPicked);
        }else{
            return dp[n][capacity] = knapSackMemo(val,wt,capacity,n-1,dp);
        }
    }

    /*
     * Tabulation (By Bottom up approach)
        # just write the base case and then replace by for loop and inside for loop just copy paste the code of recursion and replace function call by DP value
        # Each cell of bottom up approach defines a sub problem -  Dp[W][n] - stores the answer when there was n elements and the capacity of 
	            Knapsack was W.
Ex. Dp[4][3] - stores the answer when there were 3 elements and the knapsack's capacity was 4.
     */
    static int knapSackTabulation(int[] val, int[] wt, int capacity, int n) {
        int[][] dp = new int[n + 1][capacity + 1];
        // Base case initialization: if there are no items or capacity is 0, the max value is 0.
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (i == 0 || j == 0) dp[i][j] = 0;  // No items or no capacity means 0 value.
            }
        }
        // Start filling the DP table for each item (i) and capacity (j)
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (wt[i - 1] <= j) {  // If current item can be included (weight <= current capacity)
                    // currentWeight: weight of the current item
                    int currentWeight = wt[i - 1];
                    // currentValue: value of the current item (incorrect in your code, corrected below)
                    int currentValue = val[i - 1];  // This should be 'val[i-1]', not 'val[n-1]'.
                    // Case when the current item is picked up: add the value of the current item to the remaining capacity.
                    int pickedUp = currentValue + dp[i - 1][j - currentWeight];
                    // Case when the current item is not picked: take the previous value for this capacity.
                    int notPicked = dp[i - 1][j];
                    // The current cell in dp is the maximum of picked or not-picked cases.
                    dp[i][j] = Math.max(pickedUp, notPicked);
                } else {
                    // If the current item cannot be included (because weight exceeds capacity)
                    dp[i][j] = dp[i - 1][j];  // The value will be the same as the previous row (excluding the item).
                }
            }
        }
        return dp[n][capacity];
    }


    
    // Function to return max value that can be put in knapsack of capacity.
    static int knapSack(int capacity, int val[], int wt[]) {
        // code here
        //return knapSackRecursive(val,wt,capacity,wt.length);
        int n=wt.length;
        int[][]dp=new int[n+1][capacity+1];
        //for(int[]row : dp) Arrays.fill(row,-1);
        for(int i=0;i<=n;i++){
            for(int j=0;j<=capacity;j++) dp[i][j]=-1;
        }
       //return knapSackMemo(val,wt,capacity,n,dp);
       return knapSackTabulation(val,wt,capacity,n);
    }
}