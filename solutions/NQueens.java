/**
 *
 * 51. N-Queens:::::
 * 
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 * 
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 * 
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
 * 
 *  
 * 
 * Example 1:
 * 
 * 
 * Input: n = 4
 * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
 * Example 2:
 * 
 * Input: n = 1
 * Output: [["Q"]]
 *  
 * 
 * Constraints:
 * 
 * 1 <= n <= 9
 * 
*/



import java.util.*;

class Solution {
    List<List<String>> res;
    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        char[][] board = new char[n][n];
        initBoard(board);
        
        nQueens(board, 0, n);
        
        return res;
    }
    
    public void nQueens(char[][] board, int row, int n){
        if(row >= n){ //Solution found. Add it to the list
            addToList(board, n);
        }
        
        //try for all columns for the given row: i=column
        for(int col=0; col<n; col++){
            if(isSafe(board, row, col)){
                board[row][col] = 'Q';
                nQueens(board, row+1, n);
                board[row][col] = '.';
            }
        }
    }
    
    public boolean isSafe(char[][] board, int row, int col){
        //not possible cases due to our algorithm: hence ignore checks::
        //1. same row --- 2. anywhere below
        
        //check top
        for(int i=0; i<row; i++){
            if(board[i][col] == 'Q'){
                return false;
            }
        }
        
        //check upper left diagonal
        for(int i=row, j=col; i>=0 && j>=0; i--, j--){
            if(board[i][j] == 'Q'){
                return false;
            }
        }
        
        //check upper right diagonal
        for(int i=row, j=col; i>=0 && j<board[0].length; i--, j++){
            if(board[i][j] == 'Q'){
                return false;
            }
        }
        
        return true;
    }
    
    public void initBoard(char[][] board){
        int rC = board.length;
        int cC = board[0].length;
        for(int i=0; i<rC; i++){
            for(int j=0; j<cC; j++){
                board[i][j] = '.';
            }
        }
    }
    
    public void addToList(char[][] board, int n){
        List<String> currSolution = new ArrayList<>();
        for(int rowNum=0; rowNum<n; rowNum++){
            String currRow = new String(board[rowNum]);
            currSolution.add(currRow);
        }
        
        res.add(currSolution);
    }
}
