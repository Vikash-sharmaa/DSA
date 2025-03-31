package Graph;

import java.util.*;

/**********************************************************************************************************************************/

/*

    🚨 When is the Alien Dictionary Order NOT Possible?
        The alien dictionary order is not possible in the following cases:

        1️⃣ Cycle in the Graph (Contradictory Order)
            📌 Example:

            a → b
            b → c
            c → a  ❌ (Cycle detected!)
            Explanation:

            If a cycle exists in the dependency graph, then no valid topological order exists.
            This means some letters contradict each other, making an order impossible.

        2️⃣ Prefix Rule Violation
            📌 Example:

            abc
            ab
            Explanation:

            Here, abc comes before ab, but ab is a prefix of abc.
            This is invalid because a shorter word cannot come after a longer word that starts the same way!
            Correct case: ab should come before abc.

        3️⃣ Conflicting Character Comparisons
            📌 Example:

            a → b
            a → c
            b → c
            c → b  ❌ (Conflict: `b` before `c` and `c` before `b`)
            Explanation:

            If two characters must be placed before each other, then the order cannot be determined.

        4️⃣ Disjoint Graph Components
            📌 Example:

            a → b
            c → d
            Explanation:

            If separate independent groups of letters exist with no connecting information, then the order is ambiguous.

 */

/**********************************************************************************************************************************/

class Solution {

/**********************************************************************************************************************************/
    
// Function to perform Topological Sorting using Kahn's Algorithm (BFS)
    private List<Integer> topoSort(int V, List<List<Integer>> adj) {
        int[] indegree = new int[V]; // Array to store the in-degree of each node

        // Calculate in-degrees for all nodes
        for (int i = 0; i < V; i++) {
            for (int neighbor : adj.get(i)) {
                indegree[neighbor]++; // Increase in-degree for each directed edge
            }
        }

        // Queue to store nodes with in-degree = 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) { // If in-degree is 0, add to queue
                queue.add(i);
            }
        }

        List<Integer> topo = new ArrayList<>(); // List to store topological order
        while (!queue.isEmpty()) {
            int node = queue.poll(); // Remove and process the front node
            topo.add(node); // Add to topological order

            // Reduce in-degree of all adjacent nodes
            for (int neighbor : adj.get(node)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) { // If in-degree becomes 0, add to queue
                    queue.add(neighbor);
                }
            }
        }

        return topo; // Return topological order
    }

    // Function to determine the order of characters in the alien dictionary
    public String findOrder(String[] dict, int n, int k) {
        // Step 1: Create adjacency list for K unique characters
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            adj.add(new ArrayList<>());
        }

        // Step 2: Build the directed graph from the given dictionary
        for (int i = 0; i < n - 1; i++) {
            String s1 = dict[i];     // First word
            String s2 = dict[i + 1]; // Next word
            int len = Math.min(s1.length(), s2.length());

            for (int j = 0; j < len; j++) {
                if (s1.charAt(j) != s2.charAt(j)) { 
                    // Found first mismatching character, create edge s1[j] -> s2[j]
                    adj.get(s1.charAt(j) - 'a').add(s2.charAt(j) - 'a');
                    break; // Stop after first mismatch
                }
            }
        }

        // Step 3: Perform Topological Sorting to get the character order
        List<Integer> topo = topoSort(k, adj);
        
        // Step 4: Convert topological order (integer values) to characters
        StringBuilder ans = new StringBuilder();
        for (int ch : topo) {
            ans.append((char) (ch + 'a')); // Convert int to corresponding character
        }

        return ans.toString(); // Return the final order of characters
    }

/**********************************************************************************************************************************/

}


