package Trees.BST;
import Trees.TreeNode;


/**********************************************************************************************************************************************************/



class Solution1 {


    // Helper function to check if a tree is a BST
    private boolean isBST(TreeNode root, TreeNode minNode, TreeNode maxNode) {
        if (root == null) return true;

        // Check the BST property: left < root < right
        if ((minNode != null && root.val <= minNode.val) ||
            (maxNode != null && root.val >= maxNode.val)) {
            return false;
        }

        // Recursively check the left and right subtrees
        return isBST(root.left, minNode, root) && isBST(root.right, root, maxNode);
    }
    


    // Helper function to calculate the size of a tree
    private int treeSize(TreeNode root) {
        if (root == null) return 0;

        // Total size = size of left subtree + size of right subtree + 1 (current node)
        return 1 + treeSize(root.left) + treeSize(root.right);
    }
    


    // Function to find the largest BST in the binary tree
    public int largestBST(TreeNode root) {
        if (root == null) return 0;

        // If the current tree is a BST, calculate its size
        if (isBST(root, null, null)) {
            return treeSize(root);
        }

        // Otherwise, find the largest BST in the left and right subtrees
        int leftLargest = largestBST(root.left);
        int rightLargest = largestBST(root.right);

        // Return the larger size
        return Math.max(leftLargest, rightLargest);
    }
}




/**********************************************************************************************************************************************************/




class Solution {

    // Helper class to store information about the subtree
    class NodeInfo {
        boolean isBST; // Is the subtree a BST
        int size;      // Size of the largest BST
        int min;       // Minimum value in the subtree
        int max;       // Maximum value in the subtree

        NodeInfo(boolean isBST, int size, int min, int max) {
            this.isBST = isBST;
            this.size = size;
            this.min = min;
            this.max = max;
        }
    }
    


    // Helper function to compute information about the subtree
    private NodeInfo largestBSTHelper(TreeNode root) {
        // Base case: if the node is null, it is a valid BST with size 0
        if (root == null) {
            return new NodeInfo(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        // Recursively compute information for the left and right subtrees
        NodeInfo leftInfo = largestBSTHelper(root.left);
        NodeInfo rightInfo = largestBSTHelper(root.right);

        // Check if the current subtree is a BST
        if (leftInfo.isBST && rightInfo.isBST && root.val > leftInfo.max && root.val < rightInfo.min) {
            // Current subtree is a BST
            int size = 1 + leftInfo.size + rightInfo.size; // Total size of this BST
            int min = Math.min(root.val, leftInfo.min);    // Minimum value in the subtree
            int max = Math.max(root.val, rightInfo.max);   // Maximum value in the subtree
            return new NodeInfo(true, size, min, max);     // Return updated information
        }

        // If not a BST, return the size of the largest BST found so far
        return new NodeInfo(false, Math.max(leftInfo.size, rightInfo.size), 0, 0);
    }



    // Main function to find the largest BST
    public int largestBST(TreeNode root) {
        return largestBSTHelper(root).size; // Start the recursion
    }
}



/**********************************************************************************************************************************************************/
