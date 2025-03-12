

import java.util.Stack;

class Solution
{ 
    
    static void insertAtBack(Stack<Integer> s, int temp) {
        // Base case: If stack is empty, push the element at the bottom
        if (s.isEmpty()) {
            s.push(temp);
            return;
        }

        // Pop the top element and store it
        int val = s.pop();

        // Recursively call to reach the bottom
        insertAtBack(s, temp);

        // Push the stored element back after inserting temp at the bottom
        s.push(val);
    }


    static void reverse(Stack<Integer> s) {
        // Base case: If stack is empty, return
        if (s.isEmpty()) return;

        // Remove the top element and store it
        int temp = s.pop();

        // Recursively call to reverse the remaining stack
        reverse(s);

        // Insert the stored element at the bottom of the reversed stack
        insertAtBack(s, temp);
    }
    
}
