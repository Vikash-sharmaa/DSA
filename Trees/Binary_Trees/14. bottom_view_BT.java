package Trees.Binary_Trees;

/*
 * 
   Top View: Adds a node only if the vertical line is not yet in the map.
   Bottom View: Updates the map entry for every node encountered at a vertical line.
 *
 * 
 * time and space complexities - O(N⋅logN).
 */
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Map;
import java.util.TreeMap;

class Tuple {
    TreeNode root;
    int verticalLine; // Tracks the vertical line of the node.
    
    // Default constructor.
    Tuple() {}

    // Constructor with parameters.
    Tuple(TreeNode root, int verticalLine) {
        this.root = root; // The current node in the tree.
        this.verticalLine = verticalLine; // The vertical line corresponding to the node.
    }
}

class Solution {
    // Function to return a list containing the bottom view of the given tree.
    public ArrayList<Integer> bottomView(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>(); // Result list to store the bottom view.
        Map<Integer, Integer> map = new TreeMap<>(); // Map to store the latest node at each vertical line.
        
        // Handle edge case where the tree is empty.
        if (root == null) return res;
        
        // Queue for BFS traversal, storing nodes and their vertical lines.
        Deque<Tuple> queue = new ArrayDeque<>();
        queue.offerLast(new Tuple(root, 0)); // Start with the root node at vertical line 0.
        
        // Perform BFS traversal.
        while (!queue.isEmpty()) {
            Tuple front = queue.pollFirst(); // Remove the front node from the queue.
            TreeNode currentNode = front.root; // Extract the current node.
            int verticalLine = front.verticalLine; // Extract its vertical line.
            
            // Add the current node to the map (overwriting ensures we keep the bottom-most node).
            map.put(verticalLine, currentNode.val);
            
            // If the current node has a left child, add it to the queue with vertical line -1.
            if (currentNode.left != null) {
                queue.offerLast(new Tuple(currentNode.left, verticalLine - 1));
            }
            
            // If the current node has a right child, add it to the queue with vertical line +1.
            if (currentNode.right != null) {
                queue.offerLast(new Tuple(currentNode.right, verticalLine + 1));
            }
        }
        
        // Add all the values from the map (bottom-most nodes) to the result list.
        res.addAll(map.values());
        return res; // Return the bottom view.
    }
}

