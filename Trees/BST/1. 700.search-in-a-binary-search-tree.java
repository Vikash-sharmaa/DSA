package Trees.BST;
/*
 * @lc app=leetcode id=700 lang=java
 *
 * [700] Search in a Binary Search Tree
 * 
 * Time:- O(log(n))
 * Space:- O(log(n))
 */

import Trees.TreeNode;

class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        // Base case: If the current node is null, the value is not found in the tree.
        if(root == null) return null;
    
        // If the current node's value matches the target value, return the current node.
        if(root.val == val) return root;
    
        // If the target value is greater than the current node's value,
        // recursively search the right subtree (since all larger values are there in a BST).
        else if(root.val < val) return searchBST(root.right, val);
    
        // If the target value is smaller than the current node's value,
        // recursively search the left subtree (since all smaller values are there in a BST).
        else return searchBST(root.left, val); 
    }
    
}
// @lc code=end

