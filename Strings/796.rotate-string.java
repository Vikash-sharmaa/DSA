package Strings;
/*
 * @lc app=leetcode id=796 lang=java
 *
 * [796] Rotate String
 */

// @lc code=start
class Solution {
/********************************************************************************************************************/
    // This is wrong

    // public boolean rotateString_(String s, String goal) {
    //     if(s.length()!=goal.length()) return false;
    //     int n=s.length();

    //     char ch=goal.charAt(0);

    //     int j=0;
    //     for(int i=0;i<n;i++){
    //         if(s.charAt(i)==ch){
    //             j=i;
    //             break;
    //         }
    //     }
    //     int k=0;
    //     while(k!=n){
    //         j=j%n;
    //         if(s.charAt(j++)!=goal.charAt(k)) return false;
    //         k++;
    //     }
    //     return true;
    // }

/********************************************************************************************************************/

    public boolean rotateString_1(String s, String goal) {
        if(s.length()!=goal.length()) return false;
        String str = s+s;
        return str.contains(goal);
    }

/********************************************************************************************************************/

    public boolean rotateString_2(String s, String goal) {
        if (s.length() != goal.length()) return false;
        
        String str = s + s;
        int i = 0;  // Tracks the starting position in `str`
        
        while (i < s.length()) {  // Only check within first `s.length()` positions
            int j = 0;  // Tracks position in `goal`
            int k = i;  // Separate pointer to iterate `str`
            
            while (j < goal.length() && str.charAt(k) == goal.charAt(j)) {
                j++;
                k++;  // Instead of `i + j`, we move `k` separately
            }
            
            if (j == goal.length()) return true;
            
            i++;  // Move to next starting position
        }
        
        return false;
    }

/********************************************************************************************************************/

    public boolean rotateString(String s, String goal) {
        return rotateString_2(s,goal);
    }

/********************************************************************************************************************/

}
// @lc code=end

