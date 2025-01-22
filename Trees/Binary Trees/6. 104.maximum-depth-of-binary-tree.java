package Trees;
/*
 * @lc app=leetcode id=104 lang=java
 *
 * Maximum Depth of Binary Tree || Height of Binary Tree
 */


class Solution {
    public int maxDepth(TreeNode root) {
        // Base case: If the current node is null, the depth is 0.
        if (root == null) return 0;
    
        // Recursively find the depth of the right subtree.
        int rightDepth = maxDepth(root.right);
    
        // Recursively find the depth of the left subtree.
        int leftDepth = maxDepth(root.left);
    
        // The depth of the current node is 1 (for the current node itself)
        // plus the maximum depth of its left and right subtrees.
        return 1 + Math.max(rightDepth, leftDepth);
    }
    
}
// @lc code=end

