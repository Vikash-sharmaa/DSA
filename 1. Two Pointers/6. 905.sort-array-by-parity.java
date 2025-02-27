/*
 * @lc app=leetcode id=905 lang=java
 *
    Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.
        Return any array that satisfies this condition.

            Example 1:

            Input: nums = [3,1,2,4]
            Output: [2,4,3,1]
            Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.

            Example 2:

            Input: nums = [0]
            Output: [0]
            

            Constraints:

            1 <= nums.length <= 5000
            0 <= nums[i] <= 5000

 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {

/********************************************************************************************************************************/

    // Time:- O(n)      ||      Space:- O(n)

    int[] sortArrayByParity1(int[] nums){
        List<Integer> lis=new ArrayList<>();
        for(int num:nums) {
            if(num%2==0) lis.add(num);
        }
        for(int num:nums) {
            if(num%2!=0) lis.add(num);
        }

        int i=0;
        for(int num : lis) nums[i++]=num;
        return nums;
    }

/********************************************************************************************************************************/
// below is working b'coz answer is asked in any order rather than having the same order as they appear in the array.

    // Time:- O(n)      ||      Space:- O(1)
    int[] sortArrayByParity2(int[] nums){
        int i=0,j=nums.length-1;
        while(i<j){
            if(nums[i]%2!=0 && nums[j]%2==0){
                int temp=nums[i];
                nums[i]=nums[j];
                nums[j]=temp;
                i++;
                j--;
            }else if(nums[i]%2==0){
                i++;
            }else if(nums[j]%2!=0){
                j--;
            }
        }
        return nums;
    }

/********************************************************************************************************************************/


    public int[] sortArrayByParity(int[] nums) {
        return sortArrayByParity2(nums);
    }
}
// @lc code=end

