package Trees.Binary_Trees;
/*
 * @lc app=leetcode id=226 lang=java
 *
 * [226] Invert Binary Tree
 */

// @lc code=start

class Solution {

/****************************************************************************************************************************************/

    public TreeNode invertTree1(TreeNode root) {
        // Base case: If the current node is null, return null (no inversion needed for an empty tree).
        if (root == null) return null;
    
        // Temporarily store the left subtree in a variable.
        TreeNode temp = root.left;
    
        // Swap the left and right subtrees of the current node.
        root.left = root.right;
        root.right = temp;
    
        // Recursively invert the left subtree (which is now the original right subtree).
        invertTree(root.left);
    
        // Recursively invert the right subtree (which is now the original left subtree).
        invertTree(root.right);
    
        // Return the current node after inversion.
        return root;
    }
    

/****************************************************************************************************************************************/


    void invert(TreeNode root) {
        // Base case: If the current node is null, there's nothing to invert.
        if (root == null) return;
    
        // Temporarily store the left subtree in a variable.
        TreeNode temp = root.left;
    
        // Swap the left and right subtrees of the current node.
        root.left = root.right;
        root.right = temp;
    
        // Recursively invert the left subtree (now the original right subtree).
        invertTree(root.left);
    
        // Recursively invert the right subtree (now the original left subtree).
        invertTree(root.right);
    }
    
    public TreeNode invertTree(TreeNode root) {
        // Call the helper method `invert` to perform the inversion starting from the root.
        invert(root);
    
        // Return the root of the inverted tree.
        return root;
    }

/****************************************************************************************************************************************/

}
// @lc code=end

