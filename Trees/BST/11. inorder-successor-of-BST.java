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
    
    public int inorderSuccessorMethod1(TreeNode root, TreeNode x) {
        // Step 1: Perform an inorder traversal and store the result in a list
        ArrayList<Integer> inorder = new ArrayList<>();
        inorderTraversalMethod1(root, inorder);
        
        int successor = -1; // Default value if no successor is found
        
        // Step 2: Iterate through the inorder list to find the successor
        for (int i = 0; i < inorder.size(); i++) {
            if (x.val == inorder.get(i)) { // If the current node matches x
                if (i == inorder.size() - 1) return -1; // If x is the last element, no successor exists
                else successor = inorder.get(i + 1); // Successor is the next element in the list
            }
        }
        return successor; // Return the successor
    }

/**********************************************************************************************************************************************************/
    
    // Method 2: Recursive approach without using extra space for an inorder list
    
    void inorderMethod2(TreeNode root, TreeNode x, int[] successor) {
        if (root == null) return; // Base case: If node is null, stop recursion
    
        // Recursively traverse the left subtree
        inorderMethod2(root.left, x, successor);
    
        // Check if the current node is a valid successor
        if (successor[0] == -1 && root.val > x.val) {
            successor[0] = root.val; // Update the successor
            return; // Stop further traversal once the successor is found
        }
    
        // Recursively traverse the right subtree
        inorderMethod2(root.right, x, successor);
    }

    public int inorderSuccessorMethod2(TreeNode root, TreeNode x) {
        // Initialize an array to store the successor
        int[] successor = new int[1];
        successor[0] = -1; // Default value if no successor is found
    
        // Perform the recursive traversal to find the successor
        inorderMethod2(root, x, successor);
        return successor[0]; // Return the successor
    }

/**********************************************************************************************************************************************************/
    
    // Method 3: Iterative approach to find successor without recursion

    public int inorderSuccessor(TreeNode root, TreeNode x) {
        int successor = -1; // Default value if no successor is found
        
        // Traverse the BST iteratively
        while (root != null) {
            if (x.val >= root.val) {
                // If x.data is greater than or equal to root.data, move to the right subtree
                root = root.right;
            } else {
                // If root.data is greater than x.data, update the successor
                successor = root.val;
                root = root.left; // Move to the left subtree to look for a smaller successor
            }
        }
        
        return successor; // Return the successor
    }
}