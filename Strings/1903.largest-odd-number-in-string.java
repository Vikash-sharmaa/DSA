package Strings;
/*
 * @lc app=leetcode id=1903 lang=java
 *
 * [1903] Largest Odd Number in String
 */

// @lc code=start
class Solution {
/**********************************************************************************************************************************************/
    // tried generating all the substrings like a window shrinking process - converting each substring int and finding largest odd
    // This is wrong

    // public String largestOddNumber(String num) {
    //     int n=num.length();
    //     StringBuilder sb=new StringBuilder();
    //     long maxOdd=-1;
    //     for(int i=0;i<n;i++){
    //         sb.append(num.charAt(i));
    //         long curr=Long.parseLong(sb.toString());
    //         if(curr%2!=0){
    //             maxOdd=Math.max(maxOdd,curr);
    //         }
    //     }

    //     for(int i=0;i<n;i++){
    //         sb.substring(i);
    //         long curr=Long.parseLong(sb.toString());
    //         if(curr%2!=0){
    //             maxOdd=Math.max(maxOdd,curr);
    //         }
    //     }

    //     return maxOdd==-1 ? "" : maxOdd+"";
    // }


/**********************************************************************************************************************************************/

/*
    The rightmost odd digit determines the largest odd-numbered prefix. By scanning from right to left, we find the first odd digit 
        and return the substring from 0 to that index. If no odd digit is found, return an empty string. This approach runs in O(n) time
             with minimal operations.
 */

    public String largestOddNumber(String num) {
        int n = num.length();

        // Iterate from the last character to the first
        for (int i = n - 1; i >= 0; i--) {
            // Convert character to integer and check if it's odd
            if ((num.charAt(i) - '0') % 2 != 0) { 
                // If found, return the substring from 0 to i (inclusive)
                return num.substring(0, i + 1);
            }
        }

        // If no odd digit is found, return an empty string
        return "";
    }
/**********************************************************************************************************************************************/
}
// @lc code=end

