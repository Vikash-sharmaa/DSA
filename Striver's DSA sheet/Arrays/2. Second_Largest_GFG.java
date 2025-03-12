import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

class Solution {
    
/**********************************************************************************************************/

    // Time:- O(nlogn) Space:- O(n)

    public int getSecondLargest1(int[] a) {
        // Step 1: Sort the array in ascending order
        Arrays.sort(a);
    
        // Step 2: Use a TreeSet to store unique elements in sorted order
        Set<Integer> set = new TreeSet<>();
        for (int num : a) set.add(num); // Add all elements to the set (removes duplicates)
    
        // Step 3: If the set has only one unique element, there is no second largest
        if (set.size() == 1) return -1;
    
        // Step 4: Traverse the set to find the second largest element
        int count = 0;
        for (int num : set) {
            count++;
            // Second largest is the second last element in the sorted set
            if (count == set.size() - 1) return num;
        }
    
        return -1; // Should not reach here unless input is incorrect
    }

/**********************************************************************************************************/

    // Time:- O(n) Space:- O(1)   - two pass

    public int getSecondLargest2(int[] arr) {
        // Initialize variables to track the largest and second largest elements
        int largest = -1, secondLargest = -1;
    
        // First pass: Find the largest element in the array
        for (int i = 0; i < arr.length; i++) {
            largest = Math.max(arr[i], largest);
        }
    
        // Second pass: Find the second largest element
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != largest) { // Ignore the largest element
                secondLargest = Math.max(arr[i], secondLargest);
            }
        }
    
        return secondLargest; // Return the second largest element
    }
    
/**********************************************************************************************************/

    // Time:- O(n) Space:- O(1)  - one pass
    public int getSecondLargest3(int[] a) {
        // Initialize variables to track the largest and second largest elements
        int largest = -1, secondLargest = -1;
    
        // Iterate through each number in the array
        for (int num : a) {
            if (num > largest) { // If current number is greater than the largest found so far
                secondLargest = largest; // Update second largest before changing the largest
                largest = num; // Update largest
            } else if (num > secondLargest && num != largest) { 
                // If num is greater than secondLargest but not equal to largest
                secondLargest = num; // Update second largest
            }
        }
    
        return secondLargest; // Return the second largest element
    }
/**********************************************************************************************************/
}