/*
    Given a positive integer n. Your task is to generate a string list of all n-bit binary numbers where, 
        for any prefix of the number, there are more or an equal number of 1's than 0's. The numbers should be sorted in decreasing order of magnitude.

        Example 1:

            Input:  
            n = 2
            Output: 
            {"11", "10"}
            Explanation: Valid numbers are those where each prefix has more 1s than 0s:
            11: all its prefixes (1 and 11) have more 1s than 0s.
            10: all its prefixes (1 and 10) have more 1s than 0s.
            So, the output is "11, 10".
 */

package Recursion;

import java.util.ArrayList;

/*
    1. Purpose: The function generates all binary strings of length n where the number of 1s is always greater than or 
                equal to the number of 0s at any point in the string.
    2. Base Case: When n reaches 0, the current string op is complete and added to the result list lis.
    3. Adding 1: If the number of 1s equals the number of 0s, the next character must be 1 to maintain the balance condition.
    4. Adding 0 or 1: If 1s are already greater than 0s, the function explores two branches: adding a 1 or a 0.
    5. Recursive Calls: Each recursive call reduces n by 1, appends a 0 or 1 to op, and adjusts the counts of zeroes 
                and ones accordingly.
*/


// Time: O(2^n)     ||      Space: O(2^n)
class Solution {
    void generate(int ones, int zeroes, int n, String op, ArrayList<String> res) {
        // Base Case: When 'n' reaches 0, we have generated a valid n-bit binary string
        if (n == 0) {
            res.add(op);  // Add the generated string to the result list
            return;
        }
    
        // If the number of ones is equal to the number of zeroes, we must start with '1'
        if (ones == zeroes) {
            generate(ones + 1, zeroes, n - 1, op + "1", res);
        } 
        // Otherwise, we can add either '1' or '0', ensuring '1's are always ≥ '0's
        else {
            generate(ones + 1, zeroes, n - 1, op + "1", res); // Add '1' and recurse
            generate(ones, zeroes + 1, n - 1, op + "0", res); // Add '0' and recurse
        }
    }
    
    ArrayList<String> NBitBinary(int n) {
        // Initialize result list
        ArrayList<String> res = new ArrayList<>();
    
        // Start recursion with an empty string, zero ones and zeroes
        generate(0, 0, n, "", res);
        return res;
    }

}
