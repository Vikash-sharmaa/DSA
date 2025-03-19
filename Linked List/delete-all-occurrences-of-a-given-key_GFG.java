
class Node
{
	int data;
	Node next;
	Node prev;
	Node(int data)
	{
	    this.data = data;
	    next = prev = null;
	}
}
class Solution {
    static Node deleteAllOccurOfX(Node head, int x) {
        Node mover = head;
        
        while (mover != null) {
            if (mover.data == x) {
                Node prevNode = mover.prev;
                Node nextNode = mover.next;
    
                if (mover == head) {
                    head = nextNode; // Move head forward
                    if (head != null) {
                        head.prev = null; // Update new head's prev to null
                    }
                } else {
                    if (prevNode != null) {
                        prevNode.next = nextNode;
                    }
                    if (nextNode != null) {
                        nextNode.prev = prevNode;
                    }
                }
                
                // Move to the next node after deletion
                mover = nextNode;
            } else {
                mover = mover.next;
            }
        }
        
        return head;
    }
    
}