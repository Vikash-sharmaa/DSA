import java.util.ArrayList;
import java.util.List;

class Node
{
    int data;
    Node next,prev;
    
    Node(int x){
        data = x;
        next = null;
        prev = null;
    }
}


class Solution {
/************************************************************************************************************/
    // Time: O(n^2)     Space: O(1)

    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum_1(int target, Node head) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        Node first = head;
    
        // Outer loop: Traverse each node
        while (first != null) {
            Node second = first.next; // Inner loop starts from the next node
    
            // Inner loop: Check each subsequent node for a pair
            while (second != null) {
                if (first.data + second.data == target) {
                    res.add(new ArrayList<>(List.of(first.data, second.data))); // Add valid pair
                }
                second = second.next; // Move second pointer forward
            }
    
            first = first.next; // Move first pointer forward
        }
    
        return res;
    }

/************************************************************************************************************/
    // Time: O(n)     Space: O(1)
    
    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum_2(int target, Node head) {
        // Result list to store pairs that sum up to the target
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        
        // Two-pointer approach: start (beginning) and end (last node)
        Node start = head;
        Node end = head;
        
        // Move 'end' to the last node of the doubly linked list
        while (end.next != null) {
            end = end.next;
        }
        
        // Iterate while 'start' is before 'end'
        while (start != end && start.prev != end) {  // while(start.data < end.data) - also works.
            int sum = start.data + end.data; // Calculate current sum
    
            if (sum == target) {  // If pair matches the target
                res.add(new ArrayList<>(List.of(start.data, end.data))); // Add pair to result list
                start = start.next;  // Move start forward
                end = end.prev;  // Move end backward
            } else if (sum < target) {  
                start = start.next;  // If sum is too small, move start forward to increase sum
            } else {
                end = end.prev;  // If sum is too large, move end backward to decrease sum
            }
        }
        
        return res;  // Return list of pairs
    }

/************************************************************************************************************/

    // handler
    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target, Node head) {
        return findPairsWithGivenSum_2(target, head);
    }

/************************************************************************************************************/

}