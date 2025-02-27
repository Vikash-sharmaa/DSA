/*
    The problem aims to count the number of ways to partition a given array into two subsets such that the absolute difference between the sums of the subsets is equal to a given value
        𝑑d. This can be a challenging problem, but it becomes more manageable with dynamic programming (DP).
    
    Intuition:
    
    Sum and Target Calculation: Given an array, the problem is essentially about finding subsets that satisfy a certain sum condition. The total sum of the array can be denoted as sum.
        The goal is to partition the array into two subsets such that the difference between their sums is d.
        
    Equation Derivation: If we let 𝑆1 and 𝑆2 be the sums of the two subsets, we have:
    
    𝑆1+𝑆2=sum
    ∣𝑆1−𝑆2∣=𝑑
    From these equations, we derive:
    𝑆1=(sum+𝑑)/2​
    
    Therefore, we need to find subsets whose sum equals (sum+𝑑)/2​.
    
    
    # Each zero can either be included in sum1 or sum2 without affecting the sum. Thus, for every zero in the array, the number of valid subsets doubles.
        Multiply the result from the DP calculation by 2^zeroes
 .
*/

import java.util.Arrays;

class Solution {
    int numberOfSubsetWithTarget(int[] nums, int n, int target) {
    // This function counts the number of subsets that sum up to the target.
    int[][] dp = new int[n + 1][target + 1];
    // Initialize the DP table.
    for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= target; j++) {
            if (i == 0) dp[i][j] = 0; // If no elements, no subset can form a positive sum.
            if (j == 0) dp[i][j] = 1; // A subset with sum 0 is always possible (empty subset).
        }
    }

    // Fill the DP table.
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= target; j++) {
            if (nums[i - 1] <= j) {
                // Current element can either be included or excluded.
                dp[i][j] = dp[i - 1][j - nums[i - 1]] + dp[i - 1][j];
            } else {
                // Current element cannot be included.
                dp[i][j] = dp[i - 1][j];
            }
        }
    }

    return dp[n][target]; // Return the count of subsets with the target sum.
}

    int countPartitions(int[] nums, int d) {
        int n = nums.length;
        int zeroes = 0;
        // Count the number of zeros and remove them from the array.
        for (int i = 0; i < n; i++) if (nums[i] == 0) zeroes++;
        int[] numsWithoutZeroes = Arrays.stream(nums).filter(x -> x != 0).toArray();
        int nWithoutZeroes = numsWithoutZeroes.length;
        // Calculate the range (sum of all non-zero elements).
        int range = 0;
        for (int num : numsWithoutZeroes) range += num;
        /*
            Target sum calculation:
            s2 - s1 = d
            => range - s1 - s1 = d
            => range - 2 * s1 = d
            => s1 = (range - d) / 2
            We need to find subsets with sum s1 (if valid).
        */
        if ((range - d) % 2 != 0 || (range - d) < 0) return 0; 
        // If (range - d) is odd or negative, it's impossible to partition.
        int target = (range - d) / 2;
        // Count the subsets with sum target using non-zero elements.
        int res = numberOfSubsetWithTarget(numsWithoutZeroes, nWithoutZeroes, target);
        // Account for subsets involving zeros. Each zero doubles the subset count.
        return res * (int) Math.pow(2, zeroes);
    }
}
