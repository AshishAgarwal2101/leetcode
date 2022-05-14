/**
 *
 * 118. Pascal's Triangle:::::::
 * 
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 * 
 * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
 * 
 * 
 *  
 * 
 * Example 1:
 * 
 * Input: numRows = 5
 * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * 
 * Example 2:
 * 
 * Input: numRows = 1
 * Output: [[1]]
 *  
 * 
 * Constraints:
 * 
 * 1 <= numRows <= 30
 * 
*/



import java.util.*;

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        for(int i=0; i<numRows; i++){
            List<Integer> row = new ArrayList<>();
            triangle.add(row);
            for(int j=0; j<=i; j++){
                int nextNum = getNum(triangle, i, j);
                row.add(nextNum);
            }
        }
        
        return triangle;
    }
    
    private int getNum(List<List<Integer>> triangle, int rowNum, int columnIndex){
        if(rowNum == 0){
            return 1;
        }
        List<Integer> prevRow = triangle.get(rowNum - 1);
        int lengthOfPrevRow = rowNum;
        
        int first = 0;
        int second = 0;
        if(columnIndex-1 >= 0){
            first = prevRow.get(columnIndex-1);
        }
        if(columnIndex < lengthOfPrevRow){
            second = prevRow.get(columnIndex);
        }
        
        return (first+second);
    }
}
