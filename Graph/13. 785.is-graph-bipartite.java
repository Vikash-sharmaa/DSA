/*
 * @lc app=leetcode id=785 lang=java
 *
 *  If a graph is linear - it will always be bipartite
 *  If a graph has even circle - it will be bipartite
 *  If graph has odd circle - not bipartite
 */



 /*
    Time Complexity: O(V+E), where V is the number of nodes and E is the number of edges. Each node and edge is processed once in BFS.
    Space Complexity: O(V) due to the color array and BFS queue storage.
  */
import java.util.*;

class Solution {

/*************************************************************************************************************/

    // Function to check if the graph is bipartite using BFS
    private boolean checkBipartiteBFS(int start, int[][] graph, int[] color) {
        Deque<Integer> queue = new ArrayDeque<>(); // Queue for BFS traversal
        
        queue.offerLast(start); // Start BFS from the given node
        color[start] = 0; // Assign the first color (0) to the starting node

        while (!queue.isEmpty()) { // BFS traversal
            int front = queue.pollFirst(); // Get the front node from the queue
            
            for (int neighbor : graph[front]) { // Traverse all adjacent nodes
                if (color[neighbor] == -1) { // If the neighbor is uncolored
                    color[neighbor] = 1 - color[front]; // Assign the opposite color
                    queue.offerLast(neighbor); // Push neighbor to queue for further processing
                } else if (color[neighbor] == color[front]) { 
                    // If a neighbor has the same color as the current node, graph is not bipartite
                    return false;
                }
            }
        }
        return true; // If BFS completes without conflicts, the graph is bipartite
    }
/*************************************************************************************************************/

    private boolean checkBipartiteDFS(int start, int currentColor, int[][] graph, int[] color) {
        // Assign the current color to the starting node
        color[start] = currentColor;

        // Iterate through all the neighbors of the current node
        for (int neighbor : graph[start]) {
            // If the neighbor has not been colored yet
            if (color[neighbor] == -1) {
                // Recursively check if the neighbor can be colored with the opposite color
                if (checkBipartiteDFS(neighbor, 1 - currentColor, graph, color) == false) {
                    return false; // If it can't be colored properly, the graph is not bipartite
                }
            } else if (color[neighbor] == currentColor) {
                // If the neighbor is already colored with the same color, the graph is not bipartite
                return false;
            }
        }
        // If all neighbors can be colored properly, return true
        return true;
    }

/*************************************************************************************************************/

    // Function to check if the entire graph is bipartite
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n]; // Array to store colors of nodes
        Arrays.fill(color, -1); // Initialize all nodes as uncolored (-1)


/*      ------------>   For BFS      <---------------
        // Check each disconnected component of the graph
        for (int i = 0; i < n; i++) {
            if (color[i] == -1) { // If a node is unvisited
                if (!checkBipartiteBFS(i, graph, color)) { 
                    // If any component is not bipartite, return false
                    return false;
                }
            }
        }
        return true; // If all components are bipartite, return true
    }
    */


    // ------------>   For DFS      <---------------
    for (int i = 0; i < n; i++) {
        if (color[i] == -1) { // If a node is unvisited
            if (!checkBipartiteDFS(i, 0, graph, color)) { 
                // If any component is not bipartite, return false
                return false;
            }
        }
    }
    return true; // If all components are bipartite, return true
}

/*************************************************************************************************************/
}

// @lc code=end

