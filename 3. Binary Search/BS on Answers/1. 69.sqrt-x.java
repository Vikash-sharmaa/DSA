/* https://leetcode.com/problems/sqrtx/description/

 * @lc app=leetcode id=69 lang=java
 *
 * [69] Sqrt(x)
 */

 /*
        BS on answers 
    1. Find min possible / max possible integer
    2. Always has a range of answers
    

*/ 

 // max integer - which on squaring gives x

// @lc code=start
class Solution {
/*********************************************************************************************************/
    
    public int mySqrt_Direct_Method(int x) {
        return (int)Math.sqrt(x);
    }

/*********************************************************************************************************/

    public int mySqrt_Naive(int x) {
        int res=1;
        if(x==0 || x==1) return x;
        for(int i=1;i<=x;i++){
            if((long)i*i<=x) res=i;
            else break;
        }
        return res;
    }

/*********************************************************************************************************/

    public int mySqrt(int x) {
        // Base case: if x is 0 or 1, return x itself
        if (x == 0 || x == 1) return x;
    
        int res = 1;               // Variable to store the floor of sqrt(x)
        int start = 1, end = x;    // Binary search range between 1 and x
    
        // Standard binary search loop
        while (start <= end) {
            int mid = start + (end - start) / 2;   // Calculate mid safely to prevent overflow
    
            // Convert mid*mid to long to prevent integer overflow
            if ((long)mid * mid == x) return mid;  // Found perfect square root, return mid
            
            // If mid*mid is less than x, move to the right half
            else if ((long)mid * mid < x) {
                res = mid;                         // mid is a potential answer (floor)
                start = mid + 1;                    // Shift search space to right
            }
            // If mid*mid is greater than x, move to the left half
            else {
                end = mid - 1;                      // Shift search space to left
            }
        }
        
        // Return the floor of sqrt(x) when exact square root is not found
        return res;
    }
    
/*********************************************************************************************************/

}
// @lc code=end

