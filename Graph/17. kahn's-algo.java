package Graph;

import java.util.*;

/*
    Kahn's Algorithm is a BFS-based approach for Topological Sorting in a Directed Acyclic Graph (DAG). 
    It works by iteratively removing nodes with zero in-degree (no incoming edges) and updating the in-degree of their neighbors.
 */

 
class Solution {

    // Helper function to perform BFS (Kahn's Algorithm)
    static ArrayList<Integer> bfs(Deque<Integer> queue, ArrayList<ArrayList<Integer>> adj, int[] inDegree) {
        ArrayList<Integer> res = new ArrayList<>(); // Stores the topological order

        while (!queue.isEmpty()) { // Process all nodes with in-degree = 0
            int front = queue.pollFirst(); // Remove node from queue
            res.add(front); // Add node to the result list

            // Reduce in-degree of all its neighbors (outgoing edges)
            for (int neighbor : adj.get(front)) {
                inDegree[neighbor]--; // Remove edge effect by decrementing in-degree

                // If in-degree becomes zero, add neighbor to queue
                // We only process a node when all its dependencies are processed.
                if (inDegree[neighbor] == 0) {
                    queue.offerLast(neighbor);
                }
            }
        }
        return res; // Return topological order
    }

    // Function to perform Topological Sorting using Kahn's Algorithm
    static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size(); // Number of nodes
        
        Deque<Integer> queue = new ArrayDeque<>(); // Queue to process nodes with in-degree = 0
        int[] inDegree = new int[n]; // Array to store in-degree of each node

        // Step 1: Calculate in-degree for each node
        for (int i = 0; i < n; i++) {
            for (int neighbor : adj.get(i)) {
                inDegree[neighbor]++; // Increment in-degree for each incoming edge
            }
        }

        // Step 2: Add nodes with in-degree = 0 to the queue
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) { // If no incoming edge, add to queue
                queue.offerLast(i);
            }
        }

        // Step 3: Perform BFS to get topological sorting
        return bfs(queue, adj, inDegree);
    }
}
