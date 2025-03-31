/* https://leetcode.com/problems/koko-eating-bananas/description/

 * @lc app=leetcode id=875 lang=java
 *
 * [875] Koko Eating Bananas
 */

// @lc code=start
class Solution {
/**************************************************************************************************************************/
    
    public int minEatingSpeed_Naive(int[] piles, int h) {
        int n = piles.length;
        int maxK = piles[0];
        int res = Integer.MAX_VALUE;
        
        // Find the maximum pile to know the upper limit of eating speed
        for(int i = 0; i < n; i++) {
            maxK = Math.max(maxK, piles[i]);
        }
    
        // Try every possible speed from 1 to maxK
        for(int i = 1; i <= maxK; i++) {
            long hoursTaken = eatingSpeed(i, piles);
            if(hoursTaken <= h) {
                res = Math.min(res, i); // find minimum speed satisfying condition
            }
        }
        return res;
    }
    
/**************************************************************************************************************************/

// Time: O(n * log(max-element))
    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;
        int maxK = piles[0];
        int res = Integer.MAX_VALUE;
        
        // Find the maximum pile to get the upper limit of Koko's eating speed
        for(int i = 0; i < n; i++) {
            maxK = Math.max(maxK, piles[i]);
        }
        
        int start = 1, end = maxK;
        
        // Binary search on possible eating speeds
        while(start <= end) {
            int mid = start + (end - start) / 2; // safe mid to avoid overflow
            long hoursTaken = eatingSpeed(mid, piles); // total hours if she eats at speed 'mid'
            
            //possible - hold it and move to more possible i.e. lesser side
            if(hoursTaken <= h) {
                // if Koko can finish within h hours, try to minimize speed further
                res = Math.min(res, mid); 
                end = mid - 1;  // exploring more lesser times 
            } else {
                // else, she needs to eat faster
                //not possible -  move where it might be possible 
                start = mid + 1;
            }
        }
        
        return res; // return the minimum valid speed found
    
    }
/**************************************************************************************************************************/

    long eatingSpeed(int k, int[] piles) {
        long hoursTaken = 0;
        for(int i = 0; i < piles.length; i++) {
            // correct ceil operation
            hoursTaken += (piles[i] + k - 1) / k; // equivalent to ceil(piles[i] / k)
        }
        return hoursTaken;
    }
    
/**************************************************************************************************************************/

}
// @lc code=end

