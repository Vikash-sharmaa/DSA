/*
 * @lc app=leetcode id=131 lang=java
 *

    Given a string s, partition s such that every substring of the partition is a palindrome. 
    Return all possible palindrome partitioning of s.

        Example 1:

            Input: s = "aab"
            Output: [["a","a","b"],["aa","b"]]

*/

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {

    boolean isPalindrome(String s){
        int n=s.length();
        for(int i=0;i<n/2;i++){
            if(s.charAt(i)!=s.charAt(n-i-1)) return false;
        }
        return true;
    }

    void partition(String s, int idx, List<String> temp, List<List<String>> res) {
        // Base case: If the entire string has been processed, add the current partition to result
        if (idx == s.length()) {
            res.add(new ArrayList<>(temp));  // Store a copy of the current partition
            return;
        }
    
        String palin = ""; // Used to build substrings from index `idx`
    
        // Loop to expand the substring from `idx` to `i`
        for (int i = idx; i < s.length(); i++) {
            palin += s.charAt(i);  // Append current character to form substring
    
            // Check if the current substring is a palindrome
            if (isPalindrome(palin)) {
                temp.add(palin);  // Include this palindrome in the current partition
    
                // Recursively partition the remaining part of the string
                partition(s, i + 1, temp, res);
    
                // Backtrack: Remove the last added substring to explore other partitions
                temp.remove(temp.size() - 1);
            }
        }
    }
    
    
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        partition(s,0,new ArrayList<>(),res);
        return res;
    }
}
// @lc code=end


/*


            Trying: a | Current Partition: []
        ' +
        '✅ a is a palindrome, proceed further
        ' +
        '  Trying: a | Current Partition: [a]
        ' +
        '  ✅ a is a palindrome, proceed further
        ' +
        '    Trying: b | Current Partition: [a, a]
        ' +
        '    ✅ b is a palindrome, proceed further
        ' +
        '✔ Partition found: [a, a, b]
        ' +
        '    🔙 Backtracking, removing: b
        ' +
        '  🔙 Backtracking, removing: a
        ' +
        '  Trying: ab | Current Partition: [a]
        ' +
        '  ❌ ab is NOT a palindrome, skipping
        ' +
        '🔙 Backtracking, removing: a
        ' +
        'Trying: aa | Current Partition: []
        ' +
        '✅ aa is a palindrome, proceed further
        ' +
        '  Trying: b | Current Partition: [aa]
        ' +
        '  ✅ b is a palindrome, proceed further
        ' +
        '✔ Partition found: [aa, b]
        ' +
        '  🔙 Backtracking, removing: b
        ' +
        '🔙 Backtracking, removing: aa
        ' +
        'Trying: aab | Current Partition: []
        ' +
        '❌ aab is NOT a palindrome, skipping


 */

