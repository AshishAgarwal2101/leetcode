/**
 *
 * 3. Longest Substring Without Repeating Characters:::::
 * 
 * Given a string s, find the length of the longest substring without repeating characters.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * 
 * Example 2:
 * 
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * 
 * Example 3:
 * 
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *  
 * 
 * Constraints:
 * 
 * 0 <= s.length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces.
 * 
*/



import java.util.*;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() == 0){
            return 0;
        }
        Set<Character> visited = new HashSet<>();
        int startI = 0;
        int vNumChars = 1;
        int maxVNumChars = 1;
        visited.add(s.charAt(0));
        
        for(int i=1; i<s.length(); i++){
            char ch = s.charAt(i);
            if(visited.contains(ch)){
                while(s.charAt(startI) != ch){
                    visited.remove(s.charAt(startI));
                    startI++;
                    vNumChars--;
                }
                startI++;
            }
            else {
                visited.add(ch);
                vNumChars++;
            }
            
            //System.out.println("-------i="+i+"----vnumChars="+vNumChars+"----maxVNumChars="+maxVNumChars);
            
            if(vNumChars > maxVNumChars){
                maxVNumChars = vNumChars;
            }
        }
        
        return maxVNumChars;
    }
}
