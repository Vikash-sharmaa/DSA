

import java.util.ArrayList;

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
    // private boolean getPath(TreeNode root, ArrayList<Integer> path, int x) {
    //     if (root == null) return false;
    
    //     // Add current node to the path
    //     path.add(root.val);
    
    //     // If current node is the target
    //     if (root.val == x) return true;
    
    //     // Recur for left and right subtrees
    //     if (getPath(root.left, path, x) || getPath(root.right, path, x)) {
    //         return true;
    //     }
    
    //     // Backtrack: remove current node from path
    //     path.remove(path.size() - 1);
    //     return false;
    // }
    

    public ArrayList<Integer> solve(TreeNode root, int target) {
        ArrayList<Integer> res = new ArrayList<>(); // To store the path to the target
        if (root == null) return res;              // Handle the edge case of an empty tree
        //helper(root, target, res, new ArrayList<>()); // Start the helper function
        getPath(root, res, target);
        return res;                                // Return the result path
    }
}
