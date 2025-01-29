/*
 * @lc app=leetcode id=106 lang=java
 *
 * 
 * Slight change from constructing unique BT using inorder and preorder
 * Inorder and preorder - 
    => preoder array's first element will be the root and for left and right of preorder array of the subtree is taken as below:-
        root.left = buildTree(preorder, preStart + 1, preStart + numsLeft,inorder,inStart,inRoot - 1,inMap);

        // Recursively build the right subtree
        root.right = buildTree(preorder, preStart + numsLeft + 1, preEnd,inorder,inRoot + 1,inEnd, inMap);
 
 * Inorder and postOrder - 
    => postOrder array's last element will be root and for left and right will be taken as below :- 
        root.left = buildTree(postorder, postStart, postStart + numsLeft - 1, inorder, inStart, inRoot - 1, inMap);
    
        root.right = buildTree(postorder, postStart + numsLeft, postEnd - 1, inorder, inRoot + 1, inEnd, inMap);
 */

import java.util.HashMap;
import java.util.Map;

import Trees.TreeNode;

class Solution {

    TreeNode buildTree(int[] postorder, int postStart, int postEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
        // Base condition: If the pointers exceed bounds, return null (no subtree to construct).
        if (postStart > postEnd || inStart > inEnd) return null;
    
        // The last element of the current postorder segment is the root of the subtree.
        TreeNode root = new TreeNode(postorder[postEnd]);
    
        // Find the index of the root in the inorder array using the map for quick lookup.
        int inRoot = inMap.get(root.val);
    
        // Calculate the number of nodes in the left subtree.
        int numsLeft = inRoot - inStart;
    
        // Recursively build the left subtree using postorder and inorder segments.
        root.left = buildTree(postorder, postStart, postStart + numsLeft - 1, inorder, inStart, inRoot - 1, inMap);
    
        // Recursively build the right subtree using postorder and inorder segments.
        root.right = buildTree(postorder, postStart + numsLeft, postEnd - 1, inorder, inRoot + 1, inEnd, inMap);
    
        // Return the root of the subtree.
        return root;
    }
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // Create a map to store the index of each value in the inorder array for quick lookup.
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inMap.put(inorder[i], i);
    
        // Recursively construct the tree starting from the entire postorder and inorder arrays.
        TreeNode root = buildTree(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, inMap);
    
        // Return the constructed tree.
        return root;
    }
    
}
// @lc code=end

