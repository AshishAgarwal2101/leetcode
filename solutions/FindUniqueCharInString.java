/**
 *
 * 387. First Unique Character in a String:::::::
 * 
 * Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: s = "leetcode"
 * Output: 0
 * 
 * Example 2:
 * 
 * Input: s = "loveleetcode"
 * Output: 2
 * 
 * Example 3:
 * 
 * Input: s = "aabb"
 * Output: -1
 *  
 * 
 * Constraints:
 * 
 * 1 <= s.length <= 105
 * s consists of only lowercase English letters.
 * 
*/



import java.util.*;

class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> characterCount = new HashMap<>();
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            Integer count = characterCount.get(ch);
            if(count == null){
                count = 0;
            }
            characterCount.put(ch, count+1);
        }
        
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            Integer count = characterCount.get(ch);
            if(count == 1){
                return i;
            }
        }
        
        return -1;
    }
}
