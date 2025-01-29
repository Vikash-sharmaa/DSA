package Trees.BST;
/*
 * @lc app=leetcode id=99 lang=java
 *
 * [99] Recover Binary Search Tree
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Trees.TreeNode;





class Solution {

        TreeNode prev ;
        TreeNode first;
        TreeNode middle;
        TreeNode last;
/******************************************************************************************************************************************/

    // Method - 1

    // Time:- O(log(n))        Space:- O(n)

    void inorderTraversal(TreeNode root, List<Integer> lis) {
        if (root == null) return; // Base case: if the node is null, return
    
        inorderTraversal(root.left, lis); // Recursively traverse the left subtree
        lis.add(root.val); // Add the value of the current node to the list
        inorderTraversal(root.right, lis); // Recursively traverse the right subtree
    }
    
    void inorderTraversalAfterSorting(TreeNode root, List<Integer> lis, int[] i) {
        if (root == null) return; // Base case: if the node is null, return
    
        inorderTraversalAfterSorting(root.left, lis, i); // Recursively traverse the left subtree

    
        // If the current node's value is not equal to the value at the current index in the list
        if (lis.get(i[0]) != root.val) {
            root.val = lis.get(i[0]); // Update the node's value to the sorted value
        }
        i[0]++; // Move to the next index

    
        inorderTraversalAfterSorting(root.right, lis, i); // Recursively traverse the right subtree
    }
    
    public void recoverTreeNaive(TreeNode root) {
        List<Integer> lis = new ArrayList<>(); // Create a list to store the inorder traversal
        inorderTraversal(root, lis); // Perform inorder traversal and store the values in the list
    
        Collections.sort(lis); // Sort the list to get the correct order of values
    
        inorderTraversalAfterSorting(root, lis, new int[]{0}); // Update the tree nodes with the sorted values
    }

/******************************************************************************************************************************************/

    public void inorderTraversal(TreeNode root){
        if(root==null) return;

        inorderTraversal(root.left);
        if(prev!=null && prev.val>root.val){
            if(first==null){
                first=prev;
                middle=root;
            }else{
                last=root;
            }
        }
        prev=root;
        inorderTraversal(root.right);
    }

    public void recoverTree(TreeNode root){
        
        prev=new TreeNode(Integer.MIN_VALUE);

        first=last=middle=null;

        inorderTraversal(root);

        if(first!=null && last!=null){
            int temp=first.val;
            first.val=last.val;
            last.val=temp;
        }else if(first!=null && middle!=null){
            int temp=first.val;
            first.val=middle.val;
            middle.val=temp;
        }
    }

/******************************************************************************************************************************************/
}
// @lc code=end