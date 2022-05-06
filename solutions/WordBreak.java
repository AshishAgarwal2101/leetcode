/**
 *
 * 139. Word Break::::::::::
 * 
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * 
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 * 
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 * 
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 *  
 * 
 * Constraints:
 * 
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 * 
*/



import java.util.*;

class Solution {
    /*
    -------------Old solution 1------------------
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        
        return wordBreak(s, 0, wordSet);
    }
    
    public boolean wordBreak(String s, int index, Set<String> wordSet){
        if(s.length() == 0){
            return true;
        }
        if(index == s.length()){
            return false;
        }
        String currString = s.substring(0, index+1);
        boolean isAvailableInDict = wordSet.contains(currString);
        if(isAvailableInDict){
            boolean withIncludeCurrString = wordBreak(s.substring(index+1, s.length()), 0, wordSet);
            if(withIncludeCurrString){
                return true;
            }
        }
        
        return wordBreak(s, index+1, wordSet);
    }
    */
    
    
    /*
    --------------Old solution 2--------------
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int len = s.length();
        int maxLenInDict = 0;
        for(String word:wordDict){
            int currLen = word.length();
            if(currLen > maxLenInDict){
                maxLenInDict = currLen;
            }
        }
        boolean[] dp = new boolean[len+1];
        dp[0] = true;
        
        for(int i=0; i<len; i++){
            //i = curr length
           if(dp[i] == true){ //till curr length, result is true
               int maxLenToGo = Math.min(i+maxLenInDict, len);
               for(int j=i+1; j<=maxLenToGo; j++){
                   //j = new curr length
                   String str = s.substring(i, j);
                   if(wordSet.contains(str)){
                       dp[j] = true;
                   }
               }
           }
        }
        
        return dp[len];
    }
    */
    
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int len = s.length();
        int maxLenInDict = 0;
        for(String word:wordDict){
            int currLen = word.length();
            if(currLen > maxLenInDict){
                maxLenInDict = currLen;
            }
        }
        
        //below has list of indices till which a valid result was found
        ArrayList<Integer> valid = new ArrayList<>();
        valid.add(-1);
        
        for(int i=0; i<len; i++){
            for(int j=valid.size()-1; j>=0; j--){
                int validIndex = valid.get(j);
                if(i-validIndex > maxLenInDict){
                    break; //length of currStr exceeds max length in dict
                }
                String currStr = s.substring(validIndex+1, i+1);
                if(wordSet.contains(currStr)){
                    valid.add(i);
                    break; //valid already found, so no more checks required.
                }
            }
        }
        
        int lastValid = valid.get(valid.size()-1);
        return (lastValid == len-1);
    }
}
