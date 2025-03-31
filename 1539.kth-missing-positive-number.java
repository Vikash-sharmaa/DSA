/*
 * @lc app=leetcode id=1539 lang=java
 *
 * [1539] Kth Missing Positive Number
 */

// @lc code=start


import java.util.*;

class Solution {
    public int findKthPositive(int[] arr, int k) {
        Set<Integer> lookUp = new HashSet<>();
        for(int x : arr) lookUp.add(x);
        
        // We might need to go beyond 1000
        for(int i = 1; i <= arr[arr.length - 1] + k; i++) {
            if(!lookUp.contains(i)) {
                k--;
                if(k == 0) return i;
            }
        }
        return -1; // Never reached
    }
    
}
// @lc code=end

