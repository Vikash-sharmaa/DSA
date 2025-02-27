/*
    Given an array of integers nums, half of the integers in nums are odd, and the other half are even.
    Sort the array so that whenever nums[i] is odd, i is odd, and whenever nums[i] is even, i is even.
    Return any answer array that satisfies this condition.

        Example 1:

        Input: nums = [4,2,5,7]
        Output: [4,5,2,7]
        Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.

        Example 2:

        Input: nums = [2,3]
        Output: [2,3]

 */


/*
 * @lc app=leetcode id=922 lang=java
 *
 * When even and odd is given in ques - situation might ask to move +2 index position.
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {

/********************************************************************************************************************************/

    // Time:- O(n)      ||      Space:- O(n)

    public int[] sortArrayByParity1(int[] nums) {
        
        // Create two lists to store even and odd numbers separately
        List<Integer> even = new ArrayList<>();
        List<Integer> odd = new ArrayList<>();
    
        // Step 1: Separate numbers into even and odd lists
        for (int n : nums) {
            if (n % 2 == 0) even.add(n); // Add even numbers to even list
            else odd.add(n); // Add odd numbers to odd list
        }
    
        int i = 0; // Start placing even numbers at even indices
        for (int n : even) {
            nums[i] = n; // Place even number at index i
            i += 2; // Move to the next even index (0, 2, 4, …)
        }
    
        i = 1; // Start placing odd numbers at odd indices
        for (int n : odd) {
            nums[i] = n; // Place odd number at index i
            i += 2; // Move to the next odd index (1, 3, 5, …)
        }
    
        return nums; // Return the modified array
    }
    

/********************************************************************************************************************************/
// A unique type que - where in we are using the pointers adjacent

    // Time:- O(n)      ||      Space:- O(1)

// Notice how we are using two pointers keeping the pointers at adjacent element

    public int[] sortArrayByParity2(int[] nums) {
        int n = nums.length;  // Get the length of the array
        int i = 0, j = 1;  // i starts at even index, j starts at odd index

        while (i < n && j < n) {  // Ensure i and j do not exceed array bounds
            if (nums[i] % 2 != 0 && nums[j] % 2 == 0) {  
                // If nums[i] is odd (should be even) and nums[j] is even (should be odd), swap them
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                
                // Move both pointers forward to check the next pair
                i += 2;  
                j += 2;
            } else if (nums[i] % 2 == 0) {  
                // If nums[i] is already even (correct position), move i forward
                i += 2;
            } else if (nums[j] % 2 != 0) {  
                // If nums[j] is already odd (correct position), move j forward
                j += 2;
            }
        }
        return nums;  // Return the sorted array
    }


/********************************************************************************************************************************/

    public int[] sortArrayByParityII(int[] nums) {
        return sortArrayByParity2(nums);
    }
}
// @lc code=end

