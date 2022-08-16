/**
 *
 * 14. Longest Common Prefix:::::::::::::::::::
 * 
 * Write a function to find the longest common prefix string amongst an array of strings.
 * 
 * If there is no common prefix, return an empty string "".
 * 
 * Example 1:
 * 
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 * 
 * Example 2:
 * 
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 *  
 * 
 * Constraints:
 * 
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] consists of only lowercase English letters.
 * 
*/



import java.util.*;

class Solution {
    public String longestCommonPrefix(String[] strs) {
        int i=0;
        StringBuilder sb = new StringBuilder();
        char initialChar = 'a';
        for(int j=0; j<strs.length; j++){
            if(strs[j].length() == i)
                break;
            if(j == 0)
                initialChar = strs[j].charAt(i);
            else if(strs[j].charAt(i) != initialChar)
                break;
            
            if(j == strs.length-1){
                sb.append(initialChar);
                i++;
                j=-1;
            }
        }
        
        return sb.toString();
    }
}
