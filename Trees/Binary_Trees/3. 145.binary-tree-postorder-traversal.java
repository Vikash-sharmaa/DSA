package Trees.Binary_Trees;
/*
 * @lc app=leetcode id=145 lang=java
 *
 * [145] Binary Tree Postorder Traversal
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    void postorder(TreeNode root, List<Integer> lis) {
        // Base case: If the current node is null, return (end the recursive call).
        if (root == null) return;
    
        // Recursively traverse the left subtree.
        postorder(root.left, lis);
    
        // Recursively traverse the right subtree.
        postorder(root.right, lis);
    
        // Process the current node by adding its value to the list.
        lis.add(root.val);
    }
    
    
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> lis=new ArrayList<>();
        postorder(root, lis);
        return lis;
    }
}
// @lc code=end

