/*  https://leetcode.com/problems/flood-fill/description/

 * @lc app=leetcode id=733 lang=java
 *
 * Time Complexity: 
    O(m×n) in the worst case, where n are the number of rows and columns. Every pixel in the image is visited once.
   Space Complexity: 
    O(m×n) the worst case occurs when all pixels need to be colored, requiring a queue of size O(m×n) and a visited array of the same size.
 */

// @lc code=start

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

    boolean isValid(int row,int col,int m,int n,int startColor,boolean[][] visited,int[][] image){
        return row>=0 && row<m && col>=0 && col<n && !visited[row][col] && image[row][col]==startColor;
    }

/********************************************************************************************************************/

    void dfs(int[][] image, int m, int n, int row, int col, int startColor, int color, boolean[][] visited) {
        // Mark the current cell as visited
        visited[row][col] = true;
        
        // Change the color of the current pixel
        image[row][col] = color;
    
        // Explore the four possible directions (up, down, left, right)


        for(int[] dir : dirs){
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if(isValid(newRow,newCol,m,n,startColor,visited,image)){
                dfs(image, m, n, newRow, newCol, startColor, color, visited);
            }
        }
    }

    
/********************************************************************************************************************/


    // Helper method to perform BFS flood fill starting from (sr, sc)
    int[][] bfs(int[][] image, int sr, int sc, int color) {
        int m = image.length; // Number of rows in the image
        int n = image[0].length; // Number of columns in the image
        boolean[][] visited = new boolean[m][n]; // Visited array to track processed pixels

        int startColor = image[sr][sc]; // Store the original color of the starting pixel

        // Queue for BFS traversal
        Deque<Pair> queue = new ArrayDeque<>();
        queue.offerLast(new Pair(sr, sc)); // Add the starting pixel to the queue
        visited[sr][sc] = true; // Mark the starting pixel as visited

        // Perform BFS traversal
        while (!queue.isEmpty()) {
            Pair front = queue.pollFirst(); // Dequeue the front element
            int row = front.i;
            int col = front.j;
            
            // Change the color of the current pixel
            image[row][col] = color;

            // Explore the four possible directions (up, down, left, right)

            for(int[] dir : dirs){
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if(isValid(newRow,newCol,m,n,startColor,visited,image)){
                    visited[newRow][newCol] = true;
                    queue.add(new Pair(newRow, newCol));
                }
            }
        }
        return image; // Return the modified image
    }


/********************************************************************************************************************/

    // Public method that calls the BFS-or-DFS based flood fill function
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        //return bfs(image, sr, sc, color); // Start the BFS flood fill

        int m = image.length; // Number of rows in the image
        int n = image[0].length; // Number of columns in the image
        boolean[][] visited = new boolean[m][n]; // Visited array to track processed pixels
        int startColor = image[sr][sc];
        dfs(image,m,n, sr, sc, startColor, color, visited);
        return image;
    }

}
// @lc code=end

