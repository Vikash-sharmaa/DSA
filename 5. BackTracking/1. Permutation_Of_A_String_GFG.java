

import java.util.ArrayList;
import java.util.HashMap;


// Time:- O(n * n!)
// Space:- Space Complexity: O(n * n!) (for storing results) + O(n) (for recursion stack Total Space Complexity: O(n * n!)

class Solution {
    
    String swap(String s,int i,int j){
        char[] sChars = s.toCharArray();
        char temp = sChars[i];
        sChars[i] = sChars[j];
        sChars[j] = temp;
        
        return new String(sChars);
        
    }
    
    void permu(String s, int idx, ArrayList<String> res) {
        // Base case: If idx reaches the end of the string, add permutation to result list
        if (idx == s.length()) {
            res.add(s);
            return;
        }
    
        HashMap<Character, Integer> map = new HashMap<>();  // HashMap to track used characters
    
        // Iterate through each character from the current index to the end
        for (int i = idx; i < s.length(); i++) {
            // Check if the character at position 'i' has already been used at this recursion level
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), 1);  // Mark character as used
    
                s = swap(s, idx, i);      // Swap current character with the index position
                permu(s, idx + 1, res);   // idx denotes the fixed position - since we have placed a character at idx , we are moving idx+1
                s = swap(s, idx, i);      // Backtrack: Revert the swap to restore original string
            }
        }
    }

    public ArrayList<String> findPermutation(String s) {
        ArrayList<String> res=new ArrayList<>();
        permu(s,0,res);
        return res;
    }
}