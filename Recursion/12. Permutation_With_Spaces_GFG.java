package Recursion;

import java.util.ArrayList;

class Solution {

// Time:    O(2^n)      ||          Space: O(n*2^n)

    // Recursive function to generate permutations with spaces
    void permu(String ip, String op, ArrayList<String> res) {
        // Base case: If input string is empty, add the output string to result list
        if (ip.length() == 0) {
            res.add(op);
            return;
        }

        char ch = ip.charAt(0);  // Extract the first character of the input string

        // Recursion: Two choices
        // 1. Include the current character with a space
        permu(ip.substring(1), op + " " + ch, res);

        // 2. Include the current character without a space
        permu(ip.substring(1), op + ch, res);
    }


    // main function
    ArrayList<String> permutation(String s) {
        ArrayList<String> res = new ArrayList<>(); // Result list to store permutations

        // Start recursion from the second character, keeping the first character as is
        permu(s.substring(1), "" + s.charAt(0), res);

        return res;
    }
}