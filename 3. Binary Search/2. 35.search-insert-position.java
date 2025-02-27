/*
 * @lc app=leetcode id=35 lang=java
 *
 * Lower bound gives the first element that is ≥ target.
 * Upper bound gives the first element that is > target.
 */

// @lc code=start
class Solution {


/************************************************************************************************/

    // Time:- O(n)      ||      Space:- O(1)

    public int searchInsert1(int[] nums, int target) {
        int n = nums.length;  // Get the length of the array
        int probable = 0;  // Variable to store the probable insert position
    
        // Iterate through the array
        for (int i = 0; i < n; i++) {
            if (nums[i] == target) {  // If the target is found at index i
                probable = i;  // Store the index where target is found
                break;  // Exit the loop as we found the target
            } else if (nums[i] < target) {  // If the current element is smaller than target
                probable = i + 1;  // Update probable position to i+1 (where target can be inserted)
            }
        }
        
        return probable;  // Return the position where target should be inserted or found
    }
    

/************************************************************************************************/

    // Time:- O(log(n))      ||      Space:- O(1))

    public int searchInsert2(int[] nums, int target) {
        int n = nums.length;  // Get the length of the array
        int start = 0, end = n - 1;  // Initialize the search boundaries
        int probable = 0;  // Variable to store the probable insert position
    
        while (start <= end) {  // Perform binary search
            int mid = start + (end - start) / 2;  // Calculate the middle index to prevent overflow
    
            if (nums[mid] == target) return mid;  // If target is found, return its index
            
            else if (nums[mid] < target) {  // If target is greater, search in the right half
                probable = mid + 1;  // Update probable position (target would be inserted at mid+1)
                start = mid + 1;  // Move the start pointer to narrow the search
            } 
            
            else {  // If target is smaller, search in the left half
                end = mid - 1;  // Move the end pointer to narrow the search
            }
        }
    
        return probable;  // Return the probable insert position if target is not found
    }
    
/************************************************************************************************/

    // Time:- O(log(n))      ||      Space:- O(1))

    public int searchInsert3(int[] nums, int target) {
        int n = nums.length;  // Get the length of the array
        int start = 0, end = n - 1;  // Initialize binary search boundaries
    
        while (start <= end) {  // Continue searching while the window is valid
            int mid = start + (end - start) / 2;  // Compute mid index (avoiding overflow)
    
            if(nums[mid]>=target) {  
                // If target is smaller than mid element, move search to the left half
                end = mid - 1;
            }else{  
                // If target is greater than mid element, move search to the right half
                start = mid + 1;
            } 
            
            
        }
    
        // Since start always points to the first index where target should be inserted, it naturally serves as the correct answer.
        // This behavior is equivalent to the "lower bound" in binary search:
        // Lower bound: The smallest index where target or a larger number appears.

        return start;  // If target is not found, return 'start' as the insertion index
    }
    


/************************************************************************************************/

    public int searchInsert(int[] nums, int target) {
        return searchInsert3(nums, target);
    }
}
// @lc code=end

