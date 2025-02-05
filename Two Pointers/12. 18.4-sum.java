/*
 * @lc app=leetcode id=18 lang=java
 *
 * [18] 4Sum
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<List<Integer>> fourSum1(int[] nums, int target) {
        //Arrays.sort(nums);
        int n = nums.length;
        Set<List<Integer>> res = new HashSet<>();
            for(int i=0;i<n-3;i++){
                for(int j=i+1;j<n-2;j++){
                    for(int k=j+1;k<n-1;k++){
                        for(int l=k+1;l<n;l++){
                            if(nums[i]+nums[j]+nums[k]+nums[l]==target){
                                List<Integer> temp = new ArrayList<>();
                                temp.addAll(List.of(nums[i], nums[j], nums[k], nums[l]));
                                Collections.sort(temp);
                                res.add(temp);
                            }
                        }
                    }
                }
            }
        return new ArrayList<>(res);
    }

    public List<List<Integer>> fourSum2(int[] nums, int target) {
        int n = nums.length;
        Set<List<Integer>> res = new HashSet<>();
    
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                Set<Long> lookUp = new HashSet<>();
                for (int k = j + 1; k < n; k++) {
                    long required = (long) target - (long) nums[i] - (long) nums[j] - (long) nums[k];
                    if (lookUp.contains(required)) {
                        List<Integer> temp = new ArrayList<>(List.of(nums[i], nums[j], nums[k], (int) required));
                        Collections.sort(temp);
                        res.add(temp);
                    } else {
                        lookUp.add((long) nums[k]);
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }

    public List<List<Integer>> fourSum3(int[] nums, int target) {
        Arrays.sort(nums);
        int n=nums.length;
        Set<List<Integer>> res=new HashSet<>();

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int left=j+1,right=n-1;
                while(left<right){
                    long sum = (long)nums[i]+(long)nums[j]+(long)nums[left]+(long)nums[right];

                    if(sum==target){
                        res.add(List.of(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;
                    }else if(sum<target){
                        left++;
                    }else{
                        right--;
                    }

                }
            }
        }
        return new ArrayList<>(res);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n= nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res=new ArrayList<>();

        for (int i = 0; i < n - 2; i++) {
            if(i>0 && nums[i]==nums[i-1]) continue;
            for (int j = i + 1; j < n - 1; j++) {
                if(j>i+1 && nums[j]==nums[j-1]) continue;
                int left=j+1,right=n-1;
                while(left<right){
                    long sum = (long)nums[i]+(long)nums[j]+(long)nums[left]+(long)nums[right];

                    if(sum==target){
                        List<Integer> temp = List.of(nums[i], nums[j], nums[left], nums[right]);
                        res.add(temp);
                        while(left<right && nums[left]==temp.get(2) )left++;
                        while(left<right && nums[right]==temp.get(3)) right--;
                    }else if(sum<target){
                        left++;
                    }else{
                        right--;
                    }

                }
            }
        }

        return res;
    }  
}
// @lc code=end

