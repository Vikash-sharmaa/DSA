/*

    Given a number k and string s of digits denoting a positive integer, build the largest number possible by performing swap 
    operations on the digits of s at most k times.

        Examples :
        Input: s = "1234567", k = 4
        Output: 7654321
        Explanation: Three swaps can make the input 1234567 to 7654321, swapping 1 with 7, 2 with 6 and finally 3 with 5

 */

 /*
    Not greedy b'coz -
        num = 4577, k = 2
        Greedy choice: 7745
        Better choice: 7754
  */



// Think High Level - 
// taking an idx - trying to get maximum number out of others - then see if we can swap them or not as we have total-1 choices left
// if no swap is possible move the idx anyways 



/*
    Time:- Brute-force approach would be O(n!), but pruning using maxi makes it close to O(n²) in practice.
    Space:- Space complexity is O(n) due to recursion depth and immutable strings in Java.
*/

class Solution {
    // Function to find the largest number after k swaps.
    static String swap (String s,int i,int j){
        char[] sChar = s.toCharArray();
        har temp=sChar[i];
        sChar[i]=sChar[j];
        sChar[j]=temp;
        return new String(sChar);
    }

    static void findMaxNum(String s, int k, int idx, String[] res) {
    
        // Base case: If no swaps are left or we have reached the end of the string
        if (k == 0 || idx == s.length()) {
            // Compare the current string with the maximum found so far and update if necessary
            if (s.compareTo(res[0]) > 0) {
                res[0] = s; // Store the lexicographically larger string
            }
            return;
        }
        
        // Step 1: Find the maximum character in the remaining part of the string
        char maxi = s.charAt(idx); 
        
        // Iterate through the remaining characters to find the maximum
        for (int i = idx + 1; i < s.length(); i++) {
            if (s.charAt(i) > maxi) maxi = s.charAt(i);
        }
    
        // Step 2: Swap only if a greater character is found in later positions
        for (int i = idx + 1; i < s.length(); i++) {
            // Check if swapping the current index with 'i' can give a larger value
            // Also, ensure that we swap only with the rightmost occurrence of the maximum character
            if (s.charAt(idx) < s.charAt(i) && s.charAt(i) == maxi) {
                // Swap characters at 'i' and 'idx' to form a larger number
                s = swap(s, i, idx);
                
                // Recur with one less swap allowed and move to the next index
                findMaxNum(s, k - 1, idx + 1, res);
                
                // Backtrack: Undo the swap to explore other possibilities
                s = swap(s, i, idx);
            }
        }
    
        // Step 3: Continue without swapping if no beneficial swaps are found
        findMaxNum(s, k, idx + 1, res);
    }


    public static String findMaximumNum(String s, int k) {
        String[] res=new String[1];
        res[0]=s;
        findMaxNum(s,k,0,res);
        return res[0];
    }
}
