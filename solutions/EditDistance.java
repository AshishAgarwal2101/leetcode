/**
 *
 * 72. Edit Distance:::::::::;
 * 
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 * 
 * You have the following three operations permitted on a word:
 * 
 * Insert a character
 * Delete a character
 * Replace a character
 *  
 * 
 * Example 1:
 * 
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation: 
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * 
 * Example 2:
 * 
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation: 
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 *  
 * 
 * Constraints:
 * 
 * 0 <= word1.length, word2.length <= 500
 * word1 and word2 consist of lowercase English letters.
 * 
*/



import java.util.*;

class Solution {
    /* ----------- Solution 1: Recursion---------------
    public int minDistance(String word1, String word2) {
        return minDistance(word1, word2, word1.length(), word2.length());
    }
    
    public int minDistance(String word1, String word2, int lenWord1, int lenWord2){
        if(lenWord1 == 0){ //only option is to insert all chars
            return lenWord2;
        }
        
        if(lenWord2 == 0){ //only option is to remove all chars
            return lenWord1;
        }
        
        if(word1.charAt(lenWord1-1) == word2.charAt(lenWord2-1)){ //same chars, ignore
            return minDistance(word1, word2, lenWord1-1, lenWord2-1);
        }
        
        return 1 + getMin(
            minDistance(word1, word2, lenWord1, lenWord2-1), //insert char at end
            minDistance(word1, word2, lenWord1-1, lenWord2), //remove char from end
            minDistance(word1, word2, lenWord1-1, lenWord2-1) //replace last char
        );
    }
    
    public int getMin(int a, int b, int c){
        return Math.min(a, Math.min(b, c));
    }
    */
    
    /* -------------Solution 2: Dynamic Programming ---------------
    */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=0; i<=m; i++){
            dp[i][0] = i; //len of word2=0, so remove 'i' chars
        }
        for(int i=0; i<=n; i++){
            dp[0][i] = i; //len of word1=1, so add 'i' chars
        }
        
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                //i = curr len of word1
                //j = curr len of word2
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }
                else {
                    dp[i][j] = 1 + getMin(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]);
                }
            }
        }
        
        return dp[m][n];
    }
    
    public int getMin(int a, int b, int c){
        return Math.min(a, Math.min(b, c));
    }
}
