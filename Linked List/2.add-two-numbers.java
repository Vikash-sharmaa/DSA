/*  https://leetcode.com/problems/add-two-numbers/submissions/1310899661/

 * @lc app=leetcode id=2 lang=java
 *
 * [2] Add Two Numbers
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

   // Time:- O(max(len(l1),len(l2)))          Space:- O(max(len(l1),len(l2)))
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Creating a dummy node to simplify handling the result list.
        ListNode dummy = new ListNode(-1); 
        ListNode head = dummy; // Pointer to track the head of the result list.
        
        int carry = 0; // Variable to store carry-over value during addition.
        
        // Loop continues until both l1 and l2 are completely processed.
        while (l1 != null || l2 != null) {
            int sum = carry; // Start with the previous carry.
            
            if (l1 != null) { // If l1 is not null, add its value to sum.
                sum += l1.val;
                l1 = l1.next; // Move l1 to its next node.
            }
            
            if (l2 != null) { // If l2 is not null, add its value to sum.
                sum += l2.val;
                l2 = l2.next; // Move l2 to its next node.
            }
    
            carry = sum / 10; // Compute carry for the next iteration.
            sum = sum % 10; // Extract the last digit to store in the new node.
            
            dummy.next = new ListNode(sum); // Create a new node with sum value.
            dummy = dummy.next; // Move to the next node in the result list.
        }
        
        // If there's a leftover carry after processing both lists, add a new node.
        if (carry != 0) {
            dummy.next = new ListNode(carry);
        }
    
        return head.next; // Return the head of the resulting linked list.
    }
/***************************************************************************************/
    
}
// @lc code=end

