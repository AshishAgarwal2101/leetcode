/**
 *
 * 1791. Find Center of Star Graph::::::::::::::::::
 * 
 * There is an undirected star graph consisting of n nodes labeled from 1 to n. A star graph is a graph where there is one center node and exactly n - 1 edges that connect the center node with every other node.
 * 
 * You are given a 2D integer array edges where each edges[i] = [ui, vi] indicates that there is an edge between the nodes ui and vi. Return the center of the given star graph.
 *  
 * 
 * Example 1:
 * 
 * 
 * Input: edges = [[1,2],[2,3],[4,2]]
 * Output: 2
 * Explanation: As shown in the figure above, node 2 is connected to every other node, so 2 is the center.
 * 
 * Example 2:
 * 
 * Input: edges = [[1,2],[5,1],[1,3],[1,4]]
 * Output: 1
 *  
 * 
 * Constraints:
 * 
 * 3 <= n <= 105
 * edges.length == n - 1
 * edges[i].length == 2
 * 1 <= ui, vi <= n
 * ui != vi
 * The given edges represent a valid star graph.
 * 
*/



import java.util.*;

class Solution {
    /* ----------------Solution 1: Naive approach-------------
    public int findCenter(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegrees = new HashMap<>();
        int maxNode = 0;
        for(int[] edge:edges){
            int u = edge[0];
            int v = edge[1];
            int fromIndegree = indegrees.getOrDefault(u, 0);
            int toIndegree = indegrees.getOrDefault(v, 0);
            indegrees.put(u, fromIndegree+1);
            indegrees.put(v, toIndegree+1);\
        }
        
        int numNodes = indegrees.size();
        for(int node:indegrees.keySet()){
            int indegree = indegrees.get(node);\
            if(indegree == numNodes-1)
                return node;
        }
        
        return -1;
    }
    */
    
    /* ------------Solution 2: Ans can be calculated from first 2 edges*/
    public int findCenter(int[][] edges) {
        //The repeated node in the edges array is the center
        if(edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1])
            return edges[0][0];
        
        return edges[0][1];
    }
}
