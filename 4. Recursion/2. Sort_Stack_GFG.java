

import java.util.Stack;

class Solution {

    /************************************************************************************************/
    
       
        void insert(Stack<Integer> stack, int last) {
            // Base case: If stack is empty or the top element is smaller than or equal to 'last'
            if (stack.isEmpty() || stack.peek() <= last) {
                stack.push(last); // Insert 'last' at the correct position
                return;
            }
            // Remove the top element and store it
            int top = stack.pop();
    
            // Recursively call insert to find the correct place for 'last'
            insert(stack, last);
    
            // Push the removed element back on top after inserting 'last'
            stack.push(top);
        }
    
        // Function to recursively sort the stack
        void sortStack(Stack<Integer> stack) {
            // Base case: If stack has only one element, it's already sorted
            if (stack.size() == 1) return;
    
            // Remove the top element and store it
            int last = stack.pop();
    
            // Recursively sort the remaining stack
            sortStack(stack); 
    
            // Insert the removed element back in sorted order
            insert(stack, last);
        }
        
        
    /************************************************************************************************/
    
    
        public Stack<Integer> sort(Stack<Integer> stack) {
            sortStack(stack);
            return stack;
        }
    }
