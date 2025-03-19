
class Node{
    int data;
    Node next;

    Node(int x){
        data = x;
        next = null;
    }
}


class Solution {
    
/****************************************************************************************************/
    
    public Node addOne_1(Node head) {
        StringBuilder sb = new StringBuilder();
        Node mover = head;
    
        // Convert linked list to string
        while (mover != null) {
            sb.append(mover.data);
            mover = mover.next;
        }
    
        // Use BigInteger to avoid integer overflow
        java.math.BigInteger num = new java.math.BigInteger(sb.toString());
        num = num.add(java.math.BigInteger.ONE);
    
        // Convert back to linked list
        String s = num.toString();
        Node dummy = new Node(-1), temp = dummy;
        for (char c : s.toCharArray()) {
            temp.next = new Node(c - '0');
            temp = temp.next;
        }
    
        return dummy.next; // Return the new head
    }
    
    
/****************************************************************************************************/

    public Node reverse(Node head){
        Node prev=null;
        Node next=null;
        Node curr=head;
        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }
    
    public Node addOne_2(Node head) {
        if (head == null) return new Node(1); // Handle the edge case where the list is empty
    
        // Reverse the linked list to process digits from the least significant
        head = reverse(head);
        Node mover = head;
        int carry = 1; // Start with carry 1, as we need to add 1 to the number
    
        // Traverse the reversed list and add 1
        while (mover != null) {
            int sum = mover.data + carry; // Add carry to the current node's data
            mover.data = sum % 10; // Store only the last digit in the node
            carry = sum / 10; // Update carry (1 if sum >= 10, else 0)
    
            // If we reach the last node and carry is still left, add a new node
            if (mover.next == null && carry == 1) {
                mover.next = new Node(1); // Create a new node to store the carry
                carry = 0; // Reset carry after adding the new node
            }
    
            mover = mover.next; // Move to the next node
        }
    
        // Reverse the list back to its original order
        head = reverse(head);
        return head; // Return the updated head
    }

/****************************************************************************************************/

    public Node addOne(Node head) {
        return addOne_1(head);
    }

/****************************************************************************************************/
}