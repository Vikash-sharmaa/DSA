package Trees;
/*
 * @lc app=leetcode id=94 lang=java
 *
 * [94] Binary Tree Inorder Traversal
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;


class Solution {

    void inorder(TreeNode root, List<Integer> lis) {
        // Base case: If the current node is null, return (end the recursive call).
        if (root == null) return;
    
        // Recursively traverse the left subtree.
        inorder(root.left, lis);
    
        // Process the current node by adding its value to the list.
        lis.add(root.val);
    
        // Recursively traverse the right subtree.
        inorder(root.right, lis);
    }

    
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> lis=new ArrayList<>();
        inorder(root,lis);
        return lis;
    }
}
// @lc code=end

