/*
 * @lc app=leetcode id=424 lang=java
 *
 * [424] Longest Repeating Character Replacement
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

class Solution {

/********************************************************************************************************************************/

    // Time:- O(n^2)        ||      Space:- O(m)

    public int characterReplacement_Brute(String s, int k) {
        int res = 0; // Stores the maximum length of a valid substring
    
        // Outer loop: Fix the starting point of the substring
        for (int i = 0; i < s.length(); i++) {
            HashMap<Character, Integer> count = new HashMap<>(); // Frequency map for the current substring
            int maxf = 0; // Stores the highest frequency of any character in the current substring
    
            // Inner loop: Expand the substring from index `i` to `j`
            for (int j = i; j < s.length(); j++) {
                // Add the new character to the frequency map
                count.put(s.charAt(j), count.getOrDefault(s.charAt(j), 0) + 1);
    
                // Update `maxf` to store the maximum frequency of any character in this substring
                maxf = Math.max(maxf, count.get(s.charAt(j)));
    
                // Check if the current window is valid:
                // (window size - max frequency of a single character) should be <= k
                if ((j - i + 1) - maxf <= k) {
                    // If valid, update the maximum length found so far
                    res = Math.max(res, j - i + 1);
                }
            }
        }
    
        // Return the longest valid substring length found
        return res;
    }
    

/********************************************************************************************************************************/

    // Time:- O(n)        ||      Space:- O(m)

    public int characterReplacement(String s, int k) {
        int n = s.length();
        int start = 0, end = 0; // Sliding window pointers
        int maxFreq = 0; // Stores the frequency of the most common character in the current window
        int maxLen = 0; // Stores the maximum length of the valid substring
        Map<Character, Integer> map = new HashMap<>(); // Stores frequency of characters in the window
    
        while (end < n) { // Expand the window by moving `end` forward
            char ch = s.charAt(end);
            map.put(ch, map.getOrDefault(ch, 0) + 1); // Update frequency of the current character
            maxFreq = Math.max(maxFreq, map.get(ch)); // Update `maxFreq` to track the highest occurring character

            // Update the maximum length of a valid substring found so far
            if((end - start + 1) - maxFreq <= k){       // (end - start + 1) - maxFreq - number of chars to be replaced
                maxLen = Math.max(maxLen, end - start + 1);
            }
    
            // Condition to check if the window is invalid
            // `(end - start + 1) - maxFreq` gives the count of characters that need to be replaced
            // The key observation is that the longest valid substring is always formed when maxFreq increases
            while ((end - start + 1) - maxFreq > k) {
                char toBeRemoved = s.charAt(start); // Character being removed from the window
                map.put(toBeRemoved, map.get(toBeRemoved) - 1); // Reduce its frequency
                start++; // Shrink the window from the left
            }
    
            
            end++; // Expand the window
        }
    
        return maxLen; // Return the longest valid substring length
    }
/********************************************************************************************************************************/
}
// @lc code=end

