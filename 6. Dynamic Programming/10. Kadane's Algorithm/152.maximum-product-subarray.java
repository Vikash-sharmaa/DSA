/*
 * @lc app=leetcode id=152 lang=java
 *
 * [152] Maximum Product Subarray
 */

// @lc code=start
class Solution {
    // Find prod for each subarray and compare to get maxProd
    // Time - O(n^2)        Space - O(1)
    int maxProductBruteForce(int[] nums) {
        int n = nums.length; // Length of the input array
        int maxProd = Integer.MIN_VALUE; // Initialize maxProd to the smallest possible integer value

        // Outer loop: Start of the subarray
        for (int i = 0; i < n; i++) {
            int prod = 1; // Initialize product to 1 for the new subarray starting at index i
            // Inner loop: End of the subarray
            for (int j = i; j < n; j++) {
                prod *= nums[j]; // Multiply the current element with the cumulative product
                // Update maxProd if the current product is greater than the current maxProd
                maxProd = Math.max(maxProd, prod);
            }
        }
        // Return the maximum product found
        return maxProd;
    }

    int maxProduct(int[] nums) {
        int n = nums.length; // Get the length of the input array

        // Initialize the maximum and minimum products ending at the first element
        int currentMaxProd = nums[0]; // Tracks the maximum product subarray ending at the current index
        int currentMinProd = nums[0]; // Tracks the minimum product subarray ending at the current index
        int maxProd = nums[0];        // Tracks the global maximum product across all subarrays

        // Iterate through the array starting from the second element
        for (int i = 1; i < n; i++) {
            int current = nums[i]; // Current element in the array

            // If the current element is negative, swap currentMaxProd and currentMinProd
            // Why: A negative number will make the maximum product become the minimum and vice versa
            // Example: [-2, 3] → At -2, max = -2, min = -2 → At 3, max = 3 (min * 3), min = -6 (max * 3)
            if (current < 0) {
                int temp = currentMaxProd;
                currentMaxProd = currentMinProd;
                currentMinProd = temp;
            }

            // Update currentMaxProd and currentMinProd:
            // - currentMaxProd: Max of the current element itself (starting a new subarray)
            //   or the product of the current element and the previous max product (extend the subarray).
            currentMaxProd = Math.max(current, current * currentMaxProd);

            // - currentMinProd: Min of the current element itself
            //   or the product of the current element and the previous min product.
            currentMinProd = Math.min(current, current * currentMinProd);

            // Update the global maximum product
            maxProd = Math.max(maxProd, currentMaxProd);
        }

        // Return the maximum product of any subarray
        return maxProd;
    }
}
// @lc code=end

