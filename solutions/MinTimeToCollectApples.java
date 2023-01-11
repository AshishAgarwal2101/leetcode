/**
 *
 * 1443. Minimum Time to Collect All Apples in a Tree::::::::::::::::::::::
 * 
 * Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices. You spend 1 second to walk over one edge of the tree. Return the minimum time in seconds you have to spend to collect all apples in the tree, starting at vertex 0 and coming back to this vertex.
 * 
 * The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi. Additionally, there is a boolean array hasApple, where hasApple[i] = true means that vertex i has an apple; otherwise, it does not have any apple.
 * 
 * Example 1:
 * 
 * 
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
 * Output: 8 
 * Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.  
 * 
 * Example 2:
 * 
 * 
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
 * Output: 6
 * Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows. 
 *  
 * Example 3:
 * 
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
 * Output: 0
 *  
 * 
 * Constraints:
 * 
 * 1 <= n <= 105
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai < bi <= n - 1
 * fromi < toi
 * hasApple.length == n
 * 
*/



import java.util.*;

class Solution {
    class Node {
        int num;
        boolean hasApple;
        List<Node> next;
        public Node(int num, boolean hasApple){
            this.num = num;
            this.hasApple = hasApple;
            this.next = new ArrayList<>();
        }
    }
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        List<Node> nodes = initNodes(n, hasApple);
        assignNodeConnections(nodes, edges);
        boolean[] visited = new boolean[n];
        return 2*getDistToApples(nodes.get(0), visited);
    }

    public int getDistToApples(Node node, boolean[] visited){
        if(node == null || visited[node.num]) return 0;

        visited[node.num] = true;
        int totalDist = 0;
        for(int i=0; i<node.next.size(); i++){
            int dist = getDistToApples(node.next.get(i), visited);
            totalDist += dist;
        }

        if(node.num == 0) return totalDist;

        int option1 = (totalDist == 0) ? 0 : (totalDist+1);
        int option2 = node.hasApple ? 1 : 0;
        return Math.max(option1, option2);
    }

    public List<Node> initNodes(int n, List<Boolean> hasApple){
        List<Node> nodes = new ArrayList<>();
        for(int i=0; i<n; i++){
            boolean nodeHasApple = hasApple.get(i);
            Node node = new Node(i, nodeHasApple);
            nodes.add(node);
        }

        return nodes;
    }

    public void assignNodeConnections(List<Node> nodes, int[][] edges){
        for(int i=0; i<edges.length; i++){
            int parent = edges[i][0];
            int child = edges[i][1];

            Node parentNode = nodes.get(parent);
            Node childNode = nodes.get(child);
            parentNode.next.add(childNode);
            childNode.next.add(parentNode);
        }
    }
}
