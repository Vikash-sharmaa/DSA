
/*
 * @lc app=leetcode id=779 lang=java
 *
 * [779] K-th Symbol in Grammar
 */

// @lc code=start
class Solution {

/********************************************************************************************************************************/
    // Memory limit Exceed - as we are storing the string 

    int helper(int n,int k,int i,StringBuilder s){
        if(i>n) return s.charAt(k-1)-'0';
        StringBuilder sb=new StringBuilder();
        for(int j=0;j<s.length();j++){
            if(s.charAt(j)=='0') sb.append("01");
            else sb.append("10");
        }
        return helper(n,k,i+1,sb);
    }
    public int kthGrammarNaive(int n, int k) {
        StringBuilder s=new StringBuilder("0");
        return helper(n,k,1,s);
    }

/********************************************************************************************************************************/

    /*
        * If the input level is 1, return 0 as the sequence starts with 0.
        * Find the midpoint of the sequence for the current level by dividing the total length by 2.
        * If the position is in the left half, solve the problem recursively for the previous level with the same position.
        * If the position is in the right half, solve recursively for the previous level with the position adjusted for the right half 
            and take the complement of the result.
        * Repeat the process until the base case is reached and return the result.
     */



    public int kthGrammar(int n, int k) {
        if(n==1) return 0;
        int mid=(int)Math.pow(2,n-1)/2;
        if(k<=mid) return kthGrammar(n-1,k);
        else return 1-kthGrammar(n-1,k-mid);
    }

/********************************************************************************************************************************/
}
// @lc code=end

