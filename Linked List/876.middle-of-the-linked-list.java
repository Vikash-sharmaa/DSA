/* https://leetcode.com/problems/middle-of-the-linked-list/

 * @lc app=leetcode id=876 lang=java
 *
 * [876] Middle of the Linked List
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
/**************************************************************************************************************************************************************************/
//  Time: O(n)+O(n)         Space: O(1)

    int getLength(ListNode head){
        int len=0;
        ListNode mover=head;
        while(mover!=null){
            len++;
            mover=mover.next;
        }
        return len;
    }

    public ListNode middleNode_1(ListNode head) {
        // Get the total length of the linked list
        int len = getLength(head);
        
        // Calculate the middle index (0-based)
        int mid = len / 2;
        int count = 0; // Counter to track current position
        
        ListNode mover = head; // Pointer to traverse the list
    
        // Move 'mover' to the middle of the list
        while (count != mid) {
            mover = mover.next;
            count++;
        }
    
        return mover; // Return the middle node
    }

/**************************************************************************************************************************************************************************/
//  Time: O(n)      Space: O(1)

    public ListNode middleNode_2(ListNode head) {
        ListNode fast = head; // Fast pointer moves twice as fast
        ListNode slow = head; // Slow pointer moves one step at a time

        // Traverse the list with two pointers
        while (fast != null && fast.next != null) {         // for 2nd middle   for 1st middle - while (fast.next != null && fast.next.next != null)
            fast = fast.next.next; // Move fast pointer by 2 steps
            slow = slow.next; // Move slow pointer by 1 step
        }

        return slow; // Slow pointer will be at the middle when fast reaches the end
    }

/**************************************************************************************************************************************************************************/

    public ListNode middleNode(ListNode head) {
        return middleNode_2(head);
    }

/**************************************************************************************************************************************************************************/

}
// @lc code=end

