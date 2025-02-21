/*
 * @lc app=leetcode id=9 lang=java
 *
 * [9] Palindrome Number
 */

// @lc code=start
class Solution {
    boolean isPalindrome(String s, int i) {
        // Base Case: If index `i` reaches or crosses the middle, it's a palindrome
        if (i >= s.length() / 2) return true;
    
        // If characters at `i` and its corresponding position from the end are not equal, return false
        if (s.charAt(i) != s.charAt(s.length() - i - 1)) return false;
    
        // Recursively check the next index
        return isPalindrome(s, i + 1);
    }
    
    public boolean isPalindrome(int x) {
        // Convert integer to string representation
        String s = Integer.toString(x);
    
        // Start the recursive check from index 0
        return isPalindrome(s, 0);
    }
    
}
// @lc code=end

