package Recursion;

import java.util.ArrayList;
import java.util.List;

class Solution {

    // Print Subsets of a given String
    // Print all Subsets == Print Powerset == ( Print subsequences )

    /*
        Variations - print unique || print lexicographically || integer array will be given
    */

    
// Time:    O(2^n)      ||          Space: O(n*2^n)
    static void subset(String in,String out,List<String> res){
        if(in.length()==0){
            res.add(out);
            return;
        }

        char ch=in.charAt(0);
        subset(in.substring(1), out, res);
        subset(in.substring(1), out+ch, res);
    }


    public static void main(String[] args){
        List<String> res=new ArrayList<>();
        subset("abc","",res);
        for(String s : res){
            System.out.println(s);
        }
    }

    //     c    b    bc    a    ac    ab    abc  


    /*

        String s = "abcd";

        Substring = ab || bc || cd.  not ac
        Subsequence = ac || bd || bc  not ca
        Subset = ac || ca || ab

        All substrings are subsequences
        All subsequences are subsets

    */


}
