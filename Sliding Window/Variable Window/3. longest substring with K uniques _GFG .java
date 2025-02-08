/*
   Given a string s, you need to print the size of the longest possible substring with exactly k unique characters. If no possible substring exists, print -1.

    Examples:

        Input: s = "aabacbebebe", k = 3
        Output: 7
        Explanation: "cbebebe" is the longest substring with 3 distinct characters.
 */



// see the simlarity with longest substring with repeating chars leetode - 3



import java.util.*;

class Solution{

/********************************************************************************************************************************/

    // Time:- O(n^2)        ||      Space:- O(k)

    public int longestkSubstr1(String s, int k) {
        int n = s.length();  // Get the length of the input string
        int maxLen = -1;  // Stores the maximum length of a valid substring (initialized to -1 if no valid substring exists)
    
        // Iterate over each starting point of the substring
        for (int i = 0; i < n; i++) {
            Set<Character> set = new HashSet<>();  // HashSet to store unique characters in the current substring
            
            // Expand the substring from index i to j
            for (int j = i; j < n; j++) {
                set.add(s.charAt(j));  // Add the current character to the set
                
                if (set.size() == k) {  
                    maxLen = Math.max(maxLen, j - i + 1);  // Update maxLen if we have exactly 'k' distinct characters
                } 
                else if (set.size() > k) {  
                    break;  // Stop expanding if distinct characters exceed 'k'
                }
            }
        }
    
        return maxLen;  // Return the length of the longest substring with exactly 'k' distinct characters (-1 if no such substring exists)
    }
    

/********************************************************************************************************************************/

    // Time:- O(n)        ||      Space:- O(k)

    public int longestkSubstr2(String s, int k) {
        int n = s.length();  // Get the length of the input string
        int maxLen = -1;  // Stores the maximum length of a valid substring (initialized to -1 in case no valid substring exists)
        
        Map<Character, Integer> map = new HashMap<>();  // HashMap to store character frequency in the current window

        int start = 0, end = 0;  // Two pointers to define the sliding window

        // Expand the window by moving 'end' pointer
        while (end < n) {
            char current = s.charAt(end);  // Get the current character
            map.put(current, map.getOrDefault(current, 0) + 1);  // Increment the frequency of the current character in the map

            // If we have exactly 'k' distinct characters, update maxLen
            if (map.size() == k) {
                maxLen = Math.max(maxLen, end - start + 1);  // Update maxLen if current substring is the longest valid one
            }

            // If we have more than 'k' distinct characters, shrink the window from the left
            while (map.size() > k) {
                char toBeRemoved = s.charAt(start);  // Get the character to be removed from the left
                map.put(toBeRemoved, map.get(toBeRemoved) - 1);  // Reduce its frequency
                
                if (map.get(toBeRemoved) == 0) {  // If the frequency becomes 0, remove it from the map
                    map.remove(toBeRemoved);
                }
                start++;  // Move the start pointer to the right
            }

            end++;  // Move the end pointer to expand the window
        }

        return maxLen;  // Return the length of the longest substring with exactly 'k' distinct characters (-1 if no such substring exists)
    }
}
