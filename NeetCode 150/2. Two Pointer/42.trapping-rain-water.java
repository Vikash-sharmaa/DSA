/*  https://leetcode.com/problems/trapping-rain-water/description/

 * @lc app=leetcode id=42 lang=java
 *
 * [42] Trapping Rain Water
 */

// @lc code=start
class Solution {

/***********************************************************************************************************************************/
    // Time:- O(n^2)        Space:- O(1)

    int maxToLeft(int i,int[] height){
        int maxi = height[i];
        while(i>=0){
            maxi = Math.max(maxi, height[i]);
            i--;
        }
        return maxi;
    }

    int maxToRight(int i,int[] height){
        int maxi = height[i];
        while(i<height.length){
            maxi = Math.max(maxi, height[i]);
            i++;
        }
        return maxi;
    }

    public int trap_1(int[] height) {
        int waterTrapped = 0;  // Stores the total water trapped
    
        // Iterate through each index to calculate trapped water at that position
        for (int i = 0; i < height.length; i++) {
            // Water trapped at index i = min(max height to the left, max height to the right) - height[i]
            waterTrapped += Math.min(maxToLeft(i, height), maxToRight(i, height)) - height[i];
        }
    
        return waterTrapped;  // Return total trapped water
    }
    

/***********************************************************************************************************************************/
    // Time:- O(n)        Space:- O(n)

    public int trap_2(int[] height) {
        int n = height.length;
        int waterTrapped = 0; // Stores total amount of trapped water
    
        // Arrays to store the maximum height to the left and right of each index
        int[] maxHeightToLeft = new int[n];
        int[] maxHeightToRight = new int[n];
    
        // Initialize first and last elements
        maxHeightToLeft[0] = height[0]; // First element has no left neighbor, so it's the max itself
        maxHeightToRight[n - 1] = height[n - 1]; // Last element has no right neighbor, so it's the max itself
    
        // Precompute the maximum height to the left of each index
        for (int i = 1; i < n; i++) {
            maxHeightToLeft[i] = Math.max(maxHeightToLeft[i - 1], height[i]);
        }
    
        // Precompute the maximum height to the right of each index
        for (int i = n - 2; i >= 0; i--) {
            maxHeightToRight[i] = Math.max(maxHeightToRight[i + 1], height[i]);
        }
    
        // Calculate the trapped water for each index
        for (int i = 0; i < n; i++) {
            // Water trapped at index i = min(left max, right max) - height[i]
            waterTrapped += Math.min(maxHeightToLeft[i], maxHeightToRight[i]) - height[i];
        }
    
        return waterTrapped; // Return total trapped water
    }
    

/***********************************************************************************************************************************/
    // Time:- O(n)        Space:- O(1)

    public int trap_3(int[] height) {
        int n = height.length;
        int waterTrapped = 0;  // Stores the total water trapped
        int maxHeightToLeft = 0, maxHeightToRight = 0;  // Keeps track of max heights on left & right
        int left = 0, right = n - 1;  // Two pointers, one from the left and one from the right
    
        // Use two-pointer technique to traverse the height array
        while (left < right) {
            if (height[left] < height[right]) {  // Left side is the limiting factor
                if (height[left] > maxHeightToLeft) {
                    maxHeightToLeft = height[left];  // Update max height from the left
                } else {
                    waterTrapped += maxHeightToLeft - height[left];  // Add trapped water
                }
                left++;  // Move left pointer forward
            } else {  // Right side is the limiting factor
                if (height[right] > maxHeightToRight) {
                    maxHeightToRight = height[right];  // Update max height from the right
                } else {
                    waterTrapped += maxHeightToRight - height[right];  // Add trapped water
                }
                right--;  // Move right pointer backward
            }
        }
    
        return waterTrapped;  // Return total trapped water
    }
    
/***********************************************************************************************************************************/

    public int trap(int[] height) {
        return trap_3(height);
    }
    

/***********************************************************************************************************************************/

}
// @lc code=end

