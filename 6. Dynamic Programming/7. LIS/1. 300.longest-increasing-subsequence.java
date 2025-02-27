/*
 * @lc app=leetcode id=300 lang=java
 *
 * [300] Longest Increasing Subsequence
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/************************************************************************************************************************************************/
 /*
    # If we closely observe the problem then we can convert this problem to longest Common Subsequence Problem.
    # Firstly we will create another array of unique elements of original array and sort it. 
    # Now the longest increasing subsequence of our array must be present as a subsequence in our sorted array. 
    # That’s why our problem is now reduced to finding the common subsequence between the two arrays.

    # Time and Space: O(n^2)
  */
// @lc code=start
class Solution {

    int modifiedLCS(int[] nums1, int[] nums2, int len1, int len2) {
        // Create a DP table where dp[i][j] stores the length of the LCS of the first i elements of nums1
        // and the first j elements of nums2.
        int[][] dp = new int[len1 + 1][len2 + 1];

        // Initialize the DP table. If either sequence has zero elements, the LCS is 0.
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 || j == 0) dp[i][j] = 0;
            }
        }

        // Fill the DP table using the LCS relation:
        // If nums1[i-1] == nums2[j-1], then the element is part of the LCS.
        // Otherwise, take the maximum LCS by excluding one element from either nums1 or nums2.
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    // If the elements match, include them in the LCS and add 1 to the LCS length.
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    // Otherwise, take the maximum of excluding the current element from nums1 or nums2.
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // The length of the LCS of nums1 and nums2 is stored in dp[len1][len2].
        return dp[len1][len2];
    }


/************************************************************************************************************************************************/

/*
    # Note vvvi: This is better one template in case of 'take' and 'notTake'  which there is 'notTake' option always there.
    # So just find the condition for 'take' and return the max(take, notTake)

    # How?
    # There is no need to check the possibility of 'taking' and 'not taking' in possible case.
    # There is always one choice to 'not take' and we can only take if follows the sequence.

    # in case of pick and non_pick always write the pick case and non_pick case separately 
    # if there are some condition involved before including or not_including any ele.

    # time: O(2^n)
    # space: O(n)
 */

    int lengthOfLISRecursive(int[] nums, int n, int lastTakenIndex) {
        // Base case: If there are no elements to process, return 0
        if (n == 0) return 0;

        // Initialize the "take" option to 0
        int take = 0;

        // Check if the current element can be included in the LIS
        // `lastIndex` starts as nums.length (invalid index) for the first call
        if (lastTakenIndex == nums.length || nums[n - 1] < nums[lastTakenIndex]) {
            // Include the current element and move to the next element
            take = 1 + lengthOfLISRecursive(nums, n - 1, n - 1);
        }

        // Exclude the current element and move to the next element
        int notTake = lengthOfLISRecursive(nums, n - 1, lastTakenIndex);

        // Return the maximum of including or excluding the current element
        return Math.max(take, notTake);
    }

    int lengthOfLISMemo(int[] nums,int n,int lastTakenIndex,Integer[][] dp){
        if(n==0) return 0;

        if(dp[n][lastTakenIndex]!=null) return dp[n][lastTakenIndex];
        int take=0;
        if(lastTakenIndex==nums.length || nums[n-1]<nums[lastTakenIndex]){
            take=1+lengthOfLISMemo(nums,n-1,n-1,dp);
        }
        int notTake=lengthOfLISMemo(nums,n-1,lastTakenIndex,dp);

        return dp[n][lastTakenIndex] = Math.max(take,notTake);
    }

/************************************************************ Need to understand this again ********************************************************/

    public int lengthOfLISTabulation(int[] nums) {
        int n = nums.length;
        // dp[i][j] stores the length of the longest increasing subsequence starting from index i when j is the index of the previous picked element.
        int[][] dp = new int[n + 1][n + 1];
        
        // Bottom-up calculation
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int pre_ind = ind - 1; pre_ind >= -1; pre_ind--) {
                int take = 0;
                int notTake = dp[ind + 1][pre_ind + 1];
                
                if (pre_ind == -1 || nums[ind] > nums[pre_ind]) {
                    take = 1 + dp[ind + 1][ind + 1];
                }
                
                dp[ind][pre_ind + 1] = Math.max(take, notTake);
            }
        }
        
        // The result is stored in dp[0][0], which corresponds to the case where we start from index 0 with no previous element.
        return dp[0][0];
    }

