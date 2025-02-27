/*
 * @lc app=leetcode id=1456 lang=java
 *
 * [1456] Maximum Number of Vowels in a Substring of Given Length
 */

// @lc code=start
class Solution {


/********************************************************************************************************************************/

    // Time:- O(n*k)        ||      Space:- O(1)

    public int maxVowels(String s, int k) {
        int n = s.length();  // Length of the input string
        String vowelString = "aeiou";  // String containing all vowels
        int maxVowels = 0;  // Variable to store the maximum number of vowels in a window
        
        for(int i=0;i<n-k+1;i++){
            int vowels=0;
            for(int j=i;j<i+k;j++){
                if(vowelString.indexOf(s.charAt(j))!=-1) vowels++;
            }
            maxVowels = Math.max(maxVowels, vowels);
        }

        return maxVowels;
    }




/********************************************************************************************************************************/

    // Time:- O(n)        ||      Space:- O(1)

    public int maxVowels2(String s, int k) {
        int n = s.length();  // Length of the input string
        String vowelString = "aeiou";  // String containing all vowels
        int maxVowels = 0;  // Variable to store the maximum number of vowels in a window
        int vowels = 0;  // Variable to count the number of vowels in the current window
        int start = 0, end = 0;  // Two pointers defining the sliding window
    
        while (end < n) {  // Iterate over the string
            char current = s.charAt(end);  // Current character being added to the window
    
            // If the current character is a vowel, increase the vowel count
            if (vowelString.indexOf(current) != -1) 
                vowels++;
    
            // If the window size reaches 'k', start processing results
            if (end - start + 1 == k) {
                // Update the maximum number of vowels found in any window
                maxVowels = Math.max(maxVowels, vowels);
    
                char toBeRemoved = s.charAt(start);  // Character that is leaving the window
    
                // If the removed character is a vowel, decrease the vowel count
                if (vowelString.indexOf(toBeRemoved) != -1) 
                    vowels--;
    
                start++;  // Slide the window forward
            }
    
            end++;  // Expand the window
        }
    
        return maxVowels;  // Return the maximum count of vowels found in any window of size 'k'
    }

/********************************************************************************************************************************/
    
}
// @lc code=end

