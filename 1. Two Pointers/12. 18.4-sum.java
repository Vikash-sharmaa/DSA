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

/********************************************************************************************************************************/

    public List<List<Integer>> fourSum1(int[] nums, int target) {
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


/********************************************************************************************************************************/

 /*
        Time & Space Complexity
            Time Complexity: O(n³ log n)
            3 nested loops: O(n³)
            Sorting each quadruplet: O(4 log 4) ≈ O(1)
            HashSet operations: O(1)

            Space Complexity: O(n²) (for storing results in res & lookup set).
*/


    public List<List<Integer>> fourSum2(int[] nums, int target) {
        int n = nums.length;  
        Set<List<Integer>> res = new HashSet<>();  // Using a HashSet to store unique quadruplets.

        // Outer loop: Fix the first element (i)
        for (int i = 0; i < n - 2; i++) {  
            
            // Second loop: Fix the second element (j)
            for (int j = i + 1; j < n - 1; j++) {  

                Set<Long> lookUp = new HashSet<>();  // HashSet to store visited numbers for two-sum check

                // Third loop: Iterate over the remaining elements (k)
                for (int k = j + 1; k < n; k++) {  
                    
                    // Calculate the required number to complete the quadruplet
                    long required = (long) target - (long) nums[i] - (long) nums[j] - (long) nums[k];  
                    
                    // If the required number is already seen, we found a quadruplet
                    if (lookUp.contains(required)) {  
                        List<Integer> temp = new ArrayList<>(List.of(nums[i], nums[j], nums[k], (int) required));  
                        Collections.sort(temp);  // Sort to ensure uniqueness in HashSet
                        res.add(temp);  // Store the quadruplet
                    } else {
                        lookUp.add((long) nums[k]);  // Add current number to lookup set
                    }
                }
            }
        }
        return new ArrayList<>(res);  // Convert set to list before returning
    }

/********************************************************************************************************************************/

 /*
        Time complexity :-
            Sorting the array	O(n log n)
            Two nested loops (i, j)	O(n²)
            Two-pointer traversal	O(n)
            Storing results in HashSet	O(k) (unique quadruplets)
            Total Time Complexity	O(n² log n) (better than O(n³))

        Space Complexity:-
        	O(k) (for storing unique quadruplets)
*/

    public List<List<Integer>> fourSum3(int[] nums, int target) {
        // Step 1: Sort the input array (to easily handle duplicates and use two-pointer approach)
        Arrays.sort(nums);  
        int n = nums.length;  
        Set<List<Integer>> res = new HashSet<>();  // Using a HashSet to avoid duplicate quadruplets

        // Step 2: Fix the first element (i)
        for (int i = 0; i < n - 2; i++) {  

            // Step 3: Fix the second element (j)
            for (int j = i + 1; j < n - 1; j++) {  

                // Step 4: Two-pointer approach for the remaining two elements
                int left = j + 1, right = n - 1;  

                while (left < right) {  
                    // Calculate the sum of four numbers
                    long sum = (long) nums[i] + (long) nums[j] + (long) nums[left] + (long) nums[right];  

                    if (sum == target) {  
                        // If we found a valid quadruplet, add it to the set (ensures uniqueness)
                        res.add(List.of(nums[i], nums[j], nums[left], nums[right]));  

                        // Move both pointers to find the next possible solution
                        left++;  
                        right--;  

                    } else if (sum < target) {  
                        // If sum is too small, move left pointer to increase sum
                        left++;  

                    } else {  
                        // If sum is too large, move right pointer to decrease sum
                        right--;  
                    }
                }
            }
        }
        // Convert set to list and return the final result
        return new ArrayList<>(res);  
    }

/********************************************************************************************************************************/

/*
        Time Complexity:-
            Sorting the array	O(n log n)
            Two nested loops (i, j)	O(n²)
            Two-pointer traversal (left, right)	O(n)
            Skipping duplicates	O(n)
            Total Time Complexity	O(n² log n) (best case) & O(n³) (worst case)

        Space Complexity:-
        	O(k) (storing results, k is number of valid quadruplets)
*/

    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;  
        Arrays.sort(nums);  // Step 1: Sort the array to use the two-pointer approach efficiently
        List<List<Integer>> res = new ArrayList<>();  // List to store the unique quadruplets

        // Step 2: Iterate through the array to fix the first number (i)
        for (int i = 0; i < n - 2; i++) {  
            if (i > 0 && nums[i] == nums[i - 1]) continue;  // Skip duplicate elements for i

            // Step 3: Iterate to fix the second number (j)
            for (int j = i + 1; j < n - 1; j++) {  
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;  // Skip duplicate elements for j

                // Step 4: Two-pointer approach for remaining two numbers
                int left = j + 1, right = n - 1;  

                while (left < right) {  
                    // Calculate the sum of the four chosen numbers
                    long sum = (long) nums[i] + (long) nums[j] + (long) nums[left] + (long) nums[right];  

                    if (sum == target) {  
                        // Step 5: If a valid quadruplet is found, store it in the result list
                        List<Integer> temp = List.of(nums[i], nums[j], nums[left], nums[right]);  
                        res.add(temp);  

                        // Step 6: Skip duplicate values for left and right pointers
                        while (left < right && nums[left] == temp.get(2)) left++;  
                        while (left < right && nums[right] == temp.get(3)) right--;  

                    } else if (sum < target) {  
                        // Step 7: If sum is too small, move left pointer to increase sum
                        left++;  

                    } else {  
                        // Step 8: If sum is too large, move right pointer to decrease sum
                        right--;  
                    }
                }
            }
        }
        return res;  // Return the list of unique quadruplets
    }

/********************************************************************************************************************************/

}
// @lc code=end

