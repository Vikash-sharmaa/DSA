/*
 * @lc app=leetcode id=124 lang=java
 *
 * [124] Binary Tree Maximum Path Sum
 */

// @lc code=start

class Solution {

/****************************************************************************************************************************************/

    int helper(TreeNode root, int[] maxPathSumTillNow) {
        // Base case: if the current node is null, return 0 as it contributes nothing to the path sum.
        if (root == null) return 0;

        // Recursively calculate the maximum path sum of the left subtree.
        // If the left subtree has a negative contribution, ignore it by taking Math.max(0, ...).
        int leftSubtree = Math.max(0, helper(root.left, maxPathSumTillNow));

        // Recursively calculate the maximum path sum of the right subtree.
        // Similarly, ignore negative contributions by taking Math.max(0, ...).
        int rightSubtree = Math.max(0, helper(root.right, maxPathSumTillNow));

        // Calculate the current path sum passing through the current node.
        // This includes the value of the current node and the contributions of both subtrees.
        int currentPathSum = root.val + leftSubtree + rightSubtree;

        // Update the global maximum path sum if the current path sum is larger.
        // maxPathSumTillNow[0] stores the maximum path sum found so far across all nodes.
        maxPathSumTillNow[0] = Math.max(maxPathSumTillNow[0], currentPathSum);

        // Return the maximum path sum that can be extended to the parent node.
        // This includes the current node's value and the larger contribution of its two subtrees.
        return root.val + Math.max(leftSubtree, rightSubtree);
    }


/****************************************************************************************************************************************/
    
    int maxPathSum(TreeNode root, int[] maxPathSumTillNow) {
        // Base case: if the current node is null, it contributes nothing to the path sum.
        if (root == null) return 0;

        // Recursively calculate the maximum path sum of the left subtree.
        // Ignore negative contributions by taking Math.max(0, ...).
        int leftMaxPathSum = Math.max(0, maxPathSum(root.left, maxPathSumTillNow));

        // Recursively calculate the maximum path sum of the right subtree.
        // Similarly, ignore negative contributions by taking Math.max(0, ...).
        int rightMaxPathSum = Math.max(0, maxPathSum(root.right, maxPathSumTillNow));

        // Calculate the maximum path sum passing through the current node.
        // This path includes the value of the current node and the contributions of both left and right subtrees.
        int currentMaxPathSum = root.val + leftMaxPathSum + rightMaxPathSum;

        // Update the global maximum path sum if the current path sum is larger.
        // This ensures that the global maximum path is tracked across the entire tree.
        maxPathSumTillNow[0] = Math.max(maxPathSumTillNow[0], currentMaxPathSum);

        // Calculate the maximum path sum that can be extended to the parent node.
        // This includes the current node's value and the maximum contribution from one of its subtrees (left or right).
        int value = root.val + Math.max(leftMaxPathSum, rightMaxPathSum);

        // Return the maximum path sum that can be extended to the parent node.
        // By ignoring negatives earlier, only positive contributions are passed up the tree.
        return value;
    }



/****************************************************************************************************************************************/
    public int maxPathSum(TreeNode root) {
            int[] maxPathSumTillNow = new int[1];
            maxPathSumTillNow[0]=Integer.MIN_VALUE;
            //helper(root, maxPathSumTillNow);
            maxPathSum(root, maxPathSumTillNow);
            return maxPathSumTillNow[0];
        }
    }
// @lc code=end

