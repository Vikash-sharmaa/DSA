/*
 * @lc app=leetcode id=1161 lang=java
 *
 * 
 * Time Complexity: O(N)
   Space Complexity: O(M), where 
        M is the maximum width of the tree.
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import Trees.TreeNode;



class Solution {
    public int maxLevelSum(TreeNode root) {
        // A deque (double-ended queue) for level-order traversal.
        Deque<TreeNode> queue = new ArrayDeque<>();
        // A list to store the node values for the current level.
        List<Integer> temp = new ArrayList<>();
        
        // If the tree is empty, return 0.
        if (root == null) return 0;
    
        // Variables to track the current level, the level with the maximum sum, and the maximum sum.
        int level = 1; // Start at the root level.
        int maxLevel = 0; // To track the level with the maximum sum.
        int maxLevelSum = Integer.MIN_VALUE; // Initialize max sum to the smallest value.
    
        // Add the root node to the queue.
        queue.offerLast(root);
    
        // Add a sentinel node (value = Integer.MAX_VALUE) to mark the end of a level.
        queue.offer(new TreeNode(Integer.MAX_VALUE));
    
        // Perform a level-order traversal.
        while (!queue.isEmpty()) {
            // Get the front node of the queue.
            TreeNode front = queue.pollFirst();
            int frontValue = front.val;
    
            // If the sentinel node is encountered, process the current level.
            if (frontValue == Integer.MAX_VALUE) {
                // Calculate the sum of the current level.
                int levelSum = temp.stream().reduce(0, (sum, x) -> sum + x);
                
                // Check if the current level's sum is greater than the maximum sum so far.
                if (levelSum > maxLevelSum) {
                    maxLevelSum = levelSum; // Update the maximum sum.
                    maxLevel = level; // Update the level with the maximum sum.
                }
                
                // Clear the list for the next level.
                temp.clear();
    
                // If there are more nodes in the queue, add another sentinel node to mark the end of the next level.
                if (!queue.isEmpty()) {
                    queue.offerLast(new TreeNode(Integer.MAX_VALUE));
                    level++; // Move to the next level.
                }
            } else {
                // If the current node is not a sentinel, add its value to the temporary list.
                temp.add(frontValue);
    
                // Add the left and right children to the queue for the next level, if they exist.
                if (front.left != null) queue.offerLast(front.left);
                if (front.right != null) queue.offerLast(front.right);
            }
        }
    
        // Return the level with the maximum sum.
        return maxLevel;
    }
    
}
// @lc code=end

