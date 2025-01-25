/*
   Check for Children Sum Property in a Binary Tree
    Problem Statement: Given a Binary Tree, convert the value of its nodes to follow the Children Sum Property. 
                       The Children Sum Property in a binary tree states that for every node, the sum of its children's values 
                       (if they exist) should be equal to the node's value. If a child is missing, it is considered as having a value of 0.


    Note:
        The node values can be increased by any positive integer any number of times, but decrementing any node value is not allowed.
        A value for a NULL node can be assumed as 0.
        We cannot change the structure of the given binary tree.
 */


 /*
    Time Complexity: O(N) where N is the number of nodes in the binary tree. This is because the algorithm traverses each node exactly once, performing constant-time 
                     operations at each node.

    Space Complexity: O(N) where N is the number of nodes in the Binary Tree.

    In the worst case scenario the tree is skewed and the auxiliary recursion stack space would be stacked up to the maximum height of the tree, resulting in a space complexity of O(N).
    In the optimal case of a balanced tree, the auxiliary space would take up space proportional to O(log2N).
  */

import Trees.TreeNode;

class Solution {
    // Function to change the values of the nodes
    // based on the sum of its children's values.
    public void changeTree(TreeNode root) {
        // Base case: If the current node
        // is null, return and do nothing.
        if (root == null) return;
    

        // Calculate the sum of the values of
        // the left and right children, if they exist.
        int childrenSum = 0;
        if (root.left != null) childrenSum += root.left.val;
        
        if (root.right != null) childrenSum += root.right.val;
    
        // Compare the sum of children with
        // the current node's value and update
        if (childrenSum >= root.val) root.val = childrenSum;
        else {
            // If the sum is smaller, update the
            // child with the current node's value.
            if (root.left != null) root.left.val = root.val;
            
            if (root.right != null) root.right.val = root.val;
            
        }

        // Recursively call the function
        // on the left and right children.
        changeTree(root.left);
        changeTree(root.right);

        // Calculate the total sum of the
        // values of the left and right
        // children, if they exist.
        int tot = 0;
        if (root.left != null) tot += root.left.val;
        
        if (root.right != null) tot += root.right.val;
    
        // If either left or right child
        // exists, update the current node's
        // value with the total sum.
        if (root.left != null || root.right != null) root.val = tot;
        
    }
}