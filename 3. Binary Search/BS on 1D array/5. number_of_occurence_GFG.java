// can also use lower_bound and upper_bound
// upper_bound-1 would be last position of the target
// think it like this 

class Solution {
    
    int firstOccurrence(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        int res = -1; // default if not found

        while (start <= end) {
            int mid = start + (end - start) / 2; // safe mid calculation

            if (nums[mid] == target) {  // potential first occurrence found
                res = mid;               // store current index
                end = mid - 1;           // move left to find an earlier occurrence
            } else if (nums[mid] > target) {
                end = mid - 1;           // target is on the left half
            } else {
                start = mid + 1;         // target is on the right half
            }
        }
        return res; // returns index of first occurrence or -1 if not found
    }


    int lastOccurrence(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        int res = -1; // default if not found

        while (start <= end) {
            int mid = start + (end - start) / 2; // safe mid calculation

            if (nums[mid] == target) {  // potential last occurrence found
                res = mid;               // store current index
                start = mid + 1;         // move right to find a later occurrence
            } else if (nums[mid] > target) {
                end = mid - 1;           // target is on the left half
            } else {
                start = mid + 1;         // target is on the right half
            }
        }
        return res; // returns index of last occurrence or -1 if not found
    }

    int countFreq(int[] nums, int target) {
        // code here
        int first=firstOccurrence(nums,target);
        int last=lastOccurrence(nums,target);
        return (first==-1 || last==-1) ? 0 : last-first+1;
    }
}
