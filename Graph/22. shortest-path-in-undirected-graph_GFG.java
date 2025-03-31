package Graph;

import java.util.*;

/*
    You are given an adjacency list, adj of Undirected Graph having unit weight of 
    the edges, find the shortest path from src to all the vertex and if it is 
    unreachable to reach any vertex, then return -1 for that vertex.

        Examples :

        Input: adj[][] = [[1, 3], [0, 2], [1, 6], [0, 4], [3, 5], [4, 6], [2, 5, 7, 8], [6, 8], [7, 6]], src=0
        Output: [0, 1, 2, 1, 2, 3, 3, 4, 4]
 */
// Pair class to store (node, distance) information
class NodeDistancePair {
    int node;    // destination node
    int distance; // distance from source

    NodeDistancePair(int node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}

class Solution {

    // Step 1: Perform BFS to find the shortest path in an unweighted graph
    int[] bfsShortestPath(ArrayList<ArrayList<Integer>> adjacencyList, int source) {
        // queue for BFS traversal
        Deque<NodeDistancePair> queue = new ArrayDeque<>();
        queue.offerLast(new NodeDistancePair(source, 0)); // start from source node with distance 0

        int numberOfNodes = adjacencyList.size();
        
        // Step 2: Initialize all distances as infinity (unknown)
        int[] distances = new int[numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        
        distances[source] = 0; // distance to source itself is 0

        // Step 3: Standard BFS traversal
        while (!queue.isEmpty()) {
            NodeDistancePair current = queue.pollFirst();
            int currentNode = current.node;
            int currentDistance = current.distance;

            // Step 4: Traverse all neighbors of the current node
            for (int neighbor : adjacencyList.get(currentNode)) {
                // Step 5: Relax the edge if shorter distance is found
                if (currentDistance + 1 < distances[neighbor]) {
                    distances[neighbor] = currentDistance + 1;
                    queue.offerLast(new NodeDistancePair(neighbor, distances[neighbor]));
                }
            }
        }

        // Step 6: Mark unreachable nodes as -1
        for (int i = 0; i < numberOfNodes; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                distances[i] = -1;
            }
        }

        return distances; // return the shortest distances array
    }

    // Step 7: Main function to get shortest path distances from source node
    public int[] shortestPath(ArrayList<ArrayList<Integer>> adjacencyList, int source) {
        return bfsShortestPath(adjacencyList, source);
    }
}

