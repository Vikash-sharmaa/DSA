

class Solution {
    public static int largest(int[] arr) {
        int largest = -1;
        for(int i=0;i<arr.length;i++) largest=Math.max(largest,arr[i]);
        return largest;
    }
}
