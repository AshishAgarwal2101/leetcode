/**
 *
 * 997. Find the Town Judge::::::::::::::::
 * 
 * In a town, there are n people labeled from 1 to n. There is a rumor that one of these people is secretly the town judge.
 * 
 * If the town judge exists, then:
 * 
 * The town judge trusts nobody.
 * Everybody (except for the town judge) trusts the town judge.
 * There is exactly one person that satisfies properties 1 and 2.
 * You are given an array trust where trust[i] = [ai, bi] representing that the person labeled ai trusts the person labeled bi.
 * 
 * Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.
 * 
 * Example 1:
 * 
 * Input: n = 2, trust = [[1,2]]
 * Output: 2
 * 
 * Example 2:
 * 
 * Input: n = 3, trust = [[1,3],[2,3]]
 * Output: 3
 * 
 * Example 3:
 * 
 * Input: n = 3, trust = [[1,3],[2,3],[3,1]]
 * Output: -1
 *  
 * 
 * Constraints:
 * 
 * 1 <= n <= 1000
 * 0 <= trust.length <= 104
 * trust[i].length == 2
 * All the pairs of trust are unique.
 * ai != bi
 * 1 <= ai, bi <= n
 * 
*/



import java.util.*;

class Solution {
    public int findJudge(int n, int[][] trust) {
        List<Integer>[] graph = new ArrayList[n+1];
        int[] indegrees = new int[n+1];
        int[] outdegrees = new int[n+1];
        
        //initializing graph, indegrees and outdegrees
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] edge:trust){
            graph[edge[0]].add(edge[1]);
            indegrees[edge[1]]++;
            outdegrees[edge[0]]++;
        }
        
        //Judge would have: indegree=n-1 and outdegree=0
        for(int i=1; i<=n; i++){
            if(indegrees[i] == n-1 && outdegrees[i] == 0)
                return i;
        }
        
        return -1;
    }
}
