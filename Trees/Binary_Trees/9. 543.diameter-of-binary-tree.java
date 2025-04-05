// https://leetcode.com/problems/diameter-of-binary-tree/description/
package Trees.Binary_Trees;
/*
 * @lc app=leetcode id=543 lang=java
 *
 * [543] Diameter of Binary Tree
 */

 /*
    Given the root of a binary tree, return the length of the diameter of the tree.

   The diameter of a binary tree is the length of the longest path between any two nodes in a tree. 
   This path may or may not pass through the root.

   The length of a path between two nodes is represented by the number of edges between them.

  */

import Trees.TreeNode;

// @lc code=start

class Solution {

    /****************************************************************************************************************************************/

    // This method calculates the height of a binary tree rooted at the given node.
    int height(TreeNode root) {
        // Base case: if the node is null, return 0 as the height.
        if (root == null) return 0;

        // Recursively calculate the height of the left subtree.
        int leftSubtreeHeight = height(root.left);
        // Recursively calculate the height of the right subtree.
        int rightSubtreeHeight = height(root.right);

        // Return the height of the current node, which is 1 plus the maximum of the
        // heights of its left and right subtrees.
        return 1 + Math.max(leftSubtreeHeight, rightSubtreeHeight);
    }

    
    // This helper method calculates the diameter of a binary tree using an array `dia`
    // to store the maximum diameter encountered during traversal.
    void helper(TreeNode root, int[] dia) {
        // Base case: if the node is null, there's nothing to process, so return.
        if (root == null) return;

        // Calculate the height of the left subtree.
        int leftSubtreeHeight = height(root.left);
        // Calculate the height of the right subtree.
        int rightSubtreeHeight = height(root.right);

        // Update the diameter if the path through the current node (sum of the heights
        // of the left and right subtrees) is greater than the current maximum diameter.
        dia[0] = Math.max(dia[0], leftSubtreeHeight + rightSubtreeHeight);

        // Recursively call the helper method for the left subtree.
        helper(root.left, dia);
        // Recursively call the helper method for the right subtree.
        helper(root.right, dia);
    }


    /****************************************************************************************************************************************/

    // A utility method to calculate the height of the tree while updating the diameter.
    int modifiedHeight(TreeNode root, int[] dia) {
        // Base case: if the node is null, return 0 as the height.
        if (root == null) return 0;

        // Recursively calculate the height of the left subtree.
        int leftSubtreeHeight = modifiedHeight(root.left, dia);
        // Recursively calculate the height of the right subtree.
        int rightSubtreeHeight = modifiedHeight(root.right, dia);

        // Update the diameter. The diameter at the current node is the sum of the heights
        // of the left and right subtrees. Update dia[0] if this value is greater than the
        // current maximum diameter.
        dia[0] = Math.max(dia[0], leftSubtreeHeight + rightSubtreeHeight);

        // Return the height of the current node, which is 1 plus the maximum of the
        // heights of its left and right subtrees.
        return 1 + Math.max(leftSubtreeHeight, rightSubtreeHeight);
    }

    /****************************************************************************************************************************************/
    
    // Main method to calculate the diameter of a binary tree.
    public int diameterOfBinaryTree(TreeNode root) {
        // Create an array to store the diameter. This allows the value to be updated
        // during recursive calls, as Java passes primitive types by value.
        int[] dia = new int[1];
        // Initialize the diameter to -1 (or 0 depending on conventions; here, -1 accounts
        // for edge-based diameter as the length is measured in terms of edges).
        dia[0] = -1;

        // Call the modifiedHeight method to calculate the height and update the diameter
        // in a single traversal of the tree.
        modifiedHeight(root, dia);

        // Return the maximum diameter found.
        return dia[0];
    }


}
// @lc code=end

