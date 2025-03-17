/*
 * @lc app=leetcode id=15 lang=java
 *
 * [15] 3Sum
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
/***********************************************************************************************************************************/
    public List<List<Integer>> threeSum_1(int[] nums) {
        int n=nums.length;
        Set<List<Integer>> lis = new HashSet<>();
        for(int i=0;i<n-2;i++){
            for(int j=i+1;j<n-1;j++){
                for(int k=j+1;k<n;k++){
                    if(nums[i]+nums[j]+nums[k]==0){
                        lis.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    }
                }
            }
        }
        return new ArrayList<>(lis);
    }
/***********************************************************************************************************************************/
    public List<List<Integer>> threeSum_2(int[] nums) {
        int n=nums.length;
        List<List<Integer>> lis = new ArrayList<>();
        for(int i=0;i<n-2;i++){
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for(int j=i+1;j<n-1;j++){
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                for(int k=j+1;k<n;k++){
                    if (k > j + 1 && nums[k] == nums[k - 1]) continue;
                    if(nums[i]+nums[j]+nums[k]==0){
                        lis.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    }

                }
            }
        }
        return lis;
    }

/***********************************************************************************************************************************/
    // Time:- O(n^2)      ||      O(n²) worst-case (for storing results) but O(n) auxiliary space for set in each iteration.

    public List<List<Integer>> threeSum_3(int[] nums) {
        int n = nums.length;
        Set<List<Integer>> res = new HashSet<>(); // To store unique triplets

        // Step 1: Iterate through each number as a fixed first element
        for (int i = 0; i < n - 2; i++) {
            Set<Integer> set = new HashSet<>(); // HashSet to store visited numbers for two-sum check
            for (int j = i + 1; j < n; j++) {
                int target = -(nums[i] + nums[j]); // Step 2: Find the third number that sums to zero
                if (set.contains(target)) {
                    // Step 3: If target exists in the set, we found a valid triplet
                    res.add(List.of(nums[i], target, nums[j]));
                } else {
                    // Step 4: Store nums[j] in the set for future lookups
                    set.add(nums[j]);
                }
            }
        }
        return new ArrayList<>(res); // Convert set to list and return
    }
/***********************************************************************************************************************************/

public List<List<Integer>> threeSum_4(int[] nums) {
    Arrays.sort(nums);
    int n=nums.length;
    Set<List<Integer>> set=new HashSet<>();
    for(int i=0;i<n-2;i++){
        int target=-nums[i];
        int left=i+1,right=n-1;
        while(left<right){
            if(nums[left]+nums[right]==target) set.add(List.of(nums[i],nums[left++],nums[right--]));
            else if(nums[left]+nums[right]<target) left++;
            else right--;
        }
    }
    return new ArrayList<>(set);
}


/***********************************************************************************************************************************/
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        return threeSum_4(nums);
    }
}
// @lc code=end

