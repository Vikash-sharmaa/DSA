/*
 * @lc app=leetcode id=103 lang=java
 *
 * [103] Binary Tree Zigzag Level Order Traversal
 * Similar to level order just reverse the order of nodes for the current level for odd levels.
 * 
 * Time:- O(n)     Space:- O(n)
 */ 

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // Initialize a double-ended queue for level-order traversal.
        Deque<TreeNode> queue = new ArrayDeque<>();
        
        // Result list to store the zigzag level order traversal.
        List<List<Integer>> res = new ArrayList<>();
        
        // Temporary list to store nodes at the current level.
        List<Integer> temp = new ArrayList<>();
    
        // Edge case: if the tree is empty, return an empty result list.
        if (root == null) return res;
    
        // Add the root node to the queue.
        queue.offerLast(root);
        
        // Use a special marker node to indicate the end of a level.
        queue.offerLast(new TreeNode(Integer.MAX_VALUE));
    
        // Perform level-order traversal until the queue is empty.
        while (!queue.isEmpty()) {
            // Remove the front node from the queue.
            TreeNode front = queue.pollFirst();
    
            // Check if the current node is the marker node indicating the end of a level.
            if (front.val == Integer.MAX_VALUE) {
                // Check if the current level is odd-indexed (zigzag).
                if (res.size() % 2 != 0) {
                    // Reverse the order of nodes for the current level.
                    Collections.reverse(temp);
                }
    
                // Add the current level's nodes to the result list.
                res.add(new ArrayList<>(temp));
                
                // Clear the temporary list for the next level.
                temp.clear();
    
                // If the queue is not empty, add another marker for the next level.
                if (queue.size() != 0) {
                    queue.offerLast(new TreeNode(Integer.MAX_VALUE));
                }
            } else {
                // Add the current node's value to the temporary list for this level.
                temp.add(front.val);
    
                // If the current node has a left child, add it to the queue.
                if (front.left != null) queue.offerLast(front.left);
    
                // If the current node has a right child, add it to the queue.
                if (front.right != null) queue.offerLast(front.right);
            }
        }
    
        // Return the zigzag level order traversal.
        return res;
    }    
}
// @lc code=end

