package Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {

    // Space :- O(n^2)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of nodes (n) and edges (m)
        int n = sc.nextInt();
        int m = sc.nextInt();

        // Initialize adjacency matrix
        int[][] adj = new int[n + 1][n + 1]; // Matrix size should be [n+1][n+1]


        // Input edges
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(); // Input node u
            int v = sc.nextInt(); // Input node v

            adj[u][v] = 1; // Set the edge u -> v
            adj[v][u] = 1; // Since the graph is undirected, set the edge v -> u
        }

        // Close the scanner
        sc.close();


        // Print the adjacency matrix (optional, for testing purposes)
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(adj[i][j] + " ");
            }
            System.out.println();
        }
    }

    void adjacencyList(){
        Scanner sc = new Scanner(System.in);

        // Input number of nodes (n) and edges (m)
        int n = sc.nextInt();
        int m = sc.nextInt();
        // Initialize adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        // Input edges for adjacency list
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(); // Input node u
            int v = sc.nextInt(); // Input node v

            adjList.get(u).add(v); // Add edge u -> v
            adjList.get(v).add(u); // Since the graph is undirected, add edge v -> u
        }

        // Print the adjacency list (optional, for testing purposes)
        for (int i = 1; i <= n; i++) {
            System.out.print(i + ": ");
            for (int j : adjList.get(i)) {
            System.out.print(j + " ");
            }
            System.out.println();
        }
    }
    

}

