/**
 *
 * 1014. Best Sightseeing Pair::::::::::::::::::::::::::::::
 * 
 * You are given an integer array values where values[i] represents the value of the ith sightseeing spot. Two sightseeing spots i and j have a distance j - i between them.
 * 
 * The score of a pair (i < j) of sightseeing spots is values[i] + values[j] + i - j: the sum of the values of the sightseeing spots, minus the distance between them.
 * 
 * Return the maximum score of a pair of sightseeing spots.
 * 
 * Example 1:
 * 
 * Input: values = [8,1,5,2,6]
 * Output: 11
 * Explanation: i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
 * 
 * Example 2:
 * 
 * Input: values = [1,2]
 * Output: 2
 *  
 * 
 * Constraints:
 * 
 * 2 <= values.length <= 5 * 104
 * 1 <= values[i] <= 1000
 * 
*/



import java.util.*;

class Solution {
    /* --------------------Solution 1: DP---------------
    public int maxScoreSightseeingPair(int[] values) {
        int[] dp = new int[values.length]; 
        //for any 'i', contains max score pair such that i is definitely included
        //meaning, find 'j' (j<i) such that scores of 'i' and 'j' is maximum for any 'i'
        dp[0] = 0;
        dp[1] = values[0] + values[1] - 1;
        int res = dp[1];

        for(int i=2; i<dp.length; i++){
            int option1 = dp[i-1] - values[i-1] + values[i] - 1; //without last element
            int option2 = values[i-1] + values[i] - 1; //with last element
            dp[i] = Math.max(option1, option2);
            //System.out.print(dp[i] + "->");
            res = Math.max(res, dp[i]);
        }

        return res;
    }
    */

    /* -------------Solution 2: Keeping track of best index-----------*/
    public int maxScoreSightseeingPair(int[] values) {
        int prevBestIndex = 0;
        int res = Integer.MIN_VALUE;
        for(int i=1; i<values.length; i++){
            int curr = (values[prevBestIndex] + prevBestIndex) + (values[i] - i);
            res = Math.max(res, curr);
            if((values[i] + i) > (values[prevBestIndex] + prevBestIndex)){
                prevBestIndex = i;
            }
        }

        return res;
    }
}
