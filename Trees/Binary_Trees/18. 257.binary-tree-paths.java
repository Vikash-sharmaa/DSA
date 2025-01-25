package Trees.Binary_Trees;
/*
 * @lc app=leetcode id=257 lang=java
 *
 * [257] Binary Tree Paths
 * preorder traversal + backtracking
 * 
 *  Time Complexity: ( O(n) )
    Space Complexity: ( O(n^2) ) (dominated by the result list res)
 * 
 */



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    void binaryTreePaths(TreeNode root, List<String> res, List<String> temp) {
        // If the current node is a leaf node (no children)
        if (root.left == null && root.right == null) {
            // Join the current path into a single string
            String path = temp.stream().collect(Collectors.joining());
            // Add the value of the current node to the path
            path += root.val;
            // Add the complete path to the result list
            res.add(path);
            return;
        }

        // Add the current node's value to the temporary path list
        temp.add(root.val + "->");

        // Recursively call the function for the left child if it exists
        if (root.left != null) binaryTreePaths(root.left, res, temp);
        // Recursively call the function for the right child if it exists
        if (root.right != null) binaryTreePaths(root.right, res, temp);

        // Remove the last added node's value from the temporary path list
        temp.remove(temp.size() - 1);
    }

    public List<String> binaryTreePaths(TreeNode root) {
        // Initialize the result list to store all paths
        List<String> res = new ArrayList<>();
        // Start the recursive function with an empty temporary path list
        binaryTreePaths(root, res, new ArrayList<>());
        // Return the list of all paths
        return res;
    }
}
// @lc code=end

