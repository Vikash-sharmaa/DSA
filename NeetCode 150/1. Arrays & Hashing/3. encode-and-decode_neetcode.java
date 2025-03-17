// Time Complexity: O(N), where N is the total length of str. The primary operations (finding # and extracting the 
// substring) each take amortized O(1) per character, making the entire function run in O(N) time.
// Space Complexity: O(K), where K is the number of unique words extracted from the string. Since we store the decoded
//  words in a list, our space usage scales with the total length of the decoded words.

import java.util.ArrayList;
import java.util.List;

class Solution{
/*
    Using only # as a delimiter is problematic because the original strings might contain #, causing incorrect 
    splitting. For example, encoding ["abc", "a#b"] as "3#abc5#a#b#" leads to incorrect decoding. Prefixing each 
    word with its length (e.g., "3#one5#three" for ["one", "three"]) ensures accurate decoding by knowing exactly 
    how many characters to extract, avoiding issues if # is part of a word.
 */

    public String encode(List<String> strs) {
        StringBuilder sb=new StringBuilder();
        for(String s : strs){
            sb.append(s.length()+"#"+s);
        }
        return sb.toString();
    }


    public List<String> decode(String str) {
        List<String> res = new ArrayList<>(); // List to store decoded strings
        int i = 0; // Pointer to traverse the encoded string
        
        while (i < str.length()) {
            int j = i;
            
            // Find the position of '#' which separates the length and the actual string
            while (str.charAt(j) != '#') {
                j++; 
            }
            
            // Extract the length of the next string from the encoded data
            int length = Integer.parseInt(str.substring(i, j));
            
            // Extract the actual string using the length found before the '#'
            i = j + 1; // Move past '#'
            String word = str.substring(i, i + length); // Extract string using length
            res.add(word); // Store the extracted string
            
            // Move `i` to the next segment after extracting the current word
            i = i + length;
        }
        
        return res; // Return the list of decoded strings
    }
}
