/**
 *
 * 784. Letter Case Permutation::::::::
 * 
 * Given a string s, you can transform every letter individually to be lowercase or uppercase to create another string.
 * 
 * Return a list of all possible strings we could create. Return the output in any order.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: s = "a1b2"
 * Output: ["a1b2","a1B2","A1b2","A1B2"]
 * 
 * Example 2:
 * 
 * Input: s = "3z4"
 * Output: ["3z4","3Z4"]
 *  
 * 
 * Constraints:
 * 
 * 1 <= s.length <= 12
 * s consists of lowercase English letters, uppercase English letters, and digits.
 * 
*/



import java.util.*;

class Solution {
    List<String> res = new ArrayList<>();
    public List<String> letterCasePermutation(String s) {
        res.add(s);
        letterCasePerm(s.toCharArray(), 0);
        return res;
    }
    
    public void letterCasePerm(char[] charAr, int index){
        if(index >= charAr.length){
            return;
        }
        
        char ch = charAr[index];
        if(!Character.isLetter(ch)){
            letterCasePerm(charAr, index+1);
            return;
        }
        
        char upperCh = Character.toUpperCase(ch);
        char lowerCh = Character.toLowerCase(ch);
        
        if(ch != upperCh) charAr[index] = upperCh;
        else charAr[index] = lowerCh;
        
        res.add(new String(charAr));
        letterCasePerm(charAr, index+1);
        charAr[index] = ch;
        letterCasePerm(charAr, index+1);
    }
}
