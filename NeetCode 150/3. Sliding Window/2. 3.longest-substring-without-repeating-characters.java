/*
 * @lc app=leetcode id=3 lang=java
 *
 * [3] Longest Substring Without Repeating Characters
 */

// @lc code=start

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {

/********************************************************************************************************************************/

    // Time:- O(n^2)        ||      Space:- O(k)

    public int lengthOfLongestSubstring1(String s) {
        int n = s.length();  // Get the length of the input string
        int maxLen = 0;  // Stores the maximum length of a valid substring
    
        // Iterate over each starting point of the substring
        for (int i = 0; i < n; i++) {
            Set<Character> set = new HashSet<>();  // Set to store unique characters in the current substring
    
            // Expand the substring from index i to j
            for (int j = i; j < n; j++) {
                if (set.contains(s.charAt(j))) break;  // If character is repeated, break the loop
                
                maxLen = Math.max(maxLen, j - i + 1);  // Update maxLen with the current valid substring length
                set.add(s.charAt(j));  // Add the current character to the set
            }
        }
    
        return maxLen;  // Return the length of the longest substring without repeating characters
    }

/********************************************************************************************************************************/

    // Time:- O(n)        ||      Space:- O(k)

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();  // Get the length of the input string
        int maxLen = 0;  // Stores the maximum length of a valid substring
    
        Map<Character, Integer> map = new HashMap<>();  // HashMap to store character frequency in the current window
    
        int start = 0, end = 0;  // Two pointers to define the sliding window
    
        // Expand the window by moving 'end' pointer
        while (end < n) {
            char current = s.charAt(end);  // Get the current character
            map.put(current, map.getOrDefault(current, 0) + 1);  // Increment the frequency of the current character in the map
    
            // If all characters in the current window are unique (map size == window size)
            if (map.size() == end - start + 1) {
                maxLen = Math.max(maxLen, end - start + 1);  // Update maxLen with the current valid substring length
            }
    
            // If there are duplicate characters (map size < window size), shrink the window from the left
            while (map.size() < end - start + 1) {
                char toBeRemoved = s.charAt(start);  // Get the character to be removed from the left
    
                map.put(toBeRemoved, map.get(toBeRemoved) - 1);  // Reduce its frequency
                if (map.get(toBeRemoved) == 0) {  // If the frequency becomes 0, remove it from the map
                    map.remove(toBeRemoved);
                }
                start++;  // Move the start pointer to the right
            }
    
            end++;  // Move the end pointer to expand the window
        }
    
        return maxLen;  // Return the length of the longest substring without repeating characters
    }

/********************************************************************************************************************************/
     
}
// @lc code=end

