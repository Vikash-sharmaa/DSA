/* https://leetcode.com/problems/valid-anagram/description/

 * @lc app=leetcode id=242 lang=java
 *
 * [242] Valid Anagram
 */

 /*
    
    Two Sum
    Roman to Integer
    Palindrome Number
    Maximum Subarray
    Remove Element
    Contains Duplicate
    Add Two Numbers
    Majority Element
    Remove Duplicates from Sorted Array
    Valid Anagram
    Group Anagrams
*/
// @lc code=start

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {

/********************************************************************************************************************/
   // Time: O(nlogn)        Space: O(1)
    public boolean isAnagram_Sorting(String s, String t) {
        if(s.length()!=t.length()) return false;

        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        Arrays.sort(sArray);
        Arrays.sort(tArray);

        // for(int i=0;i<sArray.length;i++){
        //     if(sArray[i]!=tArray[i]) return false;
        // }
        // return true;
        return Arrays.equals(sArray,tArray);
    }

/********************************************************************************************************************/
   // Time: O(n)        Space: O(n)

   // Doesnt work - think of aa and bb

    // public boolean isAnagram_Map(String s, String t) {
    //     if(s.length()!=t.length()) return false;

    //     Map<Character,Integer> map = new HashMap<>();


    //     for(int i=0;i<s.length();i++){
    //         map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)+1);
    //         map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0)+1);
    //     }

    //     for(Map.Entry<Character,Integer> entry : map.entrySet()){
    //         if(entry.getValue()%2!=0) return false;
    //     }
    //     return true;
    // }

/********************************************************************************************************************/
   // Time: O(n)  
   // O(1) space (since the character set is limited to at most 26 for lowercase letters or 128 for ASCII characters)

   public boolean isAnagram_Map2(String s, String t) {
        // If the lengths of the strings are not the same, they can't be anagrams.
        if (s.length() != t.length()) return false;

        // Create a HashMap to store character frequencies.
        Map<Character, Integer> map = new HashMap<>();

        // Iterate over both strings simultaneously.
        for (int i = 0; i < s.length(); i++) {
            // Increment the count for the character from string 's'.
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);

            // Decrement the count for the character from string 't'.
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) - 1);
        }

        // Check if all character frequencies are zero.
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            // If any character count is non-zero, the strings are not anagrams.
            if (entry.getValue() != 0) return false;
        }

        // If all counts are zero, the strings are anagrams.
        return true;
    }


/********************************************************************************************************************/
   
    public boolean isAnagram(String s, String t) {
        //return isAnagram_Sorting(s, t);
         return isAnagram_Map2(s,t);
    }
}

// @lc code=end

