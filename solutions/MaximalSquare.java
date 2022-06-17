/**
 *
 * 221. Maximal Square:
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 4
 * 
 * Example 2:
 * 
 * 
 * Input: matrix = [["0","1"],["1","0"]]
 * Output: 1
 * 
 * Example 3:
 * 
 * Input: matrix = [["0"]]
 * Output: 0
 *  
 * 
 * Constraints:
 * 
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] is '0' or '1'.
 * 
*/



import java.util.*;

class Solution {
    class Point {
        int x;
        int y;
        Point(){}
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    /* ---Solution 1: Recursion---------
    public int maximalSquare(char[][] matrix) {
        int maxSquareLen = 0;
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        boolean[][] visited = new boolean[numRows][numCols];
        for(int i=0; i<numRows; i++){
            for(int j=0; j<numCols; j++){
                if(matrix[i][j] == '1'){
                    Point point = getMaxPoint(matrix, new Point(i, j), numRows, numCols);
                    //System.out.println("\ni="+i+"--j="+j+"--x="+point.x+"---y="+point.y);
                    int len = Math.min((point.x-i+1), (point.y-j+1));
                    if(len > maxSquareLen){
                        maxSquareLen = len;
                    }
                }
            }
        }
        
        return maxSquareLen*maxSquareLen;
    }
    
    public Point getMaxPoint(char[][] matrix, Point point, int numRows, int numCols){
        if(point.x >= numRows || point.y >= numCols){
            return  new Point(point.x - 1, point.y - 1);
        }
        if(matrix[point.x][point.y] == '0'){
            return  new Point(point.x - 1, point.y - 1);
        }
        
        int maxRowIndex = point.x;
        while(maxRowIndex+1 < numRows && matrix[maxRowIndex+1][point.y] == '1'){
            maxRowIndex++;
        }
        
        int maxColIndex = point.y;
        while(maxColIndex+1 < numCols && matrix[point.x][maxColIndex+1] == '1'){
            maxColIndex++;
        }
        
        Point diagonalMaxPoint = getMaxPoint(matrix, new Point(point.x+1, point.y+1), numRows, numCols);
        int x = Math.min(maxRowIndex, diagonalMaxPoint.x);
        int y = Math.min(maxColIndex, diagonalMaxPoint.y);
        
        return new Point(x, y);
    }
    */
    
    /* ---------Solution 2: Dynamic programming----------
    public int maximalSquare(char[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        Point[][] maxIndices = new Point[numRows][numCols];
        Point[][] maxSquares = new Point[numRows][numCols];
        
        fillMaxIndices(matrix, maxIndices, numRows, numCols);
        fillMaxSquares(matrix, maxIndices, maxSquares, numRows, numCols);
        
        int maxLen = findMaxSquareLength(maxSquares, numRows, numCols);
        return maxLen*maxLen;
    }
    
    public void fillMaxIndices(char[][] matrix, Point[][] maxIndices, int numRows, int numCols){
        for(int i=numRows-1; i>=0; i--){
            for(int j=numCols-1; j>=0; j--){
                Point point = new Point();
                maxIndices[i][j] = point;
                if(matrix[i][j] == '0'){
                    point.x = i-1;
                    point.y = j-1;
                }
                else{
                    if(i == numRows-1){
                        point.x = numRows-1;
                    }
                    else{
                        point.x = maxIndices[i+1][j].x;
                    }
                    
                    if(j == numCols-1){
                        point.y = numCols-1;
                    }
                    else{
                        point.y = maxIndices[i][j+1].y;
                    }
                }
            }
        }
    }
    
    public void fillMaxSquares(char[][] matrix, Point[][] maxIndices, Point[][] maxSquares, int numRows, int numCols){
        for(int i=numRows-1; i>=0; i--){
            for(int j=numCols-1; j>=0; j--){
                Point point = new Point();
                maxSquares[i][j] = point;
                if(matrix[i][j] == '0'){
                    point.x = i-1;
                    point.y = j-1;
                }
                else{
                    if(i == numRows - 1 && j == numCols - 1){
                        point.x = numRows - 1;
                        point.y = numCols - 1;
                    }
                    else if(i == numRows-1){
                        point.x = numRows-1;
                        point.y = maxIndices[i][j+1].y;
                    }
                    else if(j == numCols-1){
                        point.x = maxIndices[i+1][j].x;
                        point.y = numCols-1;
                    }
                    else{
                        int x = maxIndices[i+1][j].x;
                        int diagX = maxSquares[i+1][j+1].x;
                        point.x = Math.min(x, diagX);
                        
                        int y = maxIndices[i][j+1].y;
                        int diagY = maxSquares[i+1][j+1].y;
                        point.y = Math.min(y, diagY);
                    }
                }
            }
        }
    }
    
    public int findMaxSquareLength(Point[][] maxSquares, int numRows, int numCols){
        int maxSquareLen = 0;
        for(int i=0; i<numRows; i++){
            for(int j=0; j<numCols; j++){
                Point point = maxSquares[i][j];
                int len = Math.min((point.x-i+1), (point.y-j+1));
                if(len > maxSquareLen){
                    maxSquareLen = len;
                }
            }
        }
        
        return maxSquareLen;
    }
    */
    
    //------Solution 3: Dynamic Programming - simpler approach-----------
     public int maximalSquare(char[][] matrix) {
         int numRows = matrix.length;
         int numCols = matrix[0].length;
         
         int dp[][] = new int[numRows][numCols];
         int maxLen = 0;
         
         //initializing
         for(int i=0; i<numRows; i++){
             for(int j=0; j<numCols; j++){
                 if(matrix[i][j] == '1'){
                     dp[i][j] = 1;
                     maxLen = 1;
                 }
                 else{
                     dp[i][j] = 0;
                 }
             }
         }
         
         //filling and finding max length
         for(int i=1; i<numRows; i++){
             for(int j=1; j<numCols; j++){
                 if(dp[i][j] == 1){
                     int len = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]);
                     dp[i][j] = 1 + len;
                     if(dp[i][j] > maxLen){
                         maxLen = dp[i][j];
                     }
                 }
             }
         }
         
         return maxLen*maxLen;
     }
}
