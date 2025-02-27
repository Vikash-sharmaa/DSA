/*
 * The problem follows the Fibonacci-based pattern:
 * Each state depends on the results of two previous states.
 * Base cases ensure no invalid calls are made.
 * 
 * The problem allows you to either:
  - End on the last step (n−1).
  - Skip the last step and end on the second last step (n−2).
  - The goal is to reach the "top" with the minimum cost, which means we don’t necessarily have to stand on the last step. 
    We can reach the top from either of the last two steps. Thus, the final result is: Math.min(reachLast, secondLast);


    * How above method will take care of not_take case i.e skip case?
        # Ans: When we are at index 'i' and if we include ans of 'i+2' (called 'i + 2') then we have skipped 'i+1'.
 */

class Solution {
    int minCostClimbingStairsRecursive(int n,int[] cost){
        if(n<0) return 0;
        if(n==0 || n==1) return cost[n];
        
        int oneStep = minCostClimbingStairsRecursive(n-1,cost);
        int twoStep = minCostClimbingStairsRecursive(n-2,cost);
        return cost[n] + Math.min(oneStep,twoStep);
    }

    int minCostClimbingStairsMemo(int n,int[] cost,int[] dp){
        if(n<0) return 0;
        if(n==0 || n==1) return cost[n];

        if(dp[n]!=-1) return dp[n];
        int oneStep = minCostClimbingStairsMemo(n-1, cost, dp);
        int twoStep = minCostClimbingStairsMemo(n-2, cost, dp);

        return dp[n] = cost[n] + Math.min(oneStep, twoStep);
    }

    int minCostClimbingStairsTabulation(int[] cost){
        int n=cost.length;
        int[] dp=new int[n];
        // Cost to reach the first or second step is directly the value in the cost array
        dp[0]=cost[0];
        dp[1]=cost[1];
        for(int i=2;i<n;i++){
            // Cost of reaching the current step is the cost of the step itself plus
            // the minimum cost to reach one step below or two steps below
            int oneStep = dp[i-1];
            int twoStep = dp[i-2];
            dp[i]=cost[i] + Math.min(oneStep, twoStep);
        }
        int minCostToReachLastStair = dp[n-1];
        int minCostToReachSecondLastStair = dp[n-2];
        // Return the minimum of the two as we can stop at either of the last two steps
        return Math.min(minCostToReachLastStair, minCostToReachSecondLastStair);
    }
    public int minCostClimbingStairs(int[] cost) {
        int n=cost.length;
        // int reachLast = minCostClimbingStairsRecursive(n-1, cost);
        // int secondLast = minCostClimbingStairsRecursive(n-2, cost);
        // return Math.min(reachLast, secondLast);

        // int[] dp=new int[n+1];
        // Arrays.fill(dp, -1);
        // int reachLast = minCostClimbingStairsMemo(n-1, cost, dp);
        // int reachSecondLast = minCostClimbingStairsMemo(n-2, cost, dp);

        // return Math.min(reachLast,reachSecondLast);

        return minCostClimbingStairsTabulation(cost);
    }
}


