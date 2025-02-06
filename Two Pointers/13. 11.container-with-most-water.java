/*
 * @lc app=leetcode id=11 lang=java
 *
 * [11] Container With Most Water
 */

// @lc code=start
class Solution {

/********************************************************************************************************************************/

    // Time:- O(n^2)        ||      Space:- O(1)

    public int maxArea1(int[] height) {
        int n = height.length;  // Get the number of elements in the height array
        int maxiArea = -1;  // Initialize maximum area with a very small value
    
        // Step 1: Use two nested loops to check all possible container pairs
        for (int left = 0; left < n - 1; left++) {  // Iterate through the array (left boundary of the container)
            for (int right = left + 1; right < n; right++) {  // Iterate through the remaining elements (right boundary)
                
                // Step 2: Calculate the area formed between indices left and right
                int currentArea = Math.min(height[left], height[right]) * (right - left);  
                // Height is determined by the smaller of the two lines
                // Width is the difference between indices (right - left)
    
                // Step 3: Update maxiArea if the current area is larger
                maxiArea = Math.max(maxiArea, currentArea);  
            }
        }
    
        // Step 4: Return the maximum area found
        return maxiArea;  
    }

/********************************************************************************************************************************/
    

    // Time:- O(n)        ||      Space:- O(1)

    public int maxArea2(int[] height) {
        int left = 0, right = height.length - 1;  // Initialize two pointers at both ends of the array
        int maxiArea = -1;  // Variable to store the maximum area found
    
        // Step 1: Use the two-pointer approach to find the maximum area
        while (left < right) {  
            // Step 2: Calculate the area using the current left and right boundary
            maxiArea = Math.max(maxiArea, Math.min(height[left], height[right]) * (right - left));
    
            // Step 3: Move the pointer with the smaller height to potentially find a taller boundary
            if (height[left] < height[right]) {  
                left++;  // Move left pointer to the right
            } else {  
                right--;  // Move right pointer to the left
            }
        }
    
        // Step 4: Return the maximum area found
        return maxiArea;  
    }    

/********************************************************************************************************************************/


    public int maxArea(int[] height) {
        return maxArea1(height);
    }
}
// @lc code=end

