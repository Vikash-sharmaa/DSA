/*
    "lower bound" usually refers to the first position where the value is greater than or equal to a target in a sorted array.

    So, if you are searching for x:
    lower_bound gives you the index of the first element such that arr[i] >= x.
    It doesn't mean just strictly greater. It allows equality too.

        For example:

        arr = [1, 3, 3, 5, 7, 9]
        target = 3

        lower_bound = index of first >= 3 → index = 1

 */
/*
    upper_bound gives you the first index where the element is strictly greater than the target.
    Example:

        arr = [1, 3, 3, 5, 7, 9]
        target = 3

        upper_bound = index of first > 3 → index = 3 (arr[3] = 5)

        So:
        lower_bound: first >= target
        upper_bound: first > target

    If you imagine the sorted array like stairs:

    lower_bound says "give me the first stair where I can step on or higher"
    upper_bound says "give me the first stair that's strictly higher"

 */

 /*
   
    If target > arr[n - 1] → returns n.
    If target < arr[0] → returns 0.
    Both bounds will always give you a valid "insertion index" even if the value isn't present.

  */

public class lower_bound {

    /*
      int idx = Arrays.binarySearch(arr, x);
        if (idx < 0) {
            idx = -idx - 1; // this is lower_bound
        }

    // int lb = idx < 0 ? -idx - 1 : idx;

     */

    /*
      int idx = Collections.binarySearch(list, x);
        if (idx < 0) {
            idx = -idx - 1; // lower_bound
        }

        // int lb = idx < 0 ? -idx - 1 : idx;
     */
    
    static int lowerBound(int[] arr, int x) {
        int start = 0, end = arr.length - 1;
        int ans = arr.length; // default if not found
    
        while (start <= end) {
            int mid = start + (end - start) / 2;
    
            // maybe an answer
            if (arr[mid] >= x) {
                ans = mid;
                // look for smaller index on the left
                end = mid - 1;
            } else {
                // look on the right side
                start = mid + 1;
            }
        }
        return ans;
    }

    static int upperBound(int[] arr, int x) {
        int start = 0, end = arr.length - 1;
        int ans = arr.length; // default if not found
    
        while (start <= end) {
            int mid = start + (end - start) / 2;
    
            // maybe an answer
            if (arr[mid] > x) {
                ans = mid;
                // look for smaller index on the left
                end = mid - 1;
            } else {
                // look on the right side
                start = mid + 1;
            }
        }
        return ans;
    }

}


/*
    static void findBounds(int[] arr, int x) {
        // Binary Search using Arrays utility
        int idx = Arrays.binarySearch(arr, x);

        // lower_bound
        int lowerBound = (idx < 0) ? -idx - 1 : idx;

        // upper_bound
        int upperBound = lowerBound;
        while (upperBound < arr.length && arr[upperBound] <= x) {
            upperBound++;
        }

        // floor
        int floor = (idx < 0) ? -idx - 2 : idx;

        // ceil
        int ceil = lowerBound;

        // Output
        System.out.println("Lower Bound Index  = " + lowerBound);
        System.out.println("Upper Bound Index  = " + upperBound);
        System.out.println("Floor Index        = " + floor);
        System.out.println("Ceil Index         = " + ceil);
    }


 */
