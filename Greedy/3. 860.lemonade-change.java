package Greedy;
/* https://leetcode.com/problems/lemonade-change/

 * @lc app=leetcode id=860 lang=java
 *
 * [860] Lemonade Change
 */

// @lc code=start
class Solution {
    public boolean lemonadeChange(int[] bills) {
        int fives = 0, tens = 0; // Track the count of $5 and $10 bills
    
        for (int bill : bills) {
            if (bill == 5) {
                fives++; // Customer pays with $5, no change needed
            }else if(bill == 10) {
                if (fives >= 1) { // We need a $5 bill to give change
                    tens++; // Store the $10 bill
                    fives--; // Give one $5 bill as change
                } else {
                    return false; // No $5 bill available to give change
                }
            }else{ // bill == 20
                if (fives >= 1 && tens >= 1) { 
                    // Prioritize giving one $10 and one $5 as change
                    fives--; 
                    tens--; 
                } else if (fives >= 3) { 
                    // If no $10 bill, give three $5 bills
                    fives -= 3;
                } else {
                    return false; // Not enough change available
                }
            }
        }
        return true; // Successfully gave change to all customers
    }    
}
// @lc code=end

