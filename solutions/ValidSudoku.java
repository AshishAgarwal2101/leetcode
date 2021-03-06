/**
 *
 * 36. Valid Sudoku::::::::;
 * 
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 * 
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * Note:
 * 
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 *  
 * 
 * Example 1:
 * 
 * 
 * Input: board = 
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: true
 * Example 2:
 * 
 * Input: board = 
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 *  
 * 
 * Constraints:
 * 
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit 1-9 or '.'.
 * 
*/



import java.util.*;

class Solution {
    //Ques clarification: No need to add numbers to unfilled cells, only filled cells to be checked for validity
    public boolean isValidSudoku(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                char cell = board[i][j];
                if(cell != '.' && !isValid(board, i, j)){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    
    public boolean isValid(char[][] board, int r, int c){
        int squareStartR =  (r/3)*3;
        int squareStartC = (c/3)*3;
        int currCell = board[r][c];
        
        for(int i=squareStartR; i<=(squareStartR+2); i++){
            for(int j=squareStartC; j<=(squareStartC+2); j++){
                //ignore current cell
                if(i==r && j==c){
                    continue;
                }
                if(board[i][j] == currCell){
                    return false;
                }
            }
        }
        
        for(int i=0; i<9; i++){
            
            
            //checking row - varying columns
            if(board[r][i] == currCell && i!=c){
                return false;
            }
            
            //checkimng column - varying rows
            if(board[i][c] == currCell && i!=r){
                return false;
            }
        }
        
        return true;
    }
}
