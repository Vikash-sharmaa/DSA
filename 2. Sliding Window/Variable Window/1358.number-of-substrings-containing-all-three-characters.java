/* https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/

 * @lc app=leetcode id=1358 lang=java
 *
 * [1358] Number of Substrings Containing All Three Characters
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

class Solution {
    int numberOfSubstrings(String s) {
            String pat = "abc"; // The target substring should contain 'a', 'b', and 'c'
            int n = s.length();

            Map<Character, Integer> map = new HashMap<>(); // To track occurrences of 'a', 'b', and 'c'
            int k = 0; // Count of distinct characters in the current window
            int counter = 0; // Total count of valid substrings

            int start = 0, end = 0;
            
            while (end < n) { // Expand the window by moving 'end' pointer
                char cur = s.charAt(end); // Current character at 'end'
                
                // If the character is one of 'a', 'b', or 'c', update its frequency in the map
                if (pat.indexOf(cur) != -1) {
                    map.put(cur, map.getOrDefault(cur, 0) + 1);
                    if (map.get(cur) == 1) k++; // Increase k only when a new distinct character is found
                }

                // If the window contains all three characters, count valid substrings
                while (k == 3) { 
                    counter += (n - end); // All substrings from 'start' to 'n' that include 'end' are valid
                    
                    char toBeRemoved = s.charAt(start); // Character at 'start' to be removed
                    
                    // If it's one of 'a', 'b', or 'c', update frequency and check if it's removed
                    if (pat.indexOf(toBeRemoved) != -1) {
                        map.put(toBeRemoved, map.get(toBeRemoved) - 1);
                        if (map.get(toBeRemoved) == 0) k--; // If a character count drops to 0, reduce k
                    }
                    
                    start++; // Shrink the window from the left
                }
                end++; // Expand the window from the right
            }
            
            return counter; // Return total count of valid substrings
    }
}
// @lc code=end

