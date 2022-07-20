/**
 *
 * 797. All Paths From Source to Target:::::::::::::::
 * 
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.
 * 
 * The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: graph = [[1,2],[3],[3],[]]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 * 
 * Example 2:
 * 
 * 
 * Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
 * Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 *  
 * 
 * Constraints:
 * 
 * n == graph.length
 * 2 <= n <= 15
 * 0 <= graph[i][j] < n
 * graph[i][j] != i (i.e., there will be no self-loops).
 * All the elements of graph[i] are unique.
 * The input graph is guaranteed to be a DAG.
 * 
*/



import java.util.*;

class Solution {
    /* ----------------Solution 1: Using adjacency matrix --------------
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        boolean[][] graphMatrix = new boolean[graph.length][graph.length];
        
        for(int i=0; i<graph.length; i++){
            int source = i;
            for(int j=0; j<graph[i].length; j++){
                int destination = graph[i][j];
                graphMatrix[source][destination] = true; //directed edge exists
            }
        }
        
        boolean[] visited = new boolean[graphMatrix.length];
        visited[0] = true;
        List<Integer> pathNodes = new ArrayList<>();
        pathNodes.add(0);
        for(int i=1; i<graphMatrix.length; i++){
            if(graphMatrix[0][i]){
                findPaths(graphMatrix, visited, i, pathNodes, graph.length-1);
            }
        }
        
        return res;
    }
    
    public void findPaths(boolean[][] graphMatrix, boolean[] visited, int currNode, List<Integer> pathNodes, int destination){
        if(currNode == destination){
            List<Integer> newPath = new ArrayList<>(pathNodes);
            newPath.add(destination);
            res.add(newPath);
            return;
        }
        
        visited[currNode] = true;
        pathNodes.add(currNode);
        for(int i=0; i<graphMatrix.length; i++){
            if(i != currNode && !visited[i] && graphMatrix[currNode][i]){
                findPaths(graphMatrix, visited, i, pathNodes, destination);
            }
        }
        pathNodes.remove(pathNodes.size()-1);
        visited[currNode] = false;
    }
    */
    
    /* ----------------------Solution 2: Using the given graph itself--------------*/
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        boolean[] visited = new boolean[graph.length];
        findPaths(graph, visited, 0, new ArrayList<>());
        
        return res;
    }
    
    public void findPaths(int[][] graph, boolean[] visited, int currNode, List<Integer> pathNodes){
        if(currNode == graph.length-1){
            List<Integer> newPath = new ArrayList<>(pathNodes);
            newPath.add(graph.length-1);
            res.add(newPath);
            return;
        }
        
        visited[currNode] = true;
        pathNodes.add(currNode);
        for(int neighbor:graph[currNode]){
            if(!visited[neighbor]){
                findPaths(graph, visited, neighbor, pathNodes);
            }
        }
        pathNodes.remove(pathNodes.size()-1);
        visited[currNode] = false;
    }
}
