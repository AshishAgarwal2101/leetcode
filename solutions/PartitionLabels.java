/**
 *
 * 763. Partition Labels:::::::::
 * 
 * You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part.
 * 
 * Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.
 * 
 * Return a list of integers representing the size of these parts.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: s = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
 * 
 * Example 2:
 * 
 * Input: s = "eccbbbbdec"
 * Output: [10]
 *  
 * 
 * Constraints:
 * 
 * 1 <= s.length <= 500
 * s consists of lowercase English letters.
 * 
*/



import java.util.*;

class Solution {
    public List<Integer> partitionLabels(String s) {
        int[] furthestDistance = new int[26];
        Arrays.fill(furthestDistance, -1);
        
        char[] sa = s.toCharArray();
        for(int i=0; i<sa.length; i++){
            furthestDistance[sa[i] - 'a'] = i;
        }
        
        List<Integer> partitions = new ArrayList<>();
        int start=0;
        for(int i=0; i<sa.length; i++){
            int furthestDist = furthestDistance[sa[i]-'a'];
            while(i<furthestDist){
                furthestDist = Math.max(furthestDistance[sa[i]-'a'], furthestDist);
                i++;
            }
            //completed furthest distance of letters together, completed 1 partition
            partitions.add(i-start+1);
            start = i+1;
        }
        
        return partitions;
    }
}
