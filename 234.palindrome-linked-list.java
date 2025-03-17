/*
 * @lc app=leetcode id=234 lang=java
 *
 * [234] Palindrome Linked List
 */

// @lc code=start
//**

import java.util.ArrayDeque;
import java.util.Deque;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
/***************************************************************************************/

   public boolean isPalindrome_1(ListNode head) {
       Deque<Integer> stack=new ArrayDeque<>();
       ListNode mover=head;

       while(mover!=null){
           stack.offerLast(mover.val);
           mover=mover.next;
       }

       mover=head;
       while(mover!=null){
           if(mover.val!=stack.pollLast()) return false;
           mover=mover.next;
       }
       return true;
   }
/***************************************************************************************/

// ListNode reversedHead = reverseList(head); - cant do this - else original LL will be reversed
   public ListNode reverseList(ListNode head) {
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

   public boolean isPalindrome_2(ListNode head) {
       ListNode slow = head;
       ListNode fast = head;

       // Step 1: Find the middle of the linked list using slow and fast pointers
       // when we want to stop slow at first middle of even list - so we stop first at second last 
       while (fast.next != null && fast.next.next != null) { // Move fast by 2 steps and slow by 1 step
           fast = fast.next.next;
           slow = slow.next;
       }

       // Step 2: Reverse the second half of the linked list
       ListNode slowReversed = reverseList(slow.next); // half list getting reversed but slow.next pointing the same list - so when reversed points to its reversed head
       ListNode first = head;  // Pointer for the first half
       ListNode second = slowReversed; // Pointer for the reversed second half

       // Step 3: Compare first half with reversed second half
       while (second != null) {  // Only need to check the second half
           if (second.val != first.val) { // If values don't match, it's not a palindrome
               reverseList(slowReversed); // Restore the original list before returning
               return false;
           }
           second = second.next;
           first = first.next;
       }

       // Step 4: Restore the reversed half back to its original order
       reverseList(slowReversed);

       return true; // If all values matched, it's a palindrome
   }


/***************************************************************************************/


   public boolean isPalindrome(ListNode head) {
       return isPalindrome_2(head);
   }

/***************************************************************************************/

}
// @lc code=end

