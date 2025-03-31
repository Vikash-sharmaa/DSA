
class Solution {
    /****************************************************************************************************************/
       // Time:- O(m)
       
        public int nthRoot_Naive(int n, int m) {
            // Brute-force approach: check every number from 1 to m
            for(int i = 1; i <= m; i++) {
                long val = (long)Math.pow(i, n); // Calculate i^n using Math.pow() safely (cast to long to prevent overflow)
                if(val == m) return i;            // Found nth root of m
            }
            return -1; // If no integer nth root exists, return -1
        }
    /****************************************************************************************************************/
    
        // Time:- O(log(m))
        public int nthRoot(int n, int m) {
            int start = 1, end = m;
        
            while (start <= end) {
                int mid = start + (end - start) / 2;
        
                long val = power(mid, n,m); // Compute mid^n manually using pure multiplication
        
                if (val == m) return mid;          // Found the nth root
                else if (val < m) start = mid + 1; // Move to the right half
                else end = mid - 1;                // Move to the left half
            }
        
            return -1; // No integer nth root exists
        }
        
        // Helper function to calculate base^exp using pure multiplication
        private long power(int base, int exp,int m) {
            long ans = 1;
            for (int i = 1; i <= exp; i++) {
                ans *= base;
                if (ans > m) break; // Early stopping to prevent overflow
            }
            return ans;
        }
    
    /****************************************************************************************************************/
    
}