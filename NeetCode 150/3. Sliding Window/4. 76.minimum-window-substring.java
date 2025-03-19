/*
 * @lc app=leetcode id=76 lang=java
 *
 * [76] Minimum Window Substring
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

class Solution {

/********************************************************************************************************************************/

    // Time:- O(n^3)        ||      Space:- O(k)

    // Helper function to check if string 's' contains all characters of string 't' (including their frequencies)
    private boolean containsAll(String s, String t) {
        int m = s.length();
        int n = t.length();
        Map<Character, Integer> map = new HashMap<>();  // HashMap to store frequency of characters in 't'

        // Populate the map with character frequencies of 't'
        for (int i = 0; i < n; i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }

        // Iterate over 's' and reduce the frequency of characters that appear in 't'
        for (int i = 0; i < m; i++) {
            if (map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
                if (map.get(s.charAt(i)) == 0) {  // If a character's count reaches 0, remove it from the map
                    map.remove(s.charAt(i));
                }
            }
        }

        return map.isEmpty();  // If the map is empty, 's' contains all characters of 't'
    }

    public String minWindow1(String s, String t) {
        int m = s.length();
        int minLen = Integer.MAX_VALUE;  // Stores the minimum window length
        String res = "";  // Stores the result substring

        // Iterate over all possible starting points of the substring
        for (int i = 0; i < m; i++) {
            // Iterate over all possible ending points of the substring
            for (int j = i; j < m; j++) {
                String substr = s.substring(i, j + 1);  // Extract the current substring

                // Check if the current substring contains all characters of 't'
                if (containsAll(substr, t)) {
                    if (substr.length() < minLen) {  // Update minLen and result if a smaller valid substring is found
                        minLen = substr.length();
                        res = substr;
                    }
                }
            }
        }

        return res;  // Return the minimum window substring (or an empty string if no valid substring is found)
    }

/********************************************************************************************************************************/

    // Time:- O(n)        ||      Space:- O(k)

    public String minWindow(String s, String t) {
        int m = s.length(); // Length of the source string
        int n = t.length(); // Length of the target string
        int minLen = Integer.MAX_VALUE; // Variable to store the minimum window length
        String res = ""; // Variable to store the resulting minimum window substring
    
        // Map to store the frequency of characters in 't'
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }
    
        int mapCount = map.size(); // Number of unique characters in 't' that need to be found in 's'
    
        int start = 0, end = 0; // Two pointers to define the window
    
        // Expand the window with 'end' pointer
        while (end < m) {
            char current = s.charAt(end);
    
            // If current character is in 't', decrease its count in the map
            if (map.containsKey(current)) {
                map.put(current, map.get(current) - 1);
                // If count becomes zero, it means we have found all occurrences of this character
                if (map.get(current) == 0) mapCount--;
            }
    
            // Try to shrink the window from the left when all characters are matched
            while (mapCount == 0) { 
                // Update the minimum length and store the result substring
                if (minLen > end - start + 1) {
                    minLen = end - start + 1;
                    res = s.substring(start, end + 1);
                }
    
                // Character to be removed from the left
                char toBeRemoved = s.charAt(start);
    
                // If it's part of 't', increase its count back in the map
                if (map.containsKey(toBeRemoved)) {
                    map.put(toBeRemoved, map.get(toBeRemoved) + 1);
                    // If count becomes positive, we need this character again, so increment mapCount
                    if (map.get(toBeRemoved) > 0) mapCount++;
                }
                start++; // Shrink the window from the left
            }
            end++; // Expand the window from the right
        }
        return res; // Return the minimum window substring found
    }    
}
// @lc code=end

