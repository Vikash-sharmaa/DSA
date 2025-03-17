/*  https://leetcode.com/problems/two-sum/description/

 * @lc app=leetcode id=1 lang=java
 *
 * [1] Two Sum
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

/********************************************************************************************************************/
   // Time: O(n^2)        Space: O(1)
    public int[] twoSum1(int[] nums, int target) {
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]+nums[j]==target) return new int[]{i,j};
            }
        }
        return new int[]{-1,-1};
    }
/********************************************************************************************************************/
   // Time: O(nlogn)        Space: O(n)

   public int[] twoSum2(int[] nums, int target) {
        // Create a list of lists to store numbers along with their original indices.
        List<List<Integer>> lis = new ArrayList<>();

        // Populate the list with pairs of (number, index).
        for (int i = 0; i < nums.length; i++) {
            lis.add(Arrays.asList(nums[i], i));
        }

        // Sort the list based on the numbers in ascending order.
        Collections.sort(lis, (a, b) -> a.get(0) - b.get(0));

        // Use two pointers: one starting from the beginning, one from the end.
        int i = 0, j = nums.length - 1;

        // Traverse the sorted list to find the two numbers that sum to the target.
        while (i < j) {
            int sum = lis.get(i).get(0) + lis.get(j).get(0);

            // If we find a match, return the original indices of the two numbers.
            if (sum == target) 
                return new int[]{lis.get(i).get(1), lis.get(j).get(1)};

            // If the sum is too small, move the left pointer to increase it.
            else if (sum < target) i++;

            // If the sum is too large, move the right pointer to decrease it.
            else j--;
        }

        // If no pair is found, return [-1, -1].
        return new int[]{-1, -1}; 
    }

/********************************************************************************************************************/
   // Time: O(n)        Space: O(n)

   public int[] twoSum3(int[] nums, int target) {
        // Create a HashMap to store numbers and their corresponding indices.
        Map<Integer, Integer> map = new HashMap<>();

        // Iterate through the array.
        for (int i = 0; i < nums.length; i++) {
            // Calculate the required number to form the target sum.
            int newTarget = target - nums[i];

            // If the required number is already in the map, return the indices.
            if (map.containsKey(newTarget)) 
                return new int[]{i, map.get(newTarget)};

            // Store the current number and its index in the map.
            map.put(nums[i], i);
        }

        // If no pair is found, return [-1, -1].
        return new int[]{-1, -1}; 
    }

/********************************************************************************************************************/
   // Handler
   public int[] twoSum(int[] nums, int target) {
        return twoSum3(nums, target);
   }
}
// @lc code=end

