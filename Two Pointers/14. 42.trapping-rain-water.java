/*
 * @lc app=leetcode id=42 lang=java
 *
 * [42] Trapping Rain Water
 */

// @lc code=start
class Solution {
/********************************************************************************************************************************/

// Time:- O(n^2)            Space:-O(1)
    // Helper function to find the maximum height to the left of index `i`
    int maxHeightLeft(int[] height, int i) {
        int maxHeight = 0;  

        // Traverse from index `i` to the leftmost index (0)
        while (i >= 0) {
            maxHeight = Math.max(maxHeight, height[i--]);  // Update maxHeight with the maximum value found
        }
        return maxHeight;  // Return the maximum height on the left
    }

    // Helper function to find the maximum height to the right of index `i`
    int maxHeightRight(int[] height, int i) {
        int n = height.length;
        int maxHeight = 0;  

        // Traverse from index `i` to the rightmost index (n-1)
        while (i < n) {
            maxHeight = Math.max(maxHeight, height[i++]);  // Update maxHeight with the maximum value found
        }
        return maxHeight;  // Return the maximum height on the right
    }

    // Function to compute the trapped water
    public int trap1(int[] height) {
        int n = height.length;
        int waterTrapped = 0;  // Variable to store the total trapped water

        // Iterate through each index to calculate the trapped water at that position
        for (int i = 0; i < n; i++) {
            // Find the minimum of max heights on left and right, then subtract the current height
            waterTrapped += Math.min(maxHeightLeft(height, i), maxHeightRight(height, i)) - height[i];
        }
        
        return waterTrapped;  // Return the total trapped water
    }

/********************************************************************************************************************************/

// Time:- O(n)            Space:-O(n)

    public int trap2(int[] height) {
        int n = height.length;
        int waterTrapped = 0;  // Variable to store the total trapped water

        // Arrays to store the maximum height to the left (`pre`) and to the right (`suf`) for each index
        int[] pre = new int[n];  // `pre[i]` stores the highest bar from `0` to `i`
        int[] suf = new int[n];  // `suf[i]` stores the highest bar from `i` to `n-1`

        // Step 1: Compute the `pre` array (maximum height from the left)
        pre[0] = height[0];  // First element remains the same (no elements to the left)
        for (int i = 1; i < n; i++) {
            pre[i] = Math.max(pre[i - 1], height[i]);  // Store the highest bar from the left side
        }

        // Step 2: Compute the `suf` array (maximum height from the right)
        suf[n - 1] = height[n - 1];  // Last element remains the same (no elements to the right)
        for (int i = n - 2; i >= 0; i--) {
            suf[i] = Math.max(suf[i + 1], height[i]);  // Store the highest bar from the right side
        }

        // Step 3: Compute the trapped water at each index
        for (int i = 0; i < n; i++) {
            waterTrapped += Math.min(pre[i], suf[i]) - height[i];  
            // Water trapped at index `i` = Min(left max, right max) - height[i]
        }

        return waterTrapped;  // Return the total trapped water
    }


/********************************************************************************************************************************/
 
// Time:- O(n)            Space:-O(1)
    public int trap3(int[] height) {
        int n = height.length;
        int waterTrapped = 0;  // Variable to store total trapped water

        // Two pointers approach: `left` starts from the beginning, `right` starts from the end
        int left = 0, right = n - 1;

        // Variables to store the maximum heights encountered from both ends
        int maxHeightLeft = 0, maxHeightRight = 0;

        // Traverse the array using two pointers
        while (left < right) {
            if (height[left] < height[right]) {  
                // If left height is smaller, process left side
                if (height[left] > maxHeightLeft) {
                    maxHeightLeft = height[left];  // Update left max height
                } else {
                    // Water trapped at `left` = maxHeightLeft - current height
                    waterTrapped += maxHeightLeft - height[left];
                }
                left++;  // Move left pointer to the right
            } else {  
                // If right height is smaller or equal, process right side
                if (height[right] > maxHeightRight) {
                    maxHeightRight = height[right];  // Update right max height
                } else {
                    // Water trapped at `right` = maxHeightRight - current height
                    waterTrapped += maxHeightRight - height[right];
                }
                right--;  // Move right pointer to the left
            }
        }
        
        return waterTrapped;  // Return total trapped water
    }

/********************************************************************************************************************************/

    public int trap(int[] height) {
        return trap3(height);
    }
}
// @lc code=end

