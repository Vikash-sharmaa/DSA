import java.util.Arrays;

class Solution {
/**************************************************************************************************************************/

    // Time: O(N * largestMinDis)

    public static int aggressiveCows_Naive(int[] stalls, int k) {
        int n = stalls.length;
        int res = -1;
    
        // Sort the stalls to arrange them in increasing order of positions
        Arrays.sort(stalls);
    
        // The largest possible minimum distance = distance between the first and last stall
        int largestMinDis = stalls[n - 1] - stalls[0];
    
        // Brute-force try every minimum distance from 1 to largestMinDis
        for (int i = 1; i <= largestMinDis; i++) {
    
            // Check if it is possible to place k cows such that
            // the minimum distance between any two cows is at least 'i'
            if (isPossible(stalls, k, i)) {
                res = i; // update the result if possible
            }
        }
        return res; // return the largest minimum distance found
    }

    
/**************************************************************************************************************************/

    public static int aggressiveCows(int[] stalls, int k) {
        int n = stalls.length; // total number of stalls
        int res = -1; // to store the answer
    
        // Step 1: Sort the stalls to arrange them in increasing order of positions
        Arrays.sort(stalls);
    
        // Step 2: Set the search space for minimum distance
        // Smallest minimum distance = 1
        // Largest minimum distance = distance between first and last stall
        int start = 1, end = stalls[n - 1] - stalls[0];
    
        // Step 3: Apply Binary Search on "minimum distance"
        while (start <= end) {
            int mid = start + (end - start) / 2; // Safe mid calculation to avoid overflow
    
            // Step 4: Check if it is possible to place k cows with at least mid distance
            if (isPossible(stalls, k, mid)) {
                res = mid;    // mid is a valid answer, but try to find a larger minimum distance
                start = mid + 1;
            } else {
                end = mid - 1; // mid is too large, reduce the distance
            }
        }
    
        return res; // return the largest minimum distance found
    }

    
/**************************************************************************************************************************/

    private static boolean isPossible(int[] stalls, int k, int minDis) {
        int cows = 1;           // Place the first cow in the first stall
        int last = stalls[0];   // Record the position of the last placed cow
    
        // Iterate through the remaining stalls
        for(int i = 1; i < stalls.length; i++) {
            // Check if the current stall is at least 'minDis' away from the last cow
            if(stalls[i] - last >= minDis) {
                cows++;            // Place the next cow here
                last = stalls[i];  // Update the position of the last placed cow
            }
    
            // If we have placed all 'k' cows successfully
            if(cows >= k) return true;  // Feasible to place with this 'minDis'
        }
    
        // Not possible to place all cows with at least 'minDis' distance
        return false;
    }

/**************************************************************************************************************************/

}