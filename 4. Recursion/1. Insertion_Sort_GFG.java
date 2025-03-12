

class Solution {
    
/************************************************************************************************/

    // Time:- O(n^2)      ||      Space:- O(1)

    public void insertionSortIterative(int nums[]) {
        int n = nums.length; // Get the length of the array
    
        // Start iterating from the second element (index 1) because the first element is already "sorted" 
        // since we have to maintin the sorted and unsorted subparts in the same array
        for (int i = 1; i < n; i++) {
            int key = nums[i]; // The current element to be placed in the correct position
            int j = i - 1; // Start comparing with the previous element
    
            // Move elements that are greater than 'key' one position ahead
            while (j >= 0 && nums[j] > key) {
                nums[j + 1] = nums[j]; // Shift the element to the right
                j--; // Move one step back in the sorted part
            }
            nums[j + 1] = key; // Place the 'key' at its correct position
        }
    }
    

/************************************************************************************************/

            // Recursive
            
    // Time:- O(n^2)      ||      Space:- O(n)

    
    public void insert(int[] nums, int n, int last) {
        // Base case: If the array is empty or the last element is already in the correct position
        if (n == 0 || nums[n - 1] <= last) {
            nums[n] = last; // Place 'last' in the correct position
            return;
        }
    
        // Store the last sorted element temporarily
        int val = nums[n - 1];
    
        // Recursively call insert to shift elements until we find the right place for 'last'
        insert(nums, n - 1, last);
    
        // Place the stored element back after shifting
        nums[n] = val;
    }
    
    public void sort(int[] nums, int n) {
        // Base case: If only one element is left, it's already sorted
        if (n == 1) return;
    
        // Store the last element before sorting the remaining array
        int last = nums[n - 1];
    
        // Recursively sort the first (n-1) elements
        sort(nums, n - 1);
    
        // Insert the last element at its correct position
        insert(nums, n - 1, last);
    }

/************************************************************************************************/

    public void insertionSort(int nums[]) {
        sort(nums,nums.length);
    }
}
