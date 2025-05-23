package Graph;
/*  https://leetcode.com/problems/number-of-islands/description/

 * @lc app=leetcode id=200 lang=java
 // if asked diagonal check for (row+1,col-1) , (row+1,col+1), (row-1,col+1), (row-1,col-1)
 // we could also do this using DFS
 */

// @lc code=start

/*
    Time Complexity: O(m×n), where m is the number of rows and n is the number of columns. Each cell is visited once.
    Space Complexity: O(m×n) for the visited array and BFS queue in the worst case.
 */
import java.util.ArrayDeque;
import java.util.Deque;

class Pair{
    int i;
    int j;
    Pair(int i,int j){
        this.i=i;
        this.j=j;
    }
}
class Solution {
    int[][] dirs = {{0,-1},{-1,0},{0,1},{1,0}};

    boolean isValid(int newRow,int newCol,int m,int n,boolean[][] visited,char[][] grid){
        return newRow>=0 && newRow<m && newCol>=0 && newCol<n && visited[newRow][newCol]==false && grid[newRow][newCol]=='1';
    }
    
    // Helper method to perform BFS traversal from a given starting cell
    void bfs(Pair start, char[][] grid, boolean[][] visited) {
        int m = grid.length; // Number of rows in the grid
        int n = grid[0].length; // Number of columns in the grid
        Deque<Pair> queue = new ArrayDeque<>(); // Queue for BFS traversal
        queue.offerLast(start); // Add the starting cell to the queue
        visited[start.i][start.j] = true; // Mark the starting cell as visited

        // Perform BFS traversal
        while (!queue.isEmpty()) {
            Pair front = queue.pollFirst(); // Dequeue the front element
            int row = front.i;
            int col = front.j;

            // Explore the four possible directions (up, down, left, right)
            
            for(int[] dir : dirs){
                int newRow   = row+dir[0];
                int newCol = col+dir[1];

                if(isValid(newRow, newCol, m, n, visited, grid)){
                    visited[newRow][newCol]=true;
                    queue.add(new Pair(newRow, newCol));
                }
            }
        }
    }


    // Main method to count the number of islands in the given grid
    public int numIslands(char[][] grid) {
        int m = grid.length; // Number of rows in the grid
        int n = grid[0].length; // Number of columns in the grid

        boolean[][] visited = new boolean[m][n]; // Array to track visited cells

        int count = 0; // Counter to keep track of the number of islands
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If the cell is '1' (land) and hasn't been visited, it's a new island
                if (grid[i][j] == '1' && !visited[i][j]) {
                    count++; // Increment the island count
                    bfs(new Pair(i, j), grid, visited); // Perform BFS to mark all connected land as visited
                }
            }
        }
        return count; // Return the total number of islands
    }

}
// @lc code=end


/*
    void dfs(int i,int j,int m,int n,char[][] grid,boolean[][] vis){
        vis[i][j]=true;
        for(int[] dir : dirs){
            int newI = i+dir[0];
            int newJ = j+dir[1];
            if(newI>=0 && newI<m && newJ>=0 && newJ<n && grid[newI][newJ]=='1' && !vis[newI][newJ]){
                dfs(newI,newJ,m,n,grid,vis);
            }
        }
        
    }
 */
