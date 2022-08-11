/**
 *
 * 32. Longest Valid Parentheses:::::::::::::::::
 * 
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 * 
 * Example 1:
 * 
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * 
 * Example 2:
 * 
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 * 
 * Example 3:
 * 
 * Input: s = ""
 * Output: 0
 *  
 * 
 * Constraints:
 * 
 * 0 <= s.length <= 3 * 104
 * s[i] is '(', or ')'.
 * 
*/



import java.util.*;

class Solution {
    /*----Solution 1: Scanning L to R and then R to L----------------
    public int longestValidParentheses(String s) {
        char[] chars = s.toCharArray();
        int left = 0, right = 0, max = 0;
        
        //left to right scan
        for(int i=0; i<chars.length; i++){
            if(chars[i] == '(') left++;
            else right ++;
            
            //System.out.println("left="+left+"---right="+right);
            if(left == right && left*2>max) max = left*2;
            else if(right > left){
                left = 0;
                right = 0;
            }
        }
        
        //right to left scan
        left = right = 0;
        for(int i=chars.length-1; i>=0; i--){
            if(chars[i] == '(') left++;
            else right ++;
            
            //System.out.println("left="+left+"---right="+right);
            if(left == right && left*2>max) max = left*2;
            else if(left > right){
                left = 0;
                right = 0;
            }
        }
        
        return max;
    }
    */
    
    /*-------------Solution 2: Using stack and keeping track of valid strings--------------*/
    public int longestValidParentheses(String s) {
        char[] chars = s.toCharArray();
        Stack<Integer> stack = new Stack<>(); //this will contain index of opening brackets
        boolean[] valid = new boolean[chars.length];
        
        //assign whether valid entry available for indices
        for(int i=0; i<chars.length; i++){
            if(chars[i] == '(') stack.push(i);
            else {
                if(stack.isEmpty()) valid[i] = false;
                else {
                    valid[i] = true;
                    valid[stack.pop()] = true;
                }
            }
        }
        
        int len = 0, max = 0;
        for(int i=0; i<valid.length; i++){
            if(valid[i]) len++;
            else len = 0;
            max = Math.max(len, max);
        }
        
        return max;
    }
}
