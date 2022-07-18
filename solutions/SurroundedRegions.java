/**
 *
 * 130. Surrounded Regions::::::::::::::::::::
 * 
 * Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
 * 
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * 
 *  
 * 
 * Example 1:
 * 
 * 
 * Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * Explanation: Notice that an 'O' should not be flipped if:
 * - It is on the border, or
 * - It is adjacent to an 'O' that should not be flipped.
 * The bottom 'O' is on the border, so it is not flipped.
 * The other three 'O' form a surrounded region, so they are flipped.
 * Example 2:
 * 
 * Input: board = [["X"]]
 * Output: [["X"]]
 *  
 * 
 * Constraints:
 * 
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] is 'X' or 'O'.
 * 
*/



import java.util.*;

class Solution {
    /*-------------Solution 1: DFS: Check for each cell --------------
    public void solve(char[][] board) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int i=1; i<board.length-1; i++){
            for(int j=1; j<board[0].length-1; j++){
                if(board[i][j] == 'O' && canFlip(board, visited, i, j)){
                    board[i][j] = 'X';
                }
            }
        }
    }
    
    public boolean canFlip(char[][] board, boolean[][] visited, int x, int y){
        if(board[x][y] == 'X') return true;
        if(x==0 || y==0 || x==board.length-1 || y==board[0].length-1) return false;
        if(visited[x][y]) return true;
        
        visited[x][y] = true;
        boolean left = canFlip(board, visited, x, y-1);
        boolean right = canFlip(board, visited, x, y+1);
        boolean top = canFlip(board, visited, x-1, y);
        boolean bottom = canFlip(board, visited, x+1, y);
        visited[x][y] = false;
        
        return (left && right && top && bottom);
    }
    */
    
    /* ----------------Solution 2: DFS: Update all non flippable cells--------------*/
    int m;
    int n;
    public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;
        for(int i=0; i<m; i++){
            dfs(board, i, 0); //leftmost column
            dfs(board, i, n-1); //rightmost column
        }
        for(int i=0; i<n; i++){
            dfs(board, 0, i); //topmost row
            dfs(board, m-1, i); //bottommost row
        }
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(board[i][j] == 'O') board[i][j] = 'X'; //remaining 'O' can be flipped
                if(board[i][j] == '#') board[i][j] = 'O';
            }
        }
    }
    
    public void dfs(char[][] board, int x, int y){
        if(x<0 || x>=m || y<0 || y>=n || board[x][y] == 'X' || board[x][y] == '#') return;
        board[x][y] = '#';
        
        dfs(board, x, y-1);
        dfs(board, x, y+1);
        dfs(board, x-1, y);
        dfs(board, x+1, y);
    }
}
