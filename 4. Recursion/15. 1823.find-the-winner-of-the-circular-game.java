
/*
    Asked to me in UKG -  every person kills the next one 
 * @lc app=leetcode id=1823 lang=java
 *
 * 
  There are n friends that are playing a game. The friends are sitting in a circle and are numbered from 1 to n in clockwise order. More formally, moving clockwise from the ith friend brings you to the (i+1)th friend for 1 <= i < n, and moving clockwise from the nth friend brings you to the 1st friend.

    The rules of the game are as follows:

        1. Start at the 1st friend.
        2. Count the next k friends in the clockwise direction including the friend you started at. The counting wraps around the circle and may count some friends more than once.
        3. The last friend you counted leaves the circle and loses the game.
        4. If there is still more than one friend in the circle, go back to step 2 starting from the friend immediately clockwise of the friend who just lost and repeat.
        5. Else, the last friend in the circle wins the game.
        6. Given the number of friends, n, and an integer k, return the winner of the game.


        Input: n = 5, k = 2
        Output: 3
            Explanation: Here are the steps of the game:
            1) Start at friend 1.
            2) Count 2 friends clockwise, which are friends 1 and 2.
            3) Friend 2 leaves the circle. Next start is friend 3.
            4) Count 2 friends clockwise, which are friends 3 and 4.
            5) Friend 4 leaves the circle. Next start is friend 5.
            6) Count 2 friends clockwise, which are friends 5 and 1.
            7) Friend 1 leaves the circle. Next start is friend 3.
            8) Count 2 friends clockwise, which are friends 3 and 5.
            9) Friend 5 leaves the circle. Only friend 3 is left, so they are the winner.
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {

/*
  ✅ Each recursive call only modifies the list and index but does not change the returned winner.
  ✅ Once the base case is reached, the same value propagates up through all recursive calls without modification.
 */

    int winner(List<Integer> lis, int i, int k) {
        int len = lis.size();
    
        // Base Case: When only one element remains, return it as the winner
        if (len == 1) return lis.get(0);
    
        // Calculate the next index to remove using modular arithmetic
        // % - bcoz circular array 
        // -1 bcoz current friend must be included while counting
        i = (i + k - 1) % len; 
    
        // Remove the element at the computed index
        lis.remove(i);
    
        // Recur with the updated list and next starting index
        return winner(lis, i, k);
    }
    
    public int findTheWinner(int n, int k) {
        // Initialize the list with players numbered from 1 to n
        List<Integer> lis = new ArrayList<>();
        for (int i = 1; i <= n; i++) lis.add(i);
    
        // Start recursion with the first player (index 0) and step size k
        return winner(lis, 0, k);
    }
    
}
// @lc code=end

