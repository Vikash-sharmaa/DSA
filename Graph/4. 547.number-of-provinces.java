package Graph;
/*  https://leetcode.com/problems/number-of-provinces/description/

 * @lc app=leetcode id=547 lang=java
 *
 * [547] Number of Provinces === Number of connected components of a graph
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {

    public int findCircleNum(int[][] isConnected) {
        // Number of nodes (cities) in the graph
        int n = isConnected.length;

        // Create an adjacency list representation of the graph
        List<List<Integer>> adjList = new ArrayList<>();
        
        // Initialize the adjacency list with empty lists for each node
        for (int i = 0; i < n; i++) adjList.add(new ArrayList<>());

        // Populate the adjacency list from the adjacency matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // If there is a connection between city i and city j, and it's not self-loop
                if (isConnected[i][j] == 1 && i != j) {
                    adjList.get(i).add(j); // Add j to i's adjacency list
                }
            }
        }

        // Counter for the number of connected components (provinces)
        int count = 0;

        // Array to track whether a node has been visited
        boolean[] visited = new boolean[n];

        // Iterate over all nodes
        for (int i = 0; i < n; i++) {
            // If a node hasn't been visited, it represents a new province
            if (!visited[i]) {
                count++; // Increment the province counter
                dfs(i, adjList, visited); // Perform DFS to mark all nodes in this province as visited
            }
        }

        return count; // Return the total number of provinces
    }


    // Helper method to perform DFS traversal
    private void dfs(int node, List<List<Integer>> adjList, boolean[] visited) {
        visited[node] = true; // Mark the current node as visited

        // Traverse all neighbors of the current node
        for (Integer neighbor : adjList.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adjList, visited); // Recursively visit unvisited neighbors
            }
        }
    }
    
}
// @lc code=end

