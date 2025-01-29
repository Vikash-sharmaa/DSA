package Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

class Solution {
    // Function to return Breadth First Traversal of given graph.
    // Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges.
    // Space Complexity: O(V), where V is the number of vertices.
    public ArrayList<Integer> bfsOfGraph(int n, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> res = new ArrayList<>(); // Result list to store BFS traversal
        
        Deque<Integer> queue = new ArrayDeque<>(); // Queue for BFS traversal
        boolean[] visited = new boolean[n]; // Boolean array to track visited nodes
    
        // Start BFS from node 0
        queue.offerLast(0);
        visited[0] = true;
    
        while (!queue.isEmpty()) {
            int front = queue.pollFirst(); // Get the front node from the queue
            res.add(front); // Add it to the result list
    
            // Traverse all the neighbors of the current node
            for (Integer neighbor : adj.get(front)) {
                if (!visited[neighbor]) { // If the neighbor is not visited
                    queue.offerLast(neighbor); // Add it to the queue
                    visited[neighbor] = true; // Mark it as visited
                }
            }
        }
        return res; // Return the BFS traversal result
    }
}
