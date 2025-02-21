package Recursion;
/*
 * @lc app=leetcode id=784 lang=java
 *
    * Given a string s, you can transform every letter individually to be lowercase or uppercase to create another string.
        Return a list of all possible strings we could create. Return the output in any order.

    

        Example 1:

        Input: s = "a1b2"
        Output: ["a1b2","a1B2","A1b2","A1B2"]
        Example 2:

        Input: s = "3z4"
        Output: ["3z4","3Z4"]

 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {

    void permu(String ip, String op, List<String> res) {
        // Base case: If input string is empty, add the current output string to the result list
        if (ip.length() == 0) {
            res.add(op);
            return;
        }
    
        char ch = ip.charAt(0); // Extract the first character from the input string
    
        // If the character is a digit, it remains unchanged in the output
        if (Character.isDigit(ch)) {
            permu(ip.substring(1), op + ch, res);
        } else {
            // Recursive calls:
            // 1. Include the character in lowercase
            permu(ip.substring(1), op + Character.toLowerCase(ch), res);
    
            // 2. Include the character in uppercase
            permu(ip.substring(1), op + Character.toUpperCase(ch), res);
        }
    }

    
    public List<String> letterCasePermutation(String s) {
        List<String> res=new ArrayList<>();
        permu(s,"",res);
        return res;
    }
}
// @lc code=end

