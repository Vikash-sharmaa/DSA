/*
 * @lc app=leetcode id=160 lang=java
 *
 * [160] Intersection of Two Linked Lists
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
         val = x;
        next = null;
    }
}

class Solution {
/************************************************************************************************************/
    // Time:- O(n1+n2)          Space:- O(n)

    public ListNode getIntersectionNode_1(ListNode headA, ListNode headB) {
        // Create a HashMap to store visited nodes from List A
        Map<ListNode, ListNode> map = new HashMap<>();
        
        ListNode moverA = headA;
        ListNode moverB = headB;
        ListNode prev = null; // Keeps track of the previous node (not needed for this solution)

        // Traverse List A and store each node in the map
        while (moverA != null) {
            map.put(moverA, prev); // Store node in the map (prev is not used later)
            prev = moverA; // Move prev pointer forward
            moverA = moverA.next; // Move to the next node
        }

        prev = null; // Reset prev (not actually required)

        // Traverse List B and check if any node is already in the map (i.e., intersection found)
        while (moverB != null) {
            if (map.containsKey(moverB)) return moverB; // Found intersection, return the node
            map.put(moverB, prev); // Store node in the map (again, prev is not needed)
            prev = moverB; // Move prev pointer forward
            moverB = moverB.next; // Move to the next node
        }

        return null; // No intersection found, return null
    }


/************************************************************************************************************/


    int getLength(ListNode head){
        int len=0;
        ListNode mover=head;
        while(mover!=null){
            len++;
            mover=mover.next;
        }
        return len;
    }

    // Time: O(n1+n2)       Space: O(1)
    public ListNode getIntersectionNode_2(ListNode headA, ListNode headB) {
        // Get the length of both linked lists
        int len1 = getLength(headA);
        int len2 = getLength(headB);

        // Calculate the difference in lengths
        int diff = Math.abs(len1 - len2);
        
        ListNode moverA = headA;
        ListNode moverB = headB;

        // Move the pointer of the longer list ahead by 'diff' steps
        while (len1 > len2 && diff != 0) {
            moverA = moverA.next;
            diff--;
        }

        while (len2 > len1 && diff != 0) {
            moverB = moverB.next;
            diff--;
        }

        // Traverse both lists together until intersection is found
        while (moverA != null) {
            if (moverA == moverB) return moverA; // Intersection found, return the node

            moverA = moverA.next;
            moverB = moverB.next;
        }

        return null; // No intersection found
    }

/************************************************************************************************************/
// Time: O(N + M)    Space: O(1)
    public ListNode getIntersectionNode_3(ListNode headA, ListNode headB) {
        ListNode t1 = headA; // Pointer for List A
        ListNode t2 = headB; // Pointer for List B

        // Traverse both lists until intersection is found or both become null
        while (t1 != t2) { 
            t1 = t1.next; // Move t1 forward
            t2 = t2.next; // Move t2 forward

            // If they meet after moving, return the intersection node
            if (t1 == t2) return t1;

            // If t1 reaches the end, redirect it to headB
            if (t1 == null) t1 = headB;

            // If t2 reaches the end, redirect it to headA
            if (t2 == null) t2 = headA;
        }

        return null; // If no intersection is found
    }

/************************************************************************************************************/

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        return getIntersectionNode_3(headA,headB);
    }

}
// @lc code=end

