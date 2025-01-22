class Solution {
    static int maxStairHeightN(int height) {
        // Variable `stair` represents the current step number we are trying to build.
        int stair = 0;
    
        // Continue building stairs until the remaining height is insufficient for the next step.
        while (stair <= height) {
            // Subtract the height required for the current step from the total available height.
            height = height - stair;
    
            // Move to the next step.
            stair++;
        } 
    
        // The loop exits when the remaining height is insufficient to build the next step.
        // Subtract 1 because the last increment of `stair` was not used.
        return stair - 1;
    }
    
    
    
    static int maxStairHeight(int height) {
        // The formula is derived from the sum of the first `n` natural numbers: (n * (n + 1)) / 2 <= height
        // Rearranging this quadratic inequality gives the formula for `n` (stair height).
        int ans = (int)(Math.sqrt(2.0 * height + 0.25) - 0.5);
        return ans; // Return the largest integer `n` satisfying the inequality.
    }

};