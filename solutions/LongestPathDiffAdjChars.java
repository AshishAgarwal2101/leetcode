/**
 *
 * 2246. Longest Path With Different Adjacent Characters::::::::::::
 * 
 * You are given a tree (i.e. a connected, undirected graph that has no cycles) rooted at node 0 consisting of n nodes numbered from 0 to n - 1. The tree is represented by a 0-indexed array parent of size n, where parent[i] is the parent of node i. Since node 0 is the root, parent[0] == -1.
 * 
 * You are also given a string s of length n, where s[i] is the character assigned to node i.
 * 
 * Return the length of the longest path in the tree such that no pair of adjacent nodes on the path have the same character assigned to them.
 * 
 * Example 1:
 * 
 * 
 * Input: parent = [-1,0,0,1,1,2], s = "abacbe"
 * Output: 3
 * Explanation: The longest path where each two adjacent nodes have different characters in the tree is the path: 0 -> 1 -> 3. The length of this path is 3, so 3 is returned.
 * It can be proven that there is no longer path that satisfies the conditions. 
 * 
 * Example 2:
 * 
 * 
 * Input: parent = [-1,0,0,0], s = "aabc"
 * Output: 3
 * Explanation: The longest path where each two adjacent nodes have different characters is the path: 2 -> 0 -> 3. The length of this path is 3, so 3 is returned.
 *  
 * 
 * Constraints:
 * 
 * n == parent.length == s.length
 * 1 <= n <= 105
 * 0 <= parent[i] <= n - 1 for all i >= 1
 * parent[0] == -1
 * parent represents a valid tree.
 * s consists of only lowercase English letters.
 * 
*/



import java.util.*;

class Solution {
    int globalMax = 0;

    class Node {
        int val;
        char ch;
        List<Node> next;
        List<Node> children;
        public Node(int val, char ch){
            this.val = val;
            this.ch = ch;
            this.next = new ArrayList<>();
            this.children = new ArrayList<>();
        }
    }
    public int longestPath(int[] parent, String s) {
        Node[] nodes = initNodes(parent, s);
        findLongestPath(nodes[0]);
        return globalMax;
    }

    public int findLongestPath(Node node){
        if(node == null) return 0;

        int max = 0;
        int secondMax = 0;
        for(Node nextNode:node.children){
            int childLongest = findLongestPath(nextNode);
            if(nextNode.ch == node.ch) continue;

            if(childLongest > max){
                secondMax = max;
                max = childLongest;
            }
            else if(childLongest > secondMax){
                secondMax = childLongest;
            }
        }

        globalMax = Math.max(globalMax, max+secondMax+1);
        return 1 + max;
    }

    public Node[] initNodes(int[] parent, String s){
        Node[] nodes = new Node[parent.length];
        for(int i=0; i<parent.length; i++){
            char ch = s.charAt(i);
            nodes[i] = new Node(i, ch);
        }
        for(int i=1; i<parent.length; i++){
            nodes[parent[i]].next.add(nodes[i]);
            nodes[i].next.add(nodes[parent[i]]);

            nodes[parent[i]].children.add(nodes[i]);
        }

        return nodes;
    }
}
