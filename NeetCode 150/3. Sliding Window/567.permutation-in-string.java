/*
 * @lc app=leetcode id=567 lang=java
 *
 * [567] Permutation in String
 */

// @lc code=start

import java.util.HashMap;

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();  // Length of pattern string s1
        int len2 = s2.length();  // Length of search string s2

        int start = 0, end = 0;  // Sliding window pointers
        HashMap<Character, Integer> map = new HashMap<>();  // Frequency map for characters in s1

        // Populate the frequency map with characters from s1
        for (int i = 0; i < len1; i++) {
            map.put(s1.charAt(i), map.getOrDefault(s1.charAt(i), 0) + 1);
        }
        int mapSize = map.size(); // Count of distinct characters in s1 that need to be matched

        int windowSize = len1;  // The window size should always be equal to s1 length

        // Traverse s2 using the sliding window technique
        while (end < len2) {
            char current = s2.charAt(end);  // Current character being added to the window
            
            // If the character exists in the map, decrement its frequency
            if (map.containsKey(current)) {
                map.put(current, map.get(current) - 1);
                
                // If its frequency becomes zero, it means we've matched all occurrences of this character
                if (map.get(current) == 0) {
                    mapSize--;  
                }
            }

            // If the window size matches s1 length, check if it's a valid permutation
            if (end - start + 1 == windowSize) {
                if (mapSize == 0) return true; // Found a valid permutation

                // Slide the window by removing the leftmost character
                char toBeRemoved = s2.charAt(start);

                if (map.containsKey(toBeRemoved)) {
                    // If the character being removed was previously matched (freq=0), increment mapSize
                    if (map.get(toBeRemoved) == 0) {
                        mapSize++;
                    }

                    // Restore the frequency of the removed character
                    map.put(toBeRemoved, map.get(toBeRemoved) + 1);
                }

                start++; // Move the start pointer to shrink the window
            }

            end++; // Expand the window
        }

        return false; // No permutation found
    }
}

// @lc code=end

