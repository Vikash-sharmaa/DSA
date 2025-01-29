/*
 * @lc app=leetcode id=863 lang=java
 *
 * [863] All Nodes Distance K in Binary Tree
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Trees.TreeNode;






class Solution {

    void parentTrack(TreeNode root, Map<TreeNode, TreeNode> parentMap) {
        
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);
    
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.pollFirst();
            if (currentNode.left != null) {
                queue.offerLast(currentNode.left);
                parentMap.put(currentNode.left, currentNode); // Map left child to parent
            }
            if (currentNode.right != null) {
                queue.offerLast(currentNode.right);
                parentMap.put(currentNode.right, currentNode); // Map right child to parent
            }
        }
    }
    
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        parentTrack(root, parentMap); // Populate parent-child relationships
    
        Map<TreeNode, Boolean> visited = new HashMap<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
    
        queue.offerLast(target); // Start BFS from the target node
        queue.offerLast(new TreeNode(Integer.MAX_VALUE)); // Marker for level separation
        visited.put(target, true);
    
        int currentDistance = 0;
        while (!queue.isEmpty()) {
            TreeNode front = queue.pollFirst();
    
            if (front.val == Integer.MAX_VALUE) { // Marker indicates the end of a level
                currentDistance++;
                if (currentDistance == k) break;
    
                if (!queue.isEmpty()) {
                    queue.offerLast(new TreeNode(Integer.MAX_VALUE)); // Add marker for the next level
                }
            } else {
                // Visit left child
                if (front.left != null && visited.get(front.left) == null) {
                    queue.offerLast(front.left);
                    visited.put(front.left, true);
                }
    
                // Visit right child
                if (front.right != null && visited.get(front.right) == null) {
                    queue.offerLast(front.right);
                    visited.put(front.right, true);
                }
    
                // Visit parent node
                TreeNode parent = parentMap.get(front);
                if (parent != null && visited.get(parent) == null) {
                    queue.offerLast(parent);
                    visited.put(parent, true);
                }
            }
        }
    
        
        while (!queue.isEmpty()) {
            TreeNode current = queue.pollFirst();
            if (current.val != Integer.MAX_VALUE) { // Exclude markers from the result
                res.add(current.val);
            }
        }
        return res;
    }
    
}
// @lc code=end

