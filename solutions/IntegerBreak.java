/**
 *
 * 343. Integer Break:::::::::::::
 * 
 * Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.
 * 
 * Return the maximum product you can get.
 * 
 * 
 * Example 1:
 * 
 * Input: n = 2
 * Output: 1
 * Explanation: 2 = 1 + 1, 1 × 1 = 1.
 * 
 * Example 2:
 * 
 * Input: n = 10
 * Output: 36
 * Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 *  
 * 
 * Constraints:
 * 
 * 2 <= n <= 58
 * 
*/



import java.util.*;

class Solution {
    /*--------Solution 1: DP: Knowing that max possible break int is 4-----------*/
    public int integerBreak(int n) {
        if(n == 2) return 1;
        if(n == 3) return 2;
        if(n == 4) return 4;
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 4;
        for(int i=5; i<=n; i++){
            dp[i] = 1*dp[i-1];
            dp[i] = Math.max(dp[i], 2*dp[i-2]);
            dp[i] = Math.max(dp[i], 3*dp[i-3]);
            dp[i] = Math.max(dp[i], 4*dp[i-4]);
        }
        
        return dp[n];
    }
    
    /*-------------Solution 2: Normal DP-----------------
    public int integerBreak(int n) {
        if(n == 2) return 1;
        int[] dp = new int[n+1];
        dp[1] = 1;
        for(int i=2; i<=n; i++){
            for(int j=1; j<i; j++){ //'j' becomes one integer break
                int productAfterJBreak = Math.max(dp[i-j], (i-j)) * j;
                dp[i] = Math.max(dp[i], productAfterJBreak);
            }
        }
        
        return dp[n];
    }
    */
}
