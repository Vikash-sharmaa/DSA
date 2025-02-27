/*
 * In this type of Q(Fibonacci based) Make sure , function doesn't call for negative number
 * i.e write the base condition such that it doesn't reach till negative number and at last return all the function call
 * or write the minimal base case and before calling the function & check if it will not lead to call fn with negative number.
 */

import java.util.Arrays;

class Solution {
    public int tribonacciRecursive(int n) {
        if(n<=1) return n;
        if(n==2) return 1;
        return tribonacciRecursive(n-1)+tribonacciRecursive(n-2)+tribonacciRecursive(n-3);
    }

    public int tribonacciMemo(int n,int[] dp){
        if(n<=1) return n;
        if(n==2) return 1;

        if(dp[n]!=-1) return dp[n];
        return dp[n] = tribonacciMemo(n-1, dp) + tribonacciMemo(n-2, dp) + tribonacciMemo(n-3, dp);
    }

    public int tribonacci(int n){
        int [] dp=new int[n+1];
        Arrays.fill(dp, -1);
        return tribonacciMemo(n,dp);
    }
}


