package Graph;

import java.util.ArrayList;

// Time Complexity: O(V + E)
// - We visit every vertex (V) exactly once and traverse all edges (E) in the graph during the DFS traversal.
// Space Complexity: O(V)
// - The space complexity includes the visited array (O(V)) and the recursive call stack, which can go up to O(V) in the worst case.

class Solution {
    
    // Helper function to perform Depth First Search (DFS)
    void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, ArrayList<Integer> res) {
        visited[node] = true; // Mark the current node as visited
        res.add(node); // Add the current node to the result list
        
        // Traverse all the neighbors of the current node
        for (Integer neighbor : adj.get(node)) {
            if (!visited[neighbor]) { // If the neighbor is not visited
                dfs(neighbor, adj, visited, res); // Perform DFS recursively on the neighbor
            }
        }
    }
    
    // Main function to return the DFS traversal of the graph
    public ArrayList<Integer> dfsOfGraph(ArrayList<ArrayList<Integer>> adj) {
        // Create a visited array to keep track of visited nodes
        boolean[] visited = new boolean[adj.size()];
        
        // Create a list to store the result of DFS traversal
        ArrayList<Integer> res = new ArrayList<>();
        
        // Start DFS from node 0 (assuming the graph is connected)
        dfs(0, adj, visited, res);
        
        return res; // Return the list containing DFS traversal
    }
}

