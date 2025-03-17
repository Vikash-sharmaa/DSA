/*
 * @lc app=leetcode id=49 lang=java
 *
 * [49] Group Anagrams
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

/********************************************************************************************************************/
   // Time Complexity: O(n² log m) → Sorting each word takes O(m log m), and the nested loops run O(n²) in the worst case.
  //  Space Complexity: O(n + m log m) → Stores results in O(n) and sorting each word takes O(m log m) auxiliary space.

    public static List<List<String>> groupAnagrams1(String[] strs) {
        int n = strs.length; // Get the total number of strings

        boolean[] visited = new boolean[n]; // Track if a word has been grouped already
        List<List<String>> res = new ArrayList<>(); // List to store the groups of anagrams

        for (int i = 0; i < n; i++) { // Iterate over each word in the input array
            if (visited[i]) continue; // Skip if this word is already part of a group

            List<String> temp = new ArrayList<>(); // Create a new list for this group
            temp.add(strs[i]); // Add the current word as the first element of the group

            char[] iArray = strs[i].toCharArray(); // Convert string to char array for sorting
            Arrays.sort(iArray); // Sort the characters to get a unique identifier for the anagram

            for (int j = i + 1; j < n; j++) { // Compare with all words after the current word
                if (visited[j]) continue; // Skip if this word has already been grouped

                char[] jArray = strs[j].toCharArray(); // Convert the next word to char array
                Arrays.sort(jArray); // Sort it to compare with the first word

                if (Arrays.equals(iArray, jArray)) { // If both sorted words are the same
                    temp.add(strs[j]); // Add this word to the current anagram group
                    visited[j] = true; // Mark this word as visited
                }
            }

            visited[i] = true; // Mark the first word of the group as visited
            res.add(temp); // Add the group to the final result
        }

        return res; // Return the list of grouped anagrams
    }

/********************************************************************************************************************/
    /*
        // Find a common key using which U can store the elements as a group //

        Each word is sorted to get a unique key representing its anagram group.
        A HashMap stores these sorted keys, mapping them to lists of original words.
        Finally, we return the values of the map, which are the grouped anagrams.
    */

    // Time Complexity: O(n m log m) (Sorting each word O(m log m) and inserting into the map O(n)).
    // Space Complexity: O(n m) (Storing n words and their sorted versions).

        public static List<List<String>> groupAnagrams2(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();

            for (String s : strs) {
                char[] sArray = s.toCharArray();
                Arrays.sort(sArray);
                String sortedS = new String(sArray); // Sorted string as the key

                if (!map.containsKey(sortedS)) {
                    map.put(sortedS, new ArrayList<>()); // Use a mutable list
                }
                map.get(sortedS).add(s); // Add original string to the group
            }

            return new ArrayList<>(map.values()); // Convert map values to list
        }

/********************************************************************************************************************/
    public List<List<String>> groupAnagrams(String[] strs) {
        return groupAnagrams2(strs);
    }
}
// @lc code=end

