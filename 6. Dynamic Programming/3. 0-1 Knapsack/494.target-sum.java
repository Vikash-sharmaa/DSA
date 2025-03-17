/*  https://leetcode.com/problems/target-sum/description/

 * @lc app=leetcode id=494 lang=java
 *
 * [494] Target Sum
 */

// @lc code=start

import java.util.HashMap;
import java.util.Map;

class Solution {
    int findTargetSumWaysRecursive(int[] nums,int n,int target){
        if(n==0 && target==0) return 1;
        if(n==0) return 0;

        int positive = findTargetSumWaysRecursive(nums,n-1,target-nums[n-1]);
        int negative = findTargetSumWaysRecursive(nums,n-1, target-(-nums[n-1]));
        return positive + negative;
    }

    int findTargetSumWaysMemo(int[] nums,int n,int target,Map<String,Integer> dp){
        if(n==0 && target==0) return 1;
        if(n==0) return 0;
        
        String key = n+":"+target;
        if(dp.containsKey(key)) return dp.get(key);
        int positive = findTargetSumWaysMemo(nums, n-1, target-nums[n-1], dp);
        int negative = findTargetSumWaysMemo(nums, n-1, target-(-nums[n-1]), dp);
        dp.put(key, positive+negative);
        return positive+negative;
    }
    public int findTargetSumWays(int[] nums, int target) {
        int n=nums.length;
        Map<String, Integer> dp=new HashMap<>();
        //return findTargetSumWaysRecursive(nums, nums.length, target);
        return findTargetSumWaysMemo(nums, n, target, dp);
    }

}
// @lc code=end

