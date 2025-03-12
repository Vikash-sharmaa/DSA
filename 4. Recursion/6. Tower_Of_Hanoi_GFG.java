


class Solution {

//  Time: O(2^n)        ||      Space: O(n) - call stack
    
    void solve(int n, int sour, int help, int dest) {
        // Base case: If there is only one disk, move it directly from source to destination
        if (n == 1) {
            System.out.println("move disk " + n + " from rod " + sour + " to rod " + dest);
            return;
        }

        // Step 1: Move (n-1) disks from source to helper using destination as auxiliary
        solve(n - 1, sour, dest, help);

        // Step 2: Move the nth (largest) disk from source to destination
        System.out.println("move disk " + n + " from rod " + sour + " to rod " + dest);

        // Step 3: Move (n-1) disks from helper to destination using source as auxiliary
        solve(n - 1, help, sour, dest);
    }

    
    int towerOfHanoi(int n, int from, int to, int aux) {
        // The minimum number of moves required for `n` disks is 2^n - 1
        return (1 << n) - 1;  // Equivalent to (int) Math.pow(2, n) - 1
    }
    
}
