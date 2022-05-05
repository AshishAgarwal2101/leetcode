/**
 *
 * 131. Palindrome Partitioning::::
 * 
 * Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
 * 
 * A palindrome string is a string that reads the same backward as forward.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * Example 2:
 * 
 * Input: s = "a"
 * Output: [["a"]]
 *  
 * 
 * Constraints:
 * 
 * 1 <= s.length <= 16
 * s contains only lowercase English letters.
 * 
*/



import java.util.*;


class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> partitionsAtAnyGivenTime = new ArrayList<>();
        partition(s, partitionsAtAnyGivenTime, result);
        
        return result;
    }
    
    public void partition(String remainingStr, List<String> currPartitions, List<List<String>> result){
        if(remainingStr.length() == 0){
            result.add(new ArrayList<>(currPartitions));
            return;
        }
        
        for(int i=0; i<remainingStr.length(); i++){
            int end = i;
            String currSubStr = remainingStr.substring(0, end+1);
            if(isPalindrome(currSubStr)){
                currPartitions.add(currSubStr); 
                partition(remainingStr.substring(end+1, remainingStr.length()), currPartitions, result);
                
                currPartitions.remove(currPartitions.size() - 1); //for partitions without the currSubStr
            }
        }
    }
    
    public boolean isPalindrome(String s){
        int left = 0;
        int right = s.length() - 1;
        while(left <= right){
            if(s.charAt(left++) != s.charAt(right--)){
                return false;
            }
        }
        
        return true;
    }
}
