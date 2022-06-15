/**
 *
 * 338. Counting Bits::::::
 * 
 * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: n = 2
 * Output: [0,1,1]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 
 * Example 2:
 * 
 * Input: n = 5
 * Output: [0,1,1,2,1,2]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 *  
 * 
 * Constraints:
 * 
 * 0 <= n <= 105
 *  
 * 
 * Follow up:
 * 
 * It is very easy to come up with a solution with a runtime of O(n log n). Can you do it in linear time O(n) and possibly in a single pass?
 * Can you do it without using any built-in function (i.e., like __builtin_popcount in C++)?
 * 
*/



import java.util.*;

class Solution {
    public int[] countBits(int n) {
        int[] res = new int[n+1];
        res[0] = 0;
        for(int i=1; i<=n; i++){
            /**
             * even number has same no. of bits as half the no.
             * in bit representation, a number "n" has an extra "0" at the end compared to "n/2"
             * e.g. 3=11, then 6=110 ;;;; 2=10, then 4=100
            */
            
            /**
             * odd no. has an extra bit compared to half the no. (even part)
             * in bit representation, a number "n" has an extra "1" at the end compared to "n/2"
             * e.g. 3=11, then 7=111 ;;;; 2=10, then 5=101
            */
            
            if(i%2 == 0){
                res[i] = res[i/2];
            }
            else {
                res[i] = res[i/2] + 1;
            }
        }
        
        return res;
    }
}
