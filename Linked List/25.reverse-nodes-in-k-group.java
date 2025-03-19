/* https://leetcode.com/problems/reverse-nodes-in-k-group/description/

 * @lc app=leetcode id=25 lang=java
 *
 * [25] Reverse Nodes in k-Group
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
    ListNode reverse(ListNode head){
        ListNode nextNode=null;
        ListNode currentNode=head;
        ListNode previousNode=null;

        while(currentNode!=null){
            nextNode=currentNode.next;
            currentNode.next=previousNode;
            previousNode=currentNode;
            currentNode=nextNode;
        }
        return previousNode;
    }

    private ListNode findKthNode(ListNode node, int k) {
        while (node != null && k > 1) {
            node = node.next;
            k--;
        }
        return node;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        ListNode kthNode = null;
        ListNode previousNode = null;
    
        while (temp != null) {
            // Find the Kth node
            kthNode = findKthNode(temp, k);
            if (kthNode == null) break; // If less than k nodes left, do not reverse
    
            ListNode nextNode = kthNode.next; // Store next group's starting node
            kthNode.next = null; // Break the list
    
            // Reverse the k-group
            ListNode newHead = reverse(temp);
    
            // Connect the previous part of the list to the new head
            if (previousNode != null) {
                previousNode.next = newHead;
            } else {
                head = newHead; // Update head for the first group
            }
    
            // Connect the reversed segment to the remaining list
            temp.next = nextNode;
            previousNode = temp; // Move previousNode to the last node of the reversed part
            temp = nextNode; // Move temp to the start of the next segment
        }
    
        return head;
    }
}
// @lc code=end

