/*
 * @lc app=leetcode id=206 lang=java
 *
 * [206] Reverse Linked List
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.Deque;

/***************************************************************************************/

class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

/***************************************************************************************/

class Solution {

/***************************************************************************************/
// Time:- O(2(len))         Space:- O(1)
    public ListNode reverseList_1(ListNode head) {
        Deque<Integer> stack = new ArrayDeque<>();
        ListNode mover=head;
        // Traverse the LL and store the elements in a stack to have them in a reverse order
        while(mover!=null){
            stack.offerLast(mover.val);
            mover=mover.next;
        }

        // Traverse the LL again and replace its elements with stack's tops
        mover=head;
        while(mover!=null){
            mover.val=stack.pollLast();
            mover=mover.next;
        }
        return head;
    }

/***************************************************************************************/
// Time:- O(len)        Space:- O(1)        ITERATIVE

    public ListNode reverseList_2(ListNode head) {
        ListNode prev=null;
        ListNode next=null;
        ListNode curr=head;

        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }

/***************************************************************************************/
    // Time:- O(len)        Space:- O(1)        ITERATIVE

    public ListNode reverseList_3(ListNode head) {
        // Base case: If the list is empty or has only one node, return head (already reversed).
        if (head == null || head.next == null) return head;
    
        // Recursively reverse the rest of the linked list and store the new head.
        ListNode newHead = reverseList_3(head.next);
    
        // 'front' is the next node in the original order.
        ListNode front = head.next;
    
        // Reverse the link: Make 'front' point back to 'head'.
        front.next = head;
    
        // Break the original forward link to avoid cycles.
        head.next = null;
    
        // Return the new head of the reversed list.
        return newHead;
    }

/***************************************************************************************/
    
    public ListNode reverseList(ListNode head) {
        return reverseList_3(head);
    }

/***************************************************************************************/
}
// @lc code=end

