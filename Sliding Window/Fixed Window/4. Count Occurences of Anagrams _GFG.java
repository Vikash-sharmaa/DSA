/*
   Given a word pat and a text txt. Return the count of the occurrences of anagrams of the word in the text.

    Example 1:
        Input: txt = "forxxorfxdofr", pat = "for"
        Output: 3
        Explanation: for, orf and ofr appears in the txt, hence answer is 3.
 */

import java.util.*;

class Solution {

    int search1(String pat, String txt) {
        int n = txt.length(); // Get the length of the text
        int k = pat.length(); // Get the length of the pattern
    
        // Convert the pattern string to a character array and sort it
        char[] patChars = pat.toCharArray();
        Arrays.sort(patChars); 
    
        int count = 0; // Counter to store the number of anagram occurrences
    
        // Iterate through all substrings of 'txt' with length 'k'
        for (int i = 0; i < n - k + 1; i++) {
            // Extract a substring of length 'k' starting at index 'i'
            String temp = txt.substring(i, i + k);
            
            // Convert the substring to a character array and sort it
            char[] tempChars = temp.toCharArray();
            Arrays.sort(tempChars);
    
            // Compare sorted arrays; if equal, it's an anagram
            if (Arrays.equals(patChars, tempChars)) count++;
        }
    
        return count; // Return the number of anagram occurrences
    }
    
    
    int search(String pat, String txt) {
        int n = txt.length(); // Get the length of the text
        int k = pat.length(); // Get the length of the pattern
    
        Map<Character, Integer> freq = new HashMap<>(); // Frequency map for pattern characters
    
        // Populate the frequency map with characters from the pattern
        for (int i = 0; i < k; i++) {
            freq.put(pat.charAt(i), freq.getOrDefault(pat.charAt(i), 0) + 1);
        }
    
        int mapCount = freq.size(); // Number of distinct characters in the pattern
        int start = 0, end = 0, count = 0; // Two pointers for sliding window and anagram count
    
        while (end < n) {
            char current = txt.charAt(end); // Current character in the text
    
            // If current character exists in pattern, decrease its frequency
            if (freq.containsKey(current)) {
                freq.put(current, freq.get(current) - 1);
                
                // If frequency becomes zero, one unique character's count is matched
                if (freq.get(current) == 0) mapCount--;
                
            }
    
            // If window size reaches 'k'
            if (end - start + 1 == k) {
                // If all character frequencies are zero, we found an anagram
                if (mapCount == 0) count++;
                
                // Remove the start character as the window slides forward
                char toBeRemoved = txt.charAt(start);
                if (freq.containsKey(toBeRemoved)) {
                    // If frequency was zero, increase mapCount (since we are reintroducing the character)
                    if (freq.get(toBeRemoved) == 0) {
                        mapCount++;
                    }
                    // Restore the frequency of the removed character
                    freq.put(toBeRemoved, freq.get(toBeRemoved) + 1);
                }
    
                start++; // Move the start pointer to slide the window
            }
    
            end++; // Expand the window by moving the 'end' pointer
        }
    
        return count; // Return the number of anagram occurrences
    }

}
