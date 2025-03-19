import java.util.HashMap;
import java.util.Map;

class Node
{
    int data;
    Node next;
    Node(int d) {data = d; next = null; }
}


class Solution {
/************************************************************************************************************/
    // Time:- O(n)          Space:- O(n)
        public int countNodesinLoop_1(Node head) {
            Node mover = head;  // Pointer to traverse the linked list
            Map<Node, Integer> visited = new HashMap<>();  // HashMap to store visited nodes and their index
            int timer = 0;  // Timer to track the position of nodes
        
            while (mover != null) {  // Traverse the linked list
                timer++;  // Increment position counter
        
                if (visited.containsKey(mover)) {  // If node is already visited, a cycle is detected
                    return timer - visited.get(mover);  // Return the loop length (current position - first occurrence)
                }
        
                visited.put(mover, timer);  // Store node with its position
                mover = mover.next;  // Move to the next node
            }
            
            return 0;  // No cycle found, return 0
        }
    
        
/************************************************************************************************************/
    // Time:- O(n)          Space:- O(1)

        // Function to find the length of a loop in the linked list.
        public int countNodesinLoop_2(Node head) {
            Node slow = head;  // Slow pointer moves one step at a time
            Node fast = head;  // Fast pointer moves two steps at a time
        
            // Step 1: Detect cycle using Floyd’s Cycle Detection Algorithm
            while (fast != null && fast.next != null) {  
                slow = slow.next;   // Move slow pointer by 1 step
                fast = fast.next.next;  // Move fast pointer by 2 steps
        
                if (fast == slow) {  // If they meet, a cycle is detected
                    int count = 1;  // Start counting the cycle length
                    
                    // Step 2: Count the number of nodes in the loop
                    while (slow.next != fast) { // Traverse the cycle until we reach the start again
                        slow = slow.next;
                        count++;
                    }
                    return count;  // Return the cycle length
                }
            }
            return 0;  // No cycle found, return 0
        }
/************************************************************************************************************/
        
        public int countNodesinLoop(Node head) {
            return countNodesinLoop_1(head);
        }
}