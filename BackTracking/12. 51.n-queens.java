/*
 * @lc app=leetcode id=51 lang=java
 *
 * 
 *  We are trying to place queen col by col while ensuring no 2 queens attacks each other 
 *  For each col , we are traversing earch row 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {

/*
    Time Complexity: O(N!) – Each row has N choices, but valid placements reduce over time, leading to approximately N! recursive calls.
    Space Complexity: O(N²) – The chessboard takes O(N²) space, and the recursion depth reaches O(N) in the worst case.
 */

    private boolean isValid(int row, int col, int n, List<StringBuilder> board) {
        int tempRow = row;
        int tempCol = col;
    
        // Check the left side of the current row (same row, previous columns)
        while (col >= 0) {
            if (board.get(row).charAt(col) == 'Q') return false; // A queen is already placed in this row
            col--;
        }
    
        // Restore original row and column values
        row = tempRow;
        col = tempCol;
    
        // Check the upper-left diagonal
        while (col >= 0 && row >= 0) {
            if (board.get(row).charAt(col) == 'Q') return false; // A queen is present in the diagonal
            col--;
            row--;
        }
    
        // Restore original row and column values
        row = tempRow;
        col = tempCol;
    
        // Check the lower-left diagonal
        while (col >= 0 && row < n) {
            if (board.get(row).charAt(col) == 'Q') return false; // A queen is present in the diagonal
            col--;
            row++;
        }
    
        return true; // No conflicts, it's a valid position
    }
    
    void solveNQueens(int col, int n, List<StringBuilder> board, List<List<String>> res) {
        // Base case: If all queens are placed (i.e., we reached the last column)
        if (col == n) {
            List<String> temp = new ArrayList<>();
            for (StringBuilder sb : board) temp.add(sb.toString()); // Convert board to list of strings
            res.add(temp); // Store the valid configuration
            return;
        }
    
        // Try placing a queen in each row of the current column
        for (int row = 0; row < n; row++) {
            // Check if placing a queen at (row, col) is safe
            if (isValid(row, col, n, board)) {
                board.get(row).setCharAt(col, 'Q'); // Place the queen
                solveNQueens(col + 1, n, board, res); // Recursively place the rest of the queens
                board.get(row).setCharAt(col, '.'); // Backtrack: Remove the queen for next iteration
            }
        }
    }
    
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>(); // Store the valid board configurations
    
        List<StringBuilder> board = new ArrayList<>(); // Represent the board as a list of StringBuilders
    
        // Initialize the board with empty spaces ('.')
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) sb.append(".");
            board.add(sb);
        }
    
        // Start placing queens from the first column
        solveNQueens(0, n, board, res);
    
        return res; // Return all valid board configurations
    }
    
}
// @lc code=end

