package Trees.BST;

import Trees.TreeNode;

class Solution{
    
    static void floor(TreeNode root, int key, int[] res) {
        // If the current node is null, return as there is no more tree to search.
        if (root == null) return;

        // If the current node's value equals the key, it is the floor.
        if (root.val == key) {
            res[0] = root.val; // Update the result with the key itself.
            return; // Stop further traversal as the floor is found.
        }

        // If the current node's value is less than the key:
        if (root.val < key) {
            res[0] = root.val; // Update result as this is a potential floor.
            floor(root.right, key, res); // Continue searching in the right subtree.
        } else {
            // If the current node's value is greater than the key:
            floor(root.left, key, res); // Search the left subtree as no values in the right subtree can be smaller.
        }
    }

    static int floorInBST(TreeNode root, int key) {
        // Initialize the floor value as -1. This will remain -1 if no floor is found.
        int floor = -1;
    
        // Iterate through the tree until the root becomes null.
        while (root != null) {
            // If the current node's value equals the key, it is the floor.
            if (root.val == key) {
                floor = root.val; // Update the floor with the key itself.
                return floor; // Return immediately as the floor is found.
            }
    
            // If the current node's value is less than the key:
            if (root.val < key) {
                floor = root.val; // Update the floor to the current node's value.
                root = root.right; // Move to the right subtree to check for larger potential floor values.
            } else {
                // If the current node's value is greater than the key:
                root = root.left; // Move to the left subtree as no values in the right subtree can be smaller.
            }
        }
    
        // Return the final floor value. If no floor is found, it will remain -1.
        return floor;
    }
    

    public static int floor(TreeNode root, int key) {
        // Initialize the result array to store the floor value.
        int[] res = new int[]{-1}; // Default to -1 to indicate no floor found if the key is smaller than all values.

        // Call the helper function to find the floor.
        floor(root, key, res);

        // Return the result value.
        return res[0];
    }

}
