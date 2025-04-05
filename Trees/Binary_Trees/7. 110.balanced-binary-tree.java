// https://leetcode.com/problems/balanced-binary-tree/description/

package Trees.Binary_Trees;
/*
 * @lc app=leetcode id=110 lang=java
 *
 * [110] Balanced Binary Tree
 */

 // Height balanced means - tree's left's height and right's height difference should be 1 or 0.

import Trees.TreeNode;

class Solution {

/****************************************************************************************************************************************/
    
int height(TreeNode root){
        if(root==null) return 0;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        return 1+Math.max(leftHeight, rightHeight);
    }

    // Time:- O(n^2)  ||  Space:- O(n) (Skewed Tree)
    public boolean isBalancedNaive(TreeNode root) {
        // Base case: If the current node is null, the tree is balanced (empty tree is always balanced).
        if (root == null) return true;

        // Calculate the height of the left subtree.
        int leftHeight = height(root.left);

        // Calculate the height of the right subtree.
        int rightHeight = height(root.right);

        // If the difference in height between the left and right subtrees is more than 1, the tree is unbalanced.
        if (Math.abs(leftHeight - rightHeight) > 1) return false;

        // Recursively check if the left subtree is balanced.
        boolean isLeftBalanced = isBalanced(root.left);

        // Recursively check if the right subtree is balanced.
        boolean isRightBalanced = isBalanced(root.right);

        // If either the left or right subtree is not balanced, return false.
        if (!isLeftBalanced || !isRightBalanced) return false;

        // If all checks pass, the tree is balanced.
        return true;
    }

/****************************************************************************************************************************************/

    // This method calculates the height of the tree while checking if it is balanced.
    public int heightModified(TreeNode root) {
        // Base case: if the node is null, return 0 as the height.
        if (root == null) return 0;

        // Recursively calculate the height of the left subtree.
        int leftHeight = heightModified(root.left);
        // Recursively calculate the height of the right subtree.
        int rightHeight = heightModified(root.right);

        // If the left or right subtree is unbalanced, propagate -1 to indicate imbalance.
        if (leftHeight == -1 || rightHeight == -1) return -1;

        // Check the balance condition: the difference in heights of left and right subtrees
        // should not exceed 1. If it does, return -1 to indicate imbalance.
        if (Math.abs(rightHeight - leftHeight) > 1) return -1;

        // If the current node and its subtrees are balanced, return the height of the tree
        // rooted at this node (1 + max of left and right subtree heights).
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // This method determines if a binary tree is balanced.
    public boolean isBalanced(TreeNode root) {
        // A null tree is considered balanced.
        if (root == null) return true;

        // Call the helper method to get the height of the tree or determine if it's unbalanced.
        int heightOfTree = heightModified(root);

        // If heightModified returns -1, the tree is unbalanced; otherwise, it is balanced.
        return heightOfTree != -1;
    }

/****************************************************************************************************************************************/

}
// @lc code=end

