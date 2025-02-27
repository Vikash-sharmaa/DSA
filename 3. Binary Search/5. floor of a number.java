class Solution{
    
    public static int floor(int[] nums, int key) {
        int n = nums.length;  // Get the length of the array
        int floor = -1;  // Variable to store the largest element <= key (floor)
        int start = 0, end = n - 1;  // Initialize search boundaries
    
        while (start <= end) {  // Binary search loop
            int mid = start + (end - start) / 2;  // Calculate mid index to avoid overflow
    
            if (nums[mid] == key) return nums[mid];  // If key matches mid, return it immediately
            else if (nums[mid] > key) {
                end = mid - 1;  // Move search to the left half since mid is too large
            } else {
                floor = nums[mid];  // Update floor to the current mid (largest possible <= key)
                start = mid + 1;  // Move search to the right half
            }
        }
    
        return floor;  // Return the final floor value
    }
    
}
