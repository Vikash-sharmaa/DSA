// https://leetcode.com/problems/climbing-stairs/description/

/*
 * @lc app=leetcode id=70 lang=java
 * 
 * Base condition should be such that - function is not called for negative n
 * dp[i] denotes no. of ways to climb upto ith stair
 * climbStairsRecursive(n-1) -  will bring all the ways when we take current step as 1 
 * climbStairsRecursive(n-2) - will bring all the ways when we take current step as 2
 * 
 */
import java.util.Arrays;

class Solution {
    public int climbStairsRecursive(int n) {
        if(n<=1) return 1;
        return climbStairsRecursive(n-1) + climbStairsRecursive(n-2);
    }

    public int climbStairsMemo(int n,int[] dp){
        if(n<=1) return 1;
        if(dp[n]!=-1) return dp[n];
        return dp[n] = climbStairsMemo(n-1, dp) + climbStairsMemo(n-2, dp);
    }

    public int climbStairsTabulation(int n){
        int[] dp=new int[n+1];
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<=n;i++) dp[i]=dp[i-1]+dp[i-2];
        return dp[n];
    }
    public int climbStairs(int n) {
        int[] dp=new int[n+1];
        Arrays.fill(dp, -1);
        //return climbStairsRecursive(n);
        //return climbStairsMemo(n, dp);

        return climbStairsTabulation(n);
    }
}