/******************************************************************************************************************************************************/
    
/*
        # logic: traverse the array from right to left . just the conversion of above logic and optimising the space to O(n).
        # e.g: LIS[2] means nums[2] will get appened(+1) to any of the LIS ahead of it,
        # (i.e if all the ele of of any of the LIS will be grater than nums[2] then we will add +1).     
        # very better one  

        # Giving essence of Kadane's algorithm

        # dp[i] -> depicts the LIS at index i
        # time: O(n^2)
     */

    public int lengthOfLISOptimized(int[] nums) {
        int n=nums.length;
        int[] dp=new int[n];
        Arrays.fill(dp, 1);

        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                if(nums[j]<nums[i]){
                    dp[i]=Math.max(dp[i], 1+dp[j]);
                }
            }
        }

        int ans=0;
        for(int i=0;i<n;i++){
            ans=Math.max(ans, dp[i]);
        }

        return ans;
    }

    /*
    similar as above - no need to traverse the dp array again

    public int lengthOfLISOptimized(int[] nums) {
        int n=nums.length;
        int[] dp=new int[n];
        Arrays.fill(dp, 1);

        int ans=1;
        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                if(nums[j]<nums[i]){
                    dp[i]=Math.max(dp[i], 1+dp[j]);
                }
            }
            ans=Math.max(ans, dp[i]);
        }

        return ans;
    }
         */





/*********************************************************************** Best (Binary Search)**********************************************************/

         public int lengthOfLISbinarySearch(int[] nums) {
            // `res` will store the elements of the current longest increasing subsequence
            List<Integer> res = new ArrayList<>();
            
            // Iterate through the array
            for (int i = 0; i < nums.length; i++) {
                // If the current number is greater than the last element of `res`, 
                // it can be safely appended to maintain the increasing order
                if (res.isEmpty() || nums[i] > res.get(res.size() - 1)) {
                    res.add(nums[i]);
                } else {
                    // If the current number is not greater, find the position to replace
                    // using binary search
                    int ind = Collections.binarySearch(res, nums[i]);
                    
                    
                    // If the number is not found, `Collections.binarySearch` returns
                    // `-(insertion point) - 1`. We calculate the insertion point.
                    if (ind < 0) ind = -ind - 1;
                    System.out.println(ind);
                    
                    // Replace the element at the found index to maintain the smallest possible
                    // elements for future replacements
                    res.set(ind, nums[i]);
                }
            }
            
            // The size of `res` gives the length of the longest increasing subsequence
            return res.size();
        }
        



    //Handler method
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;

        // Step 1: Create a sorted array of unique elements from `nums`.
        // This sorted array represents the "ideal" LIS (Longest Increasing Subsequence) sequence.
        int[] sortedNums = Arrays.stream(nums)
                                .distinct()  // Remove duplicates to ensure uniqueness.
                                .sorted()    // Sort the array to create an increasing order.
                                .toArray();

        // Step 2: Use the `modifiedLCS` function to compute the LCS between the original array `nums`
        // and the sorted array `sortedNums`.
        // The LCS gives the length of the LIS, as it identifies the subsequence of `nums`
        // that matches the increasing sequence in `sortedNums`.
        //return modifiedLCS(nums, sortedNums, n, sortedNums.length);

        //return lengthOfLISRecursive(nums,n,n);
        Integer[][] dp=new Integer[n+1][n+1];
        //return lengthOfLISMemo(nums,n,n,dp);
        //return lengthOfLISTabulation(nums);

        //return lengthOfLISOptimized(nums);
        return lengthOfLISbinarySearch(nums);
    }

}

// @lc code=end

