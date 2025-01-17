package Strings;
import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    
    public boolean checkStringStack(String s) {
        // Create a stack (using Deque) to process the string characters
        Deque<Character> stack = new ArrayDeque<>();
        
        // Traverse the string from the last character to the first
        for (int i = s.length() - 1; i >= 0; i--) {
            // If the stack is empty, push the current character onto the stack
            if (stack.isEmpty()) {
                stack.offerLast(s.charAt(i));
                continue;
            }
            
            // Check if the current character is 'b' and the last character in the stack is 'a'
            // This violates the condition that all 'b's must appear before 'a's
            if (stack.peekLast() == 'a' && s.charAt(i) == 'b') return false;
            
            // Push the current character onto the stack
            stack.offerLast(s.charAt(i));
        }
        
        // If no invalid order is found, return true
        return true;
    }


    public boolean checkString(String s) {
        // Start with the last character of the string
        char last = s.charAt(s.length() - 1);
        
        // Traverse the string from the second-to-last character to the first
        for (int i = s.length() - 2; i >= 0; i--) {
            // Check if the current character is 'b' and the last processed character is 'a'
            // This violates the condition that all 'b's must appear before 'a's
            if (last == 'a' && s.charAt(i) == 'b') return false;
            
            // Update the last character to the current character
            last = s.charAt(i);
        }
        
        // If no invalid order is found, return true
        return true;
    }


}