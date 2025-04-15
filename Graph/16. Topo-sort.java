package Graph;

import java.util.ArrayList;
import java.util.Deque;

// Topological Sorting of a Directed Acyclic Graph (DAG)
// Ensures that for every directed edge u -> v, node u appears before v in the ordering

class Solution {
    
    // Depth-First Search (DFS) function to explore nodes and push them onto the stack
    static void dfs(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Deque<Integer> stack) {
        visited[node] = true; // Mark the current node as visited
        
        // Explore all adjacent nodes (outgoing edges)
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) { // If the neighbor is unvisited, perform DFS
                dfs(neighbor, adj, visited, stack);
            }
        }
        /*
          
            When you do a post-order DFS, it means:
                First finish all the neighbors (dependencies),
                Then process the current node.
            By pushing the node onto the stack after exploring all its neighbors, you're ensuring that:
                Nodes with no outgoing edges (or all visited) are pushed first.
                Their dependents come later.
         */
        // everything would have been done - sequentially - as DFS is completed
        // Once all neighbors are explored, push the current node onto the stack
        // Parents (or prerequisites/dependencies) come before their children (dependents) in the final ordering.
        stack.offerLast(node);
    }

    // Function to perform Topological Sorting
    static ArrayList<Integer> topologicalSort(ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size(); // Number of nodes in the graph
        boolean[] visited = new boolean[n]; // Track visited nodes
        Deque<Integer> stack = new ArrayDeque<>(); // Stack to store the topological order

        // Perform DFS from each unvisited node
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, adj, visited, stack);
            }
        }

        // Extract nodes from the stack to get the topological order
        ArrayList<Integer> res = new ArrayList<>();
        while (!stack.isEmpty()) {
            res.add(stack.pollLast()); // Pop from stack and add to result
        }
        return res; // Return the topological order
    }

}
