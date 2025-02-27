/*
 * @lc app=leetcode id=345 lang=java
 *
 * 
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    // Define a string containing all vowels (both uppercase and lowercase)
    String vowels = "aeiouAEIOU"; 

/********************************************************************************************************************************/

    // Time:- O(n)      ||      Space:- O(n)

        public String reverseVowels1(String s) {
            // Use StringBuilder to modify the string efficiently
            StringBuilder sb = new StringBuilder(s);
    
            // Use a Deque (stack) to store vowels for reversing
            Deque<Character> stack = new ArrayDeque<>();
    
            // First pass: Store vowels in a stack
            for (int i = 0; i < s.length(); i++) {
                // Check if the current character is a vowel
                if (vowels.indexOf(s.charAt(i)) != -1) {
                    stack.offerLast(s.charAt(i)); // Push vowel onto stack
                }
            }
    
            // Second pass: Replace vowels in reverse order
            for (int i = 0; i < sb.length(); i++) {
                // Check if the current character is a vowel
                if (vowels.indexOf(sb.charAt(i)) != -1) {
                    sb.setCharAt(i, stack.pollLast()); // Replace with last vowel from stack
                }
            }
    
            // Convert StringBuilder back to a string and return
            return sb.toString();
        }


/********************************************************************************************************************************/

    // Time:- O(n)      ||      Space:- O(1)

        public String reverseVowels2(String s) {
            // Use StringBuilder to allow modifications to the string
            StringBuilder sb = new StringBuilder(s);

            // Two-pointer approach: i starts from the left, j starts from the right
            int i = 0, j = s.length() - 1;

            // Continue swapping until the two pointers meet
            while (i < j) {
                // If both characters at i and j are vowels, swap them
                if (vowels.indexOf(sb.charAt(i)) != -1 && vowels.indexOf(sb.charAt(j)) != -1) {
                    char temp = sb.charAt(i);
                    sb.setCharAt(i, sb.charAt(j));
                    sb.setCharAt(j, temp);

                    // Move both pointers inward
                    i++;
                    j--;
                } 
                // If character at i is not a vowel, move i forward
                else if (vowels.indexOf(sb.charAt(i)) == -1) {
                    i++;
                } 
                // If character at j is not a vowel, move j backward
                else if (vowels.indexOf(sb.charAt(j)) == -1) {
                    j--;
                }
            }

            // Convert StringBuilder back to a string and return the modified result
            return sb.toString();
    }

/********************************************************************************************************************************/


        public String reverseVowels(String s) {
            return reverseVowels2(s);
        }
}
    
// @lc code=end

