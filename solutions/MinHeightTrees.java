/**
 *
 * 310. Minimum Height Trees::::::::::::::
 * 
 * A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.
 * 
 * Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
 * 
 * Return a list of all MHTs' root labels. You can return the answer in any order.
 * 
 * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 * 
 * Example 1:
 * 
 * 
 * Input: n = 4, edges = [[1,0],[1,2],[1,3]]
 * Output: [1]
 * Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
 * 
 * Example 2:
 * 
 * 
 * Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 * Output: [3,4]
 *  
 * 
 * Constraints:
 * 
 * 1 <= n <= 2 * 104
 * edges.length == n - 1
 * 0 <= ai, bi < n
 * ai != bi
 * All the pairs (ai, bi) are distinct.
 * The given input is guaranteed to be a tree and there will be no repeated edges.
 * 
*/



import java.util.*;

class Solution {
    /*-----------------------Solution 1: BFS/DFS-----------------------
    private class Node{
        int val;
        List<Node> adj;
        public Node(int val){
            this.val = val;
            this.adj = new ArrayList<>();
        }
    }
    
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Node[] nodes = initializeNodes(n, edges);
        int[] heights = assignHeights(n, nodes);
        
        List<Integer> res = new ArrayList<>();
        int minHeight = Integer.MAX_VALUE;
        for(int i=0; i<n; i++){
            minHeight = Math.min(minHeight, heights[i]);
        }
        for(int i=0; i<n; i++){
            if(heights[i] == minHeight)
                res.add(i);
        }
        
        return res;
    }
    
    public Node[] initializeNodes(int n, int[][] edges){
        Node[] nodes = new Node[n];
        for(int i=0; i<n; i++){
            nodes[i] = new Node(i);
        }
        
        for(int[] edge:edges){
            nodes[edge[0]].adj.add(nodes[edge[1]]);
            nodes[edge[1]].adj.add(nodes[edge[0]]);
        }
        
        return nodes;
    }
    
    public int[] assignHeights(int n, Node[] nodes){
        boolean[] visited;
        int[] heights = new int[n];
        for(int i=0; i<n; i++){
            visited = new boolean[n];
            Set<Node> firstLevelNodeList = new HashSet<>();
            firstLevelNodeList.add(nodes[i]);
            heights[i] = getNodeHeight(firstLevelNodeList, visited);
        }
        
        return heights;
    }
    */
    
    /*--------------------BFS------------------
    public int getNodeHeight(Set<Node> nodes, boolean[] visited){
        if(nodes.isEmpty()) return -1;
        Set<Node> nextLevelNodes = new HashSet<>();
        boolean isAllVisited = true;
        for(Node node:nodes){
            if(!visited[node.val]){
                isAllVisited = false;
                for(Node neighbor:node.adj){
                    nextLevelNodes.add(neighbor);
                }
                visited[node.val] = true;
            }
        }
        if(isAllVisited) return -1;
        int neighborsMaxHeight = getNodeHeight(nextLevelNodes, visited);
        
        return neighborsMaxHeight+1;
    }
    */
    
    /* -----------------DFS---------------------------
    public int getNodeHeight(Node node, boolean[] visited){
        if(visited[node.val]){
            return -1;
        }
        
        //System.out.println("\nCalculating height for "+node.val);
        visited[node.val] = true;
        int maxNeighborHeight = -1;
        for(Node neighbor:node.adj){
            //System.out.println("Neighbor for "+node.val+"="+neighbor.val);
            int neighborHeight = getNodeHeight(neighbor, visited);
            //System.out.println("Height for neighbor "+neighbor.val+" for "+node.val+"="+neighborHeight);
            maxNeighborHeight = Math.max(maxNeighborHeight, neighborHeight);
        }
        //System.out.println("Max height Neighbor for "+node.val+"="+maxNeighborHeight);
        visited[node.val] = false;
        //heights[node.val] = maxNeighborHeight+1;
        return maxNeighborHeight+1;
    }
    */
        
    /* ------------------------Solution 2: Modification of Kahn's algorithm----------------*/
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n == 1 || edges.length == 0)
            return Arrays.asList(0);
        List<Integer>[] graph = initGraph(n, edges);
        int[] indegrees = computeIndegrees(graph);
        
        return findMinHeightNodes(graph, indegrees);
    }
    
    public List<Integer>[] initGraph(int n, int[][] edges){
        List<Integer>[] graph = new ArrayList[n];
        for(int i=0; i<graph.length; i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] edge:edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        
        return graph;
    }
    
    public int[] computeIndegrees(List<Integer>[] graph){
        int[] indegrees = new int[graph.length];
        for(List<Integer> neighbors:graph){
            for(int neighbor:neighbors){
                indegrees[neighbor]++;
            }
        }
        
        return indegrees;
    }
    
    public List<Integer> findMinHeightNodes(List<Integer>[] graph, int[] indegrees){
        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<indegrees.length; i++){
            if(indegrees[i] == 1) queue.add(i); //indegree of 1 indicates it's a leaf node
        }
        
        int scannedNodes = indegrees.length;
        while(scannedNodes > 2){
            int currentIndegreeQueueSize = queue.size();
            scannedNodes -= currentIndegreeQueueSize;
            for(int i=0; i<currentIndegreeQueueSize; i++){
                int node = queue.remove();
                for(int neighbor:graph[node]){
                    if(--indegrees[neighbor] == 1){
                        queue.add(neighbor);
                    }
                }
            }
        }
        
        while(!queue.isEmpty()){
            res.add(queue.remove());
        }
        return res;
    }
}
