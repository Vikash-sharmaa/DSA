
class Solution {


    /**************************************************************************************************************************/
        // Time: O(n*m)
        
        public int rowWithMax1s_Naive(int arr[][]) {
            int maxCountOnes = 0; // stores maximum number of 1's found so far in any row
            int index = -1;       // stores the index of the row with maximum 1's
            
            // Traverse each row
            for(int i = 0; i < arr.length; i++){
                int currCountOnes = 0; // stores count of 1's for current row
                
                // Traverse each column of current row
                for(int j = 0; j < arr[0].length; j++){
                    if(arr[i][j] == 1) currCountOnes++; // count the 1's
                }
                
                // If current row has more 1's than previous maximum
                if(currCountOnes > maxCountOnes){
                    maxCountOnes = currCountOnes; // update maximum
                    index = i; // update the row index
                }
            }
            
            return index; // return the row index with the maximum 1's
        }
    /**************************************************************************************************************************/
    
        // Time: O(n*log(m))
        
        public int firstOccurenceOfOne(int[] a){
            int firstOcc = -1;  // initialize as -1
            int start = 0, end = a.length - 1;
            while(start <= end){
                int mid = start + (end - start) / 2;
                
                if(a[mid] == 1){
                    firstOcc = mid;    // store the first occurrence
                    end = mid - 1;     // try to find even earlier
                } else {
                    start = mid + 1;   // move right
                }
            }
            return firstOcc;
        }
        
        // Function to find row with maximum number of 1's
        public int rowWithMax1s(int arr[][]) {
            int maxCountOnes = 0;  // maximum count of 1's found so far
            int index = -1;        // index of the row with maximum 1's
        
            // Traverse each row
            for(int i = 0; i < arr.length; i++){
                int firstOcc = firstOccurenceOfOne(arr[i]);  // first occurrence of 1 in current row
                
                // If no 1 is present in this row, skip it
                if(firstOcc != -1){
                    int currentCountOnes = arr[i].length - firstOcc;  // total number of 1's in this row
                    
                    if(currentCountOnes > maxCountOnes){
                        maxCountOnes = currentCountOnes;   // update max count
                        index = i;                         // update row index 
                    }
                }
            }
            return index;  // return row index with max 1's
        }
    
    
    /**************************************************************************************************************************/
    
    }
