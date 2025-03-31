/* https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/description/

 * @lc app=leetcode id=1482 lang=java
 *
 * [1482] Minimum Number of Days to Make m Bouquets
 */

// @lc code=start
class Solution {
/**************************************************************************************************************************/

    // Time: O(n*range)
    public int minDays_Naive(int[] bloomDay, int m, int k) {
        int n=bloomDay.length;
        int res=-1;
        if((long)m*k > n) return -1;

        int minDay=Integer.MAX_VALUE;
        int maxDay=Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            minDay=Math.min(minDay, bloomDay[i]);
            maxDay=Math.max(maxDay, bloomDay[i]);
        }

        for(int day=minDay;day<=maxDay;day++){
            if(isPossible(bloomDay, m, k, day)){
                res=day;
                break;
            }
        }
        return res;
    }

/**************************************************************************************************************************/

// Binary Search + Greedy

    // Time: O(n*log(range))
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
    
        // Edge Case: Not enough flowers to make the bouquets
        if ((long)m * k > n) return -1;
    
        // Find minDay and maxDay from bloomDay array
        int minDay = Integer.MAX_VALUE;
        int maxDay = Integer.MIN_VALUE;
    
        for (int day : bloomDay) {
            minDay = Math.min(minDay, day);
            maxDay = Math.max(maxDay, day);
        }
    
        int res = -1;
    
        // Binary Search on answer space [minDay ... maxDay]
        int start = minDay, end = maxDay;
    
        while (start <= end) {
            int mid = start + (end - start) / 2;
    
            if (isPossible(bloomDay, m, k, mid)) {
                res = mid;      // mid is a valid day, try to find smaller
                end = mid - 1;  // search left
            } else {
                start = mid + 1; // search right
            }
        }
    
        return res;
    }

/**************************************************************************************************************************/

    // if we can make 'm' bouquets on or before 'day'
    private boolean isPossible(int[] bloomDay, int m, int k, int day) {
        int bouquets = 0, flowers = 0;

        for (int bloom : bloomDay) {
            if (bloom <= day) { 
                flowers++;  // flower bloomed
                if (flowers == k) { // formed a bouquet
                    bouquets++;
                    flowers = 0; // reset flower count
                }
            } else {
                flowers = 0; // broken sequence
            }
        }
        return bouquets >= m;
    }

/**************************************************************************************************************************/
}
// @lc code=end

