/*
 * @lc app=leetcode id=238 lang=java
 *
 * [238] Product of Array Except Self
 */

// @lc code=start
class Solution {
/**************************************************************************************************/

// Time: O(n^2)     Space: O(1)
    public int[] productExceptSelf_Naive(int[] nums) {
        int n=nums.length;
        int[] res=new int[n];

        for(int i=0;i<n;i++){
            int mul=1;
            for(int j=0;j<n;j++){
                if(i==j) continue;
                mul*=nums[j];
            }
            res[i]=mul;
        }
        return res;
    }

/**************************************************************************************************/
    /* We can think of multiplying all the elements of array and then divide by each elements
     * but it will fail , since elements also could be 0
     */
    /*
      Handles zero cases:
        If no zeros → Use division-based approach.
        If one zero → Only that index gets the product of non-zero numbers.
        If more than one zero → Entire array is 0.
     */

     // overall complexity is O(N) time and O(N) space (excluding output storage)

     public int[] productExceptSelf_1(int[] nums) {
        int n = nums.length;
        int[] res = new int[n]; // Result array to store the output
    
        int zeroes = 0, mul = 1; // Count of zeros and product of non-zero elements
    
        // Step 1: Calculate product of all non-zero elements and count zeroes
        for (int num : nums) {
            if (num == 0) zeroes++; // Count zeros
            else mul *= num; // Multiply non-zero elements
        }
    
        // Step 2: If more than one zero, all elements in res will be 0
        if (zeroes > 1) return res;
    
        int i = 0; // Index for result array
    
        // Step 3: Compute the product for each element except itself
        for (int num : nums) {
            if (zeroes == 0) { 
                // If no zeros, each element gets product/missing element
                res[i++] = mul / num;
            } else { 
                // If one zero, only the position with zero gets the product
                res[i++] = (num == 0) ? mul : 0;
            }
        }
    
        return res;
    }
    
/**************************************************************************************************/
    // I was thinking to start the pre and suf arrays with last elements 
    // it was wrong approch , but here we started each with 1

// Time: O(n)       Space: O(n)

    public int[] productExceptSelf_2(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];  // Result array to store final product values
        
        int[] suf = new int[n];  // Suffix product array
        int[] pre = new int[n];  // Prefix product array

        suf[n - 1] = 1;  // Initialize last element of suffix array as 1
        pre[0] = 1;      // Initialize first element of prefix array as 1

        // Step 1: Compute prefix products
        // pre[i] stores the product of all elements before index i
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] * nums[i - 1];
        }

        // Step 2: Compute suffix products
        // suf[i] stores the product of all elements after index i
        for (int i = n - 2; i >= 0; i--) {
            suf[i] = suf[i + 1] * nums[i + 1];
        }

        // Step 3: Compute the result array
        // Each index i gets the product of all elements except itself
        for (int i = 0; i < n; i++) {
            res[i] = pre[i] * suf[i];
        }

        return res;
    }


/**************************************************************************************************/
// Time: O(n)           Space: O(1)

    public int[] productExceptSelf_3(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];  // Result array to store final product values

        res[0] = 1;  // Initialize the first element of res to 1 (acts as prefix product)

        // Step 1: Compute prefix product for each element
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];  
            // res[i] stores the product of all elements before index i
        }

        int post = 1;  // Variable to store postfix product (product of elements after index i)

        // Step 2: Compute postfix product and multiply with prefix product stored in res
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= post;  // Multiply current res[i] (prefix product) with postfix product
            post *= nums[i];  // Update postfix product for next iteration
        }

        return res;  // Return the final result array
    }


/**************************************************************************************************/


    public int[] productExceptSelf(int[] nums) {
        return productExceptSelf_3(nums);
    }
}
// @lc code=end

