/*
 * @lc app=leetcode id=1901 lang=java
 *
 * [1901] Find a Peak Element II
 */

// @lc code=start
class Solution {
    
/**************************************************************************************************************************/

    public static int[] findPeakGrid_Naive(int[][] mat) {
        int n = mat.length;         // number of rows
        int m = mat[0].length;      // number of columns
    
        // Traverse every cell
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
    
                // Get neighbors
                int top    = (i - 1 >= 0) ? mat[i - 1][j] : -1;
                int bottom = (i + 1 < n) ? mat[i + 1][j] : -1;
                int left   = (j - 1 >= 0) ? mat[i][j - 1] : -1;
                int right  = (j + 1 < m) ? mat[i][j + 1] : -1;
    
                // Check if current cell is a peak
                if(mat[i][j] > top && mat[i][j] > bottom && mat[i][j] > left && mat[i][j] > right) {
                    return new int[]{i, j}; // return its coordinates
                }
            }
        }
    
        return new int[]{-1, -1}; // if no peak exists
    }

/**************************************************************************************************************************/
    
    // Function to find the index of the maximum element in a given column
    private static int findMaxIndex(int[][] mat, int rows, int col) {
        int maxValue = -1;
        int index = -1;
        for (int i = 0; i < rows; i++) {
            if (mat[i][col] > maxValue) {
                maxValue = mat[i][col];
                index = i;
            }
        }
        return index;
    }

    // Main function to find the peak element's coordinates
    public static int[] findPeakGrid(int[][] mat) {
        int n = mat.length;        // number of rows
        int m = mat[0].length;     // number of columns

        int low = 0, high = m - 1; // search space is columns [0...m-1]

        while (low <= high) {
            int mid = low + (high - low) / 2;   // find the mid column
            int maxRowIndex = findMaxIndex(mat, n, mid);   // get the row with max element in mid column

            int left = (mid - 1 >= 0) ? mat[maxRowIndex][mid - 1] : -1;  // left neighbor or -1 if out of bounds
            int right = (mid + 1 < m) ? mat[maxRowIndex][mid + 1] : -1;  // right neighbor or -1 if out of bounds

            // Check if current is peak
            if (mat[maxRowIndex][mid] > left && mat[maxRowIndex][mid] > right) {
                return new int[]{maxRowIndex, mid};   // found peak
            } 
            else if (mat[maxRowIndex][mid] < left) {
                high = mid - 1;   // search in left half
            } 
            else {
                low = mid + 1;    // search in right half
            }
        }

        return new int[]{-1, -1};   // if no peak found (edge case)
    }

/**************************************************************************************************************************/

}
// @lc code=end

