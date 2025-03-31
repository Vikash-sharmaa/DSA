/*
 * @lc app=leetcode id=1011 lang=java
 *
 * [1011] Capacity To Ship Packages Within D Days
 */

// @lc code=start
class Solution {
/**************************************************************************************************************************/

// Time: O(n * (endCapacity-startCapacity))

    public int shipWithinDays_Naive(int[] weights, int days) {
        int res = 0;
    
        // Step 1: Find the lower bound (startCapacity) = max(weights) 
        // and upper bound (endCapacity) = sum of all weights
        int startCapacity = Integer.MIN_VALUE; // Minimum possible capacity
        int endCapacity = 0; // Maximum possible capacity
    
        for (int i = 0; i < weights.length; i++) {
            startCapacity = Math.max(startCapacity, weights[i]); // At least the max weight should be the minimum capacity
            endCapacity += weights[i]; // Sum of all weights
        }
    
        // Step 2: Brute-force check from startCapacity to endCapacity (Linear Search)
        for (int capacity = startCapacity; capacity <= endCapacity; capacity++) {
            if (minimumDaysForCapacity(weights, capacity,days)) { // ✅ daysRequired <= days (not ==) -> smallest capacity satisfying
                res = capacity;
                break; // Found the minimum capacity
            }
        }
    
        return res;
    }


/**************************************************************************************************************************/

// Time: O(n * log(endCapacity-startCapacity))

    public int shipWithinDays(int[] weights, int days) {
        int res = 0;
    
     
        int startCapacity = Integer.MIN_VALUE; 
        int endCapacity = 0; 
    
        for (int i = 0; i < weights.length; i++) {
            startCapacity = Math.max(startCapacity, weights[i]); 
            endCapacity += weights[i]; 
        }

        int start=startCapacity,end=endCapacity;
        while(start<=end){
            int mid=start+(end-start)/2;
            if(minimumDaysForCapacity(weights, mid,days)){
                res=mid;
                end=mid-1;
            }else{
                start=mid+1;
            }
        }
        return res;
    }

    
/**************************************************************************************************************************/


    // Step 3: Given a capacity, count how many days will be needed to ship all weights
    private boolean minimumDaysForCapacity(int[] weights, int capacity,int days) {
        int daysRequired = 1; // We start with day 1
        int load = 0; // Current day's load
        
        for (int i = 0; i < weights.length; i++) {
            if (load + weights[i] > capacity) { // if adding this weight exceeds capacity
                days++; // use a new day
                load = weights[i]; // start new day with this weight
            } else {
                load += weights[i]; // add weight to current day
            }
        }
    
        return daysRequired<=days; // return total days required for this capacity
    }

/**************************************************************************************************************************/

}
// @lc code=end

