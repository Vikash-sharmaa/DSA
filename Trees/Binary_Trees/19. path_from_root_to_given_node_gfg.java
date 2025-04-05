

import java.util.ArrayList;

import Trees.TreeNode;

class Solution{
    void helper(TreeNode root, int target, ArrayList<Integer> res, ArrayList<Integer> temp) {
        if(root==null) return;
        
        // Add the current node to the temporary path
        temp.add(root.val);
        // If the current node matches the target value
        if (root.val == target) res.addAll(temp);   // Copy the entire path to the result list
        
        // Recursively explore the left subtree if it exists
        helper(root.left, target, res, temp);
        // Recursively explore the right subtree if it exists
        helper(root.right, target, res, temp);

        // Backtrack: remove the last added node to explore other paths
        temp.remove(temp.size() - 1);
    }

    public ArrayList<Integer> solve(TreeNode root, int target) {
        ArrayList<Integer> res = new ArrayList<>(); // To store the path to the target
        if (root == null) return res;              // Handle the edge case of an empty tree
        helper(root, target, res, new ArrayList<>()); // Start the helper function
        return res;                                // Return the result path
    }
}
