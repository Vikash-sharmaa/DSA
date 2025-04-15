package Trees.BST;

/*
 * @lc app=leetcode id=450 lang=java
 * 
 *
 * [450] Delete Node in a BST
 */

// @lc code=start

/*
 * Intuition:-
 
    The code deletes a node from a BST while preserving its properties. If the key is smaller or larger than 
    the current node's value, the function recursively moves to the left or right subtree. If the node to 
    delete has one or no child, it is replaced with its child or null. For a node with two children, the 
    smallest node (inorder successor) from the right subtree replaces it, and the successor is deleted 
    recursively. This approach ensures the BST remains valid after deletion.

 */

 /*

   Time Complexity: 
        O(h), where h is the height of the tree. In the worst case (unbalanced tree), 
        it could be O(n), where n is the number of nodes, as we might traverse all nodes. For a balanced tree, it is O(log n).
   Space Complexity: 
        O(h) for the recursion stack, where h is the height of the tree. In the worst case 
        (unbalanced tree), it could be O(n), and for a balanced tree, it would be O(log n).

  */

//import Trees.TreeNode;

class Solution {
    
    private TreeNode findMin(TreeNode node) {
        // Traverse to the leftmost node, which is the smallest in a BST
        while(node.left != null) {
            node = node.left;
        }
        return node; // Return the leftmost (minimum) node
    }
    
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null; // Base case: If tree is empty, return null
    
        if(root.val < key) {
            // If the key is greater than the current node's value, recurse into the right subtree
            root.right = deleteNode(root.right, key);
        } else if(root.val > key) {
            // If the key is smaller than the current node's value, recurse into the left subtree
            root.left = deleteNode(root.left, key);
        } else {
            // Node with the key found
            if(root.left == null) return root.right; // Case 1: No left child, return right subtree
            else if(root.right == null) return root.left; // Case 2: No right child, return left subtree
            else {
                // Case 3: Node has two children
                TreeNode successor = findMin(root.right); // Find the inorder successor (smallest in right subtree)
                root.val = successor.val; // Replace current node's value with successor's value
                root.right = deleteNode(root.right, successor.val); // Delete the successor node from right subtree
            }
        }
        return root; // Return the updated root node
    }
    

}
// @lc code=end

