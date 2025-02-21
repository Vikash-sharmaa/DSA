import java.util.Arrays;

class Solution {

    // Recursive 
    public int fibRecursive(int n) {
        if(n<=1) return n;
        return fibRecursive(n-1)+fibRecursive(n-2);
    }

    //Memoization
    public int fibMemo(int n,int[] dp){
        if(n<=1) return n;
        if(dp[n]!=-1) return dp[n];
        return dp[n]=fibMemo(n-1,dp)+fibMemo(n-2,dp);
    }

    // Tabulation
    public int fibTabulation(int n) {
        if(n==0) return 0;
        int[] dp=new int[n+1];
        dp[0]=0;
        dp[1]=1;
        for(int i=2;i<=n;i++) dp[i]=dp[i-1]+dp[i-2];
        return dp[n];
    }

    public int fib(int n) {
        int[] dp=new int[n+1];
        Arrays.fill(dp,-1);
        //return helper(n,dp);
        return -1;
    }
}