import java.util.Arrays;

class Solution
{
    //Function to arrange all letters of a string in lexicographical 
    //order using Counting Sort.
    public static String countSortNaive(String arr)
    {
        // code here
        char[] arrChars = arr.toCharArray();
        Arrays.sort(arrChars);
        return new String(arrChars);
    }

    
    
    public static String countSort(String s) {
        // Step 1: Initialize a frequency array for 26 letters
        int n = s.length();
        int[] countArray = new int[26];
    
        // Step 2: Count the frequency of each character in the string
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            countArray[current - 'a']++;
        }
    
        // Step 3: Build the sorted string using the frequency array
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            int count = countArray[i];
            while (count != 0) {
                res.append((char) (i + 'a')); // Convert index back to character
                count--; // Decrement the count
            }
        }
    
        // Step 4: Return the sorted string
        return res.toString();
    }

    
}