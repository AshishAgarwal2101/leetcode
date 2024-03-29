/**
 *
 * 7. Reverse Integer:::::::::::::::::::
 * 
 * Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
 * 
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 * 
 * Example 1:
 * 
 * Input: x = 123
 * Output: 321
 * 
 * Example 2:
 * 
 * Input: x = -123
 * Output: -321
 * 
 * Example 3:
 * 
 * Input: x = 120
 * Output: 21
 *  
 * 
 * Constraints:
 * 
 * -231 <= x <= 231 - 1
 * 
*/



import java.util.*;

class Solution {
    /*--------------Solution 1---------------------
    public int reverse(int x) {
        if(x == 0) return 0;
        int firstReverse = reverseSigned(x);
        int secondReverse = reverseSigned(firstReverse);
        int finalNumber = removeTrailingZeros(x);
        if(secondReverse == finalNumber) return firstReverse;
        return 0;
    }
    
    public int reverseSigned(int x){
        int res = 0;
        while(x != 0){
            int rem = x % 10;
            x = x / 10;
            res = res * 10 + rem;
        }
        return res;
    }
    
    public int removeTrailingZeros(int x){
        while(x % 10 == 0)
            x = x / 10;
        return x;
    }
    */
    
    /*--------------------Solution 2---------------*/
    public int reverse(int x) {
        if(x == 0) return 0;
        int rev = 0;
        while(x != 0){
            if(rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE/10 && x%10>=8))
                return 0;
            if(rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE/10 && x%10<=-7))
                return 0;
            int rem = x % 10;
            rev = rev * 10 + rem;
            x = x / 10;
        }
        
        return rev;
    }
}
