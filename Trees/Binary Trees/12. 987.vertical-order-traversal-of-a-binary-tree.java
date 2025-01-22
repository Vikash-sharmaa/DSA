/*
 * @lc app=leetcode id=987 lang=java
 *
 * [987] Vertical Order Traversal of a Binary Tree
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/****************************************************************************************************************************************/

class Tuple {
    TreeNode node;
    int line;
    int level;

    // Default constructor.
    Tuple() {}

    // Parameterized constructor to initialize the node, vertical line, and level.
    Tuple(TreeNode node, int line, int level) {
        this.node = node;
        this.line = line;
        this.level = level;
    }
}

/****************************************************************************************************************************************/

class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>(); // Result list to store vertical order traversal.

        // Map structure to group nodes by their vertical line and level.
        // Outer key: vertical line, Inner key: level, Value: PriorityQueue of node values.
        Map<Integer, Map<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();

        if (root == null) return res; // Edge case: if the tree is empty, return an empty result list.

        // Queue to perform BFS with nodes, storing their associated line and level.
        Deque<Tuple> queue = new ArrayDeque<>();
        queue.offerLast(new Tuple(root, 0, 0)); // Start traversal with the root node at line 0, level 0.

        // BFS traversal.
        while (!queue.isEmpty()) {
            // Get the front of the queue, which represents the current node and its position.
            Tuple front = queue.pollFirst();
            TreeNode currentNode = front.node;
            int currentVerticalLine = front.line; // The vertical line of the current node.
            int currentLevel = front.level;       // The level (depth) of the current node.

            // Add the current node to the map:
            // 1. If the current vertical line is not present, initialize it with a new TreeMap.
            map.putIfAbsent(currentVerticalLine, new TreeMap<>());
            // 2. If the current level is not present within the vertical line, initialize it with a new PriorityQueue.
            map.get(currentVerticalLine).putIfAbsent(currentLevel, new PriorityQueue<>());
            // 3. Add the current node's value to the priority queue for its vertical line and level.
            map.get(currentVerticalLine).get(currentLevel).offer(currentNode.val);

            // If the left child exists, add it to the queue with:
            // - Line decremented by 1 (left shifts the vertical line leftwards).
            // - Level incremented by 1 (child is one level deeper).
            if (currentNode.left != null) {
                queue.offerLast(new Tuple(currentNode.left, currentVerticalLine - 1, currentLevel + 1));
            }

            // If the right child exists, add it to the queue with:
            // - Line incremented by 1 (right shifts the vertical line rightwards).
            // - Level incremented by 1 (child is one level deeper).
            if (currentNode.right != null) {
                queue.offerLast(new Tuple(currentNode.right, currentVerticalLine + 1, currentLevel + 1));
            }
        }

        // Convert the map to the required result list:
        for (Map<Integer, PriorityQueue<Integer>> levelsMap : map.values()) {
            List<Integer> vertical = new ArrayList<>(); // List to store nodes for one vertical line.
            // Iterate over the levels for the current vertical line:
            for (PriorityQueue<Integer> pq : levelsMap.values()) {
                // Extract all node values from the priority queue in sorted order.
                while (!pq.isEmpty()) {
                    vertical.add(pq.poll());
                }
            }
            // Add the completed vertical line to the result list.
            res.add(vertical);
        }

        return res; // Return the final vertical order traversal result.
    }
}
/****************************************************************************************************************************************/
// @lc code=end

