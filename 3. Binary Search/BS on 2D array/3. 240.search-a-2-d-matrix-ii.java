/* https://leetcode.com/problems/search-a-2d-matrix-ii/

 * @lc app=leetcode id=240 lang=java
 *
 * [240] Search a 2D Matrix II
 */

 // each row sorted also each col sorted  - not whole matrix sorted

// @lc code=start
class Solution {
/**************************************************************************************************************************/
        // Time: O(n*m)

    public boolean searchMatrix_Naive(int[][] matrix, int target) {
        int rows=matrix.length;
        int cols=matrix[0].length;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(matrix[i][j]==target) return true;
            }
        }
        return false;
    }
/**************************************************************************************************************************/
        // Time: O(n+log(m))

        public boolean searchMatrix_Optimized(int[][] matrix, int target) {
            int rows = matrix.length;        // Number of rows
            int cols = matrix[0].length;     // Number of columns
        
            // Loop through each row
            for(int i = 0; i < rows; i++) {
                
                // Check if the target can possibly exist in this row
                // Condition: The target must be between the first and last element of this row (since rows are sorted)
                if(matrix[i][0] <= target && target <= matrix[i][cols - 1]) {
        
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
        // Time: O(n + m)

        public boolean searchMatrix(int[][] matrix, int target) {
            int rows = matrix.length;        
            int cols = matrix[0].length;     
        
            int row = 0;                     // Start from the first row (top)
            int col = cols - 1;              // Start from the last column (top-right corner)
        
            // Loop until we go out of the matrix boundaries
            while (row < rows && col >= 0) {
                if (matrix[row][col] == target) {   //  Found the target
                    return true;
                }
                else if (matrix[row][col] > target) { // If current element > target, move left
                    col--;                           // Because all elements to the left are smaller
                }
                else {                              // If current element < target, move down
                    row++;                           // Because all elements below are larger
                }
            }
        
            return false;  //  If loop finishes, target is not present
        }
        

/**************************************************************************************************************************/

}
// @lc code=end

