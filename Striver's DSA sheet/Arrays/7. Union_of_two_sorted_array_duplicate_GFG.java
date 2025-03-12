// https://www.geeksforgeeks.org/problems/union-of-two-sorted-arrays-1587115621/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=union-of-two-sorted-arrays

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

class Solution {

/**********************************************************************************************************/

    // Time:- O(mlog(m+n)+nlog(m+n)+(m+n))=O((m+n)log(m+n))     Space:- O((m+n))
    
    public static ArrayList<Integer> findUnion1(int a[], int b[]) {
        Set<Integer> res = new TreeSet<>();
        for (int num : a) res.add(num);  // Insert elements of array `a`
        for (int num : b) res.add(num);  // Insert elements of array `b`
        
        return new ArrayList<>(res); // Convert TreeSet to ArrayList
    }
    
/**********************************************************************************************************/

    // Time	O(n + m)         Space	O(n + m) (output storage)
    
    public static ArrayList<Integer> findUnion(int a[], int b[]) {
        int n = a.length;
        int m = b.length;
        int i = 0, j = 0;
        ArrayList<Integer> ans = new ArrayList<Integer>();

        // Using two pointers i and j over the two arrays which helps
        // in storing the smaller element.
        while (i < n && j < m) {
            // Updating the pointer i until we have identical
            // elements at consecutive position in a.
            while (i + 1 < n && a[i] == a[i + 1]) i++;

            // Updating the pointer j until we have identical
            // elements at consecutive position in b.
            while (j + 1 < m && b[j] == b[j + 1]) j++;

            // Comparing element of the arrays a and b at pointers
            // i and j and accordingly storing the smaller element
            // and updating the pointers.
            if (a[i] < b[j])
                ans.add(a[i++]);
            else if (b[j] < a[i])
                ans.add(b[j++]);
            else {
                // If a[i] is the same as b[j], we update both pointers.
                ans.add(b[j++]);
                i++;
            }
        }

        // Storing the remaining elements of first array (if there are any).
        while (i < n) {
            // Updating the pointer i until we have identical
            // elements at consecutive position in a.
            while (i + 1 < n && a[i] == a[i + 1]) i++;

            // Storing the elements.
            ans.add(a[i++]);
        }

        // Storing the remaining elements of second array (if there are any).
        while (j < m) {
            // Updating the pointer j until we have identical
            // elements at consecutive position in b.
            while (j + 1 < m && b[j] == b[j + 1]) j++;

            // Storing the elements.
            ans.add(b[j++]);
        }

        // Returning the list containing the union of the two arrays.
        return ans;
    }
    
/**********************************************************************************************************/

}

