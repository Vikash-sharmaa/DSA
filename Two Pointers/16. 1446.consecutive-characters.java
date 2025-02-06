/*
 * @lc app=leetcode id=1446 lang=java
 *
 * [1446] Consecutive Characters
 */

// @lc code=start
class Solution {

// Time:- O(n)     ||     Space:- O(1)

    public int maxPower(String s) {
        int n = s.length();  // Get the length of the string
        char ch = s.charAt(0);  // Track the current character
        int maxLen = 1;  // Store the maximum power (longest consecutive substring of the same character)
        
        int currentLen = 1;  // Track the current streak length
    
        // Iterate through the string from the second character onward
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ch) {  
                // If the character is the same as the previous one, increase the streak count
                currentLen++;
            } else {  
                // If a different character is found, reset the current streak
                currentLen = 1;
                ch = s.charAt(i);  // Update `ch` to the new character
            }
            
            // Update `maxLen` if the current streak is the longest so far
            maxLen = Math.max(maxLen, currentLen);
        }
    
        return maxLen;  // Return the maximum power of the string
    }    
}
// @lc code=end

