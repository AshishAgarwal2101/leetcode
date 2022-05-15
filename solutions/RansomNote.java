/**
 *
 * 383. Ransom Note::::::::::
 * 
 * Given two strings ransomNote and magazine, return true if ransomNote can be constructed from magazine and false otherwise.
 * 
 * Each letter in magazine can only be used once in ransomNote.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: ransomNote = "a", magazine = "b"
 * Output: false
 * 
 * Example 2:
 * 
 * Input: ransomNote = "aa", magazine = "ab"
 * Output: false
 * 
 * Example 3:
 * 
 * Input: ransomNote = "aa", magazine = "aab"
 * Output: true
 *  
 * 
 * Constraints:
 * 
 * 1 <= ransomNote.length, magazine.length <= 105
 * ransomNote and magazine consist of lowercase English letters.
 * 
*/



import java.util.*;

class Solution {
    /* 
    ----------------Old solution------------------------
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> magazineCharacterCount = new HashMap<>();
        Map<Character, Integer> noteCharacterCount = new HashMap<>();
        
        for(int i=0; i<magazine.length(); i++){
            char ch = magazine.charAt(i);
            Integer count = magazineCharacterCount.get(ch);
            if(count == null){
                count = 0;
            }
            magazineCharacterCount.put(ch, count+1);
        }
        
        for(int i=0; i<ransomNote.length(); i++){
            char ch = ransomNote.charAt(i);
            Integer count = noteCharacterCount.get(ch);
            Integer mCount = magazineCharacterCount.get(ch);
            if(count == null){
                count = 0;
            }
            if(mCount == null || count+1 > mCount){
                return false;
            }
            noteCharacterCount.put(ch, count+1);
        }
        
        return true;
    }
    */
    
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] charCount = new int[26];
        for(int i=0; i<magazine.length(); i++){
            char ch = magazine.charAt(i);
            charCount[ch-'a']++;
        }
        for(int i=0; i<ransomNote.length(); i++){
            char ch = ransomNote.charAt(i);
            if(charCount[ch - 'a'] == 0){
                return false;
            }
            charCount[ch-'a']--;
        }
        
        return true;
    }
}
