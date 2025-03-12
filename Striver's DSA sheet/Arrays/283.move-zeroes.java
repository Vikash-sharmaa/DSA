/* https://leetcode.com/problems/move-zeroes/

 * @lc app=leetcode id=283 lang=java
 *
 * [283] Move Zeroes
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {

/**********************************************************************************************************/

// Brute Force - [Time:- O(n) || Spcae:- O(n)]

    public void moveZeroes1(int[] nums) {
            List<Integer> lis=new ArrayList<>();
            for(int i=0;i<nums.length;i++) {
                if(nums[i]!=0) lis.add(nums[i]);
            }
            int i=0;
            while(i<lis.size()) nums[i]=lis.get(i++);
            while(i<nums.length) nums[i++]=0;
    }
    
/**********************************************************************************************************/

// Brute Force - [Time:- O(n) || Spcae:- O(1)]

    public void moveZeroes(int[] nums) {
    
            int i=0;
            for(int n:nums){
                if(n!=0) nums[i++]=n;
            }
            while(i<nums.length) nums[i++]=0;
    }

/**********************************************************************************************************/

}
// @lc code=end

