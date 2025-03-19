/* https://leetcode.com/problems/linked-list-cycle/description/

 * @lc app=leetcode id=141 lang=java
 *
 * [141] Linked List Cycle
 */

// @lc code=start

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
 class Solution {

/************************************************************************************************************/
    // Time:- O(n)          Space:- O(n)

    public boolean hasCycle_1(ListNode head) {
        Set<ListNode> visited = new HashSet<>(); // HashSet to store visited nodes
        ListNode mover = head; // Pointer to traverse the linked list
    
        while (mover != null) { // Iterate through the list
            if (visited.contains(mover)) return true; // Cycle detected if node is already visited
    
            visited.add(mover); // Mark the current node as visited
            mover = mover.next; // Move to the next node
        }
    
        return false; // If traversal completes, no cycle is found
    }
    

/************************************************************************************************************/
    // Time:- O(n)          Space:- O(1)

    // If there is a cycle, fast will eventually "catch up" with slow, just like a 
    // faster runner will eventually lap a slower runner on a circular track.

    // the total distance difference between slow and fast should always decrease by 1

    /*
        The fast pointer moves 2 steps while the slow pointer moves 1 step, reducing their 
        distance by 1 in each iteration. Since the maximum distance within a cycle 
        is K (cycle length), they will meet in at most K steps if a cycle exists. 
        This ensures O(N) time complexity for cycle detection.
     */
    public boolean hasCycle_2(ListNode head) {
        // If the list is empty or has only one node, there can't be a cycle
        if (head == null || head.next == null) return false;
    
        ListNode fast = head; // Fast pointer moves two steps at a time
        ListNode slow = head; // Slow pointer moves one step at a time
    
        // Traverse the list with two pointers
        while (fast != null && fast.next != null) {  
            fast = fast.next.next; // Move fast pointer by 2 steps
            slow = slow.next; // Move slow pointer by 1 step
    
            // If slow and fast meet, a cycle is detected
            if (slow == fast) return true;
        }
    
        return false; // If traversal ends, there is no cycle
    }

/************************************************************************************************************/

    public boolean hasCycle(ListNode head) {
        return hasCycle_1(head);
    }
    
/************************************************************************************************************/

}
// @lc code=end

