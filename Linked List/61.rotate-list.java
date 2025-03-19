/*
 * @lc app=leetcode id=61 lang=java
 *
 * [61] Rotate List
 */

// @lc code=start


// class ListNode {
//     int val;
//     ListNode next;
//     ListNode() {}
//     ListNode(int val) { this.val = val; }
//     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
// }

class Solution {
    int length(ListNode head){
        ListNode mover=head;
        int len=0;
        while(mover!=null){
            mover=mover.next;
            len++;
        }
        return len;
    }

    public ListNode rotateRight_1(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;
    
        // Find the length of the list
        int n = length(head);
        k = k % n; // Normalize k
        if (k == 0) return head; // If k is a multiple of n, no rotation needed
    
        ListNode mover = head;
        int count = 1;
    
        // Move to the (n-k)th node
        while (count != (n - k)) {
            mover = mover.next;
            count++;
        }
    
        // Break the list at (n-k)th node
        ListNode newHead = mover.next;
        mover.next = null;
    
        // Find the last node of the new list
        ListNode temp = newHead;
        while (temp.next != null) {
            temp = temp.next;
        }
    
        // Connect the last node to the old head
        temp.next = head;
    
        return newHead;
    }

    public ListNode rotateRight_2(ListNode head, int k) {
        // Step 1: Handle edge cases (empty list or single-node list)
        if (head == null || head.next == null) return head;
    
        // Step 2: Find the length of the linked list
        int n = length(head);
    
        // Step 3: Normalize k to avoid unnecessary full rotations
        k = k % n;
        if (k == 0) return head; // If k is a multiple of n, return the original list
    
        // Step 4: Traverse to the last node
        ListNode mover = head;
        while (mover.next != null) {
            mover = mover.next;
        }
    
        // Step 5: Connect the last node to the head, forming a circular linked list
        mover.next = head;
    
        // Step 6: Reset mover to head to start traversing again
        mover = head;
    
        // Step 7: Traverse to the (n-k)th node (new tail of the rotated list)
        int count = 1;
        while (count < (n - k)) {
            mover = mover.next;
            count++;
        }
    
        // Step 8: The next node after mover becomes the new head
        head = mover.next;
    
        // Step 9: Break the circular link by setting mover.next to null
        mover.next = null;
    
        // Step 10: Return the new head of the rotated list
        return head;
    }

    public ListNode rotateRight(ListNode head, int k) {
        return rotateRight_2(head, k);
    }
    
}
// @lc code=end

