/*
 * @lc app=leetcode id=347 lang=java
 *
 * [347] Top K Frequent Elements
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


class Solution {

/********************************************************************************************************************/

    // Time Complexity: O(n log n) → Counting takes O(n), sorting takes O(n log n), and extracting k elements takes O(k), so overall O(n log n).
    // Space Complexity: O(n) → The HashMap and List<List<Integer>> store at most O(n) elements.
        
        public int[] topKFrequent1(int[] nums, int k) {
            // Step 1: Count the frequency of each element
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            // Step 2: Create a list to store (frequency, number) pairs
            List<List<Integer>> lis = new ArrayList<>();

            // Convert map entries to a list of (frequency, number) pairs
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                lis.add(Arrays.asList(entry.getValue(), entry.getKey()));
            }

            // Step 3: Sort the list in descending order based on frequency
            Collections.sort(lis, (a, b) -> b.get(0) - a.get(0));

            // Step 4: Extract the top k elements
            int[] result = new int[k];
            for (int i = 0; i < k; i++) {
                result[i] = lis.get(i).get(1); // Extract the number (not frequency)
            }

            return result;
        }


/********************************************************************************************************************/
// Time: O(n log n) (Since k ≤ n, it simplifies to O(n log n))
// Space: O(n) (For freqMap and pq)


    public int[] topKFrequent2(int[] nums, int k) {
        // Step 1: Count the frequency of each element
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Use a max-heap (PriorityQueue) to sort elements by frequency
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> freqMap.get(b) - freqMap.get(a));
        pq.addAll(freqMap.keySet());

        // Step 3: Extract the top k elements
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pq.poll();
        }

        return result;
    }


/********************************************************************************************************************/

// Time Complexity: O(n log k) → Counting frequencies O(n), inserting into the heap O(n log k), and extracting O(k log k).
// Space Complexity: O(n + k) → HashMap stores O(n) elements, and the heap holds O(k) elements.

    public int[] topKFrequent3(int[] nums, int k) {
        // Step 1: Count the frequency of each number
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Use a min-heap (PriorityQueue) to keep top k elements
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> freqMap.get(a) - freqMap.get(b));

        for (int num : freqMap.keySet()) {
            pq.offer(num);
            if (pq.size() > k) pq.poll(); // Remove least frequent element
        }

        // Step 3: Extract the top k elements
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = pq.poll();
        }

        return result;
    }




/********************************************************************************************************************/

        public int[] topKFrequent(int[] nums, int k) {
            return topKFrequent3(nums, k);
        }
}

// @lc code=end

