package Trees.BST;
/*
 * @lc app=leetcode id=235 lang=java
 *
 * [235] Lowest Common Ancestor of a Binary Search Tree
 */

// @lc code=start

import Trees.TreeNode;

/**
    1. one way is to go with finding LCA using BT LCA way. - O(n)
    2. finding LCA below way: O(h)
            * if both are lesser than root - move left.
            * if both are greater than root - move right.
            * else we have found a node from where the way is getting splitted - this is the LCA.
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null) return null;

        if((p.val < root.val) && (q.val < root.val)) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if((p.val > root.val) && (q.val > root.val)){
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }
}
// @lc code=end

