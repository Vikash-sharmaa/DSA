/*
 * @lc app=leetcode id=2164 lang=java
 *
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Solution {

/********************************************************************************************************************************/

    // Time:- O(nlog(n))      ||      Space:- O(n)

    int[] sortEvenOdd1(int[] nums) {
        // Create two lists to store numbers at even and odd indices separately
        List<Integer> even = new ArrayList<>();
        List<Integer> odd = new ArrayList<>();

        // Step 1: Separate elements at even and odd indices
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) even.add(nums[i]); // Elements at even indices
            else odd.add(nums[i]); // Elements at odd indices
        }

        // Step 2: Sort even index elements in ascending order
        Collections.sort(even);

        // Step 3: Sort odd index elements in descending order
        Collections.sort(odd, Comparator.reverseOrder());

        // Step 4: Place sorted even index elements back into nums
        int i = 0;
        for (int n : even) {
            nums[i] = n;  // Place even-indexed elements at even positions
            i += 2;
        }

        // Step 5: Place sorted odd index elements back into nums
        i = 1;
        for (int n : odd) {
            nums[i] = n;  // Place odd-indexed elements at odd positions
            i += 2;
        }

        return nums; // Return the modified array
    }

/********************************************************************************************************************************/

// Notice how bubble sort is being used to sort the elements in place for even and odd indices.
    // -- Using two pointers

    // Time:- O(n^2)      ||      Space:- O(1)

public int[] sortEvenOdd2(int[] nums) {
    int n = nums.length;

    // Step 1: Sort even indices in ascending order using two pointers
    for (int i = 0; i < n; i += 2) {
        for (int j = i + 2; j < n; j += 2) {
            if (nums[i] > nums[j]) {
                swap(nums, i, j);
            }
        }
    }

    // Step 2: Sort odd indices in descending order using two pointers
    for (int i = 1; i < n; i += 2) {
        for (int j = i + 2; j < n; j += 2) {
            if (nums[i] < nums[j]) {
                swap(nums, i, j);
            }
        }
    }

    return nums;
}

private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}

/********************************************************************************************************************************/

    public int[] sortEvenOdd(int[] nums) {
        return sortEvenOdd1(nums);
    }
}
// @lc code=end

