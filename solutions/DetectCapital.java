/**
 *
 * 520. Detect Capital:::::::::::
 * 
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 * 
 * All letters in this word are capitals, like "USA".
 * All letters in this word are not capitals, like "leetcode".
 * Only the first letter in this word is capital, like "Google".
 * Given a string word, return true if the usage of capitals in it is right.
 * 
 *  
 * Example 1:
 * 
 * Input: word = "USA"
 * Output: true
 * 
 * Example 2:
 * 
 * Input: word = "FlaG"
 * Output: false
 *  
 * 
 * Constraints:
 * 
 * 1 <= word.length <= 100
 * word consists of lowercase and uppercase English letters.
 * 
*/



import java.util.*;

class Solution {
    public boolean detectCapitalUse(String word) {
        if(word.length() == 0 || word.length() == 1) return true;
        char[] wordChars = word.toCharArray();
        boolean isFirstLetterCap = Character.isUpperCase(wordChars[0]);
        boolean isLastCharacterCap = Character.isUpperCase(wordChars[1]);
        if(!isFirstLetterCap && isLastCharacterCap) return false;
        
        for(int i=1; i<wordChars.length; i++){
            boolean isCurrLetterCap = Character.isUpperCase(wordChars[i]);
            if(isCurrLetterCap != isLastCharacterCap) return false;

            isLastCharacterCap = isCurrLetterCap;
        }

        return true;
    }
}
