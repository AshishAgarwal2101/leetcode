/**
 *
 * 394. Decode String::::::::::
 * 
 * Given an encoded string, return its decoded string.
 * 
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
 * 
 * You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
 * 
 * The test cases are generated so that the length of the output will never exceed 105.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * Example 2:
 * 
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * Example 3:
 * 
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 *  
 * 
 * Constraints:
 * 
 * 1 <= s.length <= 30
 * s consists of lowercase English letters, digits, and square brackets '[]'.
 * s is guaranteed to be a valid input.
 * All the integers in s are in the range [1, 300].
 * 
*/



import java.util.*;

class Solution {
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(ch == ']'){
                decodeAndPush(stack);
            }
            else {
                stack.push(ch);
            }
        }
        
        return extractAndReverse(stack);
    }
    
    public void decodeAndPush(Stack<Character> stack){
        StringBuilder builder = new StringBuilder();
        while(stack.peek() != '['){
            Character ch = stack.pop();
            builder.append(ch);
        }
        stack.pop();
        
        int count = 0;
        int pow = 0;
        while(!stack.isEmpty() && stack.peek() >= '0' && stack.peek() <= '9'){
            int currNum = stack.pop() - '0';
            count = (((int)Math.pow(10, pow++)) * currNum) + count;
        }
        pushDecoded(stack, builder, count);
    }
    
    public void pushDecoded(Stack<Character> stack, StringBuilder builder, int count){
        for(int i=0; i<count; i++){
            for(int j=builder.length()-1; j>=0; j--){
                char ch = builder.charAt(j);
                stack.push(ch);
            }
        }
    }
    
    public String extractAndReverse(Stack<Character> stack){
        char[] res = new char[stack.size()];
        int len = res.length;
        for(int i=0; i<len; i++){
            res[len-i-1] = stack.pop();
        }
        
        return new String(res);
    }
}
