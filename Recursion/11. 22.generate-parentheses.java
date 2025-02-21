package Recursion;
/*
 * @lc app=leetcode id=22 lang=java
 *
 * 
 * 
 * 
 */

 /*
    Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

        Example 1:

        Input: n = 3
        Output: ["((()))","(()())","(())()","()(())","()()()"]
  */

/*
                        ""
                     /  
                   "("  
                 /     \
              "(("      "()"  
             /    \       /  
          "((("   "(()"  "()("
           /  \     /  \    \
      "((()" "((())" "(())(" "()(("
        |      |      |      |
      "((()))" "(()())" "(())()" "()()("
                      /        \
                   "()()()"   "()())("

 */

import java.util.ArrayList;
import java.util.List;

class Solution {

// Time:	O(4ⁿ / sqrt(n))     ||     Space	O(n * 4ⁿ / sqrt(n))

    public void generate(int open, int close, String op, List<String> res) {
        // Base Case: If no more open or close brackets remain, add the generated string to the result list
        if (open == 0 && close == 0) {
            res.add(op);
            return;
        }
    
        // If the number of open and close brackets are equal, we can only add an open bracket '('
        if (open == close) {
            generate(open - 1, close, op + "(", res);
        }
        // If no open brackets remain, we can only add a close bracket ')'
        else if (open == 0) {
            generate(open, close - 1, op + ")", res);
        }
        // Otherwise, we can either:
        // 1. Add an open bracket '(' if open brackets are still available
        // 2. Add a close bracket ')' if there are more close brackets left than open ones
        else {
            generate(open - 1, close, op + "(", res);
            generate(open, close - 1, op + ")", res);
        }
    }
    
    public List<String> generateParenthesis(int n) {
        List<String> res=new ArrayList<>();
        generate(n, n, "", res);
        return res;
    }
}
// @lc code=end

