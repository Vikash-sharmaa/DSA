package Greedy;
/*
 * @lc app=leetcode id=455 lang=java
 *
 * [455] Assign Cookies
 */

// @lc code=start

import java.util.Arrays;

class Solution {
// Time: O(nlogn)


    public int findContentChildren(int[] g, int[] s) {
        int childrenCount = 0; // Variable to store the number of satisfied children
        int gLen = g.length; // Length of the greed factor array
        int sLen = s.length; // Length of the cookie size array
    
        Arrays.sort(g); // Sort the greed factors in ascending order
        Arrays.sort(s); // Sort the cookie sizes in ascending order
    
        int i = gLen - 1, j = sLen - 1; // Start from the largest greed factor and the largest cookie size
    
        // Iterate while both children and cookies are available
        while (j >= 0 && i >= 0) {
            if (s[j] >= g[i]) { // If the largest available cookie can satisfy the most greedy child
                childrenCount++; // Increase the count of satisfied children
                j--; // Move to the next largest cookie
                i--; // Move to the next most greedy child
            } else {
                i--; // If the cookie is too small, move to the next child (without using this cookie)
            }
        }
    
        return childrenCount; // Return the number of satisfied children
    }
    
}
// @lc code=end

