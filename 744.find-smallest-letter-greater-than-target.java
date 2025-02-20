/*
 * @lc app=leetcode id=744 lang=java
 *
 * [744] Find Smallest Letter Greater Than Target
 */

// @lc code=start
class Solution {

    public char nextGreatestLetter(char[] letters, char target) {
        int n= letters.length;
        char res=letters[0];
        int start=0,end=n-1;
        while(start<=end){
            int mid=start+(end-start)/2;
            if(letters[mid]==target) start=mid+1;
            else if(target<letters[mid]){
                res=letters[mid];
                end=mid-1;
            }
            else{
                start=mid+1;
            }
        }
        return res;
    }
    
}
// @lc code=end

