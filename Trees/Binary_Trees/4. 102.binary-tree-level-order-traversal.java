package Trees.Binary_Trees;
/*
 * @lc app=leetcode id=102 lang=java
 *
 * [102] Binary Tree Level Order Traversal
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // `res` will store the final list of levels, where each level is a list of integers.
    
        List<Integer> level = new ArrayList<>();
        // `level` temporarily stores the values of nodes at the current level.
    
        if (root == null) return res;
        // If the root is null, the tree is empty, so return an empty result list.
    
        Deque<TreeNode> queue = new ArrayDeque<>();
        // A queue is used to perform a level-order traversal of the tree.
    
        queue.offerLast(root);
        // Add the root node to the queue as the starting point.
    
        queue.offerLast(new TreeNode(Integer.MAX_VALUE));
        // Add a marker node with value `Integer.MAX_VALUE` to indicate the end of the current level.
    
        while (!queue.isEmpty()) {
            TreeNode front = queue.pollFirst();
            // Remove the node at the front of the queue.
    
            if (front.val == Integer.MAX_VALUE) {
                // If the current node is the marker (end of a level):
                res.add(new ArrayList<>(level));
                // Add the current level's values to the result list.
                
                level.clear();
                // Clear the level list to start recording the next level.
    
                if (!queue.isEmpty()) {
                    // If the queue is not empty, add a new marker for the next level.
                    queue.offerLast(new TreeNode(Integer.MAX_VALUE));
                }
            } else {
                // If the current node is not the marker:
                level.add(front.val);
                // Add the value of the node to the current level list.
    
                if (front.left != null) queue.offerLast(front.left);
                // If the node has a left child, add it to the queue.
    
                if (front.right != null) queue.offerLast(front.right);
                // If the node has a right child, add it to the queue.
            }
        }
    
        return res;
        // Return the final list of levels.
    }
    
}
// @lc code=end

