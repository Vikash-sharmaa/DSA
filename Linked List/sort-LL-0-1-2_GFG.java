
/***************************************************************************************/

class Node
{
    int data;
    Node next;
    Node(int data)
    {
        this.data = data;
        next = null;
    }
}

/***************************************************************************************/

class Solution {
/***************************************************************************************/
    // Using two passes - Time:- O(2n)
        static Node segregate_1(Node head) {
            // add your code here
                int zeroes=0;
                int ones=0;
                int twos=0;
                
                Node mover = head;
                while(mover!=null){
                    if(mover.data==0) zeroes++;
                    else if(mover.data==1) ones++;
                    else twos++;
                    
                    mover=mover.next;
                }
                
                mover=head;
            
                while(zeroes!=0){
                    mover.data=0;
                    zeroes--;
                    mover=mover.next;
                }
                while(ones!=0){
                    mover.data=1;
                    ones--;
                    mover=mover.next;
                }
                while(twos!=0){
                    mover.data=2;
                    twos--;
                    mover=mover.next;
                }
            
            return head;
        }
/***************************************************************************************/

        static Node segregate_2(Node head) {
            if (head == null || head.next == null) return head; // Handle edge case
        
            // Create dummy nodes for 0s, 1s, and 2s lists
            Node zeros = new Node(-1), ones = new Node(-1), twos = new Node(-1);
        
            // Keep track of list heads
            Node zerosHead = zeros, onesHead = ones, twosHead = twos;
        
            Node mover = head;
        
            // Step 1: Partition the list into separate lists for 0s, 1s, and 2s
            while (mover != null) {
                if (mover.data == 0) {
                    zeros.next = mover;
                    zeros = zeros.next;
                } else if (mover.data == 1) {
                    ones.next = mover;
                    ones = ones.next;
                } else {
                    twos.next = mover;
                    twos = twos.next;
                }
                mover = mover.next;
            }
        
            // Step 2: Merge the three lists together
        
            // Connect the 0s list to the 1s list if it exists, otherwise to 2s
            zeros.next = (onesHead.next != null) ? onesHead.next : twosHead.next;
        
            // Connect the 1s list to the 2s list if it exists
            ones.next = twosHead.next;
        
            // Terminate the last node to avoid cycles
            twos.next = null;
        
            // Step 3: Return the new head (skip the dummy node)
            return zerosHead.next;
        }

/***************************************************************************************/
    
        static Node segregate(Node head) {
            if(head==null || head.next==null) return head;
            return segregate_2(head);
        }

/***************************************************************************************/ 
    }