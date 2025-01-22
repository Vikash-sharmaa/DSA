/*
 * @lc app=leetcode id=100 lang=java
 *
 * [100] Same Tree
 */





class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Base case: If both nodes are null, the trees are identical up to this point.
        if (p == null && q == null) return true;
    
        // If one of the nodes is null but the other is not, the trees are not the same.
        if (p == null || q == null) return false;
    
        // Recursively check if the left subtrees of both nodes are the same.
        boolean checkLeft = isSameTree(p.left, q.left);
    
        // Recursively check if the right subtrees of both nodes are the same.
        boolean checkRight = isSameTree(p.right, q.right);
    
        // The trees are the same if the current nodes have the same value,
        // and their left and right subtrees are identical.
        return (p.val == q.val) && checkLeft && checkRight;
    }    
}
// @lc code=end

