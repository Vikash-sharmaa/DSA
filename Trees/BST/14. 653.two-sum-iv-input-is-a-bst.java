package Trees.BST;
/*
 * @lc app=leetcode id=653 lang=java
 *
 * [653] Two Sum IV - Input is a BST
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import Trees.TreeNode;



/**********************************************************************************************************************************************************/


class Solution1 {
        /*
            * Time Complexity:
                * - `inorderTraversal`: O(n), where n is the number of nodes in the BST, since every node is visited once.
                * - `twoSum`: O(n), as the two-pointer approach traverses the `inorderList` linearly.
                * - Overall: O(n), as the traversal dominates the computation.
            * 

            * Space Complexity:
                * - O(n), to store the inorder traversal of the BST in a list.
        */


    /**
     * Performs an inorder traversal of the BST and collects node values into a list.
     */
    public void inorderTraversal(TreeNode root, List<Integer> inorderList) {
        if (root == null) return; // Base case: If the node is null, stop recursion.

        inorderTraversal(root.left, inorderList); // Recursively visit the left subtree.
        inorderList.add(root.val); // Add the current node's value to the list.
        inorderTraversal(root.right, inorderList); // Recursively visit the right subtree.
    }

    /**
     * Uses a two-pointer approach to check if two numbers in a sorted list sum up to a target.
     */
    boolean twoSum(List<Integer> inorderList, int target) {
        int i = 0, j = inorderList.size() - 1; // Initialize two pointers at the start and end of the list.
        while (i < j) { // Continue until the pointers meet.
            int currentSum = inorderList.get(i) + inorderList.get(j); // Calculate the sum of the values at the pointers.
            if (currentSum == target) return true; // If the sum equals the target, return true.
            else if (currentSum < target) i++; // If the sum is less than the target, move the left pointer forward.
            else j--; // If the sum is greater than the target, move the right pointer backward.
        }
        return false; // If no pair is found, return false.
    }

    /**
     * Determines if there exist two elements in the BST that sum up to the target.
     */
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> inorderList = new ArrayList<>(); // Create a list to store the inorder traversal.
        inorderTraversal(root, inorderList); // Perform an inorder traversal to get the sorted node values.
        return twoSum(inorderList, k); // Use the two-pointer technique to check for the target sum.
    }

}


/**********************************************************************************************************************************************************/





    // We can use next() and before() iterator to which may be treated as left and right pointer of two sum problem
    // next() will - always give sorted increasing order array from BST whereas before() will always give sorted decreasing order array from BST.


    class BSTIterator {
        TreeNode root; // Reference to the root of the BST.
        Deque<TreeNode> stack; // Stack for controlled traversal of the BST.
        boolean reverse; // Determines whether this iterator traverses in reverse (right-to-left) or normal (left-to-right) order.
    


        // Constructor initializes the iterator and preloads the stack for traversal.
        BSTIterator(TreeNode root, boolean reverse) {
            this.root = root;
            this.reverse = reverse;
            stack = new ArrayDeque<>();
            
            // Start the traversal by pushing all nodes along the initial path.
            pushAll(root);
        }


    
        // Returns true if there are more nodes to traverse in the BST.
        boolean hasNext() {
            return !stack.isEmpty(); // If the stack is not empty, there are more nodes.
        }
    


        // Returns the next node's value in the traversal and updates the stack.
        int next() {
            TreeNode nextNode = stack.pollLast(); // Retrieve and remove the topmost node from the stack.
    
            // Continue traversal by pushing nodes from the next subtree based on the direction.
            if (reverse) pushAll(nextNode.left); // For reverse traversal, push left subtree.
            else pushAll(nextNode.right); // For normal traversal, push right subtree.
    
            return nextNode.val; // Return the value of the current node.
        }
    



        // Helper function to push all nodes along a path into the stack.
        void pushAll(TreeNode current) {
            while (current != null) {
                stack.offerLast(current); // Push the current node onto the stack.
                current = reverse ? current.right : current.left; // Traverse based on the direction.
            }
        }
    }


    
    class Solution {
        
        // Function to check if there exist two elements in the BST that sum up to a given target.
        public boolean findTarget(TreeNode root, int k) {
            if (root == null) return false; // If the tree is empty, return false.
    
            // Initialize two iterators: one for left-to-right traversal and one for right-to-left traversal.
            BSTIterator left = new BSTIterator(root, false); // Normal inorder traversal.
            BSTIterator right = new BSTIterator(root, true); // Reverse inorder traversal.
    
            int i = left.next(); // Get the smallest element from the BST.
            int j = right.next(); // Get the largest element from the BST.
    
            // Use two-pointer logic to find if two elements sum up to the target.
            while (i < j) {
                int curr = i + j; // Calculate the current sum.
                if (curr == k) return true; // If the sum equals the target, return true.
                else if (curr < k) i = left.next(); // If the sum is less than the target, move the left pointer forward.
                else j = right.next(); // If the sum is greater than the target, move the right pointer backward.
            }
    
            return false; // If no such pair is found, return false.
        }
    }
    
    
// @lc code=end

