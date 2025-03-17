/*
 * @lc app=leetcode id=125 lang=java
 *
 * [125] Valid Palindrome
 */

// @lc code=start
class Solution {
/******************************************************************************************************/
// Time: O(n)      Space: O(n)
    public boolean isPalindrome_1(String s) {
        StringBuilder sb = new StringBuilder();
    
        // Step 1: Filter out non-alphanumeric characters and convert uppercase to lowercase
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            // Check if character is a letter or digit (ignore spaces and special characters)
            if (Character.isLetter(ch) || Character.isDigit(ch)) {  
                // Convert uppercase letters to lowercase
                if (Character.isUpperCase(ch)) {
                    sb.append(Character.toLowerCase(ch));
                } else {
                    sb.append(ch);
                }
            }
        }

        
        int start = 0, end = sb.length() - 1;
    
        // Compare characters from both ends moving towards the center
        while (start < end) {
            if (sb.charAt(start) != sb.charAt(end)) return false; // If mismatch, return false
            start++;
            end--;
        }
        return true; // If no mismatches, it's a palindrome
    }

/******************************************************************************************************/
// Time: O(n)      Space: O(1)

    public boolean isPalindrome_2(String s) {
        int start=0,end=s.length()-1;

        while(start<end){
            while(start<end && !Character.isLetterOrDigit(s.charAt(start))) start++;
            while(start<end && !Character.isLetterOrDigit(s.charAt(end)))  end--;

            if(Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) return false;

            start++;
            end--;
        }
        return true;
    }
/******************************************************************************************************/

    
    public boolean isPalindrome(String s) {
        
    
        // Step 2: Check if the filtered string is a palindrome
        return isPalindrome_2(s);
    }
    
}
// @lc code=end



