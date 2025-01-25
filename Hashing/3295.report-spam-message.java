package Hashing;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean reportSpamNaive(String[] message, String[] bannedWords) {
        int count = 0;  // Counter for how many banned words are found in the message
        
        // Loop through each word in the message
        for (String s1 : message) {
            // Loop through each banned word
            for (String s2 : bannedWords) {
                // If the word in the message matches a banned word, increment count
                if (s1.equals(s2)) {
                    count++;
                    break;  // No need to check further banned words for this message word
                }
            }
            
            // If we have found 2 banned words, return true (indicating the message is spam)
            if (count == 2) return true;
        }
        
        // If fewer than 2 banned words were found, return false (not spam)
        return false;
    }


    public boolean reportSpam(String[] message, String[] bannedWords) {
        int count = 0;  // Counter for how many banned words are found in the message
        
        // Create a set to store the banned words for efficient lookup
        Set<String> set = new HashSet<>();
        for (String s1 : bannedWords) set.add(s1);  // Add all banned words to the set
        
        // Loop through each word in the message
        for (String s2 : message) {
            // Check if the message word is in the set of banned words
            if (set.contains(s2)) {
                count++;
                // If we have found 2 banned words, return true (indicating the message is spam)
                if (count == 2) return true;
            }
        }
        
        // If fewer than 2 banned words were found, return false (not spam)
        return false;
    }

}