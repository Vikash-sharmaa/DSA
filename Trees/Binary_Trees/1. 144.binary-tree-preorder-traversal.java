package Trees.Binary_Trees;


import java.util.ArrayList;
import java.util.List;

import Trees.TreeNode;


class Solution {

    void preorderRecursive(TreeNode root, List<Integer> lis) {
        // Base case: If the current node is null, return (end the recursive call).
        if (root == null) return;
        
        // Process the current node by adding its value to the list.
        lis.add(root.val);

        // Recursively traverse the left subtree.
        preorderRecursive(root.left, lis);

        // Recursively traverse the right subtree.
        preorderRecursive(root.right, lis);
    }


    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> lis=new ArrayList<>();
        preorderRecursive(root,lis);
        return lis;
    }
}
// @lc code=end

