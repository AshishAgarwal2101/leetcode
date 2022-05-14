/**
 *
 * 74. Search a 2D Matrix:::::::::
 * 
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
 * 
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 *  
 * 
 * Example 1:
 * 
 * 
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 * Example 2:
 * 
 * 
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * Output: false
 *  
 * 
 * Constraints:
 * 
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 * 
*/



import java.util.*;

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int width = matrix.length; //number of rows
        if(width == 0){
            return false;
        }
        int len = matrix[0].length; //number of columns
        int size = len * width;
        
        int left = 0;
        int right = size-1;
        
        while(left <= right) {
            int mid = (left+right)/2;
            int midI = mid / len;
            int midJ = mid % len;
            
            if(target == matrix[midI][midJ]){
                return true;
            }
            if(target < matrix[midI][midJ]){
                right = mid-1;
            }
            else {
                left = mid+1;
            }
        }
        
        return false;
    }
}
