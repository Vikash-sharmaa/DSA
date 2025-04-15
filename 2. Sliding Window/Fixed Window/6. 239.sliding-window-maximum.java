/* https://leetcode.com/problems/sliding-window-maximum/description/

 * @lc app=leetcode id=239 lang=java
 *
 * [239] Sliding Window Maximum
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution {


/********************************************************************************************************************************/

    // Time:- O(n*k)        ||      Space:- O(1)

    public int[] maxSlidingWindow1(int[] nums,int k){
        int n = nums.length;
        List<Integer> res= new ArrayList<>();
        for(int i=0;i<n-k+1;i++){
            int maxi=nums[i];
            for(int j=i;j<i+k;j++){
                maxi=Math.max(maxi, nums[j]);
            }
            res.add(maxi);
        }

        return res.stream().mapToInt(x->x).toArray();
    }


/********************************************************************************************************************************/

    // Time:- O(n)        ||      Space:- O(k)

    public int[] maxSlidingWindow2(int[] nums, int k) {
        List<Integer> res = new ArrayList<>(); // Stores the maximums of each window
        int n = nums.length;
        Deque<Integer> deque = new ArrayDeque<>(); // Monotonic decreasing deque to store useful elements
        int start = 0, end = 0; // Two pointers defining the sliding window

        while (end < n) {
            int current = nums[end];

            // Remove elements from the back of deque that are smaller than the current element
            // (They will never be the max again)
            while (!deque.isEmpty() && deque.peekLast() < current) deque.pollLast();

            // Add the current element to the back of the deque
            deque.offerLast(current);

            // If window size reaches 'k', start processing results
            if (end - start + 1 == k) {
                int front = deque.peekFirst(); // The maximum element (front of the deque)
                res.add(front); // Store the max in the result list
                
                int toBeRemoved = nums[start]; // Element that is leaving the window

                // If the element at 'start' is the max, remove it from the deque
                if (front == toBeRemoved) deque.pollFirst();

                start++; // Slide the window forward
            }

            end++; // Expand the window
        }

        // Convert List<Integer> to int[] before returning
        return res.stream().mapToInt(x -> x).toArray();
    }
}
// @lc code=end

