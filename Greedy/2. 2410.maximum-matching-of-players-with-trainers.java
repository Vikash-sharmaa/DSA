package Greedy;
/* https://leetcode.com/problems/maximum-matching-of-players-with-trainers/description/

 * @lc app=leetcode id=2410 lang=java
 *
 * [2410] Maximum Matching of Players With Trainers
 */

// @lc code=start

import java.util.Arrays;

class Solution {
// Time: O(nlogn)
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        int count = 0; // Variable to store the number of matched players
        int playersLen = players.length; // Number of players
        int trainersLen = trainers.length; // Number of trainers
    
        Arrays.sort(players); // Sort players' skill levels in ascending order
        Arrays.sort(trainers); // Sort trainers' capabilities in ascending order
    
        int i = playersLen - 1, j = trainersLen - 1; // Start from the most skilled player and the most capable trainer
    
        // Iterate while there are players and trainers left
        while (i >= 0 && j >= 0) {
            if (trainers[j] >= players[i]) { // If the most capable trainer can train the most skilled available player
                count++; // Increase the matched count
                i--; // Move to the next skilled player
                j--; // Move to the next available trainer
            } else {
                i--; // If the trainer can't handle the player, move to the next less skilled player
            }
        }
    
        return count; // Return the maximum number of matched players
    }
    
}
// @lc code=end

