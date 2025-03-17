/* https://leetcode.com/problems/remove-nth-node-from-end-of-list/

 * @lc app=leetcode id=19 lang=java
 *
 * [19] Remove Nth Node From End of List
 */

// @lc code=start

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

// Time:- 2(len)        Space:- O(1)
    public ListNode removeNthFromEnd_1(ListNode head, int n) {
        int len = 0; // Variable to store the length of the linked list
        int count = 1; // Counter to track the position of the current node

        ListNode mover = head;

        // Step 1: Calculate the length of the linked list
        while (mover != null) {
            mover = mover.next;
            len++;
        }

        // Step 2: If `n` is equal to the length of the list, remove the first node
        if (n == len) return head.next;

        // Reset `mover` to `head` to traverse again
        mover = head;

        // Step 3: Traverse to the node just before the one to be removed
        while (mover != null) {
            if (count == len - n) break; // Stop at (length - n)th node
            mover = mover.next;
            count++;
        }

        // Step 4: Remove the `nth` node from the end by skipping it
        mover.next = mover.next.next;

        // Step 5: Return the modified head of the list
        return head; 
    }

/***************************************************************************************/

// Time:- O(len)            Space:- O(1)
    public ListNode removeNthFromEnd_2(ListNode head, int n) {
        // Initialize two pointers: `fast` and `slow` both pointing to head
        ListNode fast = head;
        ListNode slow = head;

        // Move `fast` pointer `n` steps ahead
        while (n != 0) {
            fast = fast.next;
            n--;
        }

        // If `fast` becomes null, it means we have to remove the first node
        if (fast == null) return head.next;

        // Move both `fast` and `slow` one step at a time until `fast` reaches the last node
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // `slow` is now at the node just before the one to be removed
        slow.next = slow.next.next;

        // Return the modified head of the list
        return head;
    }

/***************************************************************************************/

    public ListNode removeNthFromEnd(ListNode head, int n) {
        return removeNthFromEnd_1(head,n);
    }

/***************************************************************************************/
}
// @lc code=end

