/* https://leetcode.com/problems/find-eventual-safe-states/description/

 * @lc app=leetcode id=802 lang=java
 *
 * [802] Find Eventual Safe States
 * 
 * Anyone who is a part of a cycle can never be a safe node
 * Anyone who is connected to a cycle (leading to a cycle) can never be a safe node
 */

// @lc code=start

import java.util.*;

class Solution {

/***************************************************************************************************************************************/
    
boolean cycleDFS(int start, List<List<Integer>> adj, boolean[] visited, boolean[] pathVisited, List<Integer> res) {
        visited[start] = true; // Mark the node as visited
        pathVisited[start] = true; // Mark the node as part of the current path

        // Traverse all neighbors of the current node
        for (int neighbor : adj.get(start)) {
            if (!visited[neighbor]) { // If the neighbor is not visited, do DFS
                if (cycleDFS(neighbor, adj, visited, pathVisited, res)) {
                    return true; // If a cycle is found, return true
                }
            } else if (pathVisited[neighbor]) { // If the neighbor is already in the current path, cycle detected
                return true;
            }
        }

        // If no cycle is found, this node is safe, so add it to the result
        res.add(start);
        pathVisited[start] = false; // Mark the node as no longer part of the current path

        return false; // No cycle detected
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<List<Integer>> adj = new ArrayList<>();
        int m = graph.length; // Number of nodes

        // Convert adjacency matrix representation to adjacency list
        for (int i = 0; i < m; i++) {
            adj.add(new ArrayList<>()); // Create a list for each node
            for (int neighbor : graph[i]) { // Add all outgoing edges
                adj.get(i).add(neighbor);
            }
        }

        boolean[] visited = new boolean[m]; // Track visited nodes
        boolean[] pathVisited = new boolean[m]; // Track nodes in the current DFS path
        List<Integer> res = new ArrayList<>(); // List to store safe nodes

        // Perform DFS from each node
        for (int i = 0; i < m; i++) {
            if (!visited[i]) { // Only call DFS if not already visited
                cycleDFS(i, adj, visited, pathVisited, res);
            }
        }

        Collections.sort(res); // Ensure the result is in sorted order
        return res; // Return the list of eventual safe nodes
    }
/***************************************************************************************************************************************/
    
    // => just reverse the nodes and follow kahn's algo

    public List<Integer> eventualSafeNodesBfs(int[][] graph) {
        int n = graph.length; // Number of nodes
        List<List<Integer>> revGraph = new ArrayList<>(); // Reverse graph adjacency list
        int[] inDegree = new int[n]; // Track incoming edges

        // Step 1: Initialize adjacency list
        for (int i = 0; i < n; i++) {
            revGraph.add(new ArrayList<>());
        }

        // Step 2: Reverse edges and calculate in-degrees
        for (int src = 0; src < n; src++) {
            for (int dest : graph[src]) {
                revGraph.get(dest).add(src); // Reverse edge direction
                inDegree[src]++; // Count outgoing edges in the original graph
            }
        }

        // Step 3: Push terminal nodes (original graph nodes with no outgoing edges)
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) { // These are terminal nodes in the original graph
                queue.offer(i);
            }
        }

        // Step 4: Process nodes in topological order
        List<Integer> safeNodes = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            safeNodes.add(node); // Safe nodes are processed

            // Remove edges and update in-degree
            for (int neighbor : revGraph.get(node)) {
                inDegree[neighbor]--; // Reduce the in-degree (reverse direction)
                if (inDegree[neighbor] == 0) { // If no outgoing edges left, it's safe
                    queue.offer(neighbor);
                }
            }
        }

        // Step 5: Return sorted list of safe nodes
        Collections.sort(safeNodes); // Required for lexicographical order
        return safeNodes;
    }
}
// @lc code=end

