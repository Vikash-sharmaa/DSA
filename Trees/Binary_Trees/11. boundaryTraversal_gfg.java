package Trees.Binary_Trees;
/*

Input: root[] = [1, 2, 3, 4, 5, 6, 7, N, N, 8, 9, N, N, N, N] 
        1 
      /   \
     2     3  
    / \   / \ 
   4   5 6   7
      / \
     8   9
   
Output: [1, 2, 4, 8, 9, 6, 7, 3]

    Logic:-

 * Traverse left boundary 
 * Traverse leaf
 * Traverse right boundary in reverse manner - (use call stack)
 * 
 * 
 * Time:- O(h)+O(n)+O(h)=O(n)
 * Space:- O(log(n))+O(n)=O(n)
 */

import java.util.ArrayList;

class Solution {
    void leftBoundaryTraversal(TreeNode root, ArrayList<Integer> res) {
        if (root == null) return; // Base case: if the node is null, return.
        if (root.left == null && root.right == null) return; // If the node is a leaf, return (boundary should not include leaf nodes).
        res.add(root.val); // Add the current node's data to the result list (left boundary node).
        if (root.left != null) 
            leftBoundaryTraversal(root.left, res); // Traverse the left subtree.
        else 
            leftBoundaryTraversal(root.right, res); // If no left child, traverse the right subtree to continue along the boundary.
    }
    
    void leafTraversal(TreeNode root, ArrayList<Integer> res) {
        if (root == null) return; // Base case: if the node is null, return.
        leafTraversal(root.left, res); // Traverse the left subtree to collect leaf nodes.
        if (root.left == null && root.right == null) 
            res.add(root.val); // If the current node is a leaf, add it to the result list.
        leafTraversal(root.right, res); // Traverse the right subtree to collect leaf nodes.
    }
    
    void rightBoundaryTraversal(TreeNode root, ArrayList<Integer> res) {
        if (root == null) return; // Base case: if the node is null, return.
        if (root.left == null && root.right == null) return; // If the node is a leaf, return (boundary should not include leaf nodes).
        if (root.right != null) 
            rightBoundaryTraversal(root.right, res); // Traverse the right subtree.
        else 
            rightBoundaryTraversal(root.left, res); // If no right child, traverse the left subtree to continue along the boundary.
        res.add(root.val); // Add the current node's data to the result list (add after recursion to maintain reverse order for the right boundary).
    }
    

    ArrayList<Integer> boundaryTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>(); // Initialize the result list to store the boundary traversal.
        if (root == null) return res; // If the tree is empty, return an empty list.
        
        res.add(root.val); // Add the root node to the result list (root is always part of the boundary).
        if (root.left == null && root.right == null) return res; // If the root is the only node, return the result.
    
        leftBoundaryTraversal(root.left, res); // Traverse the left boundary excluding the leaf nodes.
        leafTraversal(root, res); // Traverse and collect all the leaf nodes.
        rightBoundaryTraversal(root.right, res); // Traverse the right boundary excluding the leaf nodes.
    
        return res; // Return the complete boundary traversal.
    }


}