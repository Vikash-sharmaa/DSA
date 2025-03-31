// same as ship ques - 
// just identify range

class Solution {
/**************************************************************************************************************************/

    public static int findPages_Naive(int[] bookPages, int studentsCount) {
        int n = bookPages.length;
        int answer = -1;
        int maxSingleBookPages = Integer.MIN_VALUE;  // the largest single book page count (lower bound)
        int totalPages = 0;                     // total pages sum (upper bound)
    
        // Finding the search space
        for (int i = 0; i < n; i++) {
            maxSingleBookPages = Math.max(maxSingleBookPages, bookPages[i]);
            totalPages += bookPages[i];
        }
    
        // Brute force: try every possible max pages value from maxSingleBook to totalPages
        for (int maxPagesAllowed = maxSingleBookPages; maxPagesAllowed <= totalPages; maxPagesAllowed++) {
            if (isPossible(bookPages, studentsCount, maxPagesAllowed)) {
                answer = maxPagesAllowed;
                break; // since we want the minimum, we can stop at the first valid
            }
        }
    
        return answer;
    }
    
/**************************************************************************************************************************/

    public static int findPages(int[] bookPages, int studentsCount) {
        int n = bookPages.length;

        // Base case: if books are less than students, impossible to assign
        if(n < studentsCount) return -1;

        int res = -1; // to store the minimum of the maximum pages
        int maxSingleBookPages = Integer.MIN_VALUE;  // lower bound of search space, i.e., largest book's pages
        int totalPages = 0;                         // upper bound of search space, i.e., sum of all pages
    
        // Calculate the lower and upper bound of search space
        for (int i = 0; i < n; i++) {
            maxSingleBookPages = Math.max(maxSingleBookPages, bookPages[i]); // find max(bookPages)
            totalPages += bookPages[i]; // find sum(bookPages)
        }
        
        int start = maxSingleBookPages;  // start of binary search (minimum pages must be at least the largest single book)
        int end = totalPages;            // end of binary search (maximum pages can be the sum of all books)
        
        // Binary Search
        while(start <= end){
            int mid = start + (end - start) / 2; // try to minimize the maximum pages
            
            // If possible to assign books such that no student gets more than 'mid' pages
            if(isPossible(bookPages, studentsCount, mid)){
                res = mid;        // store the result
                end = mid - 1;    // try to find a smaller valid value
            } else {
                start = mid + 1;  // if not possible, increase the allowed maximum pages
            }
        }
    
        return res;
    }

   
/**************************************************************************************************************************/
    
    
    // Checks if it is possible to allocate books such that no student gets more than 'maxPagesAllowed' pages
    static boolean isPossible(int[] bookPages, int studentsCount, int maxPagesAllowed) {
        int studentsUsed = 1; // first student
        int pagesAllocated = 0;
    
        for (int i = 0; i < bookPages.length; i++) {
            // If adding current book exceeds limit, allocate to next student
            if (pagesAllocated + bookPages[i] > maxPagesAllowed) {
                studentsUsed++;
                pagesAllocated = bookPages[i]; // new student gets this book
            } else {
                pagesAllocated += bookPages[i]; // same student takes this book
            }
        }
    
        // We need exactly <= studentsCount students to allocate all books
        return studentsUsed <= studentsCount;
    }

/**************************************************************************************************************************/

}