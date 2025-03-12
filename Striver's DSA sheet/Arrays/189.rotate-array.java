/*
 * @lc app=leetcode id=189 lang=java
 *
 * [189] Rotate Array - right
 * 
 * Right rotation means shifting elements to the right.
 * 
 * 
 * 
 * /*
    => Right rotation (clockwise):- reverse whole array -> reverse first k -> reverse remaining.
    => Left rotation (anti clockwise):- reverse first k -> reverse remaining -> reverse whole array.

        void leftRotate(int arr[], int k, int n)
        {
            if (k == 0) return;
            k = k % n;
            reverse(nums,0,k-1);
            reverse(nums,k,n-1);
            reverse(nums,0,n-1);
        }
 */

 

// @lc code=start
class Solution {

/**********************************************************************************************************/

// Time:- O(n*k)             Space:- O(1)

    public void rotateNaive(int[] nums, int k) {
        int n=nums.length;
        while(k!=0){
            int last=nums[n-1];
            for(int i=n-1;i>0;i--) nums[i]=nums[i-1];
            nums[0]=last;

            k--;
        }
    }

/**********************************************************************************************************/

// Time:- O(n)             Space:- O(1)

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left]; 
            nums[left] = nums[right]; // Swap elements
            nums[right] = temp;
            left++;
            right--;
        }
    }


    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // Ensure k is within bounds (handling cases where k > n)
        
        // Step 1: Reverse the entire array
        reverse(nums, 0, n - 1); 
    
        // Step 2: Reverse the first k elements
        reverse(nums, 0, k - 1); 
    
        // Step 3: Reverse the remaining n-k elements
        reverse(nums, k, n - 1); 
    }

/**********************************************************************************************************/


}
// @lc code=end

