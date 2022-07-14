/**
 *
 * 844. Backspace String Compare::::::::::::::
 * 
 * Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.
 * 
 * Note that after backspacing an empty text, the text will continue empty.
 * 
 * 
 * Example 1:
 * 
 * Input: s = "ab#c", t = "ad#c"
 * Output: true
 * Explanation: Both s and t become "ac".
 * 
 * Example 2:
 * 
 * Input: s = "ab##", t = "c#d#"
 * Output: true
 * Explanation: Both s and t become "".
 * 
 * Example 3:
 * 
 * Input: s = "a#c", t = "b"
 * Output: false
 * Explanation: s becomes "c" while t becomes "b".
 *  
 * 
 * Constraints:
 * 
 * 1 <= s.length, t.length <= 200
 * s and t only contain lowercase letters and '#' characters.
 *  
 * 
 * Follow up: Can you solve it in O(n) time and O(1) space?
 * 
*/



import java.util.*;

class Solution {
    /* ----------Solution 1: Using stack and StringBuilder: O(n) extra space------------
    public boolean backspaceCompare(String s, String t) {
        Stack<Character> stackS = new Stack<>();
        Stack<Character> stackT = new Stack<>();
        extractToStack(stackS, s);
        extractToStack(stackT, t);
        
        String strS = getStringFromStack(stackS);
        String strT = getStringFromStack(stackT);
        
        if(strS.equals(strT)) return true;
        return false;
    }
    
    public void extractToStack(Stack<Character> stack, String s){
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(ch != '#'){
                stack.push(ch);
            }
            else if(!stack.isEmpty()){
                stack.pop();
            }
        }
    }
    
    public String getStringFromStack(Stack<Character> stack){
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            char ch = stack.pop();
            sb.append(ch);
        }
        
        return sb.reverse().toString();
    }
    */
    
    /*-------------Using 2: Using only StringBuilder: O(1) extra space--------------*/
    public boolean backspaceCompare(String s, String t) {
        String strS = extractToString(s);
        String strT = extractToString(t);
        
        if(strS.equals(strT)) return true;
        return false;
    }
    
    public String extractToString(String s){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(ch != '#'){
                sb.append(ch);
            }
            else if(sb.length() != 0){
                sb.deleteCharAt(sb.length()-1);
            }
        }
        
        return sb.toString();
    }
}
