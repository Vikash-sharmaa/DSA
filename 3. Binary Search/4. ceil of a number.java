class Solution{

    private static int ceil(int nums[], int key) {
        int n = nums.length;  // Get the length of the array
        int start = 0, end = n - 1;  // Initialize search boundaries
    
        int ceil = -1;  // Variable to store the smallest element >= key
    
        while (start <= end) {  // Binary search loop
            int mid = start + (end - start) / 2;  // Calculate mid index to avoid overflow
    
            if (key == nums[mid]) return nums[mid];  // If key matches mid, return immediately
            else if (key < nums[mid]) {
                ceil = nums[mid];  // Update ceil to the current mid (smallest possible >= key)
                end = mid - 1;  // Move search to the left half
            } else {
                start = mid + 1;  // Move search to the right half
            }
        }
    
        return ceil;  // Return the final ceil value
    }      

}