/*
 * @lc app=leetcode id=1980 lang=java
 *
 * [1980] Find Unique Binary String
 */

// @lc code=start

import java.util.HashSet;

class Solution {

    boolean permu(String ip,String op,HashSet<String> set,String[] res){
        if(ip.length()==0){
            if(!set.contains(op)){
                res[0]=op;
                return true;
            }
            return false;
        }

        if(permu(ip.substring(1), op+"0", set, res) || 
            permu(ip.substring(1), op+"1", set, res)){
                return true;
            }
        
            return false;
    }

    

    public String findDifferentBinaryString(String[] nums) {
            String[] res=new String[]{""};
            HashSet<String> set = new HashSet<>();
            for(String n:nums) set.add(n);

            permu(nums[0], "", set, res);
            return res[0];
        
    }



}
// @lc code=end

