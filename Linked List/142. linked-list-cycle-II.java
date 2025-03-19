/*
 * @lc app=leetcode id=142 lang=java
 *
 * [142] Linked List Cycle II
 */

// @lc code=start

import java.util.HashSet;
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
    // Time:- O(n)         Space:- O(n)

    public ListNode detectCycle_1(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        ListNode mover=head;

        while(mover!=null){
            if(visited.contains(mover)) return mover;

            visited.add(mover);
            mover=mover.next;
        }
        return null;
    }
/************************************************************************************************************/
    // Time:- O(n)         Space:- O(1)

    /*
        Cycle Detection (First Phase)
            * If there's a cycle, fast and slow will eventually meet because fast moves 2 steps while slow moves 1 step, 
                reducing their distance by 1 per iteration.
        Finding the Cycle Start (Second Phase)
            * Once fast meets slow, we reset slow to head.
            * Now, moving both fast and slow one step at a time, they meet at the starting node of the cycle.
            * This works because the distance from the head to the cycle start equals the distance from the meeting point to the cycle start. 
     */

// This works because the distance from the head to the cycle start equals the distance from the meeting point to the cycle start.
    public ListNode detectCycle_2(ListNode head) {
        ListNode fast = head; // Fast pointer moves 2 steps at a time
        ListNode slow = head; // Slow pointer moves 1 step at a time
    
        // First phase: Detect if a cycle exists using Floyd's Cycle Detection
        while (fast != null && fast.next != null) {  
            fast = fast.next.next; // Move fast pointer by 2 steps
            slow = slow.next;      // Move slow pointer by 1 step
    
            // If fast and slow meet, a cycle is detected
            if (fast == slow) {  
                slow = head; // Reset slow to head to find cycle entry point
    
                // Second phase: Find the start of the cycle
                while (fast != slow) {  
                    fast = fast.next; // Move fast pointer by 1 step
                    slow = slow.next; // Move slow pointer by 1 step
                }
    
                return slow; // The node where they meet is the cycle start
            }
        }
    
        return null; // No cycle found
    } 
/************************************************************************************************************/
     
    public ListNode detectCycle(ListNode head) {
        return detectCycle_1(head);
    }
}
// @lc code=end

