/* https://leetcode.com/problems/search-a-2d-matrix/description/

 * @lc app=leetcode id=74 lang=java
 *
 * [74] Search a 2D Matrix
 */ 
//each row sorted

// @lc code=start
class Solution {
/**************************************************************************************************************************/
        // Time: O(n*m)

    public boolean searchMatrix_Naive(int[][] matrix, int target) {
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                // If the current element matches the target, return true
                if(matrix[i][j] == target) return true;
            }
        }
        // If target is not found after checking all elements, return false
        return false;
    }

/**************************************************************************************************************************/
        // Time: O(n+log(m))

        public boolean searchMatrix_Optimized(int[][] matrix, int target) {
            int n = matrix.length;        // Number of rows
            int m = matrix[0].length;     // Number of columns
        
            // Loop through each row
            for(int i = 0; i < n; i++) {
                
                // Check if the target can possibly exist in this row
                // Condition: The target must be between the first and last element of this row (since rows are sorted)
                if(matrix[i][0] <= target && target <= matrix[i][m - 1]) {
        
                    // Perform binary search on the current row
                    if(binarySearch(matrix[i], target)) 
                        return true; // If found, return true immediately
                }
            }
        
            // If target is not found in any row, return false
            return false; 
        }

        public boolean binarySearch(int[] nums,int target){
            int start=0,end=nums.length-1;
            while(start<=end){
                int mid=start+(end-start)/2;
    
                if(nums[mid]==target) return true;
                else if(nums[mid]>target) end=mid-1;
                else start=mid+1;
            }
            return false;
        }
        
/**************************************************************************************************************************/

    // Time: O(log(n*m))
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;        // Total rows
        int cols = matrix[0].length;     // Number of columns
    
        int start = 0, end = rows * cols - 1;   // Treat the matrix as a virtual 1D array
    
        while (start <= end) {
            int mid = start + (end - start) / 2;
    
            int row = mid / cols;   // find the corresponding row
            int col = mid % cols;   // find the corresponding column
    
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] < target) start = mid + 1;  
            else end = mid - 1;                                 
        }
    
        return false;
    }
        
/**************************************************************************************************************************/
}
// @lc code=end

