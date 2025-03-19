/*
 * @lc app=leetcode id=21 lang=java
 *
 * [21] Merge Two Sorted Lists
 */

// @lc code=start

class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

class Solution {

/************************************************************************************************************/
    // Time:- O(n)         Space:- O(n)

    public ListNode mergeTwoLists_1(ListNode list1, ListNode list2) {
        // Initialize pointers to traverse both lists
        ListNode mover1 = list1;
        ListNode mover2 = list2;
    
        // Create a dummy node to simplify list merging
        ListNode res = new ListNode(-1);
        ListNode result = res; // Keeps track of the head of the merged list
    
        // Traverse both lists while neither is fully processed
        while (mover1 != null && mover2 != null) {
            if (mover1.val == mover2.val) {
                // If values are equal, add both nodes to the merged list
                res.next = new ListNode(mover1.val);
                res = res.next;
                res.next = new ListNode(mover2.val);
                res = res.next;
    
                // Move both pointers forward
                mover1 = mover1.next;
                mover2 = mover2.next;
            } else if (mover1.val < mover2.val) {
                // If list1's node is smaller, add it to the merged list
                res.next = new ListNode(mover1.val);
                res = res.next;
                mover1 = mover1.next;
            } else {
                // If list2's node is smaller, add it to the merged list
                res.next = new ListNode(mover2.val);
                res = res.next;
                mover2 = mover2.next;
            }
        }
    
        // If there are remaining nodes in list1, add them to the merged list
        while (mover1 != null) {
            res.next = new ListNode(mover1.val);
            res = res.next;
            mover1 = mover1.next;
        }
    
        // If there are remaining nodes in list2, add them to the merged list
        while (mover2 != null) {
            res.next = new ListNode(mover2.val);
            res = res.next;
            mover2 = mover2.next;
        }
    
        // Return the merged list, skipping the dummy node
        return result.next;
    }    

/************************************************************************************************************/
    // Time:- O(n)        Space:- O(1)

    public ListNode mergeTwoLists_2(ListNode list1, ListNode list2) {
        // Create a dummy node to simplify list merging
        ListNode res = new ListNode(-1);
        ListNode result = res; // Pointer to track the merged list head
    
        ListNode mover1 = list1;
        ListNode mover2 = list2;
    
        // Merge both lists in sorted order
        while (mover1 != null && mover2 != null) {
            if (mover1.val <= mover2.val) {
                res.next = mover1;
                mover1 = mover1.next;
            } else {
                res.next = mover2;
                mover2 = mover2.next;
            }
            res = res.next; // Move the result pointer forward
        }
    
        // If there are remaining nodes in either list, append them
        if (mover1 != null) res.next = mover1;
        
        if (mover2 != null) res.next = mover2;

        // Return the merged list, skipping the dummy node
        return result.next;
    }
    
/************************************************************************************************************/

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        return mergeTwoLists_2(list1, list2);
    }

/************************************************************************************************************/
}
// @lc code=end

