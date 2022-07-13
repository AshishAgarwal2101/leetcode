/**
 *
 * 76. Minimum Window Substring:::::::::::::::::::::
 * 
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
 * 
 * The testcases will be generated such that the answer is unique.
 * 
 * A substring is a contiguous sequence of characters within the string.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * 
 * Example 2:
 * 
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * 
 * Example 3:
 * 
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 *  
 * 
 * Constraints:
 * 
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s and t consist of uppercase and lowercase English letters.
 *  
 * 
 * Follow up: Could you find an algorithm that runs in O(m + n) time?
 * 
*/



import java.util.*;

class Solution {
    public String minWindow(String s, String t) {
        if(s.length() < t.length()) return "";
        
        Map<Character, Integer> tMap = new HashMap<>();
        for(int i=0; i<t.length(); i++){
            char ch = t.charAt(i);
            tMap.put(ch, tMap.getOrDefault(ch, 0)+1);
        }
        
        int minLen = Integer.MAX_VALUE;
        String minLenStr = "";
        Map<Character, Integer> currMap = new HashMap<>();
        initMap(currMap, tMap);
        int start = 0;
        int count = 0;
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(currMap.containsKey(ch)){
                int newChCount = currMap.get(ch)+1;
                currMap.put(ch, newChCount);
                if(newChCount <= tMap.get(ch)){
                    count++;
                }
            }
            
            char chStart = s.charAt(start);
            while(start<i && (!currMap.containsKey(chStart) || currMap.get(chStart)>tMap.get(chStart))){
                if(currMap.containsKey(chStart) && currMap.get(chStart)>tMap.get(chStart)){
                    currMap.put(chStart, currMap.get(chStart)-1);
                }
                //System.out.println("chStart="+chStart+"---count="+currMap.get(chStart));
                start++;
                chStart = s.charAt(start);
            }
            
            if(count == t.length()){
                //System.out.println("start="+start+"--i="+i);
                int newLen = (i-start+1);
                if(newLen < minLen){
                    minLen = newLen;
                    minLenStr = s.substring(start, i+1);
                }
            }
        }
        
        return minLenStr;
    }
    
    public void initMap(Map<Character, Integer> map1, Map<Character, Integer> map2){
        for(char ch:map2.keySet()){
            map1.put(ch, 0);
        }
    }
}
