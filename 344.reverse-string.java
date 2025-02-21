/*
 * @lc app=leetcode id=344 lang=java
 *
 * [344] Reverse String
 */

// @lc code=start
class Solution {
    void swap(char[] s, int i, int j) {
        // Swap characters at index i and j
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
    
    void reverse(char[] s, int i) {
        // Base Case: Stop recursion when index reaches the middle of the array
        if (i >= s.length / 2) return;
    
        // Swap the current character with its corresponding character from the end
        swap(s, i, s.length - i - 1);
    
        // Recursively call for the next index
        reverse(s, i + 1);
    }
    
    public void reverseString(char[] s) {
        // Start recursion from the first index
        reverse(s, 0);
    }
    
}
// @lc code=end

