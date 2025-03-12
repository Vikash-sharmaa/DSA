/*
 * @lc app=leetcode id=3306 lang=java
 *
 * [3306] Count of Substrings Containing Every Vowel and K Consonants II
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

class Solution {
        public long countOfSubstrings(String word, int k) {
            // We use the difference method to count substrings with exactly 'k' consonants.
            // atLeastK(word, k) gives substrings with at least 'k' consonants.
            // atLeastK(word, k+1) gives substrings with at least 'k+1' consonants.
            // Their difference gives substrings with exactly 'k' consonants.
            return atLeastK(word, k) - atLeastK(word, k + 1);
        }
    
        public long atLeastK(String word, int k) {
            int n = word.length();
            String vowels = "aeiou"; // Set of vowels for checking
            
            Map<Character, Integer> vowelMap = new HashMap<>(); // Map to count occurrences of vowels in the current window
            int consonants = 0;  // Count of consonants in the current window
            long count = 0;       // Stores the number of valid substrings
            int start = 0, end = 0; // Sliding window pointers (start and end)
    
            while (end < n) { // Expand the window by moving 'end' pointer
                char cur = word.charAt(end); // Current character at 'end'
    
                // If 'cur' is a vowel, update its count in vowelMap
                if (vowels.indexOf(cur) != -1) {
                    vowelMap.put(cur, vowelMap.getOrDefault(cur, 0) + 1);
                } else {
                    consonants++; // If it's not a vowel, it is a consonant
                }
    
                // Shrink the window from the left while the window is valid
                while (vowelMap.size() == 5 && consonants >= k) { 
                    // When all 5 vowels are present and we have at least 'k' consonants,
                    // all substrings from 'start' to 'end' are valid.
                    count += (n - end); // Add all valid substrings starting from 'start' to 'end'
    
                    // Remove the character at 'start' to shrink the window
                    char toBeRemoved = word.charAt(start);
                    if (vowels.indexOf(toBeRemoved) != -1) {  
                        // If it's a vowel, decrement its count in the map
                        vowelMap.put(toBeRemoved, vowelMap.get(toBeRemoved) - 1);
                        if (vowelMap.get(toBeRemoved) == 0) {
                            vowelMap.remove(toBeRemoved); // Remove from map if count is 0
                        }
                    } else {
                        consonants--; // If it's a consonant, decrease the count
                    }
                    start++; // Move 'start' forward to shrink the window
                }
                end++; // Move 'end' forward to expand the window
            }
            return count; // Return the total count of valid substrings
        }
}    

// @lc code=end

