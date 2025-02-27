
/*
    Problem: Find the minimum difference element with the given target value in a sorted array.
    Input: a[] = [2, 5, 10, 12, 15], target = 6
    Output: 5
    Explanation: The difference between the target value '6' and '5' is the minimum.
*/




class Solution{
    private static int minDifference(int[] nums, int target) {
        int start = 0, end = nums.length - 1; // Initialize start and end pointers for binary search

        while (start <= end) { // Continue as long as the search space is valid
            int mid = start + (end - start) / 2; // Calculate the middle index to avoid potential overflow
            
            if (nums[mid] == target) {
                return 0; // If the target is found, the difference is 0, so return 0
            } else if (nums[mid] < target) {
                start = mid + 1; // If the middle element is less than the target, search in the right half
            } else {
                end = mid - 1; // If the middle element is greater than the target, search in the left half
            }
        }
        
        // At this point, the while loop terminates, meaning the target is not found in the array.
        // 'start' and 'end' pointers now represent the potential closest elements.
        // 'start' will be the index of the next greater element (ceiling) or the end of the array if target is largest.
        // 'end' will be the index of the previous smaller element (floor) or the beginning of the array if target is smallest.

        // Calculate the difference between the target and the elements at 'start' and 'end' indices.
        // Handle potential IndexOutOfBoundsException by checking if start and end are within array bounds.
        int diff1 = (start < nums.length) ? Math.abs(target - nums[start]) : Integer.MAX_VALUE;
        int diff2 = (end >= 0) ? Math.abs(target - nums[end]) : Integer.MAX_VALUE;

        return Math.min(diff1, diff2); // Return the minimum of the two differences
    }
}