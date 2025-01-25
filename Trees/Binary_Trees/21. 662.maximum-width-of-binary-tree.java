/*
 * @lc app=leetcode id=662 lang=java
 *
 * Use index mapping (2 * index + 1 for left, 2 * index + 2 for right) to calculate the width of levels in a binary tree efficiently.
 * above will always calculate correct index as if some other null elements also present 
 * 
 *  Time Complexity: 
        O(N), where N is the number of nodes in the tree. Each node is visited once during the level-order traversal.
    Space Complexity: 
        O(N) in the worst case, due to the space required to store nodes in the queue.
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import Trees.TreeNode;



class Pair{

    TreeNode node;
    int index;

    Pair(){}
    Pair(TreeNode node,int index){
        this.node=node;
        this.index=index;
    }
}

class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        
        // A list to store the nodes and their indices for the current level.
        List<Pair> temp = new ArrayList<>();
        
        // A deque (double-ended queue) to facilitate level-order traversal.
        Deque<Pair> queue = new ArrayDeque<>();
        
        // Variable to track the maximum width of the binary tree.
        int width = 1;

        // Base case: if the tree is empty, return 0.
        if (root == null) return 0;

        // Start with the root node, assigning it an index of 0.
        queue.offerLast(new Pair(root, 0));

        // Add a sentinel node (value = Integer.MAX_VALUE, index = -1) 
        // to mark the end of each level in the queue.
        queue.offerLast(new Pair(new TreeNode(Integer.MAX_VALUE), -1));

        // Perform a level-order traversal.
        while (!queue.isEmpty()) {
            // Get the first node and its index in the queue.
            Pair currentPair = queue.pollFirst();
            TreeNode currentNode = currentPair.node;
            int currentIndex = currentPair.index;
            int currentNodeValue = currentNode.val;

            // If the sentinel node is encountered, calculate the width of the current level.
            if (currentNodeValue == Integer.MAX_VALUE) {
                // Calculate the width of the current level: 
                // difference between the first and last indices + 1.
                int widthForCurrentLevel = temp.get(temp.size() - 1).index - temp.get(0).index + 1;
                
                // Update the maximum width if this level's width is larger.
                width = Math.max(width, widthForCurrentLevel);

                // Clear the temporary list to prepare for the next level.
                temp.clear();

                // If there are more nodes in the queue, add another sentinel node
                // to mark the end of the next level.
                if (!queue.isEmpty()) {
                    queue.offerLast(new Pair(new TreeNode(Integer.MAX_VALUE), -1));
                }
            } else {
                // If the current node is not a sentinel, add it to the temp list.
                temp.add(currentPair);

                // Add the left child to the queue with the corresponding index.
                // The left child's index is `2 * currentIndex + 1`.
                if (currentNode.left != null) {
                    queue.offerLast(new Pair(currentNode.left, 2 * currentIndex + 1));
                }

                // Add the right child to the queue with the corresponding index.
                // The right child's index is `2 * currentIndex + 2`.
                if (currentNode.right != null) {
                    queue.offerLast(new Pair(currentNode.right, 2 * currentIndex + 2));
                }
            }
        }

        // Return the maximum width found during the traversal.
        return width;
    }
}

// @lc code=end

