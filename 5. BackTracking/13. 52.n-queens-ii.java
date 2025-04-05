/* https://leetcode.com/problems/n-queens-ii/

 * @lc app=leetcode id=52 lang=java
 *
 * Same as N-Queen 
 */

// @lc code=start

/*
    Time Complexity:
        The worst-case time complexity is O(N!), as we try to place a queen in each column while ensuring no conflicts, leading to an N! recursive branching factor.
    Space Complexity:
        The auxiliary space is O(N²) for storing the board, but it can be optimized to O(N) using arrays instead of StringBuilder.
        The recursive stack space in the worst case is O(N) due to backtracking.
 */

import java.util.ArrayList;
import java.util.List;

class Solution {

    boolean isValid(int row, int col, int n, List<StringBuilder> board) {
        int tempRow = row;
        int tempCol = col;
    
        // Check the left side of the current row (no two queens should be in the same row)
        while (col >= 0) {
            if (board.get(row).charAt(col) == 'Q') return false; // Queen found in the same row
            col--;
        }
    
        // Restore row and column values
        row = tempRow;
        col = tempCol;
    
        // Check the upper-left diagonal (no two queens should be in the same diagonal)
        while (row >= 0 && col >= 0) {
            if (board.get(row).charAt(col) == 'Q') return false; // Queen found on upper-left diagonal
            col--;
            row--;
        }
    
        // Restore row and column values
        row = tempRow;
        col = tempCol;
    
        // Check the lower-left diagonal (no two queens should be in the same diagonal)
        while (row < n && col >= 0) {
            if (board.get(row).charAt(col) == 'Q') return false; // Queen found on lower-left diagonal
            col--;
            row++;
        }
    
        // If no conflicts were found, it's a valid position to place a queen
        return true;
    }
    
    void placeNQueens(int col, int n, List<StringBuilder> board, int[] count) {
        // If all queens are placed, increment the count and return
        if (col == n) {
            count[0] += 1;
            return;
        }
    
        // Try placing a queen in each row of the current column
        for (int row = 0; row < n; row++) {
            if (isValid(row, col, n, board)) { // Check if placing a queen here is valid
                board.get(row).setCharAt(col, 'Q'); // Place the queen
                placeNQueens(col + 1, n, board, count); // Recur for the next column
                board.get(row).setCharAt(col, '.'); // Backtrack: Remove the queen
            }
        }
    }
    
    public int totalNQueens(int n) {
        List<StringBuilder> board = new ArrayList<>();
    
        // Initialize the board with empty cells ('.')
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(".");
            }
            board.add(sb);
        }
    
        int[] count = new int[]{0}; // To store the total number of valid solutions
        placeNQueens(0, n, board, count); // Start solving from the first column
        return count[0]; // Return the total count of valid queen placements
    }    

}
// @lc code=end

