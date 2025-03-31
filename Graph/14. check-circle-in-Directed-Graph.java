package Graph;



import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

class Solution {

/***********************************************************************************************************************************/

// If there would be a cycle in a Directed Graph -> Number of 0 inDegree nodes will not be equal to total Number of nodes we have.
    public boolean isCyclicTopoSort(ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size(); // Number of nodes
        int[] inDegree = new int[n]; // Array to store in-degree of each node
        
        // Step 1: Compute in-degree for each node
        for (int i = 0; i < n; i++) {
            for (int neighbor : adj.get(i)) {
                inDegree[neighbor]++; // Increment in-degree for each incoming edge
            }
        }

        // Step 2: Add all nodes with in-degree = 0 to the queue
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) { // Nodes with no dependencies
                queue.offerLast(i);
            }
        }

        // Step 3: Process nodes in topological order
        int count = 0; // Count of processed nodes
        while (!queue.isEmpty()) {
            int node = queue.pollFirst(); // Remove node from queue
            count++; // Increase processed node count
            
            // Reduce in-degree of all its neighbors
            for (int neighbor : adj.get(node)) {
                inDegree[neighbor]--; // Remove edge effect by decrementing in-degree
                
                if (inDegree[neighbor] == 0) { // If in-degree becomes 0, add to queue
                    queue.offerLast(neighbor);
                }
            }
        }

        // Step 4: Check if all nodes were processed
        return count != n; // If count < n, a cycle exists (some nodes couldn't be processed)
    }



/***********************************************************************************************************************************/

    // `pathVisited` keeps track of nodes visited in the current DFS path
    boolean isCyclicDFS(int start, ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] pathVisited) {
        visited[start] = true; // Mark the current node as visited
        pathVisited[start] = true; // Mark the current node as part of the current DFS path
        
        // Traverse all neighbors of the current node
        for (int neighbor : adj.get(start)) {
            if (!visited[neighbor]) { // If the neighbor is not visited
                if (isCyclicDFS(neighbor, adj, visited, pathVisited)) return true; // Recur for the neighbor
            } else if (pathVisited[neighbor]) { // If the neighbor is visited and part of the current path, cycle found
                return true;
            }
        }
        
        pathVisited[start] = false; // Remove the node from the current DFS path before backtracking
        return false; // No cycle detected
    }

    public boolean isCyclic(ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size(); // Number of nodes in the graph
        boolean[] visited = new boolean[n]; // Track visited nodes
        boolean[] pathVisited = new boolean[n]; // Track nodes in the current DFS path
        
        // Iterate through all nodes to ensure all components are checked
        for (int i = 0; i < n; i++) {
            if (!visited[i]) { // If the node is not visited yet, start DFS from it
                if (isCyclicDFS(i, adj, visited, pathVisited)) return true; // Cycle detected
            }
        }
        return false; // No cycle found in any component
    }
/***********************************************************************************************************************************/
}

