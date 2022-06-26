/**
 *
 * 647. Palindromic Substrings::::::::::
 * 
 * Given a string s, return the number of palindromic substrings in it.
 * 
 * A string is a palindrome when it reads the same backward as forward.
 * 
 * A substring is a contiguous sequence of characters within the string.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 * 
 * Example 2:
 * 
 * Input: s = "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 *  
 * 
 * Constraints:
 * 
 * 1 <= s.length <= 1000
 * s consists of lowercase English letters.
 * 
*/



import java.util.*;

class Solution {
    /* -----Solution 1: Similar to brute force, little optimization------------
    public int countSubstrings(String s) {
        char[] sa = s.toCharArray();
        boolean[][] dp = new boolean[sa.length][sa.length];
        int res = 0;
        
        for(int i=sa.length-1; i>=0; i--){
            for(int j=i; j<sa.length; j++){
                if(sa[i] == sa[j]){
                    if((j-i)<=2 || dp[i+1][j-1]){
                        dp[i][j] = true;
                        res++;
                    }
                }
            }
        }
        
        return res;
    }
    */
    
    /*------------Solution 2: Palindrome around center----------
    */
    public int countSubstrings(String s) {
        int res = 0;
        char[] sa = s.toCharArray();
        for(int i=0; i<sa.length; i++){ //i = center
            //palins with odd no. of characters
            res += palinsAroundCenter(sa, i, i);
            
            //palins with even no. of characters
            res += palinsAroundCenter(sa, i, i+1);
        }
        
        return res;
    }
    
    public int palinsAroundCenter(char[] sa, int low, int high){
        int res = 0;
        while(low>=0 && high<sa.length && sa[low]==sa[high]){
            res++;
            low--;
            high++;
        }
        
        return res;
    }
}
