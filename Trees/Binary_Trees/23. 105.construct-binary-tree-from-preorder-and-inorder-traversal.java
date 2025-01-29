/*
 * @lc app=leetcode id=105 lang=java
 *
*/

/*
      The intuition is to use the preorder array to identify the root of the tree or subtree and the inorder array
      to divide it into left and right subtrees. By storing inorder indices in a map, we quickly find the root's 
      position to calculate the size of the left subtree. Recursively, the process builds the tree's left and right 
      parts.
 */

 /*
    Time Complexity: Building the tree takes O(n), where n is the number of nodes, due to a single traversal of the preorder and inorder arrays 
       and O(1) lookup in the inMap.

    Space Complexity: O(n) for the inMap and O(h) for the recursive stack, where h is the height of the tree, resulting in 
    O(n) in the worst case for a skewed tree.  
  */

import java.util.HashMap;
import java.util.Map;

import Trees.TreeNode;



class Solution {

    TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> inMap) {
    // Base case: if there are no elements to construct the subtree
    if (preStart > preEnd || inStart > inEnd) return null;

    // Create the root node using the current value in the preorder traversal
    TreeNode root = new TreeNode(preorder[preStart]);

    // Find the index of the root in the inorder traversal using the map
    int inRoot = inMap.get(root.val);

    // Calculate the number of nodes in the left subtree based on inorder index
    int numsLeft = inRoot - inStart;

    // Recursively build the left subtree
    root.left = buildTree(preorder, 
                          preStart + 1,            // Start of the left subtree in preorder
                          preStart + numsLeft,     // End of the left subtree in preorder
                          inorder, 
                          inStart,                 // Start of the left subtree in inorder
                          inRoot - 1,              // End of the left subtree in inorder
                          inMap);

    // Recursively build the right subtree
    root.right = buildTree(preorder, 
                           preStart + numsLeft + 1, // Start of the right subtree in preorder
                           preEnd,                  // End of the right subtree in preorder
                           inorder, 
                           inRoot + 1,              // Start of the right subtree in inorder
                           inEnd,                   // End of the right subtree in inorder
                           inMap);

    // Return the root node of the subtree
    return root;
}

public TreeNode buildTree(int[] preorder, int[] inorder) {
    // Map to store the indices of the elements in the inorder traversal for quick lookup
    Map<Integer, Integer> inMap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) 
        inMap.put(inorder[i], i); // Store value and its index in inorder

    // Call the recursive function with initial bounds
    TreeNode root = buildTree(preorder, 
                              0, preorder.length - 1, // Bounds for preorder traversal
                              inorder, 
                              0, inorder.length - 1, // Bounds for inorder traversal
                              inMap);
    return root; // Return the constructed binary tree
}

}
// @lc code=end

