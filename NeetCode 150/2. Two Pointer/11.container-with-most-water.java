/*  https://leetcode.com/problems/container-with-most-water/description/

 * @lc app=leetcode id=11 lang=java
 *
 * [11] Container With Most Water
 */

// @lc code=start
class Solution {

/***********************************************************************************************************************************/
// Time:- O(n^2)        Space:- O(1)

    public int maxArea_1(int[] height) {
        int n = height.length;  // Get the number of vertical lines
        int maxAreaRes = 0;  // Variable to store the maximum water area found
    
        // Iterate through all possible pairs of lines (brute force approach)
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                // Calculate the area of water stored between lines at index i and j
                int currentArea = (j - i) * Math.min(height[i], height[j]);
                
                // Update maxAreaRes if the current area is larger
                maxAreaRes = Math.max(maxAreaRes, currentArea);
            }
        }
        
        return maxAreaRes;  // Return the maximum found area
    }

/***********************************************************************************************************************************/
// Time:- O(n)          Space:- O(1)

    public int maxArea_2(int[] height) {
        int n = height.length;  // Get the number of vertical lines
        int start = 0, end = n - 1;  // Two pointers: one at the beginning, one at the end
        int maxAreaRes = 0;  // Variable to store the maximum water area found

        // Use a two-pointer approach to find the maximum area efficiently
        while (start < end) {
            // Calculate the current area using the shorter height
            if (height[start] < height[end]) {  
                int currentArea = (end - start) * height[start];  
                maxAreaRes = Math.max(maxAreaRes, currentArea);  // Update max area if needed
                start++;  // Move the left pointer forward (since the shorter line is limiting)
            } else {
                int currentArea = (end - start) * height[end];
                maxAreaRes = Math.max(maxAreaRes, currentArea);  // Update max area if needed
                end--;  // Move the right pointer backward (since the shorter line is limiting)
            }
        }
        
        return maxAreaRes;  // Return the maximum found area
    }
/***********************************************************************************************************************************/


    public int maxArea(int[] height) {
        return maxArea_2(height);
    }
}
// @lc code=end

