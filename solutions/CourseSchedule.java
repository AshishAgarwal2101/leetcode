/**
 *
 * 210. Course Schedule II:::::::::::
 * 
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * 
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 * 
 * Example 2:
 * 
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 * 
 * Example 3:
 * 
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 *  
 * 
 * Constraints:
 * 
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * All the pairs [ai, bi] are distinct.
 * 
*/



import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = makeGraph(numCourses, prerequisites);
        int[] indegrees = computeIndegrees(graph);
        return getCourseOrdering(indegrees, graph);
    }
    
    public List<List<Integer>> makeGraph(int numCourses, int[][] prerequisites){
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0; i<numCourses; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int[] dependency:prerequisites){
            graph.get(dependency[1]).add(dependency[0]);
        }
        
        return graph;
    }
    
    public int[] computeIndegrees(List<List<Integer>> graph){
        int[] indegrees = new int[graph.size()];
        
        for(List<Integer> neighbors:graph){
            for(int neighbor:neighbors){
                indegrees[neighbor]++;
            }
        }
        
        return indegrees;
    }
    
    public int[] getCourseOrdering(int[] indegrees, List<List<Integer>> graph){
        Queue<Integer> zeroIndegreeQueue = new LinkedList<>();
        int[] ordering = new int[graph.size()];
        
        for(int i=0; i<indegrees.length; i++){
            if(indegrees[i] == 0){
                zeroIndegreeQueue.add(i);
            }
        }
        
        for(int i=0; i<graph.size(); i++){
            if(zeroIndegreeQueue.isEmpty()) return new int[0]; //contain cycle, so ordering not possible
            
            int zeroIndegreeCourse = zeroIndegreeQueue.remove();
            ordering[i] = zeroIndegreeCourse;
            for(int neighbor:graph.get(zeroIndegreeCourse)){
                if(--indegrees[neighbor] == 0) 
                    zeroIndegreeQueue.add(neighbor);
            }
        }
        
        return ordering;
    }
}
