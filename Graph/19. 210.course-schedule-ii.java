/*
 * @lc app=leetcode id=210 lang=java
 *
 * [210] Course Schedule II
 * 
 * something before something - think of topo sort 
 */

// @lc code=start

import java.util.*;

class Solution {

    int[] bfs(int numCourses, Deque<Integer> queue, List<List<Integer>> adjList, int[] inDegree) {
        List<Integer> res = new ArrayList<>(); // List to store the topological order of courses
        int count = 0; // Counter to track the number of courses processed

        while (!queue.isEmpty()) { // Process courses with zero in-degree
            int front = queue.pollFirst(); // Remove the front course from the queue
            res.add(front); // Add the course to the result list
            count++; // Increase the count of processed courses

            for (int neighbor : adjList.get(front)) { // Iterate over dependent courses
                inDegree[neighbor]--; // Reduce in-degree of the dependent course

                if (inDegree[neighbor] == 0) { // If a course has no remaining prerequisites
                    queue.offerLast(neighbor); // Add it to the queue
                }
            }
        }

        // If all courses were processed, return the order; otherwise, return an empty array (cycle detected)
        return count == numCourses ? res.stream().mapToInt(e -> e).toArray() : new int[] {};
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjList = new ArrayList<>(); // Adjacency list representation of the graph
        Deque<Integer> queue = new ArrayDeque<>(); // Queue to track courses with zero in-degree

        // Initialize adjacency list with empty lists for each course
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        int[] inDegree = new int[numCourses]; // Array to store the in-degree of each course

        // Build adjacency list and in-degree array
        for (int[] p : prerequisites) {
            adjList.get(p[1]).add(p[0]); // p[1] is a prerequisite for p[0]
        }

        // Calculate in-degrees for each course
        for (int i = 0; i < adjList.size(); i++) {
            for (int neighbor : adjList.get(i)) {
                inDegree[neighbor]++; // Increase in-degree for dependent courses
            }
        }

        // Enqueue all courses that have no prerequisites (in-degree 0)
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offerLast(i);
            }
        }

        // Perform BFS to find the topological order of courses
        return bfs(numCourses, queue, adjList, inDegree);
    }
}

// @lc code=end

