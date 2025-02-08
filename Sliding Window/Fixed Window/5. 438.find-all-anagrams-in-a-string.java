/*
 * @lc app=leetcode id=438 lang=java
 *
 * [438] Find All Anagrams in a String
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

/********************************************************************************************************************************/

    // Time:- O(n*klogk)        ||      Space:- O(1)

    public List<Integer> findAnagrams1(String s, String p) {
        int n = s.length();  // Length of the input string 's'
        int k = p.length();  // Length of the pattern string 'p'
        List<Integer> res = new ArrayList<>();  // List to store starting indices of anagrams
    
        // Convert pattern 'p' into a character array and sort it
        char[] pChars = p.toCharArray();
        Arrays.sort(pChars);  // Sorting helps in easy comparison later
    
        // Iterate through all possible substrings of length 'k' in 's'
        for (int i = 0; i < n - k + 1; i++) {
            // Extract substring of length 'k' starting at index 'i'
            String temp = s.substring(i, i + k);
            
            // Convert the substring into a character array and sort it
            char[] tempChars = temp.toCharArray();
            Arrays.sort(tempChars);
    
            // If the sorted version matches the sorted 'p', it's an anagram
            if (Arrays.equals(pChars, tempChars)) 
                res.add(i);  // Store the starting index of the anagram
        }
    
        return res;  // Return the list of starting indices
    } 

/********************************************************************************************************************************/

    // Time:- O(n)        ||      Space:- O(n)


    public List<Integer> findAnagrams(String s, String p) {
        int n = s.length();  // Length of input string 's'
        int k = p.length();  // Length of pattern string 'p'
        List<Integer> res = new ArrayList<>();  // List to store starting indices of anagrams
    
        // Frequency map to store character counts of 'p'
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < k; i++) {
            freq.put(p.charAt(i), freq.getOrDefault(p.charAt(i), 0) + 1);
        }
    
        int mapCount = freq.size(); // Number of unique characters in 'p' that need to be matched
        int start = 0, end = 0; // Pointers for the sliding window
    
        // Iterate through the string 's' with a sliding window of size 'k'
        while (end < n) {
            char current = s.charAt(end); // Current character at the right end of the window
    
            // If the current character is in the frequency map, decrease its count
            if (freq.containsKey(current)) {
                freq.put(current, freq.get(current) - 1);
                if (freq.get(current) == 0) mapCount--; // When count becomes zero, one unique char is fully matched
            }
    
            // When the window size matches 'k'
            if (end - start + 1 == k) {
                if (mapCount == 0) res.add(start); // If all characters match, store the start index
    
                char toBeRemoved = s.charAt(start); // Character going out of the window
    
                // If the character being removed was in the pattern, update the frequency map
                if (freq.containsKey(toBeRemoved)) {
                    if (freq.get(toBeRemoved) == 0) mapCount++; // If it was fully matched, we need to increase mapCount
                    freq.put(toBeRemoved, freq.get(toBeRemoved) + 1); // Restore its count
                }
    
                start++; // Move the window forward
            }
    
            end++; // Expand the window
        }
        return res; // Return the list of starting indices
    }
    
}
// @lc code=end

