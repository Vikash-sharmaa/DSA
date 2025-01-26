package Trees.BST;
/*
 * @lc app=leetcode id=230 lang=java
 *
 * [230] Kth Smallest Element in a BST
 */

import java.util.ArrayList;
import java.util.List;

import Trees.TreeNode;



class Solution {
    // Time:- O(n)  Space:- O(n)
    /* 
        Inorder traversal of a BST gives sorted elements 
        get (k-1)th element from the temp
    */
    void inOrder(TreeNode root, List<Integer> nums) {
        if (root == null) return; 
        // Base case: If the node is null, simply return.
    
        inOrder(root.left, nums); 
        // Recursive call to traverse the left subtree.
    
        nums.add(root.val); 
        // Add the current node's value to the list.
    
        inOrder(root.right, nums); 
        // Recursive call to traverse the right subtree.
    }
    

    // Time: O(n)   Space:- O(1)

    /* 
        Inorder traversal of a BST gives sorted elements. 
        Passing `count` and `res` as arrays ensures their values persist across recursive calls.
        * If `count[0] == k`, we have found our answer and return immediately.
    */
    void inOrder(TreeNode root, int k, int[] count, int[] res) {
        if (root == null) return; 
        // Base case: If the node is null, return immediately (end of a branch).

        inOrder(root.left, k, count, res); 
        // Recursively traverse the left subtree to process smaller values first.

        count[0]++; 
        // Increment the count after visiting the current node.

        if (count[0] == k) { 
            // If the count matches k, we have found the kth smallest element.
            res[0] = root.val; 
            // Store the kth smallest value in the result array.
            return; 
            // Exit recursion early since the desired element is found.
        }

        inOrder(root.right, k, count, res); 
        // Recursively traverse the right subtree to process larger values.
    }



    public int kthSmallest(TreeNode root, int k) {
        List<Integer> nums=new ArrayList<>();
        inOrder(root, nums);
        
        //return nums.get(k-1);
        int[] count=new int[1];
        int[] res=new int[1];
        inOrder(root, k, count ,res);
        return res[0];
    }



}
// @lc code=end

