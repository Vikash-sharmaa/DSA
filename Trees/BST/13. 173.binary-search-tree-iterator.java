package Trees.BST;
/*
 * @lc app=leetcode id=173 lang=java
 *
 * [173] Binary Search Tree Iterator
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import Trees.TreeNode;




/**********************************************************************************************************************************************************/

// Time Complexity: O(n) for constructor (inorder traversal), O(1) for `next()` and `hasNext()` methods.
// Space Complexity: O(n) for storing the entire inorder traversal in a list.
class BSTIterator1 {
    int iterator; // Pointer to track the current position in the list.
    TreeNode root; // Reference to the root of the BST.
    ArrayList<Integer> lis = new ArrayList<>(); // List to store the inorder traversal of the BST.

    // Constructor initializes the iterator and generates the inorder traversal of the BST.
    public BSTIterator1(TreeNode root) {
        this.iterator = 0; // Start from the first element in the inorder sequence.
        this.root = root; // Assign the root of the BST.
        inorder(root, lis); // Perform inorder traversal and store the result in the list.
    }

    // Helper method to perform inorder traversal and store the values in `lis`.
    // Time Complexity: O(n) since it visits each node exactly once.
    // Space Complexity: O(n) for the recursion stack (if the tree is skewed).
    void inorder(TreeNode root, ArrayList<Integer> lis) {
        if (root == null) return; // Base case: stop if the node is null.

        inorder(root.left, lis); // Recursively visit the left subtree.
        lis.add(root.val); // Add the current node's value to the list.
        inorder(root.right, lis); // Recursively visit the right subtree.
    }

    // Method to get the next smallest element in the BST.
    // Time Complexity: O(1) since we just access the next element in the list.
    public int next() {
        return lis.get(iterator++); // Retrieve the current element and increment the iterator.
    }

    // Method to check if there are more elements to iterate over in the BST.
    // Time Complexity: O(1) since it just compares `iterator` with the list size.
    public boolean hasNext() {
        return iterator < lis.size(); // Return true if there are unvisited elements in the list.
    }
}


/**********************************************************************************************************************************************************/


/**
 * BSTIterator class for iterating over a Binary Search Tree (BST) in ascending order using an iterative inorder traversal.
 * 
 * Time Complexity:
 * - `next()`: Amortized O(1). Each node is visited once, and traversing the leftmost path happens once per node.
 * - `hasNext()`: O(1) since it only checks if the stack is empty.
 * 
 * Space Complexity:
 * - O(h), where h is the height of the BST. The stack stores the nodes along the current path to the leftmost node.
 */
class BSTIterator {

    TreeNode root; // Reference to the root of the BST.
    Deque<TreeNode> stack = new ArrayDeque<>(); // Stack to store nodes for iterative inorder traversal.

    /**
     * Constructor initializes the iterator.
     * Pushes all the leftmost nodes from the root onto the stack, so the smallest element is ready to be processed first.
     * 
     * @param root The root of the BST.
     */
    public BSTIterator(TreeNode root) {
        this.root = root; // Save the root reference.
        pushAll(root);
    }

    /**
     * Returns the next smallest value in the BST.
     * Pops the top node from the stack (current smallest), processes its value, and handles its right subtree.
     * 
     * @return The next smallest value in the BST.
     */
    public int next() {
        TreeNode nextNode = stack.pollLast(); // Remove and retrieve the top node from the stack.
        int nextValue = nextNode.val; // Store its value to return later.
        pushAll(nextNode.right);
        // If the node has a right child, push all its leftmost descendants onto the stack.
        
        return nextValue; // Return the value of the node.
    }

    void pushAll(TreeNode current){
        while (current != null) { // Traverse the leftmost path of the right subtree.
            stack.offerLast(current); // Push the current node onto the stack.
            current = current.left; // Move to the left child.
        }
    }

    /**
     * Checks if there are more elements to iterate in the BST.
     * If the stack is not empty, there are still nodes to be processed.
     * 
     * @return True if there are more elements, false otherwise.
     */
    public boolean hasNext() {
        return !stack.isEmpty(); // Return true if the stack is not empty.
    }
}


/**********************************************************************************************************************************************************/

// @lc code=end

