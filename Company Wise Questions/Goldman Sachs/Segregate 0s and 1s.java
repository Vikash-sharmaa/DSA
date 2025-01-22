import java.util.Arrays;

class Solution {
    void segregate0and1Sorting(int[] arr) {
        // Use the built-in sorting algorithm to sort the array
        Arrays.sort(arr); 
        // Sorting will naturally place all 0s at the beginning and 1s at the end due to their values.
    }

    
    void segregate0and1Counting(int[] arr) {
        int countZero = 0; // Variable to count the number of 0s in the array
        for (int i = 0; i < arr.length; i++) { 
            if (arr[i] == 0) countZero++; // Increment the count for each 0
        }
        int i = 0;
        // Fill the first part of the array with 0s
        while (i < countZero) arr[i++] = 0;
        // Fill the remaining part of the array with 1s
        while (i < arr.length) arr[i++] = 1;
    }

    
    void segregate0and1(int[] arr) {
        int i = 0, j = arr.length - 1; // Two pointers, one starting from the left and the other from the right
        while (i < j) {
            if (arr[i] == 1 && arr[j] == 0) {
                // Swap if the left pointer is at 1 and the right pointer is at 0
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++; // Move both pointers inward
                j--;
            } else if (arr[i] == 0) {
                // If the left pointer is already at 0, move it to the right
                i++;
            } else if (arr[j] == 1) {
                // If the right pointer is already at 1, move it to the left
                j--;
            }
        }
    }

}
