import java.util.List;

class Solution {
    public int findKRotation(List<Integer> nums) {
        // Initialize result as maximum possible value to store the minimum found
        int res = Integer.MAX_VALUE;
        // Initialize index as -1, will hold the index of the minimum element (rotation count)
        int index = -1;
    
        // Standard binary search setup
        int start = 0, end = nums.size() - 1;
    
        // Perform binary search
        while (start <= end) {
            int mid = start + (end - start) / 2; // safe mid calculation
    
            // Case 1: Left half [start...mid] is sorted
            if (nums.get(start) <= nums.get(mid)) {
                // If nums[start] is smaller than current res, update res and index
                if (nums.get(start) < res) {
                    res = nums.get(start);
                    index = start; // store the index of the minimum found
                }
                // Since left half is sorted and already considered, move to the right half
                start = mid + 1;
            }
            
            // Case 2: Right half [mid...end] is sorted
            else if (nums.get(mid) <= nums.get(end)) {
                // If nums[mid] is smaller than current res, update res and index
                if (nums.get(mid) < res) {
                    res = nums.get(mid);
                    index = mid; // store the index of the minimum found
                }
                // Since right half is sorted and already considered, move to the left half
                end = mid - 1;
            }
        }
    
        // Finally return the index of the minimum element, which is the rotation count
        return index;
    }
}
