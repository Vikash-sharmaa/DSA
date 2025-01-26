package Trees.BST;
import Trees.TreeNode;
/*
    Kth Largest to Kth Smallest Conversion: The kth largest element corresponds to the (n−k+1)-th smallest element in the sorted order, 
    where n is the total number of nodes. */
/*
    Time Complexity:
        Counting nodes: O(n)
        Finding kth smallest: O(n)
        Total: O(n)
    Space Complexity:
        Recursion stack for in-order traversal: 
        O(h), where h is the height of the tree.
 */
class Solution {
    void countTotalNodes(TreeNode root, int[] totalCount) {
        if (root == null) return;
    
        countTotalNodes(root.left, totalCount); // Recursively count nodes in the left subtree
        totalCount[0]++; // Increment count for the current node
        countTotalNodes(root.right, totalCount); // Recursively count nodes in the right subtree
    }
    
    void findKthSmallest(TreeNode root, int k, int[] count, int[] result) {
        if (root == null) return;
    
        findKthSmallest(root.left, k, count, result); // Traverse the left subtree
        count[0]++; // Increment count when visiting the current node
    
        if (count[0] == k) { // If the count matches k, we found the kth smallest element
            result[0] = root.val;
            return; // Stop further recursion
        }
    
        findKthSmallest(root.right, k, count, result); // Traverse the right subtree
    }
    
    // Return the Kth largest element in the given BST rooted at 'root'
    public int kthLargest(TreeNode root, int k) {
        if (root == null) return -1; // Handle edge case of an empty tree
    
        int[] totalCount = new int[1]; // Array to store the total number of nodes
        countTotalNodes(root, totalCount); // Get the total number of nodes in the BST
    
        int totalElements = totalCount[0];
        k = totalElements - k + 1; // Convert kth largest to kth smallest
    
        int[] count = new int[1]; // Array to track the current position during traversal
        int[] result = new int[1]; // Array to store the result
    
        findKthSmallest(root, k, count, result); // Find the kth smallest element
        return result[0];
    }

}