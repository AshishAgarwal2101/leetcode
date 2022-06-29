/**
 *
 * 120. Triangle:::::::::::::::
 * 
 * Given a triangle array, return the minimum path sum from top to bottom.
 * 
 * For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
 * 
 * 
 * Example 1:
 * 
 * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * Output: 11
 * Explanation: The triangle looks like:
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
 * 
 * Example 2:
 * 
 * Input: triangle = [[-10]]
 * Output: -10
 *  
 * 
 * Constraints:
 * 
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -104 <= triangle[i][j] <= 104
 *  
 * 
 * Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?
 * 
*/



import java.util.*;

class Solution {
    /* ------Solution 1: DP: O(n^2) extra space-----------
    public int minimumTotal(List<List<Integer>> triangle) {
        int rowCount = triangle.size();
        //colCount = rowCount
        int[][] dp = new int[rowCount+1][rowCount+1]; //dp for 0 to n-1, extra last row or col for init values
        
        for(int i=rowCount-1; i>=0; i--){
            for(int j=i; j>=0; j--){ //num of columns. in a row = i+1
                dp[i][j] = Math.min(dp[i+1][j], dp[i+1][j+1]) + triangle.get(i).get(j);
            }
        }
        
        return dp[0][0];
    }
    */
    
    /*---------Solution 2: DP: O(n) extra space---------------
    */
    public int minimumTotal(List<List<Integer>> triangle) {
        int rowCount = triangle.size();
        //colCount = rowCount; dp only for columns
        int[] dp = new int[rowCount+1]; //dp for 0 to n-1, extra last col for init values
        
        for(int i=rowCount-1; i>=0; i--){
            for(int j=0; j<=i; j++){ //num of columns. in a row = i+1
                dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
            }
        }
        
        return dp[0];
    }
}
