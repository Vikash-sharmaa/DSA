package Trees.BST;
/*
 * @lc app=leetcode id=98 lang=java
 *
 * [98] Validate Binary Search Tree
 */

import Trees.TreeNode;

// @lc code=start



class Solution {
    /*
        * This will not be able to check - [5,4,6,null,null,3,7]
        * The below implementation only checks the immediate children of each node, but it does not ensure that all nodes in the left subtree 
            are less than the current node and all nodes in the right subtree are greater than the current node. This can lead to incorrect 
            validation for certain trees, such as [5,4,6,null,null,3,7].

            public boolean isValidBST(TreeNode root) {
                if(root==null) return true;

                // if((root.val < root.left.val) || (root.val > root.right.val)) return false;

                if(root.left!=null && root.val <= root.left.val) return false;
                if(root.right!=null && root.val >= root.right.val) return false;

                return isValidBST(root.left) && isValidBST(root.right);
            }
    */

    /*
        Using min and max , we are insuring that each node should be in a range i.e all the nodes in the right subtree is greate than root
            and all nodes in left subtree is lesser than the root.
     */

     /*
      * The isValidBST function visits each node exactly once. For each node, it performs a constant amount of work (checking the value and making recursive calls). Therefore, the time complexity is O(n), 
        where n is the number of nodes in the binary tree.

            Space Complexity
            The space complexity is determined by the recursion stack. In the worst case, the tree is completely unbalanced (e.g., a linked list), and the recursion stack will have a depth of n. 
                In the best case, the tree is perfectly balanced, and the recursion stack will have a depth of log(n).

            Thus, the space complexity is O(h), where h is the height of the tree. In the worst case, h = n, and in the best case, h = log(n).

            Summary:-
            
            Time Complexity: O(n)
            Space Complexity: O(h), where h is the height of the tree (O(n) in the worst case, O(log(n)) in the best case).
      */

    boolean isValidBST(TreeNode root, long min, long max) {
        // If the current node is null, it means we've reached the end of a branch, so it's valid
        if (root == null) return true;
    
        // If the current node's value is not within the valid range, return false
        if (root.val <= min || root.val >= max) return false;
    
        // Recursively check the left subtree with updated max value and right subtree with updated min value
        return (root.val > min && root.val < max) && 
               isValidBST(root.left, min, root.val) && 
               isValidBST(root.right, root.val, max);
    }
    
    public boolean isValidBST(TreeNode root) {
        // Initialize the recursion with the full range of valid values for a BST
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

}
// @lc code=end

