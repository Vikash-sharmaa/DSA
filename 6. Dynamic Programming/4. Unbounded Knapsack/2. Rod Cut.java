
class Solution {
    int cutRodRecursive(int[] price, int[] lengthAvailable, int n, int len) {
        // Base case: If no items are left to consider (n == 0) or the rod length is 0 (len == 0),
        // the maximum profit is 0, as we cannot cut a rod with no length.
        if (n == 0 || len == 0) return 0;
        
        // If the current rod piece can fit into the remaining rod length:
        if (lengthAvailable[n - 1] <= len) {
            // 1. Pick the current rod piece: 
            //    Add its price (price[n-1]) to the maximum profit of the remaining length (len - lengthAvailable[n-1])
            //    and recursively calculate the maximum profit.
            int pick = price[n - 1] + cutRodRecursive(price, lengthAvailable, n, len - lengthAvailable[n - 1]);
            
            // 2. Don't pick the current rod piece:
            //    Just consider the maximum profit by excluding the current piece (i.e., reduce the number of pieces by 1).
            int dontPick = cutRodRecursive(price, lengthAvailable, n - 1, len);
            
            // Return the maximum of picking or not picking the current rod piece.
            return Math.max(pick, dontPick);
        } else {
            // If the current rod piece cannot fit into the remaining length (lengthAvailable[n-1] > len),
            // we exclude the current rod piece and consider the next one.
            return cutRodRecursive(price, lengthAvailable, n - 1, len);
        }
    }



    
    int cutRodMemo(int[] price, int[] lengthAvailable,int n,int len,int[][] dp){
        if(n==0 || len==0) return 0;
        
        if(dp[n][len]!=-1) return dp[n][len];
        if(lengthAvailable[n-1]<=len){
            int pick = price[n-1] + cutRodMemo(price,lengthAvailable,n,len-lengthAvailable[n-1],dp);
            int dontPick = cutRodMemo(price,lengthAvailable,n-1,len,dp);
            return dp[n][len] = Math.max(pick,dontPick);
        }else{
            return dp[n][len] = cutRodMemo(price,lengthAvailable,n-1,len,dp);
        }
    }
    
    
    
    int cutRodTabulation(int[] price, int[] lengthAvailable, int n, int len) {
        // DP table: dp[i][j] represents the maximum profit we can achieve using the first i lengths
        // and a rod of length j.
        int[][] dp = new int[n + 1][len + 1];
    
        // Initialize the base cases:
        // If there are no items or the rod length is 0, the maximum profit is 0.
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= len; j++) {
                if (i == 0 || j == 0) dp[i][j] = 0; // No rod length or no available pieces gives 0 profit.
            }
        }
    
        // Fill the DP table using the lengths and prices of the rod pieces.
        for (int i = 1; i <= n; i++) { // Loop through all rod pieces
            for (int j = 1; j <= len; j++) { // Loop through all possible rod lengths from 1 to 'len'
                if (lengthAvailable[i - 1] <= j) {
                    // If the current rod piece can fit into the current length 'j':
                    // Two options:
                    // 1. Take the current piece: Add its price (price[i-1]) to the maximum profit of the remaining length (j - lengthAvailable[i-1]).
                    // 2. Don't take the current piece: Use the maximum profit from the previous pieces for the same length 'j'.
                    int pick = price[i - 1] + dp[i][j - lengthAvailable[i - 1]];
                    int dontPick = dp[i - 1][j];
                    dp[i][j] = Math.max(pick, dontPick); // Store the maximum profit.
                } else {
                    // If the current piece cannot fit into the current length, skip the piece.
                    dp[i][j] = dp[i - 1][j]; // The profit remains the same as without this piece.
                }
            }
        }
    
        // The final answer is stored in dp[n][len], the maximum profit with all pieces considered and a rod of length 'len'.
        return dp[n][len];
    }

    
    public int cutRod(int[] price) {
        // code here
        int n=price.length;
        int[] lengthAvailable = new int[n];
        int[][] dp=new int[n+1][n+1];
        for(int i=0;i<=n;i++){
            for(int j=0;j<=n;j++){
                dp[i][j]=-1;
            }
        }
        
        for(int i=0;i<n;i++) lengthAvailable[i]=i+1;
        
        //return cutRodRecursive(price,lengthAvailable,n,n);
        
        //return cutRodMemo(price,lengthAvailable,n,n,dp);
        
        return cutRodTabulation(price,lengthAvailable,n,n);
        
    }

}