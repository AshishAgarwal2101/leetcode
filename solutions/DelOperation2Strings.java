/**
 *
 * 583. Delete Operation for Two Strings::::::::::::::
 * 
 * Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.
 * 
 * In one step, you can delete exactly one character in either string.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: word1 = "sea", word2 = "eat"
 * Output: 2
 * Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
 * Example 2:
 * 
 * Input: word1 = "leetcode", word2 = "etco"
 * Output: 4
 *  
 * 
 * Constraints:
 * 
 * 1 <= word1.length, word2.length <= 500
 * word1 and word2 consist of only lowercase English letters.
 * 
*/



import java.util.*;

class Solution {
    /*
    public int minDistance(String word1, String word2) {
        return minDistanceUtil(word1.toCharArray(), word2.toCharArray(), 0, 0);
    }
    
    public int minDistanceUtil(char[] w1Chars, char[] w2Chars, int i, int j){
        if(i>=w1Chars.length && j>=w2Chars.length)
            return 0;
        if(i>=w1Chars.length)
            return (w2Chars.length-j);
        if(j>=w2Chars.length)
            return (w1Chars.length-i);
        
        if(w1Chars[i] == w2Chars[j]){
            return minDistanceUtil(w1Chars, w2Chars, i+1, j+1);
        }
        int res = minDistanceUtil(w1Chars, w2Chars, i+1, j)+1;
        res = Math.min(res, minDistanceUtil(w1Chars, w2Chars, i, j+1)+1);
        
        return res;
    }
    */
    
    /*------------Dynamic Programming------------*/
    public int minDistance(String word1, String word2) {
        char[] w1Chars = word1.toCharArray();
        char[] w2Chars = word2.toCharArray();
        int[][] dp = new int[w1Chars.length+1][w2Chars.length+1];
        for(int i=0; i<=w1Chars.length; i++){
            dp[i][0] = i;
        }
        for(int j=0; j<=w2Chars.length; j++){
            dp[0][j] = j;
        }
        for(int i=1; i<=w1Chars.length; i++){
            for(int j=1; j<=w2Chars.length; j++){
                if(w1Chars[i-1] == w2Chars[j-1]){
                    dp[i][j] = dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.min(dp[i-1][j]+1, dp[i][j-1]+1);
                }
            }
        }
        
        return dp[w1Chars.length][w2Chars.length];
    }
}
