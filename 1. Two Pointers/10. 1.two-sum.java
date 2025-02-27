/*
 * @lc app=leetcode id=1 lang=java
 *
 * 
 */

import java.util.*;

class Solution {

/********************************************************************************************************************************/

    // Time:- O(n^2)      ||      Space:- O(1)

        public int[] twoSum1(int[] nums,int target){
            int n=nums.length;
            for(int i=0;i<n-1;i++){
                for(int j=i+1;j<n;j++){
                    if(nums[i]+nums[j]==target){
                        return new int[]{i,j};
                    }
                }
            }

            return new int[]{-1,-1};
        }



/********************************************************************************************************************************/

    // Time:- O(n)      ||      Space:- O(n)


        public int[] twoSum2(int[] nums, int target) {
            // Create a HashMap to store numbers and their indices
            Map<Integer, Integer> map = new HashMap<>();
    
            // Iterate through the array
            for (int i = 0; i < nums.length; i++) {
                // Calculate the required number to form the target sum
                int next = target - nums[i];
    
                // Check if the required number is already in the map
                if (map.containsKey(next)) {
                    // If found, return the current index and the index of the required number
                    return new int[]{i, map.get(next)};
                } else {
                    // Otherwise, store the current number and its index in the map
                    map.put(nums[i], i);
                }
            }
    
            // Return [-1, -1] if no pair is found (though in valid inputs this won't be reached)
            return new int[]{-1, -1};
        }


/********************************************************************************************************************************/

// Two pointers - when array is sorted - since we need to move pointers 

    // Time:- O(nlog(n))      ||      Space:- O(n)

        public int[] twoSum3(int[] nums, int target) {
            int n = nums.length;
    
            // Step 1: Create a list to store each number along with its original index
            List<List<Integer>> numsWithIndex = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                numsWithIndex.add(List.of(i, nums[i])); // Store index and value as a list
            }
    
            // Step 2: Sort the list based on the values (ascending order)
            Collections.sort(numsWithIndex, (a, b) -> a.get(1) - b.get(1));
    
            // Step 3: Use two pointers to find the pair that sums to the target
            int i = 0, j = n - 1;
            while (i < j) {
                int sum = numsWithIndex.get(i).get(1) + numsWithIndex.get(j).get(1);
    
                // If the sum matches the target, return the original indices
                if (sum == target) {
                    return new int[]{numsWithIndex.get(i).get(0), numsWithIndex.get(j).get(0)};
                } 
                // If sum is less than the target, move left pointer forward
                else if (sum < target) {
                    i++;
                } 
                // If sum is greater than the target, move right pointer backward
                else {
                    j--;
                }
            }
    
            // If no pair is found, return [-1, -1]
            return new int[]{-1, -1};
        }

/********************************************************************************************************************************/

    public int[] twoSum(int[] nums, int target) {
        return twoSum1(nums, target);
    }
}

