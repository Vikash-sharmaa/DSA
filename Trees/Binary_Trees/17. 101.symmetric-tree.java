package Trees.Binary_Trees;
/*
 * @lc app=leetcode id=101 lang=java
 *
 * [101] Symmetric Tree
 */

/*
 * Intuition:
 * A tree is symmetric if the left subtree is a mirror reflection of the right subtree.
 * To check if a tree is symmetric, we can use a helper function that compares two trees.
 * The helper function checks if the current nodes have the same value and 
 * recursively checks the symmetry of the subtrees.
 * If both nodes are null, they are symmetric.
 * If one of the nodes is null, they are not symmetric.
 * The main function uses this helper function to check if the left and right subtrees of the root are symmetric.
 */

 /*
 * Time Complexity: O(n)
 * - Each node in the tree is visited once.

 * Space Complexity: O(h)
 * - The space complexity is determined by the height of the tree `h` due to the recursion stack.
 * - In the worst case, the height of the tree is `n` (for a skewed tree), 
 *   leading to O(n) space complexity.
 */



class Solution {
    // Helper function to check if two trees are symmetric
    boolean isSymmetric(TreeNode root1, TreeNode root2) {
        // If both nodes are null, they are symmetric
        if (root1 == null && root2 == null) return true;
        // If one of the nodes is null, they are not symmetric
        if (root1 == null || root2 == null) return false;

        // Check if the current nodes have the same value and 
        // recursively check the symmetry of the subtrees
        return root1.val == root2.val && 
               isSymmetric(root1.right, root2.left) && 
               isSymmetric(root1.left, root2.right);
    }

    // Main function to check if a tree is symmetric
    public boolean isSymmetric(TreeNode root) {
        // An empty tree is considered symmetric
        if (root == null) return false;

        // Use the helper function to check symmetry
        return isSymmetric(root.left, root.right);
    }
}

// @lc code=end

