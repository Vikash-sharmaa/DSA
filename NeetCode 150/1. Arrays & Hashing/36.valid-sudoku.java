/*  https://leetcode.com/problems/valid-sudoku/

 * @lc app=leetcode id=36 lang=java
 *
 * [36] Valid Sudoku
 */

// @lc code=start
class Solution {

// Time: O(81) - O(1)          Space: O(1)

    boolean isValid(int row, int col, char[][] board, char ch) {
        for (int i = 0; i < 9; i++) {
            // Check if `ch` already exists in the current row
            if (board[row][i] == ch) return false;
    
            // Check if `ch` already exists in the current column
            if (board[i][col] == ch) return false;
    
            // Calculate the starting row and column of the 3x3 sub-grid
            int subRow = 3 * (row / 3) + i / 3;
            int subCol = 3 * (col / 3) + i % 3;
    
            // Check if `ch` already exists in the 3x3 sub-grid
            if (board[subRow][subCol] == ch) return false;
        }
        return true; // If no conflicts, return true
    }
    
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {   // Loop through each row
            for (int j = 0; j < 9; j++) { // Loop through each column
                if (board[i][j] != '.') {  // If cell is not empty
                    char ch = board[i][j];  // Store the current value
                    board[i][j] = '.';  // Temporarily empty the cell to avoid self-check
    
                    // If the number is not valid, return false
                    if (!isValid(i, j, board, ch)) return false;
    
                    board[i][j] = ch; // Restore the value after validation
                }
            }
        }
        return true; // If no violations, return true (valid Sudoku)
    }
    
}
// @lc code=end

