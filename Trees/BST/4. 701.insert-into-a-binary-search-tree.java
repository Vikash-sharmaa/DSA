package Trees.BST;
/*
 * @lc app=leetcode id=701 lang=java
 *
 * [701] Insert into a Binary Search Tree
 */

 import Trees.TreeNode;

class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root==null){
            return new TreeNode(val);
        }

        if(root.val>val){
            root.left = insertIntoBST(root.left, val);
        }
        if(root.val<val){
            root.right = insertIntoBST(root.right,val);
        }
        
        return root;
    }
}
// @lc code=end

