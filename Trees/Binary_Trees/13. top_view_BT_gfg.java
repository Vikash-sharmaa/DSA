package Trees.Binary_Trees;


/*
 * The intuition behind the top view is similar to vertical order traversal, where nodes are grouped by their vertical lines (horizontal distances). 
 * However, instead of considering all nodes at each vertical line, the top view only includes the first node encountered for each line during a level-order traversal (BFS). 
 * This ensures that only the topmost nodes from each vertical line are visible. A TreeMap is used to maintain the vertical order, ensuring the nodes are retrieved in left-to-right order for the result.
 */

 /*
  * Time Complexity:
        BFS traversal visits each node once: O(N).
        Insertion into the TreeMap for vertical lines takes 
            O(logK) per node, where K is the number of vertical lines.
        Overall: O(NlogN) in the worst case (as K≤N).
    Space Complexity:
        Space for the queue and TreeMap is O(N) in total, as both are bounded by the number of nodes.


/*
    
    Top View: Adds a node only if the vertical line is not yet in the map.
    Bottom View: Updates the map entry for every node encountered at a vertical line.

 */




import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Map;
import java.util.TreeMap;

class Tuple {
    TreeNode root; // Represents the current node.
    int verticalLine; // Represents the vertical line (horizontal distance from root) of the node.
    Tuple() {}
    Tuple(TreeNode root, int verticalLine) {
        this.root = root;
        this.verticalLine = verticalLine;
    }
}

class Solution {
    // Function to return a list of nodes visible from the top view from left to right in a binary tree.
    static ArrayList<Integer> topView(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>(); // Stores the top view nodes.
        Map<Integer, Integer> map = new TreeMap<>(); // Maps vertical lines to the first node seen at that line (TreeMap ensures sorted order by vertical line).
        
        if (root == null) return res; // Edge case: if the tree is empty, return an empty list.
        
        // Queue to perform BFS while keeping track of each node's vertical line.
        Deque<Tuple> queue = new ArrayDeque<>();
        queue.offerLast(new Tuple(root, 0)); // Start BFS with the root node at vertical line 0.
        
        // BFS traversal to populate the top view.
        while (!queue.isEmpty()) {
            Tuple front = queue.pollFirst(); // Remove the node from the front of the queue.
            TreeNode currentNode = front.root; // The current node being processed.
            int verticalLine = front.verticalLine; // The vertical line of the current node.
            
            // If this vertical line hasn't been mapped yet, it means this is the first node visible from the top for this line.
            if (!map.containsKey(verticalLine)) {
                map.put(verticalLine, currentNode.val); // Store the node's value for this vertical line.
            }
            
            // Add the left child to the queue with a vertical line decreased by 1.
            if (currentNode.left != null) {
                queue.offerLast(new Tuple(currentNode.left, verticalLine - 1));
            }
            
            // Add the right child to the queue with a vertical line increased by 1.
            if (currentNode.right != null) {
                queue.offerLast(new Tuple(currentNode.right, verticalLine + 1));
            }
        }
        
        // Add all values from the sorted map to the result list. These represent the top view of the binary tree.
        res.addAll(map.values());
        return res; // Return the top view.
    }
}
