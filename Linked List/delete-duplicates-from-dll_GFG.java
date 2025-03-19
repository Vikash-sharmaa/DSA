/************************************************************************************************************/
class Node
{
    int data;
    Node next,prev;
    
    Node(int x){
        data = x;
        next = null;
        prev = null;
    }
}
/************************************************************************************************************/
class Solution{
/************************************************************************************************************/
    
    Node removeDuplicates_1(Node head) {
        // Start traversing from the second node since the first node is always unique
        Node mover = head.next;
    
        while (mover != null) {
            Node previousNode = mover.prev;  // Get the previous node
            Node nextNode = mover.next;      // Get the next node
    
            // If the current node has the same value as the previous node, it's a duplicate
            if (mover.data == previousNode.data) {
                previousNode.next = nextNode; // Remove the current node by linking previous to next
                if (nextNode != null) {       // If nextNode exists, update its prev pointer
                    nextNode.prev = previousNode;
                }
                // Move to the next node (same as skipping the duplicate)
                mover = nextNode;
            } else {
                // If it's not a duplicate, move forward
                mover = mover.next;
            }
        }
        
        return head; // Return the updated head
    }
    
/************************************************************************************************************/
 // Just another way.
    Node removeDuplicates_2(Node head) {
        Node temp = head; // Pointer to traverse the list
    
        while (temp != null && temp.next != null) {
            Node nextNode = temp.next; // Pointer to the next node
    
            // Remove all duplicate nodes having the same value as temp
            while (nextNode != null && nextNode.data == temp.data) {
                nextNode = nextNode.next; // Move nextNode forward
            }
    
            // Link temp to the next distinct node
            temp.next = nextNode;
            if (nextNode != null) {
                nextNode.prev = temp; // Update prev pointer of next distinct node
            }
    
            temp = temp.next; // Move temp forward
        }
    
        return head; // Return the updated list
    }
/************************************************************************************************************/

}