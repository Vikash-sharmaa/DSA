package Trees.Binary_Trees;

/*
 * 
 * Everything simlar to right view 
 * just traverse BFS from left to right and take the first element of each level
 */
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import Trees.TreeNode;


class Solution {
    // Function to return list containing elements of left view of binary tree.
    ArrayList<Integer> leftView(TreeNode root) {
        // code here
        ArrayList<Integer> res=new ArrayList<>();
        ArrayList<Integer> temp=new ArrayList<>();
        
        if(root==null) return res;
        
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offerLast(root);
        queue.offerLast(new TreeNode(Integer.MAX_VALUE));
        
        while(!queue.isEmpty()){
            TreeNode front = queue.pollFirst();
            
            if(front.val==Integer.MAX_VALUE){
                res.add(temp.get(0));
                
                temp.clear();
                
                if(!queue.isEmpty()) queue.offerLast(new TreeNode(Integer.MAX_VALUE));
            }else{
                temp.add(front.val);
                
                if(front.left!=null) queue.offerLast(front.left);
                if(front.right!=null) queue.offerLast(front.right);
            }
        }
        return res;
    }
}