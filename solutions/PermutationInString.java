/**
 *
 * 567. Permutation in String::::::
 * 
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 * 
 * In other words, return true if one of s1's permutations is the substring of s2.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 * 
 * Example 2:
 * 
 * Input: s1 = "ab", s2 = "eidboaoo"
 * Output: false
 *  
 * 
 * Constraints:
 * 
 * 1 <= s1.length, s2.length <= 104
 * s1 and s2 consist of lowercase English letters.
 * 
*/



import java.util.*;

class Solution {
    //Solution 1 ------
    //Time complexity: O(n), where n=length of s2
    
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> s1Map = new HashMap<>();
        Map<Character, Integer> s2Map = new HashMap<>();
        Map<Character, Integer> pendingCountMap = new HashMap<>();
        
        for(int i=0; i<s1.length(); i++){
            char ch = s1.charAt(i);
            s1Map.computeIfAbsent(ch, k -> 0);
            s1Map.put(ch, s1Map.get(ch)+1);
        }
        
        //initially, all characters of s2 are pending to be found
        pendingCountMap.putAll(s1Map);
        
        int start = -1;
        for(int i=0; i<s2.length(); i++){
            char ch = s2.charAt(i);
            Integer pendingCount = pendingCountMap.get(ch);
            if(pendingCount != null){
                pendingCount = pendingCount-1;
                pendingCountMap.put(ch, pendingCount);
                if(start == -1){
                    start = i;
                }
                
                if(pendingCount < 0){
                    char ch1 = s2.charAt(start);
                    while(ch1 != ch){
                        pendingCountMap.put(ch1, pendingCountMap.get(ch1)+1);
                        start++;
                        ch1 = s2.charAt(start);
                    }
                    start++;
                    pendingCountMap.put(ch1, pendingCountMap.get(ch1)+1);
                }
                
                if(i - start + 1 == s1.length()){
                    return true;
                }
            }
            else{
                if(start != -1){
                    char ch1 = s2.charAt(start);
                    while(start < i){
                        pendingCountMap.put(ch1, pendingCountMap.get(ch1)+1);
                        start++;
                        ch1 = s2.charAt(start);
                    }
                    start = -1;
                }
            }
        }
        
        return false;
    }
    
    
    /* ---------Solution 2--------------
    Time complexity: O((n2-n1)*26), where n1=len of s1, n2=len of s2
    public boolean checkInclusion(String s1, String s2) {
        char[] count = new char[26]; //count will have a window of len1 characters
        int len1 = s1.length();
        int len2 = s2.length();
        
        if(len2 < len1){
            return false;
        }
        
        for(int i=0; i<len1; i++){
            char ch1 = s1.charAt(i);
            char ch2 = s2.charAt(i);
            
            count[ch1 - 'a']++;
            count[ch2 - 'a']--;
        }
        
        if(isAllZero(count)){
            return true;
        }
        
        for(int i=len1; i<len2; i++){
            char ch2 = s2.charAt(i);
            char prevCh = s2.charAt(i-len1);
            count[ch2 - 'a']--;
            count[prevCh - 'a']++;
            if(isAllZero(count)){
                return true;
            }
        }
        
        return false;
    }
    
    public boolean isAllZero(char[] count){
        for(int i=0; i<26; i++){
            if(count[i] != 0){
                return false;
            }
        }
        
        return true;
    }
    */
}
