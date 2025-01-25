 package Trees.Binary_Trees;
/*
 * @lc app=leetcode id=199 lang=java
 *
 * [199] Binary Tree Right Side View
 * using modified BFS
 * Similar to BFS code - 
 * Traverse right to left for right view and for left view traverse left to right and store the first element of temp array.
 * just here we are taking only first element as we are traversing right to left    -       res.add(temp.get(0));
 * Had we traversed - left to right - we had taken last element of each temp array
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        // Initialize the result list to store the right side view.
        List<Integer> res = new ArrayList<>();
        
        // Temporary list to store nodes at the current level.
        List<Integer> temp = new ArrayList<>();
    
        // Handle the edge case where the tree is empty.
        if(root == null) return res;
    
        // Queue for BFS traversal, starting with the root node.
        Deque<TreeNode> queue = new ArrayDeque<>();
        
        // Add the root node to the queue.
        queue.offerLast(root);
        
        // Add a marker node to signify the end of the current level.
        queue.offerLast(new TreeNode(Integer.MAX_VALUE));
    
        // Perform BFS until the queue is empty.
        while(!queue.isEmpty()) {
            // Remove the first node from the queue.
            TreeNode front = queue.pollFirst();
    
            // Check if the current node is the marker node.
            if(front.val == Integer.MAX_VALUE) {
                // Add the first node (rightmost node) from the current level to the result list.
                res.add(temp.get(0));
                
                // Clear the temporary list for the next level.
                temp.clear();
    
                // If the queue is not empty, add another marker node for the next level.
                if(!queue.isEmpty()) queue.offerLast(new TreeNode(Integer.MAX_VALUE));
            } else {
                // Add the value of the current node to the temporary list.
                temp.add(front.val);
                
                // Add the right child first to ensure the rightmost node appears first at each level.
                if(front.right != null) queue.offerLast(front.right);
                
                // Add the left child next.
                if(front.left != null) queue.offerLast(front.left);
            }
        }
    
        // Return the right side view of the tree.
        return res;
    }
    
}
// @lc code=end

