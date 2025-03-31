/* https://leetcode.com/problems/find-the-smallest-divisor-given-a-threshold/description/

 * @lc app=leetcode id=1283 lang=java
 *
 * [1283] Find the Smallest Divisor Given a Threshold
 */

// @lc code=start
class Solution {

/**************************************************************************************************************************/


    // Time: O(n*range)

    public int smallestDivisor_Naive(int[] nums, int threshold) {
        // Step 1: find the maximum element in the array to know the upper limit of divisor
        int maxDivisor = Integer.MIN_VALUE; // to store maximum element
        
        int res = -1; // to store the answer
        
        for (int i = 0; i < nums.length; i++) {
            maxDivisor = Math.max(maxDivisor, nums[i]); // find max element
        }
    
        // Step 2: try all possible divisors from 1 to maxDivisor
        for (int i = 1; i < maxDivisor; i++) {
            int resultSum = sumOfDivisionResult(nums, i); // sum of ceil(nums[i] / divisor) for all i
            
            // Step 3: if the sum is <= threshold, then this divisor works
            if (resultSum <= threshold) {
                res = i; // store the result
                break;   // since we need smallest, break here
            }
        }
    
        // Step 4: return the smallest valid divisor
        return res;
    }

/**************************************************************************************************************************/

    // Binary Search + Greedy

    // Time: O(n*log(range))

    public int smallestDivisor(int[] nums, int threshold) {
        int maxDivisor = Integer.MIN_VALUE; // to store maximum element of the array
        int res = -1; // to store the final answer
    
        // Step 1: find the maximum element in the array
        for (int i = 0; i < nums.length; i++) {
            maxDivisor = Math.max(maxDivisor, nums[i]);
        }
    
        // Step 2: apply binary search on divisor range [1, maxDivisor]
        int start = 1, end = maxDivisor;
        while (start <= end) {
            int mid = start + (end - start) / 2; // safe mid calculation
            
            // Step 3: calculate total sum of (ceil division) with divisor = mid
            int resultSum = sumOfDivisionResult(nums, mid);
    
            // Step 4: check if current divisor satisfies the threshold
            if (resultSum <= threshold) {
                res = mid;      // store the possible answer
                end = mid - 1;  // try to find smaller divisor
            } else {
                start = mid + 1; // increase divisor to reduce sum
            }
        }
        return res; // Step 5: return the minimum valid divisor
    }

/**************************************************************************************************************************/

    // Helper function to calculate sum of ceil(nums[i] / divisor) without using Math.ceil()
    private int sumOfDivisionResult(int[] nums, int divisor) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            // Equivalent to ceil(nums[i] / divisor) = (nums[i] + divisor - 1) / divisor
            sum += (nums[i] + divisor - 1) / divisor;
        }
        return sum;
    }

    /**************************************************************************************************************************/
    
}
// @lc code=end

