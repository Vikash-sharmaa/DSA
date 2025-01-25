/*
 * @lc app=leetcode id=257 lang=java
 *
 * [257] Binary Tree Paths
 */

 import java.util.ArrayList;
 import java.util.List;
 import java.util.stream.Collectors;

import Trees.TreeNode;
 
 class Solution {

    void binaryTreePaths(TreeNode root, List<String> res, List<String> temp) {
        // Base case: if the current node is null, return immediately
        if (root == null) return;
    
        // If the current node is a leaf (no left or right children)
        if (root.left == null && root.right == null) {
            // Join the elements in temp to form the path string
            String path = temp.stream().collect(Collectors.joining());
            // Append the current node's value to the path
            path += root.val;
            // Add the complete path to the result list
            res.add(path);
        }
    
        // Add the current node's value followed by "->" to the temp list
        temp.add(root.val + "->");
    
        // Recursively call the function for the left child
        binaryTreePaths(root.left, res, temp);
        // Recursively call the function for the right child
        binaryTreePaths(root.right, res, temp);
    
        // Remove the last element from temp to backtrack
        temp.remove(temp.size() - 1);
    }
    
    public List<String> binaryTreePaths(TreeNode root) {
        // Initialize the result list to store all paths
        List<String> res = new ArrayList<>();
        // Start the recursive function with an empty temp list
        binaryTreePaths(root, res, new ArrayList<>());
        // Return the list of all paths
        return res;
    }

 }
 
// @lc code=end

