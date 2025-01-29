package Trees.BST;
import java.util.ArrayList;

import Trees.TreeNode;

class Solution {
    /**********************************************************************************************************************************************************/

    // Inorder traversal method (Method 1: Using ArrayList to store inorder traversal)

    void inorderTraversalMethod1(TreeNode root, ArrayList<Integer> inorder) {
        if (root == null) return; // Base case: If node is null, stop recursion

        inorderTraversalMethod1(root.left, inorder); // Recursively traverse left subtree
        inorder.add(root.val); // Add current node's value to the list
        inorderTraversalMethod1(root.right, inorder); // Recursively traverse right subtree
    }

    public int inorderPredecessorMethod1(TreeNode root, TreeNode x) {
        // Step 1: Perform an inorder traversal and store the result in a list
        ArrayList<Integer> inorder = new ArrayList<>();
        inorderTraversalMethod1(root, inorder);

        int predecessor = -1; // Default value if no predecessor is found

        // Step 2: Iterate through the inorder list to find the predecessor
        for (int i = 0; i < inorder.size(); i++) {
            if (x.val == inorder.get(i)) { // If the current node matches x
                if (i == 0) return -1; // If x is the first element, no predecessor exists
                else predecessor = inorder.get(i - 1); // Predecessor is the previous element in the list
            }
        }
        return predecessor; // Return the predecessor
    }

    /**********************************************************************************************************************************************************/

    // Method 2: Recursive approach without using extra space for an inorder list

    void inorderMethod2(TreeNode root, TreeNode x, int[] predecessor) {
        if (root == null) return; // Base case: If node is null, stop recursion

        // Recursively traverse the left subtree
        inorderMethod2(root.left, x, predecessor);

        // Check if the current node is a valid predecessor
        if (root.val < x.val) { // A valid predecessor should have value less than x
            predecessor[0] = root.val; // Update the predecessor
        }

        // Recursively traverse the right subtree
        inorderMethod2(root.right, x, predecessor);
    }

    public int inorderPredecessorMethod2(TreeNode root, TreeNode x) {
        // Initialize an array to store the predecessor
        int[] predecessor = new int[1];
        predecessor[0] = -1; // Default value if no predecessor is found

        // Perform the recursive traversal to find the predecessor
        inorderMethod2(root, x, predecessor);
        return predecessor[0]; // Return the predecessor
    }

    /**********************************************************************************************************************************************************/

    // Method 3: Iterative approach to find predecessor without recursion

    public int inorderPredecessor(TreeNode root, TreeNode x) {
        int predecessor = -1; // Default value if no predecessor is found

        // Traverse the BST iteratively
        while (root != null) {
            if (x.val <= root.val) {
                // If x.val is less than or equal to root.val, move to the left subtree
                root = root.left;
            } else {
                // If root.val is less than x.val, update the predecessor
                predecessor = root.val;
                root = root.right; // Move to the right subtree to look for a larger predecessor
            }
        }

        return predecessor; // Return the predecessor
    }
}
