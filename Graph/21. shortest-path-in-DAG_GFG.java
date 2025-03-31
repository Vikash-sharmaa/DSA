package Graph;

import java.util.*;

/*
     1. Do a Topological Sort on the graph
     2. Take the nodes out of the stack and relax the edges
     
     Finding the shortest path to a vertex is easy if you already know the shortest paths to all the vertices that can precede it.
     Finding the longest path to a vertex in DAG is easy if you already know the longest path to all the vertices that can precede it.
     Processing the vertices in topological order ensures that by the time you get to a vertex, you've already processed all the vertices that can precede it.

     Dijkstra's algorithm is necessary for graphs that can contain cycles, because they can't be topologically sorted.
*/

// Class to represent an edge from a node to its neighbor along with the edge weight
class Edge {
    int dest;   // destination node
    int weight; // weight of the edge

    Edge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }
}

class Solution {

    // Step 1: Perform DFS to get the Topological Sort of the graph
    void topologicalSort(int node, List<List<Edge>> graph, boolean[] visited, Deque<Integer> topoStack) {
        visited[node] = true; // mark the current node as visited

        // visit all its neighbors
        for (Edge edge : graph.get(node)) {
            if (!visited[edge.dest]) { // if neighbor is not visited
                topologicalSort(edge.dest, graph, visited, topoStack); // recursively visit neighbor
            }
        }

        // after visiting all neighbors, add this node to the stack (postorder)
        topoStack.offerLast(node);
    }

    // Step 2: Compute shortest paths using topological order
    int[] computeShortestPaths(int numVertices, List<List<Edge>> graph, Deque<Integer> topoStack) {
        int[] distance = new int[numVertices]; // array to store shortest distances

        // initialize all distances as infinity initially
        for (int i = 0; i < numVertices; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        distance[0] = 0; // source node (node 0) has distance 0

        // process nodes in the order defined by topological sort
        while (!topoStack.isEmpty()) {
            int currentNode = topoStack.pollLast(); // get the next node to process

            // only process if the node is reachable from the source
            if (distance[currentNode] != Integer.MAX_VALUE) {
                // visit all adjacent nodes (relaxation step)
                for (Edge edge : graph.get(currentNode)) {
                    int neighbor = edge.dest;
                    int weight = edge.weight;

                    // if the current path offers a shorter path, update distance
                    if (distance[currentNode] + weight < distance[neighbor]) {
                        distance[neighbor] = distance[currentNode] + weight;
                    }
                }
            }
        }

        // for nodes that are unreachable, set distance to -1
        for (int i = 0; i < numVertices; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                distance[i] = -1;
            }
        }

        return distance; // return the computed shortest distances
    }

    // Step 3: Main function
    public int[] shortestPath(int numVertices, int numEdges, int[][] edges) {
        // Step 3.1: Build the adjacency list representation of the graph
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            graph.add(new ArrayList<>());
        }

        // Step 3.2: Add all edges to the graph
        for (int i = 0; i < numEdges; i++) {
            int u = edges[i][0]; // source node
            int v = edges[i][1]; // destination node
            int w = edges[i][2]; // weight
            graph.get(u).add(new Edge(v, w)); // add the edge u --> v with weight w
        }

        // Step 3.3: Prepare for Topological Sort
        boolean[] visited = new boolean[numVertices]; // visited array for DFS
        Deque<Integer> topoStack = new ArrayDeque<>(); // stack to store topological order

        // Step 3.4: Perform DFS for all unvisited nodes to get the topological order
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i]) {
                topologicalSort(i, graph, visited, topoStack);
            }
        }

        // Step 4: Compute Shortest Paths using the Topological Order
        return computeShortestPaths(numVertices, graph, topoStack);
    }
}

