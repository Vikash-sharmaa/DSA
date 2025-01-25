/*
 * @lc app=leetcode id=236 lang=java
 *
 * [236] Lowest Common Ancestor of a Binary Tree
 */

import java.util.ArrayList;
import java.util.List;

import Trees.TreeNode;



//import Trees.Binary_Trees.TreeNode;

class Solution {
    
    void helper(TreeNode root, TreeNode p, TreeNode q, List<TreeNode> pRes, List<TreeNode> qRes, List<TreeNode> temp) {
        if (root == null) return; // Base case: null node
    
        // Add the current node to the temporary path
        temp.add(root);
    
        // If the current node matches `p`, copy the path to `pRes`
        if (root.val == p.val) pRes.addAll(temp);
        
        // If the current node matches `q`, copy the path to `qRes`
        if (root.val == q.val) qRes.addAll(temp);
    
        // Recursively explore the left and right subtrees
        helper(root.left, p, q, pRes, qRes, temp);
        helper(root.right, p, q, pRes, qRes, temp);
    
        // Backtrack: remove the current node from the path
        temp.remove(temp.size() - 1);
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Lists to store the paths from the root to `p` and `q`
        List<TreeNode> pRes = new ArrayList<>();
        List<TreeNode> qRes = new ArrayList<>();
    
        // Find the paths to `p` and `q` using the helper function
        helper(root, p, q, pRes, qRes, new ArrayList<>());
    
        // Find the lowest common ancestor by comparing the paths
        TreeNode res = null;
        for (int i = 0; i < Math.min(pRes.size(), qRes.size()); i++) {
            if (pRes.get(i) == qRes.get(i)) {
                res = pRes.get(i); // Update LCA as long as nodes are the same
            } else {
                break; // Stop when paths diverge
            }
        }
    
        return res; // Return the lowest common ancestor
    }
    
}
// @lc code=end

