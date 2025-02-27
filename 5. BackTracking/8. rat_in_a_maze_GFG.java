import java.util.ArrayList;

/*
    Time Complexity: O(4^(n^2)) (Each cell has up to 4 choices in the worst case)
    Space Complexity: O(n^2) (For the visited matrix + recursion stack)
 */

class Solution {
    
    // Helper function to check if the current cell is a valid move
    boolean isValid(ArrayList<ArrayList<Integer>> maze, int row, int col, int n, int[][] visited) {
        return row >= 0 && row < n && col >= 0 && col < n  // Check boundaries of the maze
            && maze.get(row).get(col) == 1  // Check if the cell is open (1) and not a wall (0)
            && visited[row][col] == 0;  // Check if the cell is not already visited
    }
    
    // Recursive function to find all possible paths in a maze
    void findPath(ArrayList<ArrayList<Integer>> maze, int row, int col, int n, int[][] visited, StringBuilder op, ArrayList<String> res) {
        
        // Base Case: If we reach the bottom-right corner, store the path and return
        if (row == n - 1 && col == n - 1) {
            res.add(op.toString());  // Store the formed path
            return;
        }
        
        // Mark the current cell as visited to avoid cycles
        visited[row][col] = 1;
        
        // **Try moving in all four possible directions: Down, Left, Right, Up**
        
        // Move **Down** (row + 1)
        if (isValid(maze, row + 1, col, n, visited)) {
            op.append("D");  // Add 'D' to the path
            findPath(maze, row + 1, col, n, visited, op, res);  // Recursive call
            op.deleteCharAt(op.length() - 1);  // Backtrack
        }
    
        // Move **Left** (col - 1)
        if (isValid(maze, row, col - 1, n, visited)) {
            op.append("L");  // Add 'L' to the path
            findPath(maze, row, col - 1, n, visited, op, res);
            op.deleteCharAt(op.length() - 1);  // Backtrack
        }
    
        // Move **Right** (col + 1)
        if (isValid(maze, row, col + 1, n, visited)) {
            op.append("R");  // Add 'R' to the path
            findPath(maze, row, col + 1, n, visited, op, res);
            op.deleteCharAt(op.length() - 1);  // Backtrack
        }
    
        // Move **Up** (row - 1)
        if (isValid(maze, row - 1, col, n, visited)) {
            op.append("U");  // Add 'U' to the path
            findPath(maze, row - 1, col, n, visited, op, res);
            op.deleteCharAt(op.length() - 1);  // Backtrack
        }
        
        // **Backtrack:** Unmark the cell before returning (to allow other paths)
        visited[row][col] = 0;
    }

  
    public ArrayList<String> findPath(ArrayList<ArrayList<Integer>> maze) {
        
        ArrayList<String> res=new ArrayList<>();
        int n=maze.size();
        
        int[][] visited = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                visited[i][j]=0;
            }
        }
        
        findPath(maze,0,0,n,visited,new StringBuilder(),res);
        
        return res;
        
        
    }
}
