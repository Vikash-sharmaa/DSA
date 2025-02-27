/*

    Given an integer n, print all the n digit numbers in increasing order, such that their digits are in strictly increasing
         order(from left to right).

    Example 1:

        Input: n = 1
        Output: 0 1 2 3 4 5 6 7 8 9

        Explanation:
        Single digit numbers are considered to be 
        strictly increasing order.

    Example 2:

        Input: n = 2
        Output: 12 13 14 15 16 17 18 19 23....79 89
        
        Explanation:
        For n = 2, the correct sequence is
        12 13 14 15 16 17 18 19 23 and so on 
        up to 89.
 */


// Time: O(9^n).   Space: O(n)

import java.util.ArrayList;
import java.util.List;

class Solution {
    
    static void increasingNumbers(int n, int idx, int last, StringBuilder op, ArrayList<Integer> res) {
        // Base case: If we have formed a number of length 'n'
        if (idx == n) {
            res.add(Integer.valueOf(new String(op))); // Convert the StringBuilder to an integer and store in result
            return;
        }
        
        // Iterate through digits 1 to 9 to construct increasing numbers
        for (int i = 1; i < 10; i++) {
            if (i > last) { // Ensure the digits are in strictly increasing order
                op.append(i); // Add the current digit to the number being formed
                increasingNumbers(n, idx + 1, i, op, res); // Recurse to build the next digit
                op.deleteCharAt(op.length() - 1); // Backtrack to try the next possibility
            }
        }
        
        // Why don't we call `increasingNumbers(n, idx + 1, i, op, res);` here?
        // Because at least one digit must be chosen to move `idx` forward.
        // Without adding a digit, the recursion would proceed without progress.
    }

    

    public static ArrayList<Integer> increasingNumbers(int n) {
        // code here
        ArrayList<Integer> res=new ArrayList<>();
        
        if(n==1) return new ArrayList<>(List.of(0,1,2,3,4,5,6,7,8,9));
        
        increasingNumbers(n,0,0,new StringBuilder(),res);
        return res;
        
        
    }
}
