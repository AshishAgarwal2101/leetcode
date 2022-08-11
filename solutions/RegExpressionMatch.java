/**
 *
 * 10. Regular Expression Matching::::::::::::
 * 
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
 * 
 * '.' Matches any single character.​​​​
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * 
 * 
 * Example 1:
 * 
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * 
 * Example 2:
 * 
 * Input: s = "aa", p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * 
 * Example 3:
 * 
 * Input: s = "ab", p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 *  
 * 
 * Constraints:
 * 
 * 1 <= s.length <= 20
 * 1 <= p.length <= 30
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '.', and '*'.
 * It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 * 
*/



import java.util.*;

class Solution {
    public boolean isMatch(String s, String p) {
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        
        boolean[][] dp = new boolean[sChars.length+1][pChars.length+1];
        dp[0][0] = true;
        for(int j=1; j<=pChars.length; j++){
            dp[0][j] = pChars[j-1] == '*' && dp[0][j-2];
        }
        
        for(int i=1; i<=sChars.length; i++){
            for(int j=1; j<=pChars.length; j++){
                if(pChars[j-1] == '*'){
                    dp[i][j] = dp[i][j-2]
                        || (dp[i-1][j] && match(sChars[i-1], pChars[j-2]));
                }
                else{
                    dp[i][j] = dp[i-1][j-1] && match(sChars[i-1], pChars[j-1]);
                }
            }
        }
        
        return dp[sChars.length][pChars.length];
    }
    
    public boolean match(char a, char b){
        return b == '.' || a == b;
    }
}
