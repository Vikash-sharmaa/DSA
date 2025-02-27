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

/********************************************************************************************************************************/

    // Time:- O(n^3)      ||      Space:- O(n^2)- due to storing unique triplets in the set

    public List<List<Integer>> threeSum1(int[] nums) {
        int n = nums.length;

        // Step 1: Use a Set to store unique triplets to avoid duplicates
        Set<List<Integer>> set = new HashSet<>();

        // Step 2: Iterate through each possible triplet in a brute-force manner
        for (int i = 0; i < n - 2; i++) { // First element (fix i)
            for (int j = i + 1; j < n - 1; j++) { // Second element (fix j)
                for (int k = j + 1; k < n; k++) { // Third element (fix k)
                    // Step 3: Check if the three numbers sum to zero
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        // Step 4: Store the triplet in a Set to avoid duplicates
                        set.add(new ArrayList<>(List.of(nums[i], nums[j], nums[k])));
                    }
                }
            }
        }

        // Step 5: Convert the set into a list and return
        return new ArrayList<>(set);
    }



/********************************************************************************************************************************/

    // Time:- O(n^2)      ||      O(n²) worst-case (for storing results) but O(n) auxiliary space for set in each iteration.

    public List<List<Integer>> threeSum2(int[] nums) {
        int n = nums.length;
        Set<List<Integer>> res = new HashSet<>(); // To store unique triplets

        // Step 1: Iterate through each number as a fixed first element
        for (int i = 0; i < n - 2; i++) {
            Set<Integer> set = new HashSet<>(); // HashSet to store visited numbers for two-sum check
            for (int j = i + 1; j < n; j++) {
                int target = -(nums[i] + nums[j]); // Step 2: Find the third number that sums to zero
                if (set.contains(target)) {
                    // Step 3: If target exists in the set, we found a valid triplet
                    res.add(new ArrayList<>(List.of(nums[i], target, nums[j])));
                } else {
                    // Step 4: Store nums[j] in the set for future lookups
                    set.add(nums[j]);
                }
            }
        }
        return new ArrayList<>(res); // Convert set to list and return
    }


/********************************************************************************************************************************/

    // Time:- O(n^2)      ||      Space:- O(k), where k is the number of unique triplets found

    public List<List<Integer>> threeSum3(int[] nums) {
        int n = nums.length;
        Set<List<Integer>> res = new HashSet<>(); // Set to store unique triplets

        // Step 1: Iterate through each number as a fixed first element
        for (int i = 0; i < n - 2; i++) {
            int j = i + 1, k = n - 1; // Two-pointer approach (j starts after i, k starts at end)

            while (j < k) { // Step 2: While the two pointers don’t cross
                int sum = nums[i] + nums[j] + nums[k]; // Calculate the sum of the triplet

                if (sum == 0) { // Step 3: Found a valid triplet
                    res.add(new ArrayList<>(List.of(nums[i], nums[j], nums[k])));
                    j++; // Move left pointer forward
                    k--; // Move right pointer backward
                } else if (sum < 0) {
                    j++; // Step 4: Increase sum by moving left pointer forward
                } else {
                    k--; // Step 5: Decrease sum by moving right pointer backward
                }
            }
        }
        return new ArrayList<>(res); // Convert set to list and return
    }


/********************************************************************************************************************************/



    public List<List<Integer>> threeSum4(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        // Step 1: Sort the array to easily handle duplicates
        Arrays.sort(nums);
        int n = nums.length;

        // Step 2: Iterate through each element as the first number in triplet
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip duplicate values for i

            int j = i + 1, k = n - 1; // Two pointers: j (left) and k (right)

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];

                if (sum == 0) { 
                    // Found a valid triplet
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));

                    // Move `j` and `k` to next unique numbers to avoid duplicates
                    while (j < k && nums[j] == nums[j + 1]) j++;
                    while (j < k && nums[k] == nums[k - 1]) k--;

                    j++; // Move to next unique number
                    k--; // Move to next unique number
                } else if (sum < 0) {
                    j++; // Increase sum by moving left pointer forward
                } else {
                    k--; // Decrease sum by moving right pointer backward
                }
            }
        }
        return res; // Return the list of triplets
    }


/********************************************************************************************************************************/

    public List<List<Integer>> threeSum(int[] nums) {
        if(nums.length<3) return new ArrayList<>();

        Arrays.sort(nums);
        return threeSum4(nums);
    }
}
// @lc code=end

