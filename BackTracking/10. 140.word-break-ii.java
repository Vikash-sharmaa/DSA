/*
 * @lc app=leetcode id=140 lang=java
 *
 * [140] Word Break II
 */

// @lc code=start

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {

    /*
        Time Complexity:  Worst case O(2ⁿ) due to exponential recursion; optimized with memoization to O(n²).
        Space Complexity: O(n²) in the worst case due to recursion depth and storing substrings.
     */

    private void wordBreak(String s, int idx, List<String> op, List<String> res, Set<String> lookUp) {
    
        // Base case: If we have processed the entire string
        if (idx == s.length()) {
            // Convert the list of words into a space-separated sentence
            String opString = op.stream().collect(Collectors.joining(" "));
            res.add(opString); // Store the valid sentence in the result
            return;
        }
    
        String currentString = ""; // To build substrings from index `idx`
        
        // Try forming words starting from index `idx`
        for (int i = idx; i < s.length(); i++) {
            currentString += s.charAt(i); // Append character to form substring
    
            // Check if the current substring exists in the dictionary (lookUp set)
            if (lookUp.contains(currentString)) {
                op.add(currentString);  // Add valid word to the current sentence
    
                // Recur to process the remaining string
                wordBreak(s, i + 1, op, res, lookUp);
    
                // Backtrack: Remove the last added word to explore other possibilities
                op.remove(op.size() - 1);
            }
        }
    }
    

    public List<String> wordBreak(String s, List<String> wordDict) {
        
        Set<String> lookUp = new HashSet<>(wordDict);
        

        List<String> res= new ArrayList<>();

        wordBreak(s, 0,new ArrayList<>(),res,lookUp);

        return res;
    }
}
// @lc code=end

