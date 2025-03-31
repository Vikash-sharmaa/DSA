import java.util.*;

class Solution {
    
/**************************************************************************************************************************/

    public static int median(int matrix[][], int m, int n) {
        List<Integer> lst = new ArrayList<>();

        // Traverse the matrix and
        // copy the elements to the list:
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                lst.add(matrix[i][j]);
            }
        }

        // Sort the list:
        Collections.sort(lst);
        return lst.get((m * n) / 2);
    }
    
/**************************************************************************************************************************/

    public static int median(int[][] matrix) {
        int n=matrix.length;
        int m=matrix[0].length;
        
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
    
        // Find min and max element of the matrix
        for(int i = 0; i < n; i++) {
            low = Math.min(low, matrix[i][0]);      // first element of each row (smallest)
            high = Math.max(high, matrix[i][m - 1]); // last element of each row (largest)
        }
    
        int desired = (n * m + 1) / 2;  // position of median
    
        while(low <= high) {
            int mid = low + (high - low) / 2;
    
            // Count how many numbers are less than or equal to mid
            int count = 0;
            for(int i = 0; i < n; i++) {
                count += countSmallerThanEqualToMid(matrix[i], mid);
            }
    
            if(count < desired) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
    
        return low; // low will be the median
    }
    
    // Counts how many numbers are <= mid in the sorted row
    private static int countSmallerThanEqualToMid(int[] row, int mid) {
        int low = 0, high = row.length - 1;
    
        while(low <= high) {
            int m = low + (high - low) / 2;
            if(row[m] <= mid) {
                low = m + 1;
            } else {
                high = m - 1;
            }
        }
    
        return low;
    }
/**************************************************************************************************************************/
}