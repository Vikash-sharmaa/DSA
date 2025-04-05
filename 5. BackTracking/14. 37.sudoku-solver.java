/*
 * @lc app=leetcode id=37 lang=java
 *
 * [37] Sudoku Solver
 */

// @lc code=start
class Solution {

    // Function to check if placing 'ch' at board[row][col] is valid
    boolean isValid(char[][] board, int row, int col, int ch) {
        for (int i = 0; i < 9; i++) {
            // Check if 'ch' is already present in the same column
            if (board[i][col] == ch) return false;

            // Check if 'ch' is already present in the same row
            if (board[row][i] == ch) return false;

            // Check if 'ch' is present in the current 3x3 sub-box
            int subBoxRow = 3 * (row / 3) + i / 3;
            int subBoxCol = 3 * (col / 3) + i % 3;
            if (board[subBoxRow][subBoxCol] == ch) return false;
        }
        return true; // If no conflicts, placement is valid
    }

    // Backtracking function to solve the Sudoku
    boolean solve(char[][] board) {
        for (int i = 0; i < 9; i++) { // Traverse rows
            for (int j = 0; j < 9; j++) { // Traverse columns
                if (board[i][j] == '.') { // Find an empty cell
                    for (char c = '1'; c <= '9'; c++) { // Try placing digits 1 to 9
                        if (isValid(board, i, j, c)) { // Check if 'c' is valid at (i, j)
                            board[i][j] = c; // Place the digit

                            if (solve(board)) return true; // Recur to solve the rest of the board

                            board[i][j] = '.'; // Backtrack (undo the move)
                        }
                    }
                    return false; // No valid digit found, trigger backtracking
                }
            }
        }
        return true; // Successfully filled all empty cells
    }

    // Wrapper function to solve the Sudoku board
    public void solveSudoku(char[][] board) {
        solve(board); // Start solving from the initial board state
    }

}
// @lc code=end

