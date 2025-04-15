package Graph;

import java.util.*;

class iPair {
    int first, second;

    iPair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

// Dikstra - dont work for negative weight or negative cycle in graph
// as at ant traveral it keeps on decreasing - we will fall for an infinte loop

class Solution {
    // Time: O(ElogV)
    // Function to find the shortest distance of all the vertices from the source vertex src.
    ArrayList<Integer> dijkstra(ArrayList<ArrayList<iPair>> adj, int src) {
        int n = adj.size(); // Total number of vertices in the graph
    
        // Initialize distance list with "infinite" values for all nodes
        ArrayList<Integer> dis = new ArrayList<>(Collections.nCopies(n, Integer.MAX_VALUE));
        
        dis.set(src, 0); // Distance to source is 0
    
        // Min-heap (priority queue) to store pairs of (distance, node)
        // Ensures the closest unvisited node is processed first
        PriorityQueue<iPair> pq = new PriorityQueue<>(
            (a, b) -> {
                if (a.first != b.first) {
                    return Integer.compare(a.first, b.first); // Compare based on 'first'
                } else {
                    return Integer.compare(a.second, b.second); // Tie-breaker: compare 'second'
                }
            }
);

        
        pq.offer(new iPair(0, src)); // Add the source node with distance 0
    
        while (!pq.isEmpty()) {
            // Extract the node with the smallest distance
            iPair curr = pq.poll();
            int dist = curr.first;   // Current known shortest distance to the node
            int node = curr.second;  // Current node being processed
    
            // Skip if this entry is outdated (we've already found a better path)
            if (dist > dis.get(node)) continue;
    
            // Explore all neighbors (adjacent nodes) of the current node
            for (iPair neighbor : adj.get(node)) {
                int edgeWeight = neighbor.first;     // Edge weight from current node to neighbor
                int adjNode = neighbor.second;       // Neighboring node
    
                // If going through the current node offers a shorter path to the neighbor
                if (dist + edgeWeight < dis.get(adjNode)) {
                    dis.set(adjNode, dist + edgeWeight); // Update the distance
                    pq.offer(new iPair(dist + edgeWeight, adjNode)); // Push the updated pair to priority queue
                }
            }
        }
    
        // After all nodes are processed, replace all unreachable distances with -1
        for (int i = 0; i < n; i++) {
            if (dis.get(i) == Integer.MAX_VALUE)
                dis.set(i, -1); // -1 signifies that the node is unreachable from the source
        }
    
        return dis; // Final list of shortest distances from source to all nodes
    }
    
}

