package Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * 
 * Time Complexity: O(V + E)  
 * - Each node and edge are processed once in BFS, leading to O(V + E).
 * 
 * Space Complexity: O(V)  
 * - `visited` array takes O(V).  
 * - `queue` stores at most O(V) nodes in the worst case.
 */
 
 
 class NodeParentPair {
    int node;   // The current node
    int parent; // The parent node in the BFS traversal

    NodeParentPair(int node, int parent) {
        this.node = node;
        this.parent = parent;
    }
}



class Solution {

/********************************************************************************************************************/

    /*
     * Performs BFS from the given starting node to detect cycles.
     */
    private boolean BFS(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int start) {
        // Queue to store (node, parent) pairs
        Deque<NodeParentPair> queue = new ArrayDeque<>();
        queue.offerLast(new NodeParentPair(start, -1)); // Start BFS from 'start' with no parent (-1)
        visited[start] = true; // Mark the starting node as visited

        while (!queue.isEmpty()) {
            NodeParentPair front = queue.pollFirst(); // Get the front node from queue
            int node = front.node; 
            int parent = front.parent; 

            // Traverse all adjacent nodes of the current node
            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) { // If neighbor is not visited, mark and enqueue it
                    queue.offerLast(new NodeParentPair(neighbor, node)); 
                    visited[neighbor] = true;
                } else if (neighbor != parent) { 
                    // If the neighbor is visited and it's not the parent, a cycle is detected
                    return true;
                }
            }
        }
        return false; // No cycle found in this component
    }

/********************************************************************************************************************/
    
    /*
     * Performs DFS from the given starting node to detect cycles.
     */
     
     private boolean DFS(ArrayList<ArrayList<Integer>> adj, boolean[] visited, int start, int parent) {
        visited[start] = true; // Mark the current node as visited
    
        for (int neighbor : adj.get(start)) {
            if (!visited[neighbor]) {
                if (DFS(adj, visited, neighbor, start)) { // Pass the current node as the new parent
                    return true; // Cycle detected in recursion
                }
            } else if (neighbor != parent) {
                return true; // If neighbor is visited and it's not the parent, cycle detected
            }
        }
    
        return false; // No cycle found
    }


/********************************************************************************************************************/

    /*
     * Checks for a cycle in an undirected graph.
     */
    public boolean isCycle(ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size(); // Number of nodes
        boolean[] visited = new boolean[n]; // Track visited nodes

        // Check for cycles in all components of the graph
        for (int i = 0; i < n; i++) {
            if (!visited[i]) { // If node is not visited, start DFS
                if (DFS(adj, visited, i,-1)) { 
                    return true; // Cycle found
                }
            }
        }
        return false; // No cycles detected in any component
    }

/********************************************************************************************************************/

}
