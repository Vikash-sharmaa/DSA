/* https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/

 * @lc app=leetcode id=17 lang=java
 *
 * 
    Time Complexity: O(3^n * 4^m), where N is the count of digits mapping to 3 letters (e.g., 2, 3, 4) and 
        M is the count of digits mapping to 4 letters (e.g., 7, 9). Each digit expands into multiple branches in recursion.
    Space Complexity: O(N+M) for recursion depth and result storage, where N+M is the number of digits in input.
 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    void letterCombinations(String digits, int idx, StringBuilder op, List<String> res, Map<Integer, String> map) {
        // Base case: If we have processed all digits, add the current combination to the result list
        if (idx == digits.length()) {
            res.add(op.toString());  // Convert StringBuilder to String and store in result list
            return;
        }
    
        // Convert current character (digit) to an integer
        int currentDigit = digits.charAt(idx) - '0';
    
        // Iterate over all possible characters mapped to the current digit
        for (char ch : map.get(currentDigit).toCharArray()) {
            op.append(ch);  // Choose the current character and append it to the combination
            letterCombinations(digits, idx + 1, op, res, map);  // Recur for the next digit
            op.deleteCharAt(op.length() - 1);  // Backtrack: Remove the last added character
        }
    }
    
    public List<String> letterCombinations(String digits) {
        List<String> res=new ArrayList<>();
        if(digits.length()==0) return res;
        Map<Integer,String> map=new HashMap<>();

        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");

        letterCombinations(digits,0,new StringBuilder(),res,map);

        return res;
    }
}
// @lc code=end


/*
    Input: "23"

    Step-by-step execution:

    Trying: a | Current Combination: []
    ✅ "a" is selected (from digit '2'), proceed further
    Trying: d | Current Combination: [a]
    ✅ "d" is selected (from digit '3'), proceed further
    ✔ Combination found: "ad"
    🔙 Backtracking, removing: d
    Trying: e | Current Combination: [a]
    ✅ "e" is selected (from digit '3'), proceed further
    ✔ Combination found: "ae"
    🔙 Backtracking, removing: e
    Trying: f | Current Combination: [a]
    ✅ "f" is selected (from digit '3'), proceed further
    ✔ Combination found: "af"
    🔙 Backtracking, removing: f
    🔙 Backtracking, removing: a

    Trying: b | Current Combination: []
    ✅ "b" is selected (from digit '2'), proceed further
    Trying: d | Current Combination: [b]
    ✅ "d" is selected (from digit '3'), proceed further
    ✔ Combination found: "bd"
    🔙 Backtracking, removing: d
    Trying: e | Current Combination: [b]
    ✅ "e" is selected (from digit '3'), proceed further
    ✔ Combination found: "be"
    🔙 Backtracking, removing: e
    Trying: f | Current Combination: [b]
    ✅ "f" is selected (from digit '3'), proceed further
    ✔ Combination found: "bf"
    🔙 Backtracking, removing: f
    🔙 Backtracking, removing: b

    Trying: c | Current Combination: []
    ✅ "c" is selected (from digit '2'), proceed further
    Trying: d | Current Combination: [c]
    ✅ "d" is selected (from digit '3'), proceed further
    ✔ Combination found: "cd"
    🔙 Backtracking, removing: d
    Trying: e | Current Combination: [c]
    ✅ "e" is selected (from digit '3'), proceed further
    ✔ Combination found: "ce"
    🔙 Backtracking, removing: e
    Trying: f | Current Combination: [c]
    ✅ "f" is selected (from digit '3'), proceed further
    ✔ Combination found: "cf"
    🔙 Backtracking, removing: f
    🔙 Backtracking, removing: c

    Final Result: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]

*/


