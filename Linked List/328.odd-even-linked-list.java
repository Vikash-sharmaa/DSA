/* https://leetcode.com/problems/odd-even-linked-list/description/

 * @lc app=leetcode id=328 lang=java
 *
 * [328] Odd Even Linked List
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

/************************************************************************************************/

 class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

/************************************************************************************************/

class Solution {

/************************************************************************************************/
    public ListNode oddEvenList_1(ListNode head) {
        // Create a list to store values of the linked list nodes.
        List<Integer> lis = new ArrayList<>();
        
        // Pointer to traverse the original linked list.
        ListNode mover = head;  
        
        // Traverse the list and store all node values in the list.
        while (mover != null) {
            lis.add(mover.val);
            mover = mover.next; // Move to the next node.
        }

        // Create a dummy node to build the rearranged linked list.
        ListNode dummy = new ListNode(-1);
        ListNode dummyHead = dummy; // Keep track of the head of the new list.

        // First loop: Add all elements at odd indices (1-based index).
        for (int i = 0; i < lis.size(); i++) {
            if ((i + 1) % 2 != 0) { // If 1-based index is odd.
                dummy.next = new ListNode(lis.get(i)); // Create a new node.
                dummy = dummy.next; // Move to the next node.
            }
        }

        // Second loop: Add all elements at even indices (1-based index).
        for (int i = 0; i < lis.size(); i++) {
            if ((i + 1) % 2 == 0) { // If 1-based index is even.
                dummy.next = new ListNode(lis.get(i)); // Create a new node.
                dummy = dummy.next; // Move to the next node.
            }
        }

        // Return the new head of the modified linked list.
        return dummyHead.next;
    }

/************************************************************************************************/

    public ListNode oddEvenList_2(ListNode head) {
        // If the list is empty or has only one node, return it as is.
        if (head == null || head.next == null) return head;

        // List to store values in odd-even order.
        List<Integer> lis = new ArrayList<>();
        
        // Pointer to traverse the list for odd-indexed nodes (1-based index).
        ListNode mover = head;

        // Collect all values of nodes at odd positions.
        while (mover != null && mover.next != null) {
            lis.add(mover.val); // Store the odd-positioned node value.
            mover = mover.next.next; // Jump two steps ahead.
        }
        // If the list has an odd number of nodes, add the last node's value.
        if (mover != null) {
            lis.add(mover.val);
        }

        // Reset mover to the second node (first even-positioned node).
        mover = head.next;

        // Collect all values of nodes at even positions.
        while (mover != null && mover.next != null) {
            lis.add(mover.val); // Store the even-positioned node value.
            mover = mover.next.next; // Jump two steps ahead.
        }
        // If the list has an even number of nodes, add the last node's value.
        if (mover != null) {
            lis.add(mover.val);
        }

        // Reset mover to the start of the list.
        mover = head;
        int i = 0;

        // Overwrite the original linked list with values from the stored list.
        while (mover != null) {
            mover.val = lis.get(i); // Assign new values in odd-even order.
            i++;
            mover = mover.next; // Move to the next node.
        }

        return head; // Return the modified list.
    }

/************************************************************************************************/

    public ListNode oddEvenList_3(ListNode head) {
        // If the list is empty or has only one node, return it as is.
        if (head == null || head.next == null) return head;

        // Initialize pointers:
        ListNode odd = head;         // Points to the first odd-positioned node
        ListNode even = head.next;    // Points to the first even-positioned node
        ListNode evenHead = even;     // Stores the head of the even list

        // Traverse the list while even and even.next are not null
        while (even != null && even.next != null) {
            odd.next = even.next;  // Connect the current odd node to the next odd node
            odd = odd.next;        // Move the odd pointer forward

            even.next = odd.next;  // Connect the current even node to the next even node
            even = even.next;      // Move the even pointer forward
        }

        // Attach the even list after the last odd node
        odd.next = evenHead;

        // Return the modified list starting from the head
        return head;
    }
/************************************************************************************************/

    public ListNode oddEvenList(ListNode head) {
        return oddEvenList_3(head);
    }

/************************************************************************************************/
}
// @lc code=end

