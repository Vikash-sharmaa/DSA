/* https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/

 * @lc app=leetcode id=1752 lang=java
 *
 * [1752] Check if Array Is Sorted and Rotated
 */

// @lc code=start
class Solution {

/**********************************************************************************************************/
    // Time :- O(n^2)       Space :- O(1)

    public boolean checkBruteForce(int[] nums) {
        int n = nums.length;
    
        for (int r = 0; r < n; r++) { // Try all rotations
            if (isSorted(nums)) return true; // Check if the current rotation is sorted
            rotateArray(nums); // Rotate the array by one position
        }
    
        return false; // No rotation resulted in a sorted array
    }
    
    // Function to check if the array is sorted
    private boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) return false; // If not sorted, return false
        }
        return true; // If no disorder found, return true
    }
    
    // Function to rotate the array by one position to the left
    private void rotateArray(int[] arr) {
        int n = arr.length;
        int first = arr[0];
    
        for (int i = 0; i < n - 1; i++) {
            arr[i] = arr[i + 1]; // Shift all elements left
        }
        
        arr[n - 1] = first; // Place first element at last
    }


/**********************************************************************************************************/
    
    // Time:- O(n)   Space - O(1)
    public boolean check(int[] nums) {
        int n = nums.length;
    
        // If the array has 0, 1, or 2 elements, it's always considered sorted (even if rotated)
        if (n <= 2) return true;
    
        int count = 0; // Count the number of times a drop (decreasing order) occurs
    
        // Traverse the array to count drops in sorted order
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] > nums[i + 1]) count++; // If a decreasing pair is found, increment count
        }
    
        // Check the circular rotation condition: last element should not be greater than the first
        if (nums[n - 1] > nums[0]) count++;
    
        // If at most one drop is found, the array is a rotated sorted array
        return count <= 1;
    }
/**********************************************************************************************************/
    
}
// @lc code=end

