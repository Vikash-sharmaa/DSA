package Trees.BST;

import Trees.TreeNode;

class Tree {
    // Recursive way

    // Function to return the ceil of given number in BST.
    void ceil(TreeNode root, int key, int[] res) {
        // If the current node is null, return as there is no more tree to search.
        if (root == null) return;
        
        // If the current node's value equals the key, update the result to the key itself.
        if (root.val == key) {
            res[0] = root.val;
            return; // Stop further traversal since the ceil is found.
        }
        
        // If the current node's value is greater than the key, it could be a potential ceil.
        if (root.val > key) {
            res[0] = root.val; // Update result with the current node's value.
            ceil(root.left, key, res); // Continue searching in the left subtree.
        } else {
            // If the current node's value is less than the key, search the right subtree.
            ceil(root.right, key, res);
        }
    }

    // Iterative way 
    
    static int ceilInBST(TreeNode root, int key) {
        // Initialize the ceil value as -1. This will remain -1 if no ceil is found.
        int ceil = -1;
        
        // Iterate through the tree until the root becomes null.
        while (root != null) {
            // If the current node's value equals the key, it is the ceil.
            if (root.val == key) {
                ceil = root.val; // Update the ceil with the key itself.
                return ceil; // Return immediately as the ceil is found.
            }
    
            // If the current node's value is greater than the key:
            if (root.val > key) {
                ceil = root.val; // Update the ceil to the current node's value.
                root = root.left; // Move to the left subtree to check for smaller potential ceil values.
            } else {
                // If the current node's value is less than the key:
                root = root.right; // Move to the right subtree as no values in the left subtree can be greater.
            }
        }
        
        // Return the final ceil value. If no ceil is found, it will remain -1.
        return ceil;
    }
    
    
    int findCeil(TreeNode root, int key) {
        // If the root is null, there is no tree to search; return -1 as the ceil doesn't exist.
        if (root == null) return -1;
    
        // Create an array to store the result since Java doesn't support pass-by-reference for primitives.
        int[] res = new int[1];
        res[0] = -1; // Initialize result to -1 to indicate no ceil found by default.
    
        ceil(root, key, res); // Call the helper function to find the ceil.
        return res[0]; // Return the result.
    }

}