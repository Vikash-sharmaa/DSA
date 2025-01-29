package Trees.BST;
/*
 * @lc app=leetcode id=1008 lang=java
 *
 * 
 * 
 * 
 * 
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import Trees.TreeNode;


// import Trees.TreeNode;

class Solution {

/**********************************************************************************************************************************************************/

    // Take first element make it root and then Take each element from the array and insert it in the BST having the root you just created.
    // Time:- O(n^2)  || Space:- O(n)
    TreeNode insertInBST(TreeNode root,int val){
        if(root==null){
            return new TreeNode(val);
        }

        if(root.val<val){
            root.right=insertInBST(root.right, val);
        }else{
            root.left=insertInBST(root.left, val);
        }
        return root;
    }

    public TreeNode bstFromPreorderNaive(int[] preorder) {
        TreeNode root=new TreeNode(preorder[0]);
        for(int i=1;i<preorder.length;i++){
            insertInBST(root,preorder[i]);
        }
        return root;
    }

/**********************************************************************************************************************************************************/

    // Sorting the preorder array will give a inorder array for a bst
    // Then you have inorder and preorder array - create a BST / BT using these 

    // Time:- O(nlogn) + O(n)   ||  Space:- O(n)

    public TreeNode createFromInorderAndPreorder(int[] preorder,int preStart,int preEnd,int[] inorder,int inStart,int inEnd,Map<Integer,Integer>inMap){
        if(preStart>preEnd || inStart>inEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = inMap.get(root.val);
        int numsLeft= inIndex - inStart;

        root.left = createFromInorderAndPreorder(preorder, preStart+1, preStart+numsLeft, inorder, inStart, inIndex-1, inMap);
        root.right = createFromInorderAndPreorder(preorder, preStart+numsLeft+1, preEnd, inorder, inIndex+1, inEnd, inMap);
        return root;
    }

    public TreeNode bstFromPreorderNaive2(int[] preorder) {
        int[] inorder = Arrays.copyOf(preorder, preorder.length);
        Arrays.sort(inorder);

        Map<Integer,Integer> inMap=new HashMap<>();
        for(int i=0;i<inorder.length;i++){
            inMap.put(inorder[i],i);
        }

        TreeNode root=createFromInorderAndPreorder(preorder,0,preorder.length-1,inorder,0,inorder.length-1,inMap);
        return root;
    }

/**********************************************************************************************************************************************************/

/*

    Intuition:-
        The preorder traversal gives the root first, followed by left and right subtrees.
        Each recursive call constructs a subtree. The bound ensures that nodes in the left and right subtrees satisfy the BST property.
        The i array tracks the current index in the preorder array across recursive calls.

    Key Points:-
        The left subtree is constructed with the root's value as the upper bound.
        The right subtree is constructed with the parent's bound.
        The array i is used to maintain the current index across recursion.

 */
    // Method to construct a Binary Search Tree (BST) from a preorder traversal.
    public TreeNode bstFromPreorder(int[] preorder, int bound, int[] i) {

        // Base case: If we have processed all elements or the current value exceeds the bound, return null.
        if (i[0] == preorder.length || preorder[i[0]] > bound) 
            return null;

        // Create a new TreeNode with the current preorder element and increment the index.
        TreeNode root = new TreeNode(preorder[i[0]++]);

        // Recursively construct the left subtree with the current root's value as the upper bound.
        root.left = bstFromPreorder(preorder, root.val, i);

        // Recursively construct the right subtree with the current bound.
        root.right = bstFromPreorder(preorder, bound, i);

        // Return the constructed subtree's root.
        return root;
    }

    // Wrapper method to initialize and start the BST construction.
    public TreeNode bstFromPreorder(int[] preorder) {

        // Use an array to keep track of the current index (mutable for recursion).
        int[] i = new int[1];

        // Call the helper function with the preorder array and an initial upper bound of Integer.MAX_VALUE.
        return bstFromPreorder(preorder, Integer.MAX_VALUE, i);
    }


/**********************************************************************************************************************************************************/


}
// @lc code=end

