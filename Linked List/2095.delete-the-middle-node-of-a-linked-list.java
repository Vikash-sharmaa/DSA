/* https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/description/

 * @lc app=leetcode id=2095 lang=java
 *
 * [2095] Delete the Middle Node of a Linked List
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
/************************************************************************************************************/
    // Time:- O(n) + O(n)         Space:- O(1)

    int getLength(ListNode head){
        ListNode mover = head;
        int count=0;
        while(mover!=null){
            mover=mover.next;
            count++;
        }
        return count;
    }
    public ListNode deleteMiddle_1(ListNode head) {
        // If the list is empty or has only one node, return null (since deleting the middle removes the only node)
        if (head == null || head.next == null) return null;  
        
        ListNode mover = head; // Pointer to traverse the list
        
        int len = getLength(head); // Get the total length of the linked list
        int mid = len / 2; // Find the middle index (0-based)
        
        int count = 1; // Counter to track the current position (1-based indexing)
        while (count != mid) { // Move mover to the node just before the middle
            mover = mover.next;
            count++;
        }
        
        // Remove the middle node by skipping it
        mover.next = mover.next.next;  
        
        return head; // Return the modified list
    }
/************************************************************************************************************/
    //  Time: O(n)      Space: O(1)

    public ListNode deleteMiddle_2(ListNode head) {
        // If the list is empty or has only one node, return null (since deleting the middle removes the only node)
        if (head == null || head.next == null) return null;

        ListNode slow = head; // Slow pointer moves one step at a time
        ListNode fast = head; // Fast pointer moves two steps at a time
        ListNode prevSlow = null; // Keeps track of the node before the middle node

        // Traverse the list with two pointers to find the middle node
        while (fast != null && fast.next != null) {
            prevSlow = slow; // Store previous node before moving slow
            fast = fast.next.next; // Move fast pointer by 2 steps
            slow = slow.next; // Move slow pointer by 1 step
        }

        // Remove the middle node by skipping it
        prevSlow.next = prevSlow.next.next;

        return head; // Return the modified list
    }
    
/************************************************************************************************************/
    //  Time: O(n)      Space: O(1)

    public ListNode deleteMiddle_3(ListNode head) {
        // If the list is empty or has only one node, return null (since deleting the middle removes the only node)
        if (head == null || head.next == null) return null;
    
        ListNode slow = head; // Slow pointer moves one step at a time
        ListNode fast = head; // Fast pointer moves two steps at a time
    
        // Skip one iteration  for slow , so that slow point to the node before the middle, so we can skip 
        // the middle.
    
        int flag = 0; // A flag to control when to move the slow pointer
    
        // Traverse the list with two pointers to find the middle node
        while (fast != null && fast.next != null) {
            fast = fast.next.next; // Move fast pointer by 2 steps
    
            if (flag == 0) 
                flag = 1; // Set flag to indicate the first iteration
            else 
                slow = slow.next; // Move slow pointer by 1 step after the first iteration
        }
    
        // Remove the middle node by skipping it
        slow.next = slow.next.next;
    
        return head; // Return the modified list
    }
    

/************************************************************************************************************/

    public ListNode deleteMiddle(ListNode head) {
        //return deleteMiddle_1(head);
        return deleteMiddle_3(head);
    }
/************************************************************************************************************/
}
// @lc code=end

