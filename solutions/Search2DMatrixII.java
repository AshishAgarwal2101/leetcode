/**
 *
 * 240. Search a 2D Matrix II::::::
 * 
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
 * 
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 *  
 * 
 * Example 1:
 * 
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 
 * Output: true
 * 
 * Example 2:
 * 
 * 
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * Output: false
 *  
 * 
 * Constraints:
 * 
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matrix[i][j] <= 109
 * All the integers in each row are sorted in ascending order.
 * All the integers in each column are sorted in ascending order.
 * -109 <= target <= 109
 * 
*/



import java.util.*;

class Solution {
    //Solution 1: Efficient - eliminating parts where we are sure target won't be present
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowCount = matrix.length;
        int colCount = matrix[0].length;
        
        return isAvailable(matrix, 0, colCount-1, 0, rowCount-1, target);
    }
    
    public boolean isAvailable(int[][] matrix, int left, int right, int top, int bottom, int target){
        if(left > right || top > bottom){
            return false;
        }
        int midRow = (top+bottom)/2;
        int midCol = (left+right)/2;
        int cell = matrix[midRow][midCol];
        if(target == cell){
            return true;
        }
        if(target < cell){
            boolean one = isAvailable(matrix, left, right, top, midRow-1, target);
            boolean two = isAvailable(matrix, left, midCol-1, midRow, bottom, target);
            
            return one || two;
        }
        
        boolean one = isAvailable(matrix, left, right, midRow+1, bottom, target);
        boolean two = isAvailable(matrix, midCol+1, right, top, midRow, target);
        
        return one || two;
    }
    
    /* ------------ Solution 2: Using 2 pointer approach-----------
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowCount = matrix.length;
        int colCount = matrix[0].length;
        
        int rowI = 0;
        int colI = matrix[0].length-1;
        while(rowI < matrix.length && colI >= 0){
            int cell = matrix[rowI][colI];
            if(target == cell){
                return true;
            }
            if(target < cell){
                colI--;
            }
            else{
                rowI++;
            }
        }
        
        return false;
    }
    */
}
