/*  https://leetcode.com/problems/course-schedule/description/

 * @lc app=leetcode id=207 lang=java
 *   Can also use cycle detection in a directed graph using DFS
 * Positive scenerio - [[1,0],[2,1],[3,2]]
 * Negative scenerio - [[1,2],[4,3],[2,4],[4,1]]  -> this will form a cycle 
 * Since there is only one side move - it is directed graph
 * It should not have a circle in a Ideal situation
 * So we need to find if this has a cycle or not
 * [207] Course Schedule
 */

// @lc code=start

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution {

    boolean bfs(Deque<Integer> queue, int[] inDegree, List<List<Integer>> adjList, int numCourses) {
        int count = 0; // Keeps track of the number of courses we are able to complete
        
        while (!queue.isEmpty()) { // Process nodes with in-degree 0
            int front = queue.pollFirst(); // Remove a node from the front of the queue
            count++; // Increment the count of completed courses

            for (int neighbor : adjList.get(front)) { // Traverse all its neighbors
                inDegree[neighbor]--; // Reduce in-degree since one prerequisite is now satisfied
                
                if (inDegree[neighbor] == 0) { // If a course has no more prerequisites
                    queue.offerLast(neighbor); // Add it to the queue to process next
                }
            }
        }
        return count == numCourses; // If we completed all courses, return true
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>(); // Adjacency list for graph representation
        Deque<Integer> queue = new ArrayDeque<>(); // Queue for processing courses with in-degree 0

        // Initialize adjacency list with empty lists for each course
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        // Build adjacency list (directed graph), where p[1] is a prerequisite for p[0]
        for (int[] p : prerequisites) {
            adjList.get(p[1]).add(p[0]); // Directed edge from p[1] to p[0]
        }

        int[] inDegree = new int[numCourses]; // Track in-degree (number of prerequisites) for each course

        // Compute in-degree for each course
        for (int i = 0; i < numCourses; i++) {
            for (int neighbor : adjList.get(i)) {
                inDegree[neighbor]++; // Increase in-degree for dependent courses
            }
        }

        // Find all courses with no prerequisites (in-degree 0) and add them to the queue
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offerLast(i);
            }
        }

        // Perform BFS to check if all courses can be completed
        return bfs(queue, inDegree, adjList, numCourses);
    }
}

// @lc code=end

